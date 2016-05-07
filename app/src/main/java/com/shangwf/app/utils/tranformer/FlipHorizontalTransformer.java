package com.shangwf.app.utils.tranformer;

import android.view.View;

/**
 * Created by Administrator on 2015/11/16.
 */
public class FlipHorizontalTransformer extends ABaseTransformer {

    @Override
    protected void onTransform(View view, float position) {
        final float rotation = 180f * position;

        view.setAlpha(rotation > 90f || rotation < -90f ? 0 : 1);
        view.setPivotX(view.getWidth() * 0.5f);
        view.setPivotY(view.getHeight() * 0.5f);
        view.setRotationY(rotation);
    }

}