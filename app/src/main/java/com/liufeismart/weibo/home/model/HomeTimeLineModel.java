package com.liufeismart.weibo.home.model;

import com.liufeismart.weibo.bean.HomeTimeLineNetBean;
import com.liufeismart.weibo.network.base.NetworkAPI;

public interface HomeTimeLineModel {
    void homeTimeLine(final NetworkAPI.Callback<HomeTimeLineNetBean> callback);
}
