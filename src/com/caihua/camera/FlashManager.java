/*
 * Copyright (C) 2012 ZXing authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.caihua.camera;

import com.caihua.idcardreader.R;
import com.caihua.utils.MyLog;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.preference.PreferenceManager;
import android.text.GetChars;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

/**
 * Detects ambient light and switches on the front light when very dark, and off
 * again when sufficiently light.
 * 
 * @author zhangzhilin
 * 
 */
public class FlashManager implements SensorEventListener {
	
	private static final String TAG="FlashManager";

	private static final float TOO_DARK_LUX = 45.0f;
	private static final float BRIGHT_ENOUGH_LUX = 100.0f;

	private final Context context;
	private CameraManager cameraManager;
	private Sensor lightSensor;
	private ImageView lightView;
	private SharedPreferences sharedPrefs;

	public FlashManager(Context context) {
		this.context = context;
//		this.lightView = imageView;
//		this.cameraManager = cameraManager;
//		if (lightView != null) {
//			lightView.setOnClickListener(new lightModeListener());
//		}
		sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
	}

	public void start(CameraManager cameraManager,ImageView lightView) {
		this.cameraManager = cameraManager;
		this.lightView = lightView;
		if (lightView != null) {
			if(FrontLightMode.readPref(sharedPrefs)==FrontLightMode.ON){
				lightView.setBackgroundResource(R.drawable.flash_on);
				lightView.setTag(R.drawable.flash_on);
			}else if(FrontLightMode.readPref(sharedPrefs)==FrontLightMode.AUTO){
				lightView.setBackgroundResource(R.drawable.flash_auto);
				lightView.setTag(R.drawable.flash_auto);
			}else{
				lightView.setBackgroundResource(R.drawable.flash_off);
				lightView.setTag(R.drawable.flash_off);
			}
			start();
			lightView.setOnClickListener(new lightModeListener());
		}
	}

	/**
	 * 开启闪光灯
	 * 
	 * @param cameraManager
	 */
	void start() {

		if (FrontLightMode.readPref(sharedPrefs) == FrontLightMode.AUTO) {
			SensorManager sensorManager = (SensorManager) context
					.getSystemService(Context.SENSOR_SERVICE);
			lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
			if (lightSensor != null) {
				sensorManager.registerListener(this, lightSensor,
						SensorManager.SENSOR_DELAY_NORMAL);
			}
		} else if (FrontLightMode.readPref(sharedPrefs) == FrontLightMode.ON) {
			cameraManager.setTorch(true);
			MyLog.d(TAG,"open flash light");
		}
	}

	/**
	 * 关闭闪光灯
	 */
	public void stop() {
		if (lightSensor != null) {
			SensorManager sensorManager = (SensorManager) context
					.getSystemService(Context.SENSOR_SERVICE);
			sensorManager.unregisterListener(this);
			//cameraManager = null;
			lightSensor = null;
		} 
		cameraManager.setTorch(false);
		MyLog.d(TAG,"close flash light");
	}

	@Override
	public void onSensorChanged(SensorEvent sensorEvent) {
		float ambientLightLux = sensorEvent.values[0];
		if (cameraManager != null) {
			if (ambientLightLux <= TOO_DARK_LUX) {
				cameraManager.setTorch(true);
			} else if(ambientLightLux >=BRIGHT_ENOUGH_LUX){
				cameraManager.setTorch(false);
			}
		}
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// do nothing
	}

	private class lightModeListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			// 先停止，在根据新状态打开？？
			switch ((Integer) v.getTag()) {
			case R.drawable.flash_auto:
				stop();
				v.setBackgroundResource(R.drawable.flash_on);
				v.setTag(R.drawable.flash_on);
				FrontLightMode.setPref(sharedPrefs, FrontLightMode.ON);
				start();
				break;
			case R.drawable.flash_on:
				v.setBackgroundResource(R.drawable.flash_off);
				v.setTag(R.drawable.flash_off);
				FrontLightMode.setPref(sharedPrefs, FrontLightMode.OFF);
				stop();
				break;
			case R.drawable.flash_off:
				v.setBackgroundResource(R.drawable.flash_auto);
				v.setTag(R.drawable.flash_auto);
				FrontLightMode.setPref(sharedPrefs, FrontLightMode.AUTO);
				start();
				break;
			}
		}

	}
}
