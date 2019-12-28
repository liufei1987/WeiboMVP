package com.liufeismart.weibo.me.model;


import com.liufeismart.network.NetworkUtil;
import com.liufeismart.network.base.NetworkAPI;
import com.liufeismart.network.bean.UserInfoBean;
import com.liufeismart.weibo.login.util.LoginUtil;

import java.util.List;

public class UserCountInfoModelImpl implements UserCountInfoModel {

    @Override
    public void loadUserCountInfo(NetworkAPI.Callback<List<UserInfoBean>> callback, String userids) {
        NetworkUtil.getInstance().getUserCountInfo(callback, userids,
                LoginUtil.getInstance().mAccessToken.getToken());
    }
}
