package com.liufeismart.weibo;

import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.util.Log;

import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.auth.AuthInfo;

public class WeiboApp extends Application {
    private static WeiboApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        WbSdk.install(this,new AuthInfo(this, Constants.APP_KEY, Constants.REDIRECT_URL, Constants.SCOPE));
    }

    public static WeiboApp getApplication() {
        return instance;
    }


    public boolean isDebug(){
        boolean isDebug = this.getApplicationInfo()!=null&&
                (this.getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE)!=0;
        Log.v("debug","isDebug = " + isDebug);
        return isDebug;
    }
}
