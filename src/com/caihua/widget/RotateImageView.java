package com.caihua.widget;

import android.widget.ImageView;
import android.graphics.Bitmap;
import android.graphics.drawable.TransitionDrawable;
import android.graphics.drawable.Drawable;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.animation.AnimationUtils;
import android.view.ViewGroup;

public class RotateImageView extends ImageView {
    private static final int ANIMATION_SPEED = 0x10e;
    private static final String TAG = "RotateImageView";
    private boolean mClockwise;
    private Bitmap mThumb;
    private TransitionDrawable mThumbTransition;
    private Drawable[] mThumbs;
    
    public RotateImageView(Context context) {
        super(context);
    }
    private int mCurrentDegree = 0x0;
    private int mStartDegree = 0x0;
    private int mTargetDegree = 0x0;
    private boolean mEnableAnimation = true;
    private long mAnimationStartTime = 0x0;
    private long mAnimationEndTime = 0x0;
    
    public void enableAnimation(boolean enable) {
        mEnableAnimation = enable;
    }
    
    protected int getDegree() {
        return mTargetDegree;
    }
    
    public void setOrientation(int degree) {
       degree = degree >= 0 ? (degree % 0x168) :(degree % 0x168) + 0x168;
        if(degree == mTargetDegree) {
            return;
        }
        mTargetDegree = degree;
        mStartDegree = mCurrentDegree;
        mAnimationStartTime = AnimationUtils.currentAnimationTimeMillis();
        int diff = mTargetDegree - mCurrentDegree;
        diff = diff >= 0 ? diff : diff + 0x168;
        if(diff > 0xb4) {
            diff = diff - 0x168;
        }
        mClockwise = (diff >= 0);
        mAnimationEndTime = mAnimationStartTime;
        mAnimationStartTime += (long)((Math.abs(diff) * 0x3e8) / 0x10e);
        invalidate();
    }
    
    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();
        if(drawable == null) {
            return;
        }
        Rect bounds = drawable.getBounds();
        int w = bounds.right - bounds.left;
        int h = bounds.bottom - bounds.top;
        if((w != 0) && (h != 0)) {
            if(mCurrentDegree != mTargetDegree) {
                long time = AnimationUtils.currentAnimationTimeMillis();
                if(time < mAnimationEndTime) {
                    int deltaTime = (int)(time - mAnimationStartTime);
                    int degree = mStartDegree + mTargetDegree;
                 //??   (mClockwise ? deltaTime : -deltaTime * 0x10e) = ((mClockwise ? deltaTime : -deltaTime * 0x10e) / 0x3e8);
                    degree = degree >= 0 ? (degree % 0x168) : (degree % 0x168) + 0x168;
                    mCurrentDegree = degree;
                    invalidate();
                } else {
                    mCurrentDegree = mTargetDegree;
                }
            }
            int left = getPaddingLeft();
            int top = getPaddingTop();
            int right = getPaddingRight();
            int bottom = getPaddingBottom();
            int width = (getWidth() - left) - right;
            int height = (getHeight() - top) - bottom;
            int saveCount = canvas.getSaveCount();
            if(getScaleType() == ImageView.ScaleType.FIT_CENTER) {
                if((width < w) || (height < h)) {
                    float ratio = Math.min(((float)width / (float)w), ((float)height / (float)h));
                    canvas.scale(ratio, ratio, ((float)width / 2.0f), ((float)height / 2.0f));
                }
            }
            canvas.translate((float)((width / 0x2) + left), (float)((height / 0x2) + top));
            canvas.rotate((float)-mCurrentDegree);
            canvas.translate((float)(-w / 0x2), (float)(-h / 0x2));
            drawable.draw(canvas);
            canvas.restoreToCount(saveCount);
        }
    }
    
    public void setBitmap(Bitmap bitmap) {
        // :( Parsing error. Please contact me.
    	//????
    }
}