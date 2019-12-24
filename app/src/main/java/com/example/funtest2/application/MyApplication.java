package com.example.funtest2.application;

import android.app.Application;
import android.content.Context;

import com.manager.XMFunSDKManager;

/**
 * @author hws
 * @class describe
 * @time 2019-12-24 8:38
 */
public class MyApplication extends Application {
    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        XMFunSDKManager.getInstance().initXMCloudPlatform(this);
        XMFunSDKManager.getInstance().initLog();
        mContext = getApplicationContext();
    }
    public static Context getContext(){
        return mContext;
    }
}
