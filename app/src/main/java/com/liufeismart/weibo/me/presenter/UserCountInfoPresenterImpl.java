package com.liufeismart.weibo.me.presenter;

import com.liufeismart.weibo.bean.UserInfoBean;
import com.liufeismart.weibo.me.model.UserCountInfoModel;
import com.liufeismart.weibo.me.model.UserCountInfoModelImpl;
import com.liufeismart.weibo.me.view.UserCountInfoView;
import com.liufeismart.weibo.network.base.NetworkAPI;

import java.util.List;

public class UserCountInfoPresenterImpl implements UserCountInfoPresenter {

    private UserCountInfoModel mModel;
    private UserCountInfoView mView;

    public UserCountInfoPresenterImpl(UserCountInfoView view) {
        mView = view;
        mModel = new UserCountInfoModelImpl();
    }
    @Override
    public void loadUserCountInfo(String userids) {
        mModel.loadUserCountInfo(new NetworkAPI.Callback<List<UserInfoBean>>() {

            @Override
            public void onSuccess(List<UserInfoBean> result) {
                mView.showUserCountInfo(result);
            }

            @Override
            public void onFailure(String reason) {

            }
        }, userids);
    }
}
