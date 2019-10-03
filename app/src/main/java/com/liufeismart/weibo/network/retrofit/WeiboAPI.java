package com.liufeismart.weibo.network.retrofit;

import com.liufeismart.weibo.bean.WeiboBeanInWeibo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 微博开放平台的相关网络接口
 */
public interface WeiboAPI {
    /**
     * 获取当前登录用户及其所关注（授权）用户的最新微博
     * @return
     */
    @GET("statuses/home_timeline.json")
    Call<List<WeiboBeanInWeibo>> getAttention(@Query("access_token") String accesstoken);
}


