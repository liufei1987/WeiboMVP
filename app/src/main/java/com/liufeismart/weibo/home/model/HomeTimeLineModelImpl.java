package com.liufeismart.weibo.home.model;

import com.liufeismart.network.NetworkUtil;
import com.liufeismart.network.base.NetworkAPI;
import com.liufeismart.network.bean.HomeTimeLineNetBean;
import com.liufeismart.weibo.login.util.LoginUtil;

public class HomeTimeLineModelImpl implements HomeTimeLineModel {


    public void homeTimeLine(final NetworkAPI.Callback<HomeTimeLineNetBean> callback) {
        NetworkUtil.getInstance().homeTimeLine(callback,
                LoginUtil.getInstance().mAccessToken.getToken());
    }
}
