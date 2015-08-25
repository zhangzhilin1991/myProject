package com.caihua.broadCastReceiver;

import com.caihua.idcardreader.BaseActivity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbManager;
import static com.caihua.manager.MyUsbManager.*;
import static com.caihua.idcardreader.BaseActivity.*;

public class UsbReceiver extends BroadcastReceiver{
	
	
	//private Context mContext;
	
//	public UsbReceiver(Context context){
//		mContext=context;
//	}

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		String action=intent.getAction();
		if(action.equals(ACTION_USB_PERMISSION)){
			synchronized(this){
				
				if(intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED,false)){
					//获得权限后的处理
					
					((BaseActivity)context).setWorkMode(STATE_ONLINE);
					
				}else{
					//未获得权限，处理
					((BaseActivity)context).setWorkMode(STATE_OFFLINE);
					
				}
			}
		}
	}
}
