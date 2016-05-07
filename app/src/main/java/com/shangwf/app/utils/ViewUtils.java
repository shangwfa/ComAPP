package com.shangwf.app.utils;

import android.content.Context;
import android.support.v4.view.ViewConfigurationCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * View工具类
 * <p/>
 * ViewUtils.java
 *
 * @author 火蚁(http://my.oschina.net/u/253900)
 * @data 2015-3-11 上午11:43:58
 */
public class ViewUtils {

    /***
     * 设置TextView的划线状态
     *
     * @param tv
     * @param
     * @return void
     * @author 火蚁
     * 2015-3-11 上午11:46:10
     */
    public static void setTextViewLineFlag(TextView tv, int flags) {
        tv.getPaint().setFlags(flags);
        tv.invalidate();
    }

    /**
     * 功能：设置动态Viewpager高度
     */
    public static void setViewPagerHeight(ViewPager viewPager) {
        ViewGroup viewGroup = (ViewGroup) viewPager.getParent();
        if (viewGroup == null) {
            return;
        }
        int viewPagerIndex = viewGroup.indexOfChild(viewPager);
        int childViewHeight = getViewPagerHeight(viewPager); //获取ViewPager的子View的高度。
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, childViewHeight);//这里设置params的高度。
        viewGroup.removeView(viewPager);
        viewGroup.addView(viewPager, viewPagerIndex, params);//使用这个params
    }

    /**
     * 功能：获取Viewpager子View的高度
     */
    public static int getViewPagerHeight(ViewPager viewPager) {
        int childcount = viewPager.getChildCount();
        int viewpagerHeight = 0;
        for (int i = 0; i < childcount; i++) {
            int h = getViewHeight(viewPager.getChildAt(i));
            if (h > viewpagerHeight) {
                viewpagerHeight = h;
            }
        }
        return viewpagerHeight;
    }

    /**
     * 功能：测量View的宽度
     */
    public static int getViewWidth(View view) {
        int w = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
        return view.getMeasuredWidth();
    }

    /**
     * 功能：测量View的宽度
     */
    public static int[] getViewWidthAndHeight(final View view) {
        final int[] wh = new int[2];
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                view.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                wh[1] = view.getHeight();
                wh[0] = view.getWidth();
            }
        });
        return wh;
    }

    /**
     * 功能：测量View的高度
     */
    public static int getViewHeight(View view) {
        int w = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
        return view.getMeasuredHeight();
    }
    /**
     * 功能：最小移动距离
     */
    public static int getTouchSlop(Context context){
        ViewConfiguration configuration = ViewConfiguration.get(context);
        // 获取TouchSlop值
        return ViewConfigurationCompat.getScaledPagingTouchSlop(configuration);
    }

    /**
     *功能：设置View的Margin
     * @param view
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    public static void setMargin(View view,int left,int top,int right,int bottom){
        LinearLayout.LayoutParams layoutParams= (LinearLayout.LayoutParams) view.getLayoutParams();
        layoutParams.setMargins(left,top,right,bottom);//4个参数按顺序分别是左上右下
        view.setLayoutParams(layoutParams);
    }
}

