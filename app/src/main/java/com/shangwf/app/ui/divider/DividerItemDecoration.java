package com.shangwf.app.ui.divider;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by menmen on 16/5/8.
 */
public class DividerItemDecoration extends RecyclerView.ItemDecoration{
    private static final int[] ATTRS = new int[]{android.R.attr.listDivider};

    private Drawable mDivider;
    private int leftAddition;
    private int rightAddition;

    public DividerItemDecoration(Context context, float leftAdditionDp, float rightAdditionDp) {
        TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        a.recycle();

        this.leftAddition = (int) (context.getResources().getDisplayMetrics().density * leftAdditionDp);
        this.rightAddition = (int) (context.getResources().getDisplayMetrics().density * rightAdditionDp);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent) {
        int left = parent.getPaddingLeft() + leftAddition;
        int right = parent.getWidth() - parent.getPaddingRight() - rightAddition;

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + mDivider.getIntrinsicHeight();

            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
        outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
    }
}
