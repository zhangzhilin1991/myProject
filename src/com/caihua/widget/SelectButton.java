package com.caihua.widget;

import com.caihua.idcardreader.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class SelectButton extends LinearLayout {

	private Button BtnOne;
	private Button BtnTwo;
	
	private onItemSelectedLintener mListener;

	public SelectButton(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public SelectButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater inflater = LayoutInflater.from(context);
		inflater.inflate(R.layout.selected_button, this);
		BtnOne = (Button) findViewById(R.id.btn_select_one);
		BtnTwo = (Button) findViewById(R.id.btn_select_two);

		TypedArray array = context.obtainStyledAttributes(attrs,
				R.styleable.SelectButton);

		String textOne = array.getString(R.styleable.SelectButton_text_one);
		if (textOne == null) {
			textOne = "one";
		}
		BtnOne.setText(textOne);

		String textTwo = array.getString(R.styleable.SelectButton_text_two);

		if (textTwo == null) {
			textTwo = "two";
		}
		BtnTwo.setText(textTwo);
		Drawable btnOneBacground = array
				.getDrawable(R.styleable.SelectButton_text_one_bacground);
		if (btnOneBacground == null) {
			btnOneBacground = getResources().getDrawable(
					R.drawable.select_left, null);
		}
		BtnOne.setBackground(btnOneBacground);

		Drawable btnTwoBacground = array
				.getDrawable(R.styleable.SelectButton_text_two_bacground);
		if (btnTwoBacground == null) {
			btnTwoBacground = getResources().getDrawable(
					R.drawable.select_right, null);
		}
		BtnTwo.setBackground(btnTwoBacground);
		float textSize = array.getDimension(R.styleable.SelectButton_text_size,
				32);
		BtnOne.setTextSize(textSize);
		BtnTwo.setTextSize(textSize);

		array.recycle();
	}

	public void setOnSelectedListener(onItemSelectedLintener listener) {
		
		this.mListener=listener;
		BtnOne.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				BtnOne.setSelected(true);
				BtnTwo.setSelected(false);
				mListener.onSelected(v);
			}
		});
		
		BtnTwo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				BtnOne.setSelected(false);
				BtnTwo.setSelected(true);
				mListener.onSelected(v);
			}
		});
	}
	
	public interface onItemSelectedLintener{
		public void onSelected(View v);
	}
}
