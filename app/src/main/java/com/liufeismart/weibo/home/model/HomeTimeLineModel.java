package com.liufeismart.weibo.home.model;

import com.liufeismart.network.base.NetworkAPI;
import com.liufeismart.network.bean.HomeTimeLineNetBean;

public interface HomeTimeLineModel {
    void homeTimeLine(final NetworkAPI.Callback<HomeTimeLineNetBean> callback);
}
