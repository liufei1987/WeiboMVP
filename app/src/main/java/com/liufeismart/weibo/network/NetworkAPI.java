package com.liufeismart.weibo.network;

import com.liufeismart.weibo.bean.WeiboBeanInWeibo;

import java.util.List;

public interface NetworkAPI {
   void getAttention(final GetAttentionCallback callback);

    public interface GetAttentionCallback {
        void onSuccess(List<WeiboBeanInWeibo> weiboList);
        void onFailure(String reason);
    }
}
