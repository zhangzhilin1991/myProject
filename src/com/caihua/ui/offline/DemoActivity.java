//package com.caihua.ui.offline;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.content.res.AssetManager;
//import android.net.Uri;
//import android.os.Bundle;
//import android.os.Environment;
//import android.os.Handler;
//import android.os.Message;
//import android.util.Log;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.Toast;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.List;
//
//public class DemoActivity extends Activity
//{
//  private Button bt_all;
//  private Button bt_import;
//  private Button bt_name;
//  private boolean dataOK = false;
//  private EditText editText;
//  private Intent intent;
//  private ImageView iv;
//  private Handler mHandler = new Handler()
//  {
//    public void handleMessage(Message paramAnonymousMessage)
//    {
//      super.handleMessage(paramAnonymousMessage);
//      switch (paramAnonymousMessage.what)
//      {
//      default:
//        return;
//      case 1:
//      }
//      Toast.makeText(DemoActivity.this.getBaseContext(), "批量识别结束！", 1).show();
//      DemoActivity.this.editText.setText("批量识别结束！-->/存储卡/ccidcard/testResult.txt");
//    }
//  };
//
//  public void IdToString(StringBuffer paramStringBuffer,IdCardInfo paramIdCardInfo)
//  {
//    if (paramIdCardInfo == null)
//    {
//      paramStringBuffer.append("没有识别结果！！图片加载失败，模糊判断错误！").append("\r\n\r\n\r\n");
//      return;
//    }
//    paramStringBuffer.append("姓名   ： ").append(paramIdCardInfo.getName()).append("\r\n");
//    paramStringBuffer.append("性别   ： ").append(paramIdCardInfo.getSex()).append("\r\n");
//    paramStringBuffer.append("民族   ： ").append(paramIdCardInfo.getFolk()).append("\r\n");
//    paramStringBuffer.append("生日   ： ").append(paramIdCardInfo.getBirthday()).append("\r\n");
//    paramStringBuffer.append("住址   ： ").append(paramIdCardInfo.getAddress()).append("\r\n");
//    paramStringBuffer.append("证号   ： ").append(paramIdCardInfo.getNum()).append("\r\n");
//    paramStringBuffer.append("机关   ： ").append(paramIdCardInfo.getAuthority()).append("\r\n");
//    paramStringBuffer.append("期限   ： ").append(paramIdCardInfo.getValid()).append("\r\n\r\n\r\n");
//  }
//
//  public byte[] getBytesFromFile(File paramFile)
//    throws IOException
//  {
//    FileInputStream localFileInputStream = new FileInputStream(paramFile);
//    long l = paramFile.length();
//    if (l > 2147483647L)
//    {
//      localFileInputStream.close();
//      throw new IOException("File is to large " + paramFile.getName());
//    }
//    byte[] arrayOfByte = new byte[(int)l];
//    int i = 0;
//    try
//    {
//      if (i < arrayOfByte.length)
//      {
//        j = localFileInputStream.read(arrayOfByte, i, arrayOfByte.length - i);
//        if (j >= 0);
//      }
//      else
//      {
//        if (i >= arrayOfByte.length)
//          break label145;
//        throw new IOException("Could not completely read file " + paramFile.getName());
//      }
//    }
//    catch (Exception localException)
//    {
//      while (true)
//      {
//        int j;
//        return null;
//        i += j;
//      }
//      label145: localFileInputStream.close();
//      return arrayOfByte;
//    }
//    finally
//    {
//      ((byte[])null);
//    }
//  }
//
//  public byte[] getBytesFromFile(String paramString)
//    throws IOException
//  {
//    return getBytesFromFile(new File(paramString));
//  }
//
//  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
//  {
//    super.onActivityResult(paramInt1, paramInt2, paramIntent);
//    if (paramInt2 == 200)
//    {
//      if (paramIntent != null)
//      {
//        IdCardInfo localIdCardInfo = (IdCardInfo)paramIntent.getSerializableExtra("id_card_info");
//        String str = "识别时间 ：  " + paramIntent.getLongExtra("testkey_1", 0L);
//        if (localIdCardInfo != null)
//        {
//          StringBuffer localStringBuffer = new StringBuffer("");
//          localStringBuffer.append(localIdCardInfo.getName()).append("\n");
//          localStringBuffer.append(localIdCardInfo.getNum()).append("\n");
//          localStringBuffer.append(localIdCardInfo.getSex()).append("\n");
//          localStringBuffer.append(localIdCardInfo.getBirthday()).append("\n");
//          localStringBuffer.append(localIdCardInfo.getFolk()).append("\n");
//          localStringBuffer.append(localIdCardInfo.getAddress()).append("\n");
//          localStringBuffer.append(localIdCardInfo.getAuthority()).append("\n");
//          localStringBuffer.append(localIdCardInfo.getValid()).append("\n");
//          localStringBuffer.append("\n\n");
//          localStringBuffer.append("找边次数 ： " + (String)localIdCardInfo.getTestRtime().get(0)).append("\n");
//          localStringBuffer.append("找边时间 ： " + (String)localIdCardInfo.getTestRtime().get(1)).append("\n");
//          localStringBuffer.append(str).append("\n");
//          this.editText.setText(localStringBuffer.toString());
//          try
//          {
//            if ((!"".equals(localIdCardInfo.getHead())) && ((localIdCardInfo.getName() != null) || (localIdCardInfo.getNum() != null) || (localIdCardInfo.getAddress() != null)))
//            {
//              this.iv.setImageURI(Uri.parse(localIdCardInfo.getHead()));
//              return;
//            }
//            this.iv.setImageURI(null);
//            return;
//          }
//          catch (Exception localException)
//          {
//            return;
//          }
//        }
//        this.editText.setText("idCardInfo is null");
//        return;
//      }
//      this.editText.setText("data is null");
//      return;
//    }
//    this.editText.setText("resultCode == " + paramInt2);
//  }
//
//  protected void onCreate(Bundle paramBundle)
//  {
//    super.onCreate(paramBundle);
//    setContentView(2130903040);
//    this.bt_all = ((Button)findViewById(2130968578));
//    this.bt_name = ((Button)findViewById(2130968579));
//    this.bt_import = ((Button)findViewById(2130968580));
//    this.editText = ((EditText)findViewById(2130968581));
//    this.iv = ((ImageView)findViewById(2130968577));
//    this.intent = new Intent(this, CameraActivity.class);
//    this.bt_all.setOnClickListener(new View.OnClickListener()
//    {
//      public void onClick(View paramAnonymousView)
//      {
//        if (DemoActivity.this.dataOK)
//        {
//          DemoActivity.this.intent.putExtra("ISRECOGNALL", true);
//          DemoActivity.this.startActivityForResult(DemoActivity.this.intent, 110);
//          return;
//        }
//        Toast.makeText(DemoActivity.this.getBaseContext(), "加载数据，请稍后", 1).show();
//      }
//    });
//    this.bt_name.setOnClickListener(new View.OnClickListener()
//    {
//      public void onClick(View paramAnonymousView)
//      {
//        if (DemoActivity.this.dataOK)
//        {
//          DemoActivity.this.intent.putExtra("ISRECOGNALL", false);
//          DemoActivity.this.startActivityForResult(DemoActivity.this.intent, 110);
//          return;
//        }
//        Toast.makeText(DemoActivity.this.getBaseContext(), "加载数据，请稍后", 1).show();
//      }
//    });
//    this.bt_import.setOnClickListener(new View.OnClickListener()
//    {
//      public void onClick(View paramAnonymousView)
//      {
//        if (!Environment.getExternalStorageState().equals("mounted"))
//        {
//          Toast.makeText(DemoActivity.this.getBaseContext(), "请插入存储卡", 1).show();
//          return;
//        }
//        Toast.makeText(DemoActivity.this.getBaseContext(), "开始识别", 1).show();
//        DemoActivity.this.editText.setText("批量识别ing ......");
//        new Thread()
//        {
//          public void run()
//          {
//            super.run();
//            File[] arrayOfFile = new File(UtilApp.getTestImgs()).listFiles();
//            long l1 = System.currentTimeMillis();
//            OcrManager localOcrManager = new OcrManager();
//            localOcrManager.initEngine(true);
//            long l2 = System.currentTimeMillis() - l1;
//            Log.d("tag", "--startBCRTime--------->>" + l2);
//            StringBuffer localStringBuffer = new StringBuffer();
//            int i = 0;
//            while (true)
//            {
//              File localFile;
//              if (i >= arrayOfFile.length)
//              {
//                localOcrManager.closeEngine();
//                localFile = new File(UtilApp.getCCIDFOLDER(), "testResult.txt");
//                if (localFile.exists())
//                  localFile.delete();
//              }
//              try
//              {
//                localFile.createNewFile();
//                FileOutputStream localFileOutputStream = new FileOutputStream(localFile);
//                localFileOutputStream.write(localStringBuffer.toString().getBytes());
//                localFileOutputStream.flush();
//                localFileOutputStream.close();
//                DemoActivity.this.mHandler.sendEmptyMessage(1);
//                return;
//                try
//                {
//                  localStringBuffer.append("图片   ： ").append(arrayOfFile[i].getName()).append("\r\n");
//                  long l3 = System.currentTimeMillis();
//                  IdCardInfo localIdCardInfo = localOcrManager.testA(DemoActivity.this.getBytesFromFile(arrayOfFile[i].getPath()));
//                  long l4 = l2 + (System.currentTimeMillis() - l3);
//                  localStringBuffer.append("耗时   ： ").append(l4).append("\r\n");
//                  DemoActivity.this.IdToString(localStringBuffer, localIdCardInfo);
//                  i++;
//                }
//                catch (IOException localIOException1)
//                {
//                  while (true)
//                    localIOException1.printStackTrace();
//                }
//              }
//              catch (IOException localIOException2)
//              {
//                while (true)
//                  localIOException2.printStackTrace();
//              }
//            }
//          }
//        }
//        .start();
//      }
//    });
//    new Thread()
//    {
//      public void run()
//      {
//        super.run();
//        try
//        {
//          File localFile1 = new File(UtilApp.getSdcDir() + "/HCBCR18b2u_mob.dat");
//          if (!localFile1.exists())
//          {
//            new File(UtilApp.getSdcDir()).mkdirs();
//            InputStream localInputStream2 = DemoActivity.this.getAssets().open("HCBCR18b2u_mob.dat");
//            byte[] arrayOfByte2 = new byte[localInputStream2.available()];
//            while (localInputStream2.read(arrayOfByte2) != -1);
//            FileOutputStream localFileOutputStream2 = new FileOutputStream(localFile1);
//            localFileOutputStream2.write(arrayOfByte2);
//            localFileOutputStream2.flush();
//            localInputStream2.close();
//            localFileOutputStream2.close();
//          }
//          File localFile2 = new File(UtilApp.getSdcDir() + "/ScanBcr_Mo.cfg");
//          if (!localFile2.exists())
//          {
//            new File(UtilApp.getSdcDir()).mkdirs();
//            InputStream localInputStream1 = DemoActivity.this.getAssets().open("ScanBcr_Mo.cfg");
//            byte[] arrayOfByte1 = new byte[localInputStream1.available()];
//            while (localInputStream1.read(arrayOfByte1) != -1);
//            FileOutputStream localFileOutputStream1 = new FileOutputStream(localFile2);
//            localFileOutputStream1.write(arrayOfByte1);
//            localFileOutputStream1.flush();
//            localInputStream1.close();
//            localFileOutputStream1.close();
//          }
//          DemoActivity.this.dataOK = true;
//          return;
//        }
//        catch (Exception localException)
//        {
//          localException.printStackTrace();
//          Log.d("tag", "----tttt------->" + localException.toString());
//        }
//      }
//    }
//    .start();
//  }
//}
//
///* Location:           C:\Users\Administrator\Desktop\APK反编译工具\jd-gui-0.3.5\身份证.jar
// * Qualified Name:     com.yunmai.cc.smart.eye.activity.DemoActivity
// * JD-Core Version:    0.6.2
// */