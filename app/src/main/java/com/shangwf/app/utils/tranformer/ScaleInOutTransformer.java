package com.shangwf.app.utils.tranformer;

import android.view.View;

/**
 * Created by Administrator on 2015/11/16.
 */
public class ScaleInOutTransformer extends ABaseTransformer {

    @Override
    protected void onTransform(View view, float position) {
        view.setPivotX(position < 0 ? 0 : view.getWidth());
        view.setPivotY(view.getHeight() / 2f);
        float scale = position < 0 ? 1f + position : 1f - position;
        view.setScaleX(scale);
        view.setScaleY(scale);
    }

}