package com.caihua.ui.online;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.caihua.idcardreader.R;

public class ConfigActivity extends Activity
{
  Button back;
  Button btnSave;
  CheckBox chshowpic;
  EditText etip;
  EditText etjmip;
  EditText etjmport;
  EditText etport;
  RadioButton rbnative;
  RadioButton rbwlan;
  RadioGroup rgmode;
  RelativeLayout rljm;
  RelativeLayout rlnatset;
  boolean showpic = false;

  public static boolean isValidIPAndPort(String paramString)
  {
    if ((paramString == null) || ("".equals(paramString)))
      return false;
    return paramString.contains(":");
  }

  public static boolean isboolIP(String paramString)
  {
    return Pattern.compile("(2[5][0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})").matcher(paramString).matches();
  }

  public static boolean isboolPort(String paramString)
  {
    try
    {
      Integer.parseInt(paramString);
      return true;
    }
    catch (Exception localException)
    {
    }
    return false;
  }

  void init()
  {
    try
    {
      this.showpic = getSharedPreferences("0", 0).getBoolean("showhead", true);
      if (getIntent().getIntExtra("mode", 11) == 11)
      {
        this.rbnative.setChecked(true);
        shownative();
      }
      while (this.showpic)
      {
        this.chshowpic.setChecked(this.showpic);
        this.etip.setText(getSharedPreferences("0", 0).getString("headip", "samb.teiwin.com"));
        this.etport.setText(getSharedPreferences("0", 0).getString("headport", "9098"));
      //  return;
        this.rbwlan.setChecked(true);
        shownwlan();
        this.etjmip.setText(getSharedPreferences("0", 0).getString("jmip", "samb.teiwin.com"));
        this.etjmport.setText(getSharedPreferences("0", 0).getString("jmport", "23333"));
      }
      this.rlnatset.setVisibility(8);
      this.chshowpic.setChecked(this.showpic);
      return;
    }
    catch (Exception localException)
    {
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.config);
    this.rljm = ((RelativeLayout)findViewById(R.id.rljm));
    this.rlnatset = ((RelativeLayout)findViewById(R.id.rlnaset));
    this.chshowpic = ((CheckBox)findViewById(R.id.chshowpic));
    this.chshowpic.setChecked(getSharedPreferences("0", 0).getBoolean("showhead", true));
    this.etip = ((EditText)findViewById(R.id.etip));
    this.etport = ((EditText)findViewById(R.id.etport));
    this.etjmip = ((EditText)findViewById(R.id.etjmip));
    this.etjmport = ((EditText)findViewById(R.id.etjmport));
    this.btnSave = ((Button)findViewById(R.id.btnSave));
    this.back = ((Button)findViewById(R.id.back));
    this.back.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        ConfigActivity.this.finish();
      }
    });
    this.btnSave.setOnClickListener(new BtnSave_click());
    this.rbnative = ((RadioButton)findViewById(R.id.rbnative));
    this.rbwlan = ((RadioButton)findViewById(R.id.rbwlan));
    this.rgmode = ((RadioGroup)findViewById(R.id.rgmode));
    if (this.chshowpic.isChecked())
      this.rlnatset.setVisibility(View.VISIBLE);
    this.chshowpic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
    {
      public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
      {
        if (paramAnonymousBoolean)
        {
          ConfigActivity.this.rlnatset.setVisibility(0);
          if (ConfigActivity.this.getSharedPreferences("0", 0).getString("headip", "samb.teiwin.com") != null)
          {
            ConfigActivity.this.etip.setText(ConfigActivity.this.getSharedPreferences("0", 0).getString("headip", "samb.teiwin.com"));
            ConfigActivity.this.etport.setText(ConfigActivity.this.getSharedPreferences("0", 0).getString("headport", "9098"));
          }
        }
        while (true)
        {
          ConfigActivity.this.getSharedPreferences("0", 0).edit().putBoolean("showhead", paramAnonymousBoolean);
          return;
         // ConfigActivity.this.rlnatset.setVisibility(8);
        }
      }
    });
    this.rgmode.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
    {
      public void onCheckedChanged(RadioGroup paramAnonymousRadioGroup, int paramAnonymousInt)
      {
        if (paramAnonymousInt == ConfigActivity.this.rbnative.getId())
          ConfigActivity.this.shownative();
        while (paramAnonymousInt != ConfigActivity.this.rbwlan.getId())
          return;
        ConfigActivity.this.shownwlan();
      }
    });
    init();
  }

  void shownative()
  {
    this.rljm.setVisibility(8);
    if (this.chshowpic.isChecked())
    {
      this.rlnatset.setVisibility(0);
      return;
    }
    this.rlnatset.setVisibility(8);
  }

  void shownwlan()
  {
    this.rljm.setVisibility(0);
    if (getSharedPreferences("0", 0).getString("jmip", "samb.teiwin.com") != null)
    {
      this.etjmip.setText(getSharedPreferences("0", 0).getString("jmip", "samb.teiwin.com"));
      this.etjmport.setText(getSharedPreferences("0", 0).getString("jmport", "23333"));
    }
    if (this.chshowpic.isChecked())
    {
      this.rlnatset.setVisibility(0);
      return;
    }
    this.rlnatset.setVisibility(8);
  }

  class BtnSave_click
    implements View.OnClickListener
  {
    BtnSave_click()
    {
    }

    public void onClick(View paramView)
    {
      SharedPreferences.Editor localEditor = ConfigActivity.this.getSharedPreferences("0", 0).edit();
      if (ConfigActivity.this.chshowpic.isChecked())
      {
        if ((ConfigActivity.this.etip.getText().toString() != null) && (!"".equals(ConfigActivity.this.etip.getText().toString())))
        {
          localEditor.putString("headip", ConfigActivity.this.etip.getText().toString().trim());
          if ((ConfigActivity.this.etport.getText().toString() == null) || ("".equals(ConfigActivity.this.etport.getText().toString())))
           // break;
          localEditor.putString("headport", ConfigActivity.this.etport.getText().toString().trim());
        }
      }
      else if (ConfigActivity.this.rbwlan.isChecked())
        {
          if ((ConfigActivity.this.etjmip.getText().toString() == null) || ("".equals(ConfigActivity.this.etjmip.getText().toString())))
         //   break label385;
          localEditor.putString("jmip", ConfigActivity.this.etjmip.getText().toString().trim());
          if ((ConfigActivity.this.etjmport.getText().toString() == null) || ("".equals(ConfigActivity.this.etjmport.getText().toString())))
        //    break label397;
          localEditor.putString("jmport", ConfigActivity.this.etjmport.getText().toString().trim());
        }
      while (true)
      {
        localEditor.putBoolean("showhead", ConfigActivity.this.chshowpic.isChecked());
        localEditor.commit();
        Toast.makeText(ConfigActivity.this, "保存成功", 0).show();
        ConfigActivity.this.finish();
        //return;
        localEditor.remove("headip");
       // break;
         localEditor.remove("headport");
        //break label165;
        localEditor.remove("jmip");
       // break label246;
        localEditor.remove("jmport");
      }
    }
  }
}

/* Location:           C:\Users\Administrator\Desktop\APK反编译工具\jd-gui-0.3.5\personal.jar
 * Qualified Name:     com.whzl.reader.ConfigActivity
 * JD-Core Version:    0.6.2
 */