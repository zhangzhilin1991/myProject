package com.ym.idcard.reg;

import android.util.Log;

public class NativeImage {
	private static final String LIB = "imageengine";
	private static final String TAG = "NativeImage";

	static {
		try {
			System.loadLibrary(LIB);
		} catch (Exception localException) {
			Log.d(TAG, "library not found!");
		}
	}

	public native int closeEngine(long engine);

	public native long createEngine();

	public native int freeImage(long engine);

	public native int getImageComponent(long engine);

	public native long getImageDataEx(long engine);

	public native int getImageHeight(long engine);

	public native int getImageWidth(long engine);

	public native int initImage(long engine, int paramInt1, int paramInt2);

	public native int loadmemjpg(long engine, byte[] data, int length);

	public void finalize() {
	}
}
