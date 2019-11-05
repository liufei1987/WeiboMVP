package com.liufeismart.weibo.login.util;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.liufeismart.weibo.WeiboApp;
import com.liufeismart.weibo.logger.util.LogUtil;
import com.sina.weibo.sdk.auth.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbAuthListener;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;
import com.sina.weibo.sdk.auth.sso.SsoHandler;

import java.lang.ref.SoftReference;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoginUtil {

    private static LoginUtil instance;

    private SsoHandler mSsoHandler;
    public Oauth2AccessToken mAccessToken;

    private LoginUtil() {

    }

    public static LoginUtil getInstance() {
        if(instance == null) {
            instance = new LoginUtil();
        }
        return instance;
    }

    /**
     * 登录
     * @param activityRef
     */
    public void login(SoftReference<Activity> activityRef) {
        final Activity activity = activityRef.get();
        mSsoHandler = new SsoHandler(activity);
        mSsoHandler.authorizeWeb(new WbAuthListener() {
            @Override
            public void onSuccess(Oauth2AccessToken oauth2AccessToken) {
                mAccessToken = oauth2AccessToken;
                AccessTokenKeeper.writeAccessToken(activity.getApplicationContext(), mAccessToken);
            }

            @Override
            public void cancel() {

            }

            @Override
            public void onFailure(WbConnectErrorMessage wbConnectErrorMessage) {
                Log.v("Weibo", "Error:" +"code: "+ wbConnectErrorMessage.getErrorCode()+"    message:"+wbConnectErrorMessage.getErrorMessage());
                Toast.makeText(activity, wbConnectErrorMessage.getErrorMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * 判断是否已登录
     * @param activityRef
     * @return
     */
    public boolean isLogin(SoftReference<Activity> activityRef, boolean doLogin) {
        if(mAccessToken == null) {
            mAccessToken = AccessTokenKeeper.readAccessToken(activityRef.get().getApplicationContext());
        }
        if(mAccessToken != null) {
            String date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(
                    new java.util.Date(mAccessToken.getExpiresTime()));
            LogUtil.v("token", mAccessToken.getToken()+" expiresTime: " + date);
            long nowTime = System.currentTimeMillis();
            if(mAccessToken.getExpiresTime() > nowTime) {//accessToken未过期
                return true;
            }
            else {//accessToken过期,重新登录
                mAccessToken = null;
            }
        }
        if(doLogin) {
            login(activityRef);
        }
        return false;
    }


    public void activityResult(int requestCode, int resultCode, Intent data) {
        // SSO 授权回调
        // 重要：发起 SSO 登陆的 Activity 必须重写 onActivityResults
        if (mSsoHandler != null) {
            mSsoHandler.authorizeCallBack(requestCode, resultCode, data);
        }
    }
}
