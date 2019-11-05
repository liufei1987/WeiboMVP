package com.liufeismart.weibo.network.base;

import com.liufeismart.weibo.bean.UserInfoBean;

import java.util.List;

public interface NetworkAPI {
    public static String BASE_URL = "https://api.weibo.com/2/";

    <T> void getAttention(final Callback<T> callback, final Class<T> TClass);

    /**
     *
     * @param callback
     */
    void getUserInfo(final Callback<UserInfoBean> callback);

    /**
     * 批量获取用户的粉丝数、关注数、微博数
     * @param callback
     */
    void getUserCountInfo(final Callback<List<UserInfoBean>> callback, String userids);

    /**
     * 获取用户经常访问的用户列表
     * @param callback
     * @param userid
     */
    void getUserOftenVisit(final Callback<List<UserInfoBean>> callback, long userid);


    public interface Callback<T> {
        void onSuccess(T result);
        void onFailure(String reason);
    }


}
