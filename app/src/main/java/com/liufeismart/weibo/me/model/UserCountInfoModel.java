package com.liufeismart.weibo.me.model;

import com.liufeismart.weibo.bean.UserInfoBean;
import com.liufeismart.weibo.network.base.NetworkAPI;

import java.util.List;

public interface UserCountInfoModel {

    void loadUserCountInfo(NetworkAPI.Callback<List<UserInfoBean>> callback, String userids);
}
