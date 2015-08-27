package com.caihua.ui.online;

import com.caihua.idcardreader.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ReaderActivity extends Activity {
	
	public static boolean isrunning;
	public static int readcount;
	public static String uuid;
	public static byte[] byuuid;
	public static boolean successImg;
	public static int readsucess;
	public static boolean showHead;
	public static String jmip;
	public static String jmport;
	private Button config;
	private Button help;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.personinfo);
		help = (Button) findViewById(R.id.btnHelp);
		config = (Button) findViewById(R.id.btnset);

		help.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(ReaderActivity.this,
						HelpActivity.class));
			}
		});

		config.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(ReaderActivity.this,
						ConfigActivity.class));
			}
		});
		
		
		//开启读线程，等待数据？？？？
		
		
	}

	
	public static String Byte2S(byte b) {
		// TODO Auto-generated method stub
		//16进制？？？
		return null;
	}
	
	
	public static class DataAnalyse{

		public static void setGUIDAndAlert() {
			// TODO Auto-generated method stub
			
		}

		public static void alert() {
			// TODO Auto-generated method stub
			
		}

		public static void clearOldData() {
			// TODO Auto-generated method stub
			
		}
		
	};
}
