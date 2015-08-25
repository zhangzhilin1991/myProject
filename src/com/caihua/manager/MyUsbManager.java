package com.caihua.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.caihua.idcardreader.R.xml;
import com.caihua.utils.XmlUtils;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;

/**
 * usb管理类
 * @author zhangzhilin
 * @date 2015/8/25
 *
 */
public class MyUsbManager {
	
	public static final String ACTION_USB_PERMISSION="com.caihua.usb.permission";
	
	private UsbManager usbManager;
	private UsbDevice usbDevice;
	private Context mContext;
	
	public MyUsbManager(Context context){
		usbManager=(UsbManager) context.getSystemService(Context.USB_SERVICE);
		mContext=context;
	}
	
	/**
	 * 判断目标设备是否已连接
	 * @param intent
	 * 
	 * @return 目标设备 或者 NULL
	 */
	public UsbDevice getUsbDevice(Intent intent){
		usbDevice=intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
		if(usbDevice==null){
		HashMap<String, UsbDevice> deviceList = usbManager.getDeviceList();
			Iterator<UsbDevice> iterator=deviceList.values().iterator();
			ArrayList<Integer> venderIds=XmlUtils.getVenderId(mContext);
			while (iterator.hasNext()) {
				UsbDevice device = (UsbDevice) iterator.next();
				for(int i=0;i<venderIds.size();i++){
				if(device.getVendorId()==venderIds.get(i).intValue()){
					usbDevice=device;
					break;
				}
				}
				if(usbDevice!=null){
					
					break;
				}
			}
		}
		return usbDevice;
	}
	
	/**
	 * 判断是否已有访问usb设备权限
	 * @param device
	 * @return true false
	 */
	public boolean hasPermission(UsbDevice device){
		return usbManager.hasPermission(device);
	}
	
	
	/**
	 * 获取访问usb设备权限
	 * @param device
	 * @param pi
	 */
	public void requestPermission(UsbDevice device,PendingIntent pi){
		usbManager.requestPermission(device,pi);
	}
}
