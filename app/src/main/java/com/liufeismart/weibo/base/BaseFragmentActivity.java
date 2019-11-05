package com.liufeismart.weibo.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

public abstract class BaseFragmentActivity extends FragmentActivity {

    protected FragmentManager mFragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragmentManager = this.getSupportFragmentManager();
        initView();
    }

    public abstract void initView();
}
