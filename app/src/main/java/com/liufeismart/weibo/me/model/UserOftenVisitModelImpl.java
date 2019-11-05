package com.liufeismart.weibo.me.model;

import com.liufeismart.weibo.bean.UserInfoBean;
import com.liufeismart.weibo.network.NetworkUtil;
import com.liufeismart.weibo.network.base.NetworkAPI;

import java.util.List;

public class UserOftenVisitModelImpl implements UserOftenVisitModel {
    @Override
    public void loadUserOftenVisit(NetworkAPI.Callback<List<UserInfoBean>> callback, long userid) {
        NetworkUtil.getInstance().getUserOftenVisit(callback, userid);
    }
}
