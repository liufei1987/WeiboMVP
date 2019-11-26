package com.liufeismart.weibo.home.view;

import com.liufeismart.weibo.bean.HomeTimeLineNetBean;
import com.liufeismart.weibo.network.base.NetworkAPI;

public interface HomeTimeLineView {
    void showHomeTineLine(HomeTimeLineNetBean result);
}
