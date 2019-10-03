package com.liufeismart.weibo.attention.model;

import com.liufeismart.weibo.bean.WeiboBeanInWeibo;

import java.util.List;

public interface AttentionModel {
    void loadData(Callback callback);

    public interface Callback {
        void onSuccess(List<WeiboBeanInWeibo> weiboList);
        void onFailure(String reason);
    }
}
