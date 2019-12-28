package com.liufeismart.weibo.me.model;

import com.liufeismart.network.base.NetworkAPI;
import com.liufeismart.network.bean.UserInfoBean;

import java.util.List;

public interface UserOftenVisitModel {
    void loadUserOftenVisit(NetworkAPI.Callback<List<UserInfoBean>> callback, long userid);
}

