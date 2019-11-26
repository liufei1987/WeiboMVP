package com.liufeismart.weibo.me.activity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.liufeismart.weibo.R;
import com.liufeismart.weibo.base.BaseActivity;

import androidx.annotation.Nullable;

public class ZxingActivity extends BaseActivity {

    private boolean hasSurface;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_zxing);
        hasSurface = false;
//        inactivityTimer = new I(this);
//        beepManager = new BeepManager(this);
//        ambientLightManager = new AmbientLightManager(this);
    }

    @Override
    protected void initView() {

    }
}
