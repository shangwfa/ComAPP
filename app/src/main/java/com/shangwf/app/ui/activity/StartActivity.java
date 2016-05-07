package com.shangwf.app.ui.activity;

import android.app.Activity;
import android.os.Bundle;

import com.shangwf.app.R;
import com.shangwf.app.bean.LocalUser;
import com.shangwf.app.utils.SharedPreferencesHelper;

import java.util.Date;

public class StartActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


        loadLocalUser();
    }

    private void loadLocalUser() {
        //首先是否有登录记录
        boolean isLogin =SharedPreferencesHelper.getInstance(this).getBooleanValue(LocalUser.KEY_LOGIN_STATE);
        if(!isLogin) return;

        //其次，登录记录是否过期
        long lastLoginTime=SharedPreferencesHelper.getInstance(this).getLongValue(LocalUser.KEY_LAST_LOGIN_TIME);
        if(lastLoginTime==-1) return;

        if(new Date().getTime()-lastLoginTime>30L*24*60*60*1000) return;

        //验证信息是否完整

    }
}
