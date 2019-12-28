package com.liufeismart.weibo.me.model;

import com.liufeismart.network.base.NetworkAPI;
import com.liufeismart.network.bean.UserInfoBean;

public interface UserInfoModel {
    void loadData(NetworkAPI.Callback<UserInfoBean> callback);

}
