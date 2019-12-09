package com.liufeismart.weibo.home.fragment;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liufeismart.weibo.R;
import com.liufeismart.weibo.base.BaseFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class HomeFragment extends BaseFragment implements AttentionDialogFragment.AttentDilaogCallback, HotFragment.HotFragmentCallback, HotDialogFragment.HotDialogFragmentCallback {


    private View rl_content;
    private TextView tv_tab_attention, tv_tab_hot;
    private View ll_line_tab;
    private Drawable redDot, triangleDown, triangleUp;

    private final int TAB_ATTENTION = 0;
    private final int TAB_HOT = 1;
    private int tab = TAB_ATTENTION;

    private Fragment attentionFragment = new AttentionFragment();
    private Fragment hotFragment = new HotFragment();
    private AttentionDialogFragment attentionDialogFragment = new AttentionDialogFragment();
    private HotDialogFragment hotDialogFragment = new HotDialogFragment();

//    TranslateAnimation animationToRight, animationToLeft;
    AnimatorSet animationToRight, animationToLeft;;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    protected void initView(View view) {
        //initDrawable
        redDot = getResources().getDrawable(R.drawable.red_dot);
        redDot.setBounds(0, 0,15,15);
        triangleDown = getResources().getDrawable(R.drawable.icon_triangle_down_tab_fragment_home);
        triangleDown.setBounds(0, 0,15,15);
        triangleUp = getResources().getDrawable(R.drawable.icon_triangle_up_tab_fragment_home);
        triangleUp.setBounds(0, 0,15,15);


        //getView
        tv_tab_attention = view.findViewById(R.id.tv_tab_attention);
        tv_tab_hot = view.findViewById(R.id.tv_tab_hot);
        ll_line_tab = view.findViewById(R.id.ll_line_tab);
        //tv_tab_attention
        tv_tab_attention.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(tab == TAB_HOT) {
                    showAttention();
                }
                else {
                     if(attentionDialogFragment.isHidden()) {
                        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                        fragmentTransaction.show(attentionDialogFragment);
                        fragmentTransaction.commit();
                        setAttentionDrawable(triangleUp);
                    }
                    else {
                        hideAttentionDialog();
                    }
                }
            }
        });
        tv_tab_hot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tab == TAB_ATTENTION) {
                    showHot();
                }
                else {
                    if(hotDialogFragment.isHidden()) {
                        showHotDialog();
                    }
                    else {
                        hideHotDialog();
                    }
                }
            }
        });
        //initAnimation
//        animationToRight = new TranslateAnimation(0,80,0,0);
//        animationToLeft = new TranslateAnimation(80,0,0,0);
//        animationToLeft.setDuration(2000);
//        animationToRight.setDuration(2000);
        int rightMax = ((RelativeLayout)ll_line_tab.getParent()).getLayoutParams().width;
        int leftMax = rightMax - ll_line_tab.getLayoutParams().width;
        int rightMin = ll_line_tab.getLayoutParams().width;
        int leftMin = 0;
        animationToRight = new AnimatorSet();
        animationToLeft = new AnimatorSet();
        ObjectAnimator animation1 = ObjectAnimator.ofInt(ll_line_tab,"right", rightMin, rightMax);
        animation1.setDuration(200);
//        ObjectAnimator animation2 = ObjectAnimator.ofInt(ll_line_tab,"left", 0, 0);
//        animation2.setDuration(200);
        ObjectAnimator animation2 = ObjectAnimator.ofInt(ll_line_tab,"left", leftMin, leftMax);
        animation2.setDuration(200);
        animationToRight.play(animation1).before(animation2);
        ObjectAnimator animation3 = ObjectAnimator.ofInt(ll_line_tab,"left", leftMax, leftMin);
        animation3.setDuration(200);
        ObjectAnimator animation4 = ObjectAnimator.ofInt(ll_line_tab,"right", rightMax, rightMax);
        animation4.setDuration(200);
        ObjectAnimator animation5 = ObjectAnimator.ofInt(ll_line_tab,"right", rightMax, rightMin);
        animation5.setDuration(200);
        animationToLeft.play(animation3).with(animation4).before(animation5);
        tv_tab_hot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tab == TAB_ATTENTION) {
                    tab = TAB_HOT;
                    showHot();
                }
            }
        });

        //showAttention
        setAttentionDrawable(triangleDown);
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.rl_list, attentionFragment);
        fragmentTransaction.add(R.id.rl_list,hotFragment);
        hotFragment.setTargetFragment(this, 0);
        fragmentTransaction.hide(hotFragment);
        fragmentTransaction.add(R.id.rl_dialog, attentionDialogFragment);
        attentionDialogFragment.setTargetFragment(this,0);
        fragmentTransaction.hide(attentionDialogFragment);
        fragmentTransaction.add(R.id.rl_dialog, hotDialogFragment);
        hotDialogFragment.setTargetFragment(this, 0);
        fragmentTransaction.hide(hotDialogFragment);
        fragmentTransaction.commit();



        tv_tab_hot.setTextAppearance(this.getContext(), R.style.textstyle_unselect_tab_fragment_home);
        tv_tab_attention.setTextAppearance(this.getContext(), R.style.textstyle_select_tab_fragment_home);
    }

    private void showHot() {
        tab = TAB_HOT;
        setAttentionDrawable(redDot);
        animationToRight.start();
//        animationToRight.start();
//        ValueAnimator animator6 = ValueAnimator.ofInt(0, 220);
//        animator6.setDuration(200);
//        animator6.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator valueAnimator) {
//                int value = (int)valueAnimator.getAnimatedValue();
//                ll_line_tab.setLeft(value);
//            }
//        });
//        animator6.start();
//        ValueAnimator animator7 = ValueAnimator.ofInt(80, 300);
//        animator7.setDuration(200);
//        animator7.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator valueAnimator) {
//                int value = (int)valueAnimator.getAnimatedValue();
//                ll_line_tab.setRight(value);
//            }
//        });
//        animator7.start();
        ll_line_tab.setLeft(220);
        ll_line_tab.setRight(300);
        tv_tab_attention.setTextAppearance(this.getContext(), R.style.textstyle_unselect_tab_fragment_home);
        tv_tab_hot.setTextAppearance(this.getContext(), R.style.textstyle_select_tab_fragment_home);
        if(hotFragment.isHidden()) {
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.show(hotFragment);
            fragmentTransaction.hide(attentionFragment);
            fragmentTransaction.commit();
        }
    }

    private void showAttention() {
        tab = TAB_ATTENTION;
        animationToLeft.start();
        setAttentionDrawable(triangleDown);
        tv_tab_hot.setTextAppearance(this.getContext(), R.style.textstyle_unselect_tab_fragment_home);
        tv_tab_attention.setTextAppearance(this.getContext(), R.style.textstyle_select_tab_fragment_home);
        if(attentionFragment.isHidden()) {
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.show(attentionFragment);
            fragmentTransaction.hide(hotFragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void hideAttentionDialog() {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.hide(attentionDialogFragment);
        fragmentTransaction.commit();
        setAttentionDrawable(triangleDown);
    }

    public void hideHotDialog() {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.hide(hotDialogFragment);
        fragmentTransaction.commit();
    }

    private void setAttentionDrawable(Drawable drawable) {
        tv_tab_attention.setCompoundDrawables(null, null, drawable, null);
    }

    @Override
    public void showHotDialog() {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.show(hotDialogFragment);
        fragmentTransaction.commit();
    }
}
