package com.liufeismart.weibo.me.model;

import com.liufeismart.weibo.bean.UserInfoBean;
import com.liufeismart.weibo.network.base.NetworkAPI;

import java.util.List;

public interface UserOftenVisitModel {
    void loadUserOftenVisit(NetworkAPI.Callback<List<UserInfoBean>> callback, long userid);
}

