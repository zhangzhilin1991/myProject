package com.ym.idcard.reg;

import android.util.Log;

public class NativeOcr {
	private static final String LIB = "IDCardengine";
	private static final String TAG = "IDCardengine";

	static {
		try {
			System.loadLibrary(LIB);
		} catch (Exception localException) {
			Log.d(TAG, "library not found!");
		}
	}

	public native byte CheckCardEdgeLine(long pEngine, long pImage,
			int[] rect, int paramInt1, int paramInt2, int paramInt3,
			int paramInt4);

	public native long DupImage(long pImage, int[] rect);

	public native long FreeRgb(long paramLong);

	public native int GetCardType(long pEngine, long pImage);

	public native long SaveImage(long pEngine, long pImage, byte[] filePath);

	public native long SetSwitch(long paramLong, int paramInt1, int paramInt2);

	public native long YuvToRgb(byte[] yuvBuffer, int witdh, int height, int type);

	public native int closeBCR(long[] paramArrayOfLong);

	public native int doImageBCR(long pEngine, long pImage, long[] ppField);

	public native void freeBField(long pEngine, long pImage, int paramInt);

	public native int freeImage(long pEngine, long[] ppImage);

	public native int getFieldId(long pField);

	public native int getFieldText(long pField, byte[] text, int length);

	public native long getNextField(long pField);

	public native long getYData(byte[] rgb, int width, int height);

	public native long getheadImgRect(long pField, int[] rect);

	public native int imageChecking(long pEngine, long pImage, int level);

	public native long loadImageMem(long pEngine, long pBuffer,
			int width, int height, int component);

	public native int startBCR(long[] ppEngine,
			byte[] dataPath, byte[] cfgPath, int language);
}
