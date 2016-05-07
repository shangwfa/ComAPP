package com.shangwf.app.utils.tranformer;

import android.view.View;

/**
 * Created by Administrator on 2015/11/16.
 */
public class DefaultTransformer extends ABaseTransformer {

    @Override
    protected void onTransform(View view, float position) {
    }

    @Override
    public boolean isPagingEnabled() {
        return true;
    }

}
