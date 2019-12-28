package com.liufeismart.weibo.home.presenter;

import android.util.Log;

import com.liufeismart.network.base.NetworkAPI;
import com.liufeismart.network.bean.HomeTimeLineNetBean;
import com.liufeismart.weibo.home.model.HomeTimeLineModel;
import com.liufeismart.weibo.home.model.HomeTimeLineModelImpl;
import com.liufeismart.weibo.home.view.HomeTimeLineView;

public class HomeTimeLinePresenterImpl implements HomeTimeLinePresenter {

    private HomeTimeLineModel mModel;
    private HomeTimeLineView mView;

    public HomeTimeLinePresenterImpl(HomeTimeLineView view) {
        mView = view;
        mModel = new HomeTimeLineModelImpl();
    }

    @Override
    public void loadHomeTimeLine() {
        mModel.homeTimeLine(new NetworkAPI.Callback<HomeTimeLineNetBean>() {
            @Override
            public void onSuccess(HomeTimeLineNetBean result) {
                mView.showHomeTineLine(result);
            }

            @Override
            public void onFailure(String reason) {
                Log.v("hometineline", reason);
            }
        });
    }
}
