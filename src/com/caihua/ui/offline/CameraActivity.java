//package com.caihua.ui.offline;
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
//import android.content.res.Resources;
//import android.graphics.Bitmap;
//import android.graphics.Rect;
//import android.graphics.drawable.BitmapDrawable;
//import android.os.Build;
//import android.os.Bundle;
//import android.os.Environment;
//import android.os.Handler;
//import android.os.Message;
//import android.util.Log;
//import android.view.Display;
//import android.view.SurfaceHolder;
//import android.view.SurfaceHolder.Callback;
//import android.view.SurfaceView;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.ViewGroup.LayoutParams;
//import android.view.Window;
//import android.view.WindowManager;
//import android.widget.ImageView;
//import android.widget.ImageView.ScaleType;
//import android.widget.LinearLayout.LayoutParams;
//import android.widget.ProgressBar;
//import android.widget.RelativeLayout;
//import android.widget.RelativeLayout.LayoutParams;
//import android.widget.TextView;
//import android.widget.Toast;
//import com.yunmai.cc.smart.eye.controler.CameraManager;
//import com.yunmai.cc.smart.eye.controler.OcrManager;
//import com.yunmai.cc.smart.eye.controler.RecognitionThread;
//import com.yunmai.cc.smart.eye.util.UtilApp;
//import com.yunmai.cc.smart.eye.view.RotateImageView;
//import com.yunmai.cc.smart.eye.view.ViewfinderView;
//import com.yunmai.cc.smart.eye.vo.IdCardInfo;
//import java.io.File;
//
//public class CameraActivity extends Activity
//  implements SurfaceHolder.Callback
//{
//  private final String TAG = "cc_smart";
//  private boolean autoFoucs = false;
//  private int btWidth;
//  private RotateImageView btnCancel;
//  private RotateImageView btnFlash;
//  private RelativeLayout.LayoutParams btn_CancelParams;
//  private RelativeLayout.LayoutParams btn_FlashParams;
//  private boolean cameraError = false;
//  private CameraManager cameraManager;
//  private int color = -1;
//  private Display display;
//  private ViewfinderView finderView;
//  private int goonTime = 100;
//  private boolean isAutoIn = false;
//  private boolean isBlurOn = true;
//  private boolean isFlashOn = false;
//  private boolean isManaulIn = false;
//  private ImageView iv;
//  private long lastClickTime;
//  private Thread mCameraOpenThread = new Thread(new Runnable()
//  {
//    public void run()
//    {
//      try
//      {
//        CameraActivity.this.cameraManager.openCamera();
//        return;
//      }
//      catch (Exception localException)
//      {
//        localException.printStackTrace();
//        CameraActivity.this.cameraError = true;
//      }
//    }
//  });
//  private Handler mHandler = new Handler()
//  {
//    public void handleMessage(Message paramAnonymousMessage)
//    {
//      super.handleMessage(paramAnonymousMessage);
//      switch (paramAnonymousMessage.what)
//      {
//      default:
//        CameraActivity.this.tipView.setText("请将身份证卡片正面置于此区域尝试对齐边缘");
//        CameraActivity.this.progressBar.setVisibility(8);
//        CameraActivity.this.isManaulIn = false;
//        CameraActivity.this.isAutoIn = false;
//        CameraActivity.this.cameraManager.initDisplay();
//        Toast.makeText(CameraActivity.this.getBaseContext(), "-->" + paramAnonymousMessage.what, 0).show();
//        CameraActivity.this.mHandler.sendEmptyMessageDelayed(5, CameraActivity.this.startTime);
//        CameraActivity.this.autoFoucs = true;
//        Toast.makeText(CameraActivity.this.getBaseContext(), "图像质量不好，请重新拍摄！", 0).show();
//      case 10:
//      case 14:
//      case 2:
//      case 3:
//      case 5:
//      case 6:
//      case 7:
//      case 8:
//      case 4:
//      case 1002:
//      case 13:
//        do
//        {
//          do
//          {
//            do
//            {
//              do
//              {
//                do
//                {
//                  do
//                  {
//                    do
//                    {
//                      do
//                      {
//                        do
//                        {
//                          do
//                          {
//                            do
//                            {
//                              do
//                                return;
//                              while (CameraActivity.this.isManaulIn);
//                              if (CameraActivity.this.ocrManager == null)
//                              {
//                                CameraActivity.this.ocrManager = new OcrManager();
//                                CameraActivity.this.rect = CameraActivity.this.cameraManager.getViewfinder(CameraActivity.this.finderView.getFinder());
//                                CameraActivity.this.rect_pthoto = CameraActivity.this.cameraManager.getRectByPhoto(CameraActivity.this.finderView.getFinder());
//                                long l3 = System.currentTimeMillis();
//                                if (!CameraActivity.this.ocrManager.initEngine(CameraActivity.this.recognAll))
//                                {
//                                  Toast.makeText(CameraActivity.this.getBaseContext(), "引擎初始化失败！！！", 0).show();
//                                  CameraActivity.this.ocrManager.closeEngine();
//                                  CameraActivity.this.ocrManager = null;
//                                  CameraActivity.this.finish();
//                                  return;
//                                }
//                                CameraActivity.this.startBCRTime = (System.currentTimeMillis() - l3);
//                              }
//                              if (CameraActivity.this.ocrManager.isSetYuvWH())
//                                CameraActivity.this.ocrManager.setYuvWidthAndHeight(CameraActivity.this.cameraManager.getPreviewWidth(), CameraActivity.this.cameraManager.getPreviewHeight());
//                              byte[] arrayOfByte2 = (byte[])paramAnonymousMessage.obj;
//                              if ((arrayOfByte2 != null) && (arrayOfByte2.length > 0))
//                              {
//                                new RecognitionThread(CameraActivity.this.mHandler, arrayOfByte2, CameraActivity.this.ocrManager, CameraActivity.this.rect, false, CameraActivity.this.isBlurOn, UtilApp.newHeadPath(), UtilApp.newHeadPath()).start();
//                                return;
//                              }
//                              CameraActivity.this.isManaulIn = false;
//                              CameraActivity.this.isAutoIn = false;
//                              Log.d("cc_smart", "----isAutoIn-1-->>>" + CameraActivity.this.isAutoIn);
//                              CameraActivity.this.finderView.setLineRect(0);
//                              Toast.makeText(CameraActivity.this.getBaseContext(), "相机出现问题，请重启手机！", 0).show();
//                              CameraActivity.this.progressBar.setVisibility(8);
//                              CameraActivity.this.mHandler.sendEmptyMessageDelayed(5, CameraActivity.this.rightNow);
//                              return;
//                              IdCardInfo localIdCardInfo = (IdCardInfo)paramAnonymousMessage.obj;
//                              CameraActivity.this.cameraManager.initDisplay();
//                              switch (localIdCardInfo.getResultType())
//                              {
//                              default:
//                                return;
//                              case 1:
//                                long l2 = System.currentTimeMillis() - CameraActivity.this.startRecogTime + CameraActivity.this.startBCRTime;
//                                CameraActivity.this.mHandler.removeMessages(5);
//                                Intent localIntent = new Intent();
//                                localIntent.putExtra("id_card_info", localIdCardInfo);
//                                localIntent.putExtra("testkey_1", l2);
//                                CameraActivity.this.setResult(200, localIntent);
//                                if (CameraActivity.this.ocrManager != null)
//                                {
//                                  Log.d("ocr_manager", "---------closeEngine------1------->>");
//                                  CameraActivity.this.ocrManager.closeEngine();
//                                  Log.d("ocr_manager", "---------closeEngine------4------->>");
//                                }
//                                CameraActivity.this.finish();
//                                return;
//                              case 4:
//                              case 2:
//                              case 3:
//                              case 5:
//                              }
//                            }
//                            while (CameraActivity.this.isManaulIn);
//                            CameraActivity.this.progressBar.setVisibility(8);
//                            CameraActivity.this.autoFoucs = true;
//                            CameraActivity.this.mHandler.sendEmptyMessageDelayed(5, CameraActivity.this.rightNow);
//                            return;
//                          }
//                          while (CameraActivity.this.isManaulIn);
//                          CameraActivity.this.mHandler.sendEmptyMessageDelayed(5, CameraActivity.this.rateTime);
//                          CameraActivity.this.tipView.setText("不是身份证！！");
//                          CameraActivity.this.progressBar.setVisibility(8);
//                          CameraActivity.this.iv.setVisibility(8);
//                          CameraActivity.this.progressBar.setVisibility(8);
//                          CameraActivity.this.finderView.setLineRect(0);
//                          return;
//                        }
//                        while (CameraActivity.this.isManaulIn);
//                        CameraActivity.this.progressBar.setVisibility(8);
//                        CameraActivity.this.mHandler.sendEmptyMessageDelayed(5, CameraActivity.this.rightNow);
//                        CameraActivity.this.tipView.setText("请将身份证卡片正面置于此区域尝试对齐边缘");
//                        return;
//                        CameraActivity.this.isAutoIn = false;
//                        Log.d("cc_smart", "----isAutoIn-2-->>>" + CameraActivity.this.isAutoIn);
//                        CameraActivity.this.isManaulIn = false;
//                        CameraActivity.this.tipView.setText("请将身份证卡片正面置于此区域尝试对齐边缘");
//                        CameraActivity.this.progressBar.setVisibility(8);
//                        CameraActivity.this.mHandler.sendEmptyMessageDelayed(5, CameraActivity.this.goonTime);
//                        CameraActivity.this.iv.setVisibility(8);
//                        CameraActivity.this.progressBar.setVisibility(8);
//                        return;
//                      }
//                      while (CameraActivity.this.isManaulIn);
//                      CameraActivity.this.progressBar.setVisibility(8);
//                      CameraActivity.this.tipView.setText("请将身份证卡片正面置于此区域尝试对齐边缘");
//                      CameraActivity.this.mHandler.sendEmptyMessageDelayed(5, CameraActivity.this.goonTime);
//                      CameraActivity.this.cameraManager.initDisplay();
//                      return;
//                    }
//                    while ((CameraActivity.this.isManaulIn) || (!CameraActivity.this.preview));
//                    if (CameraActivity.this.autoFoucs)
//                    {
//                      CameraActivity.this.cameraManager.autoFoucs();
//                      CameraActivity.this.autoFoucs = false;
//                      CameraActivity.this.mHandler.sendEmptyMessageDelayed(5, 1200L);
//                      return;
//                    }
//                    CameraActivity.this.cameraManager.autoFocusAndPreviewCallback();
//                    return;
//                  }
//                  while (CameraActivity.this.isManaulIn);
//                  CameraActivity.this.cameraManager.autoFoucs();
//                  return;
//                }
//                while (CameraActivity.this.isManaulIn);
//                int i = ((Byte)paramAnonymousMessage.obj).byteValue();
//                CameraActivity.this.finderView.setLineRect(i);
//                return;
//              }
//              while (CameraActivity.this.isManaulIn);
//              CameraActivity.this.tipView.setText("识别中 ..");
//              return;
//            }
//            while (CameraActivity.this.isManaulIn);
//            if (CameraActivity.this.isDoubleClick(2000L))
//            {
//              Log.d("cc_smart", "---auto-isDoubleClick-2-->>>");
//              return;
//            }
//            CameraActivity.this.isAutoIn = true;
//            Log.d("cc_smart", "---auto-CAMERA_TAKE_PHOTO--->>>");
//            CameraActivity.this.tipView.setText("拍照中 ..");
//            CameraActivity.this.iv.setVisibility(8);
//            CameraActivity.this.progressBar.setVisibility(8);
//            CameraActivity.this.cameraManager.takePicture();
//            return;
//          }
//          while (CameraActivity.this.isAutoIn);
//          if (CameraActivity.this.isDoubleClick(2000L))
//          {
//            Log.d("cc_smart", "---manaul-isDoubleClick-1-->>>");
//            return;
//          }
//          Log.d("cc_smart", "---manaul-CAMERA_TAKE_PHOTO--->>>");
//          CameraActivity.this.tipView.setText("拍照中 ..");
//          CameraActivity.this.iv.setVisibility(8);
//          CameraActivity.this.progressBar.setVisibility(8);
//          CameraActivity.this.cameraManager.takePicture();
//          return;
//        }
//        while ((CameraActivity.this.isManaulIn) || (CameraActivity.this.isAutoIn));
//        CameraActivity.this.isManaulIn = true;
//        Log.d("cc_smart", "----CAMERA_TAKE_PHOTO_3--->>>");
//        CameraActivity.this.mHandler.removeMessages(2);
//        CameraActivity.this.mHandler.removeMessages(3);
//        CameraActivity.this.mHandler.removeMessages(4);
//        CameraActivity.this.mHandler.removeMessages(5);
//        CameraActivity.this.mHandler.removeMessages(6);
//        CameraActivity.this.mHandler.removeMessages(8);
//        CameraActivity.this.mHandler.removeMessages(9);
//        CameraActivity.this.mHandler.removeMessages(10);
//        CameraActivity.this.mHandler.removeMessages(11);
//        CameraActivity.this.mHandler.removeMessages(1002);
//        CameraActivity.this.mHandler.removeMessages(100);
//        if (CameraActivity.this.ocrManager == null)
//        {
//          CameraActivity.this.ocrManager = new OcrManager();
//          CameraActivity.this.rect = CameraActivity.this.cameraManager.getViewfinder(CameraActivity.this.finderView.getFinder());
//          CameraActivity.this.rect_pthoto = CameraActivity.this.cameraManager.getRectByPhoto(CameraActivity.this.finderView.getFinder());
//          long l1 = System.currentTimeMillis();
//          if (!CameraActivity.this.ocrManager.initEngine(CameraActivity.this.recognAll))
//          {
//            Toast.makeText(CameraActivity.this.getBaseContext(), "引擎初始化失败！！restart ..", 0).show();
//            CameraActivity.this.ocrManager.closeEngine();
//            CameraActivity.this.ocrManager = null;
//            CameraActivity.this.finish();
//            return;
//          }
//          CameraActivity.this.startBCRTime = (System.currentTimeMillis() - l1);
//        }
//        CameraActivity.this.cameraManager.autoFoucsAndTakePicture();
//        return;
//      case 9:
//        Log.d("cc_smart", "----CAMERA_TAKE_PHOTO_ERROR--->>>");
//        CameraActivity.this.finderView.setLineRect(0);
//        CameraActivity.this.isManaulIn = false;
//        CameraActivity.this.isAutoIn = false;
//        Log.d("cc_smart", "----isAutoIn-3-->>>" + CameraActivity.this.isAutoIn);
//        CameraActivity.this.tipView.setText("请将身份证卡片正面置于此区域尝试对齐边缘");
//        CameraActivity.this.progressBar.setVisibility(8);
//        CameraActivity.this.cameraManager.initDisplay();
//        Toast.makeText(CameraActivity.this.getBaseContext(), "图像质量不好，请重新拍摄！", 0).show();
//        CameraActivity.this.autoFoucs = true;
//        CameraActivity.this.mHandler.sendEmptyMessageDelayed(5, CameraActivity.this.rightNow);
//        return;
//      case 11:
//      }
//      CameraActivity.this.tipView.setText("识别中 ..");
//      CameraActivity.this.progressBar.setVisibility(0);
//      byte[] arrayOfByte1 = (byte[])paramAnonymousMessage.obj;
//      if ((arrayOfByte1 == null) || (arrayOfByte1.length <= 0))
//      {
//        CameraActivity.this.isManaulIn = false;
//        CameraActivity.this.isAutoIn = false;
//        Toast.makeText(CameraActivity.this.getBaseContext(), "相机出现问题，请重启手机！！", 0).show();
//        CameraActivity.this.tipView.setText("请将身份证卡片正面置于此区域尝试对齐边缘");
//        CameraActivity.this.progressBar.setVisibility(8);
//        CameraActivity.this.cameraManager.initDisplay();
//        CameraActivity.this.mHandler.sendEmptyMessageDelayed(5, CameraActivity.this.rightNow);
//        return;
//      }
//      CameraActivity.this.startRecogTime = System.currentTimeMillis();
//      new RecognitionThread(CameraActivity.this.mHandler, arrayOfByte1, CameraActivity.this.ocrManager, CameraActivity.this.rect_pthoto, true, CameraActivity.this.isBlurOn, UtilApp.newHeadPath(), UtilApp.newHeadPath()).start();
//    }
//  };
//  private WindowManager manager;
//  private int nDisplayHeight;
//  private int nDisplayWidth;
//  private OcrManager ocrManager;
//  private boolean preview = true;
//  private ProgressBar progressBar;
//  private int rateTime = 100;
//  private boolean recognAll = true;
//  private Rect rect;
//  private Rect rect_pthoto;
//  private int rightNow = 100;
//  long startBCRTime;
//  private long startRecogTime;
//  private int startTime = 800;
//  private SurfaceHolder surfaceHolder;
//  private SurfaceView sv_preview;
//  private int textSize = 20;
//  private int textWidth;
//  private String tip = "";
//  private TextView tipView;
//  private RelativeLayout.LayoutParams tip_Params;
//
//  private View.OnClickListener initClickListener()
//  {
//    return new View.OnClickListener()
//    {
//      public void onClick(View paramAnonymousView)
//      {
//        if (paramAnonymousView.getTag().equals("falsh"))
//          if (CameraActivity.this.isFlashOn)
//            if (CameraActivity.this.cameraManager.closeFlashlight())
//            {
//              CameraActivity.this.btnFlash.setImageDrawable(CameraActivity.this.getResources().getDrawable(2130837506));
//              CameraActivity.this.isFlashOn = false;
//            }
//        while (!paramAnonymousView.getTag().equals("cancel"))
//        {
//          do
//            return;
//          while (!CameraActivity.this.cameraManager.openFlashlight());
//          CameraActivity.this.btnFlash.setImageDrawable(CameraActivity.this.getResources().getDrawable(2130837505));
//          CameraActivity.this.isFlashOn = true;
//          return;
//        }
//        CameraActivity.this.setResult(998);
//        CameraActivity.this.finish();
//      }
//    };
//  }
//
//  private View initView()
//  {
//    RelativeLayout localRelativeLayout = new RelativeLayout(getBaseContext());
//    localRelativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
//    this.sv_preview = new SurfaceView(getBaseContext());
//    RelativeLayout.LayoutParams localLayoutParams1 = new RelativeLayout.LayoutParams(-2, -2);
//    localLayoutParams1.addRule(13);
//    this.sv_preview.setLayoutParams(localLayoutParams1);
//    localRelativeLayout.addView(this.sv_preview);
//    this.iv = new ImageView(getBaseContext());
//    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-1, -1);
//    this.iv.setLayoutParams(localLayoutParams);
//    this.iv.setScaleType(ImageView.ScaleType.FIT_XY);
//    this.iv.setVisibility(8);
//    localRelativeLayout.addView(this.iv);
//    this.finderView = new ViewfinderView(getBaseContext());
//    localRelativeLayout.addView(this.finderView);
//    this.btWidth = ((BitmapDrawable)getResources().getDrawable(2130837506)).getBitmap().getWidth();
//    this.btnFlash = new RotateImageView(getBaseContext());
//    this.btnCancel = new RotateImageView(getBaseContext());
//    this.btn_FlashParams = new RelativeLayout.LayoutParams(this.btWidth, this.btWidth);
//    this.btn_CancelParams = new RelativeLayout.LayoutParams(this.btWidth, this.btWidth);
//    this.btn_CancelParams.addRule(11);
//    this.btnFlash.setImageDrawable(getResources().getDrawable(2130837506));
//    this.btnCancel.setImageDrawable(getResources().getDrawable(2130837504));
//    this.btnFlash.setLayoutParams(this.btn_FlashParams);
//    this.btnCancel.setLayoutParams(this.btn_CancelParams);
//    this.btnFlash.setTag("falsh");
//    this.btnCancel.setTag("cancel");
//    this.btnFlash.setOnClickListener(initClickListener());
//    this.btnCancel.setOnClickListener(initClickListener());
//    localRelativeLayout.addView(this.btnFlash);
//    localRelativeLayout.addView(this.btnCancel);
//    this.progressBar = new ProgressBar(getBaseContext());
//    RelativeLayout.LayoutParams localLayoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
//    localLayoutParams2.addRule(13);
//    this.progressBar.setLayoutParams(localLayoutParams2);
//    this.progressBar.setScrollBarStyle(134217728);
//    this.progressBar.setVisibility(8);
//    localRelativeLayout.addView(this.progressBar);
//    this.tipView = new TextView(getBaseContext());
//    this.tip_Params = new RelativeLayout.LayoutParams(this.textWidth, -2);
//    this.tip_Params.addRule(13);
//    this.tipView.setGravity(17);
//    this.tipView.setLayoutParams(this.tip_Params);
//    TextView localTextView = this.tipView;
//    if (this.tip.equalsIgnoreCase(""));
//    for (String str = "请将身份证卡片正面置于此区域尝试对齐边缘"; ; str = this.tip)
//    {
//      localTextView.setText(str);
//      this.tipView.setTextSize(this.textSize);
//      this.tipView.setTextColor(this.color);
//      localRelativeLayout.addView(this.tipView);
//      return localRelativeLayout;
//    }
//  }
//
//  private void setParameters()
//  {
//    this.cameraManager.setCameraFlashModel("off");
//    this.isBlurOn = this.cameraManager.isSupportAutoFocus();
//    this.cameraManager.setPreviewSize();
//    int i = this.cameraManager.getPreviewWidth();
//    int j = this.cameraManager.getPreviewHeight();
//    Display localDisplay = ((WindowManager)getSystemService("window")).getDefaultDisplay();
//    int k = localDisplay.getWidth();
//    int m = localDisplay.getHeight();
//    if ((Build.MANUFACTURER.equals("Lenovo")) && (Build.MODEL.equals("IdeaTabS2110AH")))
//      m = 800;
//    Log.d("cc_smart", k + "<--------W----WindowManager-----H------->" + m);
//    int n = i;
//    int i1 = j;
//    float f = 100.0F;
//    int i2 = i;
//    int i3 = j;
//    if ((k > i) && (m > j))
//      if ((k <= i2) || (m <= i3))
//        Log.d("cc_smart", "<------11--wWidth > pWidth && wHeight > pHeight------>");
//    while (true)
//    {
//      Log.d("cc_smart", n + "<--------W----setParameters-----H------->" + i1);
//      ViewGroup.LayoutParams localLayoutParams = this.sv_preview.getLayoutParams();
//      localLayoutParams.width = n;
//      localLayoutParams.height = i1;
//      this.sv_preview.getHolder().setFixedSize(n, i1);
//      this.sv_preview.setLayoutParams(localLayoutParams);
//      this.surfaceHolder = this.sv_preview.getHolder();
//      this.surfaceHolder.addCallback(this);
//      this.surfaceHolder.setType(3);
//      this.finderView.initFinder(n, i1, this.mHandler);
//      return;
//      f += 1.0F;
//      Log.d("cc_smart", "---xx----->" + f / 100.0D);
//      i2 = (int)(f * i / 100.0D);
//      i3 = (int)(f * j / 100.0D);
//      if ((k <= i2) || (m <= i3))
//        break;
//      n = i2;
//      i1 = i3;
//      break;
//      do
//      {
//        f -= 1.0F;
//        Log.d("cc_smart", "---xx----->" + f / 100.0D);
//        n = (int)(f * i / 100.0D);
//        i1 = (int)(f * j / 100.0D);
//      }
//      while ((n > k) || (i1 > m));
//      Log.d("cc_smart", "<-----22---tempWidth > wWidth || tempHeidht > wHeight------>");
//    }
//  }
//
//  public boolean isDoubleClick(long paramLong)
//  {
//    long l = System.currentTimeMillis();
//    if (l - this.lastClickTime < paramLong)
//      return true;
//    this.lastClickTime = l;
//    return false;
//  }
//
//  protected void onCreate(Bundle paramBundle)
//  {
//    super.onCreate(paramBundle);
//    getWindow().setFlags(128, 128);
//    if (!Environment.getExternalStorageState().equals("mounted"))
//    {
//      Toast.makeText(getBaseContext(), "请插入存储卡", 1).show();
//      finish();
//    }
//    File localFile = new File(UtilApp.completeManagePath());
//    if (!localFile.exists())
//      localFile.mkdirs();
//    this.manager = ((WindowManager)getBaseContext().getSystemService("window"));
//    this.display = this.manager.getDefaultDisplay();
//    this.nDisplayWidth = this.display.getWidth();
//    this.nDisplayHeight = this.display.getHeight();
//    if (this.nDisplayWidth < this.nDisplayHeight)
//    {
//      int i = this.nDisplayWidth;
//      this.nDisplayWidth = this.nDisplayHeight;
//      this.nDisplayHeight = i;
//    }
//    Log.e("QINBO", "DISPLAY_w:" + this.nDisplayWidth + "  DISPLAY_y:" + this.nDisplayHeight);
//    this.textWidth = (2 * this.nDisplayHeight / 3);
//    Bundle localBundle = getIntent().getExtras();
//    if (localBundle != null)
//    {
//      this.tip = localBundle.getString("tip");
//      if (this.tip == null)
//        this.tip = "";
//      this.textSize = localBundle.getInt("size");
//      this.color = localBundle.getInt("color");
//      this.textWidth = localBundle.getInt("width");
//      if (this.textSize <= 0)
//        this.textSize = 20;
//      if (this.textWidth <= 0)
//        this.textWidth = (2 * this.nDisplayHeight / 3);
//      if (this.color == 0)
//        this.color = -1;
//    }
//    setContentView(initView());
//    this.recognAll = getIntent().getBooleanExtra("ISRECOGNALL", true);
//    this.cameraManager = new CameraManager(getBaseContext(), this.mHandler);
//    this.mCameraOpenThread.start();
//    try
//    {
//      this.mCameraOpenThread.join();
//      this.mCameraOpenThread = null;
//      if (this.cameraError)
//      {
//        Toast.makeText(getBaseContext(), "照相机未启动！请开机重启", 0).show();
//        finish();
//      }
//      setParameters();
//      return;
//    }
//    catch (Exception localException)
//    {
//      while (true)
//      {
//        localException.printStackTrace();
//        this.cameraError = true;
//      }
//    }
//  }
//
//  protected void onDestroy()
//  {
//    super.onDestroy();
//    this.mHandler.removeMessages(2);
//    this.mHandler.removeMessages(3);
//    this.mHandler.removeMessages(4);
//    this.mHandler.removeMessages(5);
//    this.mHandler.removeMessages(6);
//    this.mHandler.removeMessages(8);
//    this.mHandler.removeMessages(9);
//    this.mHandler.removeMessages(10);
//    this.mHandler.removeMessages(11);
//    this.mHandler.removeMessages(1002);
//    this.mHandler.removeMessages(100);
//    if (this.cameraManager != null)
//      this.cameraManager.closeCamera();
//  }
//
//  public void surfaceChanged(SurfaceHolder paramSurfaceHolder, int paramInt1, int paramInt2, int paramInt3)
//  {
//    if (paramSurfaceHolder.getSurface() == null)
//    {
//      Log.d("cc_smart", "holder.getSurface() == null");
//      return;
//    }
//    Log.v("cc_smart", "surfaceChanged. w=" + paramInt2 + ". h=" + paramInt3);
//    this.surfaceHolder = paramSurfaceHolder;
//    this.cameraManager.setPreviewDisplay(this.surfaceHolder);
//    this.cameraManager.initDisplay();
//    this.mHandler.sendEmptyMessageDelayed(5, this.startTime);
//  }
//
//  public void surfaceCreated(SurfaceHolder paramSurfaceHolder)
//  {
//    Log.d("cc_smart", "surfaceCreated");
//    if (!this.cameraManager.cameraOpened())
//    {
//      this.cameraManager.openCamera();
//      setParameters();
//    }
//  }
//
//  public void surfaceDestroyed(SurfaceHolder paramSurfaceHolder)
//  {
//    Log.d("cc_smart", "surfaceDestroyed");
//    this.cameraManager.closeCamera();
//    this.surfaceHolder = null;
//  }
//}
//
///* Location:           C:\Users\Administrator\Desktop\APK反编译工具\jd-gui-0.3.5\身份证.jar
// * Qualified Name:     com.yunmai.cc.smart.eye.activity.CameraActivity
// * JD-Core Version:    0.6.2
// */