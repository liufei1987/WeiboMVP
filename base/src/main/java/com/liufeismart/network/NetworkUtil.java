package com.liufeismart.network;


import com.liufeismart.network.base.NetworkAPI;
import com.liufeismart.network.bean.HomeTimeLineNetBean;
import com.liufeismart.network.bean.UserInfoBean;
import com.liufeismart.network.retrofit.RetrofitUtil;

import java.util.List;

public class NetworkUtil implements NetworkAPI {

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
    public <T> void getAttention(Callback<T> callback, final Class<T> TClass, String accesstoken) {
        mNetwork.getAttention(callback, TClass,  accesstoken);
    }

    @Override
    public void getUserInfo(final Callback<UserInfoBean> callback, String accesstoken, String uid) {
        mNetwork.getUserInfo(callback, accesstoken, uid);
    }

    @Override
    public void getUserCountInfo(final Callback<List<UserInfoBean>> callback, String userids, String accesstoken) {
        mNetwork.getUserCountInfo(callback, userids,accesstoken);
    }

    @Override
    public void getUserOftenVisit(final Callback<List<UserInfoBean>> callback, long userid) {
        mNetwork.getUserOftenVisit(callback, userid);
    }

    @Override
    public void homeTimeLine(Callback<HomeTimeLineNetBean> callback, String accesstoken) {
        mNetwork.homeTimeLine(callback, accesstoken);
    }

}
