package com.shangwf.app.utils.tranformer;

import android.view.View;

/**
 * Created by Administrator on 2015/11/16.
 */
public class AccordionTransformer extends ABaseTransformer{
    @Override
    protected void onTransform(View view, float position) {
        view.setPivotX(position < 0 ? 0 : view.getWidth());
        view.setScaleX(position < 0 ? 1f + position : 1f - position);
    }
}
