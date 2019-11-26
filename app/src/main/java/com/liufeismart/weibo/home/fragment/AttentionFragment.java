package com.liufeismart.weibo.home.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liufeismart.weibo.R;
import com.liufeismart.weibo.base.BaseFragment;
import com.liufeismart.weibo.bean.HomeTimeLineNetBean;
import com.liufeismart.weibo.bean.WeiboBean;
import com.liufeismart.weibo.home.adapter.AttentionAdapter;
import com.liufeismart.weibo.home.presenter.HomeTimeLinePresenter;
import com.liufeismart.weibo.home.presenter.HomeTimeLinePresenterImpl;
import com.liufeismart.weibo.home.view.HomeTimeLineView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AttentionFragment extends BaseFragment implements HomeTimeLineView {

    private RecyclerView rv_attention;
    private List<WeiboBean> weiboList = new ArrayList<>();
    private AttentionAdapter adapter;

    private HomeTimeLinePresenter mHomeTimeLinePresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_attetnion,container, false);
        return view;
    }

    @Override
    protected void initView(View view) {
        rv_attention = view.findViewById(R.id.rv_attention);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        rv_attention.setLayoutManager(layoutManager);
        adapter = new AttentionAdapter(weiboList);

        rv_attention.setAdapter(adapter);
        DividerItemDecoration divider = new DividerItemDecoration(rv_attention.getContext(),
                ((LinearLayoutManager)layoutManager).getOrientation());
        divider.setDrawable(getResources().getDrawable(R.drawable.divider_attention_recyclerview));
        rv_attention.addItemDecoration(divider);
        mHomeTimeLinePresenter = new HomeTimeLinePresenterImpl(this);
        mHomeTimeLinePresenter.loadHomeTimeLine();
    }

    @Override
    public void showHomeTineLine(HomeTimeLineNetBean result) {
        weiboList.addAll(result.getStatuses());
        adapter.notifyDataSetChanged();
    }
}
