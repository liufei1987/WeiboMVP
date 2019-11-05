package com.liufeismart.weibo.logger.util;

import android.util.Log;

import com.liufeismart.weibo.WeiboApp;

public class LogUtil {

    public static void v(String tag, String msg) {
        if(WeiboApp.getApplication().isDebug()) {
            Log.v(tag, msg);
        }
    }
}
