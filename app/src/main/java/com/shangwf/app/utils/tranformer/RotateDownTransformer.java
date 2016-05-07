package com.shangwf.app.utils.tranformer;

import android.view.View;

/**
 * Created by Administrator on 2015/11/16.
 */
public class RotateDownTransformer extends ABaseTransformer {

    private static final float ROT_MOD = -15f;

    @Override
    protected void onTransform(View view, float position) {
        final float width = view.getWidth();
        final float height = view.getHeight();
        final float rotation = ROT_MOD * position * -1.25f;

        view.setPivotX(width * 0.5f);
        view.setPivotY(height);
        view.setRotation(rotation);
    }

    @Override
    protected boolean isPagingEnabled() {
        return true;
    }

}
