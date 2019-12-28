package com.liufeismart.weibo.me.presenter;


import com.liufeismart.network.base.NetworkAPI;
import com.liufeismart.network.bean.UserInfoBean;
import com.liufeismart.weibo.me.model.UserOftenVisitModel;
import com.liufeismart.weibo.me.model.UserOftenVisitModelImpl;
import com.liufeismart.weibo.me.view.UserOftenVisitView;

import java.util.List;

public class UserOftenVisitPresenterImpl implements UserOftenVisitPresenter {

    UserOftenVisitModel mModel;
    UserOftenVisitView mView;

    public UserOftenVisitPresenterImpl(UserOftenVisitView view) {
        this.mView = view;
        this.mModel = new UserOftenVisitModelImpl();
    }
    @Override
    public void loadUserOftenVisit(long userid) {
        mModel.loadUserOftenVisit(new NetworkAPI.Callback<List<UserInfoBean>>() {
            @Override
            public void onSuccess(List<UserInfoBean> result) {
                mView.showUserOftenVisit(result);
            }

            @Override
            public void onFailure(String reason) {

            }
        }, userid);
    }
}
