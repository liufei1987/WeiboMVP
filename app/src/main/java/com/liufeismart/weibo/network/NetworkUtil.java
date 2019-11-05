package com.liufeismart.weibo.network;

import com.liufeismart.weibo.bean.UserInfoBean;
import com.liufeismart.weibo.network.base.NetworkAPI;
import com.liufeismart.weibo.network.retrofit.RetrofitUtil;

import java.util.List;

public class NetworkUtil implements NetworkAPI{

    private static NetworkUtil instance;
    private NetworkAPI mNetwork;

    private NetworkUtil() {
        mNetwork = RetrofitUtil.getInstanace();
    }

    public static NetworkUtil getInstance() {
        if(instance == null) {
            instance = new NetworkUtil();
        }
        return instance;
    }


    @Override
    public <T> void getAttention(Callback<T> callback, final Class<T> TClass) {
        mNetwork.getAttention(callback, TClass);
    }

    @Override
    public void getUserInfo(final Callback<UserInfoBean> callback) {
        mNetwork.getUserInfo(callback);
    }

    @Override
    public void getUserCountInfo(final Callback<List<UserInfoBean>> callback, String userids) {
        mNetwork.getUserCountInfo(callback, userids);
    }

    @Override
    public void getUserOftenVisit(final Callback<List<UserInfoBean>> callback, long userid) {
        mNetwork.getUserOftenVisit(callback, userid);
    }

}
