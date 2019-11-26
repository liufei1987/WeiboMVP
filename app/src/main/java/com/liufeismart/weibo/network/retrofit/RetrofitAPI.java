package com.liufeismart.weibo.network.retrofit;

import com.liufeismart.weibo.bean.HomeTimeLineNetBean;
import com.liufeismart.weibo.bean.UserInfoBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 微博开放平台的相关网络接口
 */
public interface RetrofitAPI {
    /**
     * 获取当前登录用户及其所关注（授权）用户的最新微博
     * @return
     */
    @GET("statuses/home_timeline.json")
    <T> Call<T> getAttention(@Query("access_token") String accesstoken);

    @GET("users/show.json")
    Call<UserInfoBean> getUserInfo(@Query("access_token") String accesstoken, @Query("uid") long uid);

    @GET("users/counts.json")
    Call<List<UserInfoBean>> getUserCountInfo(@Query("access_token") String accesstoken, @Query("uids") String uids);

    /**
     *  获取当前登录用户及其所关注（授权）用户的最新微博
     */
    @GET("statuses/home_timeline.json")
    Call<HomeTimeLineNetBean> homeTimeLine(@Query("access_token")String accesstoken);
}


