package com.liufeismart.weibo.me.model;

import com.liufeismart.weibo.network.NetworkUtil;
import com.liufeismart.weibo.network.base.NetworkAPI;

public class UserInfoModelImpl implements UserInfoModel {
    @Override
    public void loadData(NetworkAPI.Callback callback) {
        NetworkUtil.getInstance().getUserInfo(callback);
    }
}
