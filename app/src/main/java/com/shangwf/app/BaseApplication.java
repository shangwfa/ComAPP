package com.shangwf.app;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import com.shangwf.app.bean.User;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by menmen on 16/5/8.
 */
public class BaseApplication extends Application {
    public static Context context;
    public static Resources resources;
    public static User LOCAL_LOGINED_USER;
    public static final String BUNDLE_TYPE = "BUNDLE_TYPE";

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        resources = getResources();
        LeakCanary.install(this);
    }


    public static boolean isLogined() {
        return LOCAL_LOGINED_USER != null;
    }
}
