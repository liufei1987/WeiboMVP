package com.liufeismart.weibo.me.model;

import com.liufeismart.network.base.NetworkAPI;
import com.liufeismart.network.bean.UserInfoBean;

import java.util.List;

public interface UserCountInfoModel {

    void loadUserCountInfo(NetworkAPI.Callback<List<UserInfoBean>> callback, String userids);
}
