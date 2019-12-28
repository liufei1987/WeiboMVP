package com.liufeismart.weibo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.liufeismart.weibo.base.BaseActivity;
import com.liufeismart.weibo.base.BaseFragment;
import com.liufeismart.weibo.base.BaseFragmentActivity;
import com.liufeismart.weibo.home.fragment.HomeFragment;
import com.liufeismart.weibo.login.util.LoginUtil;
import com.liufeismart.weibo.me.fragment.MeFragment;
import com.liufeismart.weibo.message.fragment.MessageFragment;
import com.liufeismart.weibo.search.fragment.SearchFragment;
import com.liufeismart.weibo.video.fragment.VideoFragment;

import java.lang.ref.SoftReference;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends BaseFragmentActivity {

    HomeFragment homeFragment = new HomeFragment();
    VideoFragment videoFragment = new VideoFragment();
    SearchFragment searchFragment = new SearchFragment();
    MessageFragment messageFragment = new MessageFragment();
    MeFragment meFragment = new MeFragment();
    private TabLayout tabLayout;

    private Fragment currentFragment;
    static final int HOME = 0;
    static final int VIDEO = 1;
    static final int SEARCH = 2;
    static final int MESSAGE = 3;
    static final int ME = 4;
    private int selectedTabTag = HOME;

    SoftReference<Activity> activityRef = new  SoftReference<Activity>(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoginUtil.getInstance().isLogin(activityRef, true);

    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_main);
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.add(R.id.fragment, homeFragment);
        transaction.add(R.id.fragment, videoFragment);
        transaction.add(R.id.fragment, searchFragment);
        transaction.add(R.id.fragment, messageFragment);
        transaction.add(R.id.fragment, meFragment);
        currentFragment = homeFragment;
        transaction.show(currentFragment);
        transaction.hide(videoFragment);
        transaction.hide(searchFragment);
        transaction.hide(messageFragment);
        transaction.hide(meFragment);
//        transaction.add
        transaction.commit();
        //
        tabLayout = this.findViewById(R.id.tablayout);
        for(int i=0; i<tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setTag(HOME+i);
        }

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int tag = (int)tab.getTag();
                if(selectedTabTag != tag) {
                    selectedTabTag = tag;
                    switch(selectedTabTag) {
                        case HOME:
                            replaceFragment(homeFragment);
                            break;
                        case VIDEO:
                            replaceFragment(videoFragment);
                            break;
                        case SEARCH:
                            replaceFragment(searchFragment);
                            break;
                        case MESSAGE:
                            replaceFragment(messageFragment);
                            break;
                        case ME:
                            replaceFragment(meFragment);
                            LoginUtil.getInstance().isLogin(activityRef, true);
                            break;
                    }
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public void replaceFragment(BaseFragment fragment) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.show(fragment);
        if(currentFragment!=null) {
            transaction.hide(currentFragment);
        }
        currentFragment = fragment;
        transaction.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LoginUtil.getInstance().activityResult(requestCode, resultCode, data);
    }
}
