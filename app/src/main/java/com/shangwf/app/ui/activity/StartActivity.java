package com.shangwf.app.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.shangwf.app.R;
import com.shangwf.app.bean.LocalUser;
import com.shangwf.app.bean.User;
import com.shangwf.app.utils.HandlerUtil;
import com.shangwf.app.utils.SharedPreferencesHelper;

import java.util.Date;

public class StartActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


        loadLocalUser();

        HandlerUtil.runOnUiThreadDelay(()->{
            startActivity(new Intent(StartActivity.this, MainActivity.class));
            finish();
        },2000);
    }

    private void loadLocalUser() {
        final SharedPreferencesHelper spHelper=SharedPreferencesHelper.getInstance(this);
        //首先是否有登录记录
        boolean isLogin =spHelper.getBooleanValue(LocalUser.KEY_LOGIN_STATE,false);
        if(!isLogin) return;

        //其次，登录记录是否过期
        long lastLoginTime=spHelper.getLongValue(LocalUser.KEY_LAST_LOGIN_TIME,-1);
        if(lastLoginTime==-1) return;

        if(new Date().getTime()-lastLoginTime>30L*24*60*60*1000) return;

        //验证信息是否完整
        User user=new User();
        user.setUid(spHelper.getLongValue(LocalUser.KEY_UID,-1));
        if(user.getUid()==-1) return;

        user.setName(spHelper.getStringValue(LocalUser.KEY_NICK,null));
        if(user.getName()==null) return;

        user.setPortrait(spHelper.getStringValue(LocalUser.KEY_PORTRAIT,null));
        if(user.getPortrait()==null) return;

        user.setAccount(spHelper.getStringValue(LocalUser.KEY_USERNAME,null));
        if(user.getAccount()==null) return;

        user.setPwd(spHelper.getStringValue(LocalUser.KEY_PASSWORD,null));
        if(user.getPwd()==null) return;

        user.setGender(spHelper.getStringValue(LocalUser.KEY_GENDER,null));
        user.setScore(spHelper.getIntValue(LocalUser.KEY_SKILL_SCORE,0));


    }
}
