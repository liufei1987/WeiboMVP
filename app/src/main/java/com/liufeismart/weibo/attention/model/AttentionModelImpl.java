package com.liufeismart.weibo.attention.model;

import com.liufeismart.weibo.bean.WeiboBeanInWeibo;
import com.liufeismart.weibo.network.NetworkAPI;
import com.liufeismart.weibo.network.NetworkUtil;

import java.util.List;

public class AttentionModelImpl implements AttentionModel {
    @Override
    public void loadData(final Callback callback) {
        NetworkUtil.getInstance().getAttention(new NetworkAPI.GetAttentionCallback() {
            @Override
            public void onSuccess(List<WeiboBeanInWeibo> weiboList) {
                callback.onSuccess(weiboList);
            }

            @Override
            public void onFailure(String reason) {
                callback.onFailure(reason);
            }
        });
    }
}
