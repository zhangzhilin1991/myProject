package com.caihua.camera;

import com.ym.idcard.reg.NativeImage;

public class ImageEngine {
	public static final int IMG_COMPONENT_GRAY = 1;
	public static final int IMG_COMPONENT_RGB = 3;
	//private static final int RET_OK = 1;
	protected long mEngine = 0L;
	protected long mImage = 0L;
	protected NativeImage mImageEngine = null;

	public void finalize() {
		if ((this.mImageEngine != null) && (this.mEngine != 0L)) {
			this.mImageEngine.freeImage(this.mEngine);
			this.mImageEngine.closeEngine(this.mEngine);
			this.mEngine = 0L;
			this.mImage = 0L;
		}
	}

	public int getComponent() {
		if (this.mImageEngine != null)
			return this.mImageEngine.getImageComponent(this.mEngine);
		return 0;
	}

	public long getDataEx() {
		if (this.mImageEngine != null)
			return this.mImageEngine.getImageDataEx(this.mEngine);
		return 0L;
	}

	public int getHeight() {
		if (this.mImageEngine != null)
			return this.mImageEngine.getImageHeight(this.mEngine);
		return 0;
	}

	public int getWidth() {
		if (this.mImageEngine != null)
			return this.mImageEngine.getImageWidth(this.mEngine);
		return 0;
	}

	public boolean init(int paramInt1, int paramInt2) {
		return (this.mImageEngine != null)
				&& (this.mImageEngine.initImage(this.mEngine, paramInt1,
						paramInt2) == 1);
	}

	public boolean load(byte[] paramArrayOfByte) {
		return (this.mImageEngine != null)
				&& (this.mImageEngine.loadmemjpg(this.mEngine,
						paramArrayOfByte, paramArrayOfByte.length) == 1);
	}
}
