package com.liufeismart.weibo.network.retrofit;

import com.liufeismart.weibo.bean.WeiboBeanInWeibo;
import com.liufeismart.weibo.network.NetworkAPI;
import com.liufeismart.weibo.network.NetworkUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtil implements NetworkAPI {
    private static RetrofitUtil instanace;
    Retrofit retrofit;
    WeiboAPI weiboAPIRequest;
    private RetrofitUtil() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.weibo.com/2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        weiboAPIRequest = retrofit.create(WeiboAPI.class);
    }

    public static RetrofitUtil getInstanace() {
        if(instanace == null) {
            instanace = new RetrofitUtil();
        }
        return instanace;
    }

    public void getAttention(final NetworkAPI.GetAttentionCallback callback) {
//        String accesstoken = "0";
//        Call<List<WeiboBeanInWeibo>> call = weiboAPIRequest.getAttention(accesstoken);
//        call.enqueue(new Callback<List<WeiboBeanInWeibo>>() {
//            @Override
//            public void onResponse(Call<List<WeiboBeanInWeibo>> call, Response<List<WeiboBeanInWeibo>> response) {
//                List<WeiboBeanInWeibo> weiboList = response.body();
//                callback.onSuccess(weiboList);
//            }
//
//            @Override
//            public void onFailure(Call<List<WeiboBeanInWeibo>> call, Throwable t) {
//
//            }
//        });
        List<WeiboBeanInWeibo> weiboList = new ArrayList<>();
        WeiboBeanInWeibo bean;
        for(int i=0; i<10; i++) {
            bean = new WeiboBeanInWeibo();
            bean.setText("Hello Weibo"+i);
            weiboList.add(bean);
        }
        callback.onSuccess(weiboList);
    }



}
