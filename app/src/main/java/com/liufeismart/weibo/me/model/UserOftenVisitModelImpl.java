package com.liufeismart.weibo.me.model;

import com.liufeismart.network.NetworkUtil;
import com.liufeismart.network.base.NetworkAPI;
import com.liufeismart.network.bean.UserInfoBean;

import java.util.List;

public class UserOftenVisitModelImpl implements UserOftenVisitModel {
    @Override
    public void loadUserOftenVisit(NetworkAPI.Callback<List<UserInfoBean>> callback, long userid) {
        NetworkUtil.getInstance().getUserOftenVisit(callback, userid);
    }
}
