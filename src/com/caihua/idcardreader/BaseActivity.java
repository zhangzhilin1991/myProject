package com.caihua.idcardreader;

import com.caihua.broadCastReceiver.UsbReceiver;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import static com.caihua.usb.MyUsbManager.*;

public abstract class BaseActivity extends AppCompatActivity{
	
	
	public static final int STATE_ONLINE=0x1000;
	public static final int STATE_OFFLINE=0x1001;
	/**
	 * 根据广播判断工作状态
	 * @param mode STATE_ONLINE STATE_OFFLINE
	 */
	public abstract void setWorkMode(int mode);
	
	private UsbReceiver receiver;
	protected ActionBar actionBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		actionBar=getSupportActionBar();
		
		//setSupportActionBar(toolbar);
		
		receiver=new UsbReceiver();
		IntentFilter filter=new IntentFilter();
		filter.addAction(ACTION_USB_PERMISSION);
		
		registerReceiver(receiver, filter);
		
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(receiver);
	}
	
}
