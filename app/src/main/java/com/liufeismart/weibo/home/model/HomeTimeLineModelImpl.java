package com.liufeismart.weibo.home.model;

import com.liufeismart.weibo.bean.HomeTimeLineNetBean;
import com.liufeismart.weibo.network.NetworkUtil;
import com.liufeismart.weibo.network.base.NetworkAPI;

public class HomeTimeLineModelImpl implements HomeTimeLineModel {


    public void homeTimeLine(final NetworkAPI.Callback<HomeTimeLineNetBean> callback) {
        NetworkUtil.getInstance().homeTimeLine(callback);
    }
}
