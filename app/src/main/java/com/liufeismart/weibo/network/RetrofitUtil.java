package com.liufeismart.weibo.network;

import com.liufeismart.weibo.bean.WeiboBeanInWeibo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtil {
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

    public RetrofitUtil getInstanace() {
        if(instanace == null) {
            instanace = new RetrofitUtil();
        }
        return instanace;
    }

    public void getAttention(final GetAttentionCallback callback) {
        String accesstoken = "0";
        Call<List<WeiboBeanInWeibo>> call = weiboAPIRequest.getAttention(accesstoken);
        call.enqueue(new Callback<List<WeiboBeanInWeibo>>() {
            @Override
            public void onResponse(Call<List<WeiboBeanInWeibo>> call, Response<List<WeiboBeanInWeibo>> response) {
                List<WeiboBeanInWeibo> weiboList = response.body();
                callback.onSuccess(weiboList);
            }

            @Override
            public void onFailure(Call<List<WeiboBeanInWeibo>> call, Throwable t) {

            }
        });
    }


    public interface GetAttentionCallback {
        void onSuccess(List<WeiboBeanInWeibo> weiboList);
        void onFailure(String reason);
    }
}
