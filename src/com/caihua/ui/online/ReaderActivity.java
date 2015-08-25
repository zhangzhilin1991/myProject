package com.caihua.ui.online;

import com.caihua.idcardreader.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ReaderActivity extends Activity {
	
	
	private Button config;
	private Button help;
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.personinfo);
	help=(Button) findViewById(R.id.btnHelp);
	config=(Button) findViewById(R.id.btnset);
	
	help.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
		startActivity(new Intent(ReaderActivity.this,HelpActivity.class));	
		}
	});
	
	config.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			startActivity(new Intent(ReaderActivity.this,ConfigActivity.class));	
		}
	});
	
	
	
}
}
