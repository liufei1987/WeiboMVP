package com.liufeismart.weibo.attention.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.liufeismart.weibo.R;
import com.liufeismart.weibo.attention.adapter.AttentionListAdapter;
import com.liufeismart.weibo.attention.presenter.AttentionPresenter;
import com.liufeismart.weibo.attention.presenter.AttentionPresenterImpl;
import com.liufeismart.weibo.attention.view.AttentionView;
import com.liufeismart.weibo.base.BaseActivity;
import com.liufeismart.weibo.bean.WeiboBeanInWeibo;

import java.util.ArrayList;
import java.util.List;

/**
 * 展示当前登录用户的微博和其关注的其他用户的微博的列表
 */

public class AttentionActivity extends BaseActivity implements AttentionView  {

    private RecyclerView attentionList;

    private AttentionPresenter attentionPresenter;

    private List<WeiboBeanInWeibo> dataList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attention);
        attentionList = this.findViewById(R.id.rc_attention_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        attentionList.setLayoutManager(layoutManager);
        attentionList.setAdapter(new AttentionListAdapter(dataList));
        attentionPresenter = new AttentionPresenterImpl(this);
        attentionPresenter.loadData();
    }


    @Override
    public void addData(List<WeiboBeanInWeibo> data) {
        dataList.addAll(data);
    }

    @Override
    public void refreshData(List<WeiboBeanInWeibo> data) {
        dataList.clear();
        dataList.addAll(data);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(String error) {

    }
}
