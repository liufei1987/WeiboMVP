package com.liufeismart.weibo.attention.view;

import com.liufeismart.weibo.bean.WeiboBeanInWeibo;

import java.util.List;

/**
 *
 */
public interface AttentionView {
    //下拉列表
    void addData(List<WeiboBeanInWeibo> data);
    //刷新列表
    void refreshData(List<WeiboBeanInWeibo> data);
    void showProgress();
    void hideProgress();
    void showError(String error);
}
