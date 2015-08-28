package com.caihua.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

public class ViewfinderView extends View {
	boolean b;
	private int height;
	boolean l;
	private float lineBottom;
	private float lineLeft;
	private float lineRight;
	private float lineTop;
	private Context mContext;
	private Handler mHandler;
	private int mHeight;
	private int mWidth;
	private int m_nImageHeight;
	private int m_nImageWidth;
	private Paint paint;
	boolean r;
	boolean t;
	private int width;
	private int lineModel = 0;
	private float marginW = 0.0f;
	private float marginH = 0.0f;
	private float marginT = 0.0f;
	private int dLineWidth = 12;
	private int dLen = 60;
	boolean L = false;

	public ViewfinderView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
	}

	public ViewfinderView(Context context) {
		super(context);
		mContext = context;
	}

	public ViewfinderView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mContext = context;
	}

	public void initFinder(int pWidth, int pHeight, Handler mHandler) {
		this.mHandler = mHandler;
		m_nImageWidth = pWidth;
		m_nImageHeight = pHeight;
		WindowManager manager = (WindowManager) mContext
				.getSystemService("window");
		Display display = manager.getDefaultDisplay();
		width = display.getWidth();
		height = display.getHeight();
		if (height >= 1200) {
			marginT = 120.0f;
		} else if (height >= 960) {
			marginT = 110.0f;
		} else if (height >= 720) {
			marginT = 80.0f;
		} else if (height >= 640) {
			marginT = 70.0f;
		} else if (height >= 480) {
			marginT = 60.0f;
		} else if (height >= 320) {
			marginT = 30.0f;
		} else if (height >= 240) {
			marginT = 20.0f;
		} else {
			marginT = 20.0f;
		}
		marginW = (float) ((double) (width - pWidth) / 2.0);
		marginH = (float) ((double) (height - pHeight) / 2.0);
		mWidth = (width / 2);
		mHeight = (height / 2);
		float g = (float) height - (marginT * 2.0f);

		// ??????k的值？？??
		float k = g * 0x3fca3d71;

		float x = 10.0f;
		Log.d("ocr", "<<--k---\u9ad8\u5ea6----g--1---->>" + g);
		if (k <= (float) pWidth) {

		} else {

			x = x - 1.0f;
			k = k * (x / 10.0f);
			g = g * (x / 10.0f);

		}
		Log.d("ocr", "<<--k---\u9ad8\u5ea6----g--2---->>" + g);
		lineLeft = (float) ((double) mWidth - ((double) k / 2.0));
		lineRight = (float) ((double) mWidth + ((double) k / 2.0));
		lineTop = (float) ((double) mHeight - ((double) g / 2.0));
		lineBottom = (float) ((double) mHeight + ((double) g / 2.0));

		int nDisplayWidth = display.getWidth();
		int nDisplayHeight = display.getHeight();
		int nImageWidth = m_nImageWidth;
		int nImageHeight = m_nImageHeight;
		double nUseWidth = 0.0;
		double nUseHeight = 0.0;
		double dRealRegionWidth = 0.0;
		double nFitHeight;
		double nFitWidth;
		if ((nImageWidth * nDisplayHeight) < (nDisplayWidth * nImageHeight)) {
			nFitHeight = (double) nDisplayHeight;
			nFitWidth = ((double) nImageWidth / (double) nImageHeight)
					* nFitHeight;
		} else {
			nFitWidth = (double) nDisplayWidth;
			nFitHeight = nFitWidth
					* ((double) nImageHeight / (double) nImageWidth);
		}
		if ((nFitWidth / nFitHeight) >= 1.0) {
			nUseHeight = nFitHeight;
			nUseWidth = (4.0 * nUseHeight) / 3.0;
		} else {
			nUseWidth = nFitWidth;
			nUseHeight = (3.0 * nUseWidth) / 4.0;
		}
		dRealRegionWidth = (nUseWidth / 480.0) * 420.0;
		paint = new Paint();
		dLineWidth = ((int) dRealRegionWidth / 0x1c);
		paint.setStrokeWidth((float) dLineWidth);
		dLen = ((int) dRealRegionWidth / 0x6);
	}

	public void initFinder(int w, int h, int d) {

	}

	public Rect getFinder() {
		return new Rect((int) (lineLeft - marginW), (int) (lineTop - marginH),
				(int) (lineRight + marginW), (int) (lineBottom + marginH));
	}

	public void setLineRect(int model) {
		lineModel = model;
		invalidate();
	}

	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		// 画笔颜色绿色
		paint.setColor(0xff0100);
		canvas.drawLine((lineLeft - (float) (dLineWidth / 0x2)), lineTop,
				((float) dLen + lineLeft), lineTop, paint);
		canvas.drawLine(lineLeft, (lineTop - (float) (dLineWidth / 0x2)),
				lineLeft, ((float) dLen + lineTop), paint);
		canvas.drawLine(lineRight, (lineTop - (float) (dLineWidth / 0x2)),
				lineRight, ((float) dLen + lineTop), paint);
		canvas.drawLine(((float) (dLineWidth / 0x2) + lineRight), lineTop,
				(lineRight - (float) dLen), lineTop, paint);
		canvas.drawLine(lineLeft, ((float) (dLineWidth / 0x2) + lineBottom),
				lineLeft, (lineBottom - (float) dLen), paint);
		canvas.drawLine((lineLeft - (float) (dLineWidth / 0x2)), lineBottom,
				((float) dLen + lineLeft), lineBottom, paint);
		canvas.drawLine(((float) (dLineWidth / 0x2) + lineRight), lineBottom,
				(lineRight - (float) dLen), lineBottom, paint);
		canvas.drawLine(lineRight, ((float) (dLineWidth / 0x2) + lineBottom),
				lineRight, (lineBottom - (float) dLen), paint);
		paint.setColor(0xff000000);
		paint.setAlpha(0x64);
		canvas.drawRect(((float) (dLineWidth / 0x2) + lineLeft),
				((float) (dLineWidth / 0x2) + lineTop),
				(lineRight - (float) (dLineWidth / 0x2)),
				(lineBottom - (float) (dLineWidth / 0x2)), paint);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (mHandler != null) {
			Log.d("cc_smart", "----CAMERA_TAKE_PHOTO_3--send2->>>");
			mHandler.sendEmptyMessage(0xd);
		}
		return true;
	}
}
