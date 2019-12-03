package com.liufeismart.weibo.home.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liufeismart.weibo.R;
import com.liufeismart.weibo.base.BaseFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HotDialogFragment extends BaseFragment {

    private RecyclerView rv_my_category, rv_categories;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_hot_fragment_home, null);
        return view;
    }

    @Override
    protected void initView(View view) {
        rv_my_category = view.findViewById(R.id.rv_my_category);
        rv_categories = view.findViewById(R.id.rv_categories);

        //rv_my_category
        RecyclerView.LayoutManager defaultGroupManager = new GridLayoutManager(view.getContext(), AttentionDialogFragment.ROWNUM);
        rv_my_category.setLayoutManager(defaultGroupManager);
    }
}
