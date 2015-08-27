package com.caihua.usb;

import com.caihua.ui.online.ReaderActivity;

import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;

public class MiniUsb {
	
	static volatile byte[] lastByte;
	static UsbDeviceConnection mConnection;
	static UsbInterface mDataInterface;
	static UsbEndpoint mReadEndpoint;
	static UsbEndpoint mWriteEndpoint;
	static volatile ReadThread reThread;
	static volatile String readGUID;
	static byte[] bs = new byte[100];
	static volatile boolean readflag = false;
	static int packcount = 0;

	public static void read(UsbInterface _mDataInterface,
			UsbDeviceConnection _mConnection) {
		try {
			packcount = 0;
			readflag = true;
			mDataInterface = _mDataInterface;
			mConnection = _mConnection;
			mReadEndpoint = mDataInterface.getEndpoint(0);
			mWriteEndpoint = mDataInterface.getEndpoint(1);
			if (reThread == null) {
				reThread = new ReadThread();
				reThread.start();
			}
			System.out.println("reThread\u4e0d\u4e3a\u7a7a");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static class ReadThread extends Thread {

		public void run() {
			System.out.println("miniusb\u8bfb\u5361\u7ebf\u7a0b\u542f\u52a8");
			if (!ReaderActivity.isrunning) {

				synchronized (this) {
					int rece = MiniUsb.mConnection.bulkTransfer(
							MiniUsb.mReadEndpoint, MiniUsb.bs, 0x100, 0x55);
					if (rece > 3) {
						if ((ReaderActivity.Byte2S(MiniUsb.bs[0]).trim()
								.equals("ff"))
								&& (ReaderActivity.Byte2S(MiniUsb.bs[1])
										.trim().equals("02"))
								&& (ReaderActivity.Byte2S(MiniUsb.bs[2])
										.trim().equals("07"))) {
							byte[] guid = new byte[8];
							System.arraycopy(MiniUsb.bs, 0xa, guid, 0x0, 0x8);
							String tt = "";
							for (int i = 0; i < 8; i++) {
								tt += ReaderActivity.Byte2S(guid[i]);
							}
							ReaderActivity.readcount = (ReaderActivity.readcount + 1);
							System.out.println(tt);
							ReaderActivity.uuid = tt.toUpperCase();
							ReaderActivity.byuuid = guid;
							ReaderActivity.DataAnalyse.setGUIDAndAlert();
							if ((tt.equals(MiniUsb.readGUID))
									&& (MiniUsb.lastByte != null)
									&& (ReaderActivity.successImg)
									|| (!ReaderActivity.showHead)) {
								System.out.println(MiniUsb.readGUID);
								try {
									ReaderActivity.readsucess = (ReaderActivity.readsucess + 1);
									ReaderActivity.DataAnalyse.alert();
									sleep(0x5dc);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
							if (!tt.equals(MiniUsb.readGUID)) {
								ReaderActivity.DataAnalyse.clearOldData();
							}
							MiniUsb.lastByte = null;
							MiniUsb.readGUID = tt;
						}
					}
					if (rece > 0) {
						MiniUsb.packcount = (MiniUsb.packcount + 1);
						if (rece <= 0x3e8) {
							UdpThread.send(MiniUsb.bs, 0, rece);
							MiniUsb.readflag = false;
							MiniUsb.reThread = null;
						}
						// Parsing error may occure here :(
					}
					// Parsing error may occure here :(
				}
			}
		}
		
		/**
		 * 结束数据通信后，关闭连接
		 * @param mDataInterface
		 * @param mConnection
		 */
		public static void disconnect(UsbInterface mDataInterface,
				UsbDeviceConnection mConnection){
			
			mConnection.releaseInterface(mDataInterface);
			mConnection.close();
			
		}

		public static void ReadData() {
			int rece = mConnection.bulkTransfer(mReadEndpoint, bs, 0x100, 0x55);
			if (rece == -1) {
				
			}
		}

		public static void WriteData(byte[] bs, int len) {
			int result = mConnection
					.bulkTransfer(mWriteEndpoint, bs, len, 0x32);
		}
	}
}
