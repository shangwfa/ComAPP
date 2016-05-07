package com.shangwf.app.utils.tranformer;

import android.view.View;

/**
 * Created by Administrator on 2015/11/16.
 */
public class ForegroundToBackgroundTransformer extends ABaseTransformer {

    @Override
    protected void onTransform(View view, float position) {
        final float height = view.getHeight();
        final float width = view.getWidth();
        final float scale = min(position > 0 ? 1f : Math.abs(1f + position), 0.5f);

        view.setScaleX(scale);
        view.setScaleY(scale);
        view.setPivotX(width * 0.5f);
        view.setPivotY(height * 0.5f);
        view.setTranslationX(position > 0 ? width * position : -width * position * 0.25f);
    }

}
