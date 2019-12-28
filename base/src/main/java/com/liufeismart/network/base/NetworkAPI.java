package com.liufeismart.network.base;

import com.liufeismart.network.bean.HomeTimeLineNetBean;
import com.liufeismart.network.bean.UserInfoBean;

import java.util.List;

import retrofit2.http.GET;

public interface NetworkAPI {
    public static String BASE_URL = "https://api.weibo.com/2/";

    <T> void getAttention(final Callback<T> callback, final Class<T> TClass, String accesstoken);

    /**
     *
     * @param callback
     */
    void getUserInfo(final Callback<UserInfoBean> callback, String accesstoken, String uid);

    /**
     * 批量获取用户的粉丝数、关注数、微博数
     * @param callback
     */
    void getUserCountInfo(final Callback<List<UserInfoBean>> callback, String userids, String accesstoken);

    /**
     * 获取用户经常访问的用户列表
     * @param callback
     * @param userid
     */
    void getUserOftenVisit(final Callback<List<UserInfoBean>> callback, long userid);


    /**
     *  获取当前登录用户及其所关注（授权）用户的最新微博
     */
    @GET("statuses/home_timeline.json")
    void  homeTimeLine(final Callback<HomeTimeLineNetBean> callback, String accesstoken);


    public interface Callback<T> {
        void onSuccess(T result);
        void onFailure(String reason);
    }


}
