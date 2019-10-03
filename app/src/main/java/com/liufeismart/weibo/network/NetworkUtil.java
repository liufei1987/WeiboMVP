package com.liufeismart.weibo.network;

import com.liufeismart.weibo.bean.WeiboBeanInWeibo;
import com.liufeismart.weibo.network.retrofit.RetrofitUtil;

import java.util.List;

public class NetworkUtil {

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

    public void getAttention(final NetworkAPI.GetAttentionCallback callback) {
        mNetwork.getAttention(callback);
    }



}
