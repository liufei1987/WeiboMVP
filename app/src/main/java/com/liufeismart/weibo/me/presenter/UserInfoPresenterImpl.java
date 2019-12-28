package com.liufeismart.weibo.me.presenter;

import com.liufeismart.network.base.NetworkAPI;
import com.liufeismart.network.bean.UserInfoBean;
import com.liufeismart.weibo.me.model.UserInfoModel;
import com.liufeismart.weibo.me.model.UserInfoModelImpl;
import com.liufeismart.weibo.me.view.UserInfoView;

public class UserInfoPresenterImpl implements UserInfoPresenter {

    UserInfoModel mUserInfoModel;
    UserInfoView mUserInfoView;

    public UserInfoPresenterImpl(UserInfoView userInfoView) {
        mUserInfoView = userInfoView;
        mUserInfoModel = new UserInfoModelImpl();
    }

    @Override
    public void loadData() {
        mUserInfoModel.loadData(new NetworkAPI.Callback<UserInfoBean>() {
            @Override
            public void onSuccess(UserInfoBean userInfoBean) {
                mUserInfoView.showUserInfo(userInfoBean);
            }

            @Override
            public void onFailure(String reason) {

            }
        });
    }
}
