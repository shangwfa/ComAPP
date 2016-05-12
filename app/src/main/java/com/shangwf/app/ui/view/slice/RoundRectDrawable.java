package com.shangwf.app.ui.view.slice;

import android.annotation.TargetApi;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;

/**
 * Created by menmen on 16/5/8.
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class RoundRectDrawable extends Drawable {
    protected float mRadius;
    protected final Paint mPaint;
    protected final RectF mBoundsF;
    protected final Rect mBoundsI;
    protected float mPadding;
    protected boolean mInsetForPadding = false;
    protected boolean mInsetForRadius = true;

    public RoundRectDrawable(int backgroundColor, float radius) {
        mRadius = radius;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mPaint.setColor(backgroundColor);
        mBoundsF = new RectF();
        mBoundsI = new Rect();
    }

    void setPadding(float padding, boolean insetForPadding, boolean insetForRadius) {
        if (padding == mPadding && mInsetForPadding == insetForPadding &&
                mInsetForRadius == insetForRadius) {
            return;
        }
        mPadding = padding;
        mInsetForPadding = insetForPadding;
        mInsetForRadius = insetForRadius;
        updateBounds(null);
        invalidateSelf();
    }

    private void updateBounds(Rect bounds) {
        if (null == bounds) {
            bounds = getBounds();
        }
        mBoundsF.set(bounds.left, bounds.top, bounds.right, bounds.bottom);
        mBoundsI.set(bounds);

        if (mInsetForPadding) {
            float vInset = RoundRectDrawableWithShadow.calculateVerticalPadding(mPadding, mRadius, mInsetForRadius);
            float hInset = RoundRectDrawableWithShadow.calculateHorizontalPadding(mPadding, mRadius, mInsetForRadius);
            mBoundsI.inset((int) Math.ceil(hInset), (int) Math.ceil(vInset));
            // to make sure they have same bounds.
            mBoundsF.set(mBoundsI);
        }
    }


    @Override
    public void getOutline(Outline outline) {
        outline.setRoundRect(mBoundsI, mRadius);
    }
    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        updateBounds(bounds);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRoundRect(mBoundsF, mRadius, mRadius, mPaint);
    }

    @Override
    public void setAlpha(int alpha) {
        // not supported because older versions do not support
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        // not supported because older versions do not support
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    public float getRadius() {
        return mRadius;
    }

    public void setColor(int color) {
        mPaint.setColor(color);
        invalidateSelf();
    }
    void setRadius(float radius) {
        if (radius == mRadius) {
            return;
        }
        mRadius = radius;
        updateBounds(null);
        invalidateSelf();
    }
}
