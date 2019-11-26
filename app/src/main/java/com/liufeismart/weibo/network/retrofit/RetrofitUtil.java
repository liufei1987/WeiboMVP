package com.liufeismart.weibo.network.retrofit;

import com.liufeismart.weibo.bean.HomeTimeLineNetBean;
import com.liufeismart.weibo.bean.UserInfoBean;
import com.liufeismart.weibo.login.util.LoginUtil;
import com.liufeismart.weibo.network.base.NetworkAPI;

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
    RetrofitAPI weiboAPIRequest;
    private RetrofitUtil() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        weiboAPIRequest = retrofit.create(RetrofitAPI.class);
    }

    public static RetrofitUtil getInstanace() {
        if( instanace == null) {
            instanace = new RetrofitUtil();
        }
        return instanace;
    }


    @Override
    public <T> void getAttention(final NetworkAPI.Callback<T> callback, final Class<T> TClass) {

        Call<T> call = weiboAPIRequest.getAttention(LoginUtil.getInstance().mAccessToken.getToken());
        call.enqueue(new retrofit2.Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });

    }

    @Override
    public void getUserInfo(final NetworkAPI.Callback<UserInfoBean> callback) {
        Call<UserInfoBean> call = weiboAPIRequest.getUserInfo(
                LoginUtil.getInstance().mAccessToken.getToken(),
                Long.parseLong(LoginUtil.getInstance().mAccessToken.getUid()));
        call.enqueue(new retrofit2.Callback<UserInfoBean>() {
            @Override
            public void onResponse(Call<UserInfoBean> call, Response<UserInfoBean> response) {
                if(response.body()!=null) {
                    callback.onSuccess(response.body());
                    return;
                }
                if(response.errorBody()!=null) {
                    callback.onFailure(response.errorBody().toString());

                }
            }

            @Override
            public void onFailure(Call<UserInfoBean> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    @Override
    public void getUserCountInfo(final Callback<List<UserInfoBean>> callback, String userids) {
        Call<List<UserInfoBean>> call = weiboAPIRequest.getUserCountInfo(
                LoginUtil.getInstance().mAccessToken.getToken(),
                userids);
        call.enqueue(new retrofit2.Callback<List<UserInfoBean>>() {
            @Override
            public void onResponse(Call<List<UserInfoBean>> call, Response<List<UserInfoBean>> response) {
                if(response.body()!=null) {
                    callback.onSuccess(response.body());
                    return;
                }
                if(response.errorBody()!=null) {
                    callback.onFailure(response.errorBody().toString());

                }
            }

            @Override
            public void onFailure(Call<List<UserInfoBean>> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    @Override
    public void getUserOftenVisit(final Callback<List<UserInfoBean>> callback, long userid) {
        List<UserInfoBean> results = new ArrayList<>();
        for(int i=0; i<5; i++) {
            UserInfoBean bean = new UserInfoBean();
            bean.setId(1404376560);
            bean.setName("zaku");
            bean.setProfile_image_url("http://tp1.sinaimg.cn/1404376560/50/0/1");
            results.add(bean);
        }

        callback.onSuccess(results);
    }

    @Override
    public void homeTimeLine(final Callback<HomeTimeLineNetBean> callback) {
        Call<HomeTimeLineNetBean> call = weiboAPIRequest.homeTimeLine(
                LoginUtil.getInstance().mAccessToken.getToken());
        call.enqueue(new retrofit2.Callback<HomeTimeLineNetBean>() {

            @Override
            public void onResponse(Call<HomeTimeLineNetBean> call, Response<HomeTimeLineNetBean> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<HomeTimeLineNetBean> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }
}
