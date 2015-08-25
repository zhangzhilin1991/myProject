package com.caihua.ui.online;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.os.Handler;
import java.util.List;

import com.caihua.idcardreader.BaseActivity;
import com.caihua.idcardreader.R;
import com.caihua.manager.MyUsbManager;
import static  com.caihua.manager.MyUsbManager.*;

public class WelComeActivity extends BaseActivity {
	
	private UsbDevice usbDevice=null;
	private MyUsbManager usbManager;
	
	@Override
	protected void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		setContentView(R.layout.welcome);
		ActivityList.activities.add(this);
		usbManager=new MyUsbManager(this);
		usbDevice=usbManager.getUsbDevice(getIntent());
		if(usbDevice==null){
			//跳转到离线状态
			StartOffline();
		}else{
			//跳转到在线状态
			if(usbManager.hasPermission(usbDevice)){
				//有权限直接跳转
				StartOnline();
				
			}else{
				//请求权限，根据广播结果来判断，在线离线状态
				//枚举得到usb设备的, 连接之前需要请求权限
				usbManager.requestPermission(usbDevice,PendingIntent
						.getBroadcast(WelComeActivity.this,0,new Intent(ACTION_USB_PERMISSION),0));
			}
		}
	}
	
	/**
	 * 跳转到在线状态
	 */
	private void StartOnline(){
		new Handler().postDelayed(new Runnable() {
			public void run() {
				Intent localIntent = new Intent(WelComeActivity.this,
						ReaderActivity.class);
				WelComeActivity.this.startActivity(localIntent);
				WelComeActivity.this.finish();
				
			}
		}, 2000L);
	}
	
	/**
	 * 跳转到离线状态
	 */
	private void StartOffline(){
		
	}

	@Override
	public void setWorkMode(int mode) {
		// TODO Auto-generated method stub
		if(mode==STATE_ONLINE){
		StartOnline();	
		}else{
			StartOffline();
		}
	}
	
	
}