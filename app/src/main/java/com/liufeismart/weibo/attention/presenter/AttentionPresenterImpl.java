package com.liufeismart.weibo.attention.presenter;

import com.liufeismart.weibo.attention.model.AttentionModel;
import com.liufeismart.weibo.attention.model.AttentionModelImpl;
import com.liufeismart.weibo.attention.view.AttentionView;
import com.liufeismart.weibo.bean.WeiboBeanInWeibo;

import java.util.List;

public class AttentionPresenterImpl implements AttentionPresenter {

    private AttentionView mAttentionView;
    private AttentionModel mAttentionModel;

    public AttentionPresenterImpl(AttentionView attentionView) {
        mAttentionView = attentionView;
        mAttentionModel = new AttentionModelImpl();
    }

    @Override
    public void loadData() {
        mAttentionView.showProgress();
        mAttentionModel.loadData(new AttentionModel.Callback() {
            @Override
            public void onSuccess(List<WeiboBeanInWeibo> weiboList) {
                mAttentionView.hideProgress();
                mAttentionView.addData(weiboList);
            }

            @Override
            public void onFailure(String reason) {
                mAttentionView.hideProgress();
                mAttentionView.showError(reason);
            }
        });
    }
}
