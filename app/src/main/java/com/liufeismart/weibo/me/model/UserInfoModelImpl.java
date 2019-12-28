package com.liufeismart.weibo.me.model;


import com.liufeismart.network.NetworkUtil;
import com.liufeismart.network.base.NetworkAPI;
import com.liufeismart.weibo.logger.util.LogUtil;
import com.liufeismart.weibo.login.util.LoginUtil;

public class UserInfoModelImpl implements UserInfoModel {
    @Override
    public void loadData(NetworkAPI.Callback callback) {
        NetworkUtil.getInstance().getUserInfo(callback,
                LoginUtil.getInstance().mAccessToken.getToken(),
                LoginUtil.getInstance().mAccessToken.getUid());
    }
}
