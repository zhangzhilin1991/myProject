package com.caihua.camera;

import android.graphics.Rect;
import android.os.Handler;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public class OcrManager
{
  private String TAG = "ocr_manager";
  private ImageEngine imageEngine = new ImageEngine();
  private OcrEngine ocrEngine = new OcrEngine();
  private int rRect = 0;
  private long rTime = 0L;
  private int yuvHeight = 0;
  private int yuvWidth = 0;

  public void closeEngine()
  {
    this.imageEngine.finalize();
    this.imageEngine = null;
    this.ocrEngine.closeBCR();
  }

  public boolean initEngine(boolean paramBoolean)
  {
    return (this.ocrEngine.startBCR(UtilApp.getSdcDir() + "/ScanBcr_Mo.cfg", UtilApp.getSdcDir(), 2, paramBoolean)) && (this.imageEngine.init(1, 90));
  }

  public boolean isSetYuvWH()
  {
    return (this.yuvHeight <= 0) || (this.yuvWidth <= 0);
  }

  public IdCardInfo recognitionPhoto(byte[] paramArrayOfByte, int paramInt, Rect paramRect, Handler paramHandler, boolean paramBoolean1, boolean paramBoolean2, String paramString1, String paramString2)
  {
    if (!paramBoolean1)
    {
      long l3 = System.currentTimeMillis();
      long l4 = this.ocrEngine.yuvToRGB(paramArrayOfByte, this.yuvWidth, this.yuvHeight, 420);
      if (this.ocrEngine.loadImageMem(l4, this.yuvWidth, this.yuvHeight, 3))
      {
        this.ocrEngine.freeImgData(l4);
        this.rRect = (1 + this.rRect);
        if (!this.ocrEngine.isInRect(paramRect, paramHandler))
        {
          this.rTime += System.currentTimeMillis() - l3;
          this.ocrEngine.freeImage();
          IdCardInfo localIdCardInfo5 = new IdCardInfo();
          localIdCardInfo5.setResultType(3);
          return localIdCardInfo5;
        }
        this.rTime += System.currentTimeMillis() - l3;
        Log.d(this.TAG, "---------模糊判断开始--y---------");
        if (paramBoolean2)
        {
          if (this.ocrEngine.isBlurImage(8))
          {
            Log.d(this.TAG, "---------模糊判断失败-x----------");
            this.ocrEngine.freeImage();
            IdCardInfo localIdCardInfo4 = new IdCardInfo();
            localIdCardInfo4.setResultType(4);
            return localIdCardInfo4;
          }
          Log.d(this.TAG, "---------模糊判断通过--y---------");
        }
        paramHandler.sendEmptyMessage(4);
        this.ocrEngine.freeImage();
        IdCardInfo localIdCardInfo3 = new IdCardInfo();
        localIdCardInfo3.setResultType(6);
        return localIdCardInfo3;
      }
      this.ocrEngine.freeImgData(l4);
      this.ocrEngine.freeImage();
      IdCardInfo localIdCardInfo2 = new IdCardInfo();
      localIdCardInfo2.setResultType(3);
      this.rRect = 0;
      this.rTime = 0L;
      return localIdCardInfo2;
    }
    boolean bool1 = this.imageEngine.load(paramArrayOfByte);
    IdCardInfo localIdCardInfo1 = null;
    if (bool1)
    {
      int i = this.imageEngine.getWidth();
      int j = this.imageEngine.getHeight();
      int k = this.imageEngine.getComponent();
      long l1 = this.imageEngine.getDataEx();
      boolean bool2 = this.ocrEngine.loadImageMem(l1, i, j, k);
      localIdCardInfo1 = null;
      if (bool2)
      {
        Log.d(this.TAG, "---------模糊判断开始--2---------");
        if (this.ocrEngine.isBlurImage(4))
        {
          Log.d(this.TAG, "---------模糊判断失败---2--------");
          this.ocrEngine.freeImage();
          this.rRect = 0;
          this.rTime = 0L;
          return null;
        }
        Log.d(this.TAG, "---------模糊判断通过---2--------");
        boolean bool3 = this.ocrEngine.doImageBCR();
        localIdCardInfo1 = null;
        if (bool3)
        {
          long l2 = this.ocrEngine.dupImageOnly(new Rect(0, 0, i, j));
          localIdCardInfo1 = this.ocrEngine.getIdCardInfo(paramInt);
          localIdCardInfo1.setResultType(1);
          ArrayList localArrayList = new ArrayList();
          localArrayList.add(this.rRect);
          localArrayList.add(this.rTime);
          localIdCardInfo1.setTestRtime(localArrayList);
          this.rRect = 0;
          this.rTime = 0L;
          if ((paramString1 != null) && (paramString1.length() > 0))
          {
            localIdCardInfo1.setImg(paramString1);
            this.ocrEngine.saveImg(l2, localIdCardInfo1.getImg());
          }
          if ((paramString2 != null) && (paramString2.length() > 0))
          {
            localIdCardInfo1.setHead(paramString2);
            this.ocrEngine.saveImg(this.ocrEngine.getheadImg(l2, this.ocrEngine.getheadImgRect()), localIdCardInfo1.getHead());
          }
          this.ocrEngine.freeBFields();
        }
        this.ocrEngine.freeImage();
      }
    }
    return localIdCardInfo1;
  }

  public void setYuvWidthAndHeight(int paramInt1, int paramInt2)
  {
    this.yuvWidth = paramInt1;
    this.yuvHeight = paramInt2;
  }

  public IdCardInfo testA(byte[] paramArrayOfByte)
  {
    boolean bool1 = this.imageEngine.load(paramArrayOfByte);
    IdCardInfo localIdCardInfo = null;
    if (bool1)
    {
      int i = this.imageEngine.getWidth();
      int j = this.imageEngine.getHeight();
      int k = this.imageEngine.getComponent();
      long l = this.imageEngine.getDataEx();
      boolean bool2 = this.ocrEngine.loadImageMem(l, i, j, k);
      localIdCardInfo = null;
      if (bool2)
      {
        if (this.ocrEngine.isBlurImage(4))
        {
          this.ocrEngine.freeImage();
          return null;
        }
        boolean bool3 = this.ocrEngine.doImageBCR();
        localIdCardInfo = null;
        if (bool3)
        {
          localIdCardInfo = this.ocrEngine.getIdCardInfo(2);
          localIdCardInfo.setResultType(1);
          this.ocrEngine.freeBFields();
        }
        this.ocrEngine.freeImage();
      }
    }
    return localIdCardInfo;
	}
}

/* Location:           C:\Users\Administrator\Desktop\APK反编译工具\jd-gui-0.3.5\身份证.jar
 * Qualified Name:     com.yunmai.cc.smart.eye.controler.OcrManager
 * JD-Core Version:    0.6.2
 */