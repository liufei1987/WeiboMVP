package com.liufeismart.weibo.me.model;

import com.liufeismart.weibo.bean.UserInfoBean;
import com.liufeismart.weibo.network.NetworkUtil;
import com.liufeismart.weibo.network.base.NetworkAPI;

import java.util.List;

public class UserCountInfoModelImpl implements UserCountInfoModel {

    @Override
    public void loadUserCountInfo(NetworkAPI.Callback<List<UserInfoBean>> callback, String userids) {
        NetworkUtil.getInstance().getUserCountInfo(callback, userids);
    }
}
