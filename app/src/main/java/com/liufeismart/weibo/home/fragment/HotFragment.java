package com.liufeismart.weibo.home.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.material.tabs.TabLayout;
import com.liufeismart.weibo.R;
import com.liufeismart.weibo.base.BaseFragment;
import com.liufeismart.weibo.bean.TabBean;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class HotFragment extends BaseFragment {
    private TabLayout tablayout_category;
    private ImageView iv_tab_show;

    private TabBean[] tab_category = {
            new TabBean(0,"推荐"),
            new TabBean(1,"明星"),
            new TabBean(2,"社会"),
            new TabBean(3,"游戏"),
            new TabBean(4,"摄影"),
            new TabBean(5,"体育"),
            new TabBean(6,"股市"),
            new TabBean(7,"数码"),
            new TabBean(8,"综艺"),
            new TabBean(9,"动漫"),
            new TabBean(10,"法律"),};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hot, container, false);
        return view;
    }

    @Override
    protected void initView(View view) {
        tablayout_category = view.findViewById(R.id.tablayout_category);
        iv_tab_show = view.findViewById(R.id.iv_tab_show);
        for(int i=0; i<tab_category.length; i++ ) {
            TabBean tabBean = tab_category[i];
            TabLayout.Tab tab = tablayout_category.newTab();
            tab.setText(tabBean.getName());
            tab.setTag(tabBean.getId());
            tablayout_category.addTab(tab);
        }

        iv_tab_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((HotFragmentCallback)HotFragment.this.getTargetFragment()).showHotDialog();
            }
        });
    }

    public interface HotFragmentCallback {
        void showHotDialog();
    }
}
