package com.shangwf.app.utils;

import android.view.View;

/**
 * Created by menmen on 16/5/13.
 * 功能：防止快速点击，产生多响应事件
 */
public abstract class OnClickEvent implements View.OnClickListener {

    public static long lastTime;
    public static long delayTime=500;

    public abstract void singleClick(View v);

    @Override
    public void onClick(View v) {
        if (onDoubClick()) {
            return;
        }
        singleClick(v);
    }

    public boolean onDoubClick() {
        boolean flag = false;
        long time = System.currentTimeMillis() - lastTime;

        if (time < delayTime) {
            flag = true;
        }
        lastTime = System.currentTimeMillis();
        return flag;
    }
}
