package com.caihua.ui.online;

import com.caihua.idcardreader.R;

import android.app.Activity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HelpActivity extends Activity
{
  Button button;
  TextView site;

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.help);
    this.site = ((TextView)findViewById(R.id.site));
    this.button = ((Button)findViewById(R.id.button));
    try
    {
      SpannableString localSpannableString = new SpannableString("1、软件演示视频链接：http://v.youku.com/v_show/id_XNzMwMzc5MDQw.html");
      localSpannableString.setSpan(new URLSpan("http://v.youku.com/v_show/id_XNzMwMzc5MDQw.html"), 11, "1、软件演示视频链接：http://v.youku.com/v_show/id_XNzMwMzc5MDQw.html".length(), 33);
      this.site.setText(localSpannableString);
      this.site.setMovementMethod(LinkMovementMethod.getInstance());
      this.button.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          HelpActivity.this.finish();
        }
      });
      return;
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
  }
}
