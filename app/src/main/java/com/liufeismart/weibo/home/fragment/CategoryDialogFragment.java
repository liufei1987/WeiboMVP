package com.liufeismart.weibo.home.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.liufeismart.weibo.R;
import com.liufeismart.weibo.base.BaseFragment;
import com.liufeismart.weibo.bean.DefaultGroupBean;
import com.liufeismart.weibo.home.adapter.CategoryAdapter;
import com.liufeismart.weibo.home.adapter.MyCategoryAdapter;
import com.liufeismart.weibo.animation.AnimationOnMoveItem;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CategoryDialogFragment extends BaseFragment implements MyCategoryAdapter.MyCategoryAdapterCallback {

    private RecyclerView rv_my_category, rv_categories;
    private TextView tv_edit;
    private ImageView iv_cancel;

    private String finishStr, editStr;

    List<DefaultGroupBean> myCategories = new ArrayList<>();
    List<DefaultGroupBean> categories = new ArrayList<>();
    MyCategoryAdapter myCategoryAdapter;
    CategoryAdapter categoriesAdapter;
    private AnimationOnMoveItem animationUtil;


    private String contentVisible;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_hot_fragment_home, null);
        return view;
    }

    @Override
    protected void initView(View view) {
        finishStr = getResources().getString(R.string.finish);
        editStr = getResources().getString(R.string.edit);
        rv_my_category = view.findViewById(R.id.rv_my_category);
        rv_categories = view.findViewById(R.id.rv_categories);
        tv_edit = view.findViewById(R.id.tv_edit);
        iv_cancel = view.findViewById(R.id.iv_cancel);

        //rv_my_category
        RecyclerView.LayoutManager myCategoryManager = new GridLayoutManager(view.getContext(), AttentionDialogFragment.ROWNUM);
        rv_my_category.setLayoutManager(myCategoryManager);
        myCategories.add(new DefaultGroupBean("摄影"));
        myCategories.add(new DefaultGroupBean("股市"));
        myCategories.add(new DefaultGroupBean("动漫"));
        myCategories.add(new DefaultGroupBean("摄影"));
        myCategories.add(new DefaultGroupBean("股市"));
        myCategories.add(new DefaultGroupBean("动漫"));
        myCategories.add(new DefaultGroupBean("摄影"));
        myCategories.add(new DefaultGroupBean("股市"));
        myCategories.add(new DefaultGroupBean("动漫"));
        myCategories.add(new DefaultGroupBean("摄影"));
        myCategories.add(new DefaultGroupBean("股市"));
        myCategories.add(new DefaultGroupBean("动漫"));
        myCategories.add(new DefaultGroupBean("摄影"));
        myCategories.add(new DefaultGroupBean("股市"));
        myCategories.add(new DefaultGroupBean("动漫"));
        myCategories.add(new DefaultGroupBean("摄影"));
        myCategories.add(new DefaultGroupBean("股市"));
        myCategories.add(new DefaultGroupBean("动漫"));
        myCategories.add(new DefaultGroupBean("摄影"));
        myCategories.add(new DefaultGroupBean("股市"));
        myCategories.add(new DefaultGroupBean("动漫"));

        myCategories.add(new DefaultGroupBean("动漫"));
        myCategories.add(new DefaultGroupBean("摄影"));
        myCategories.add(new DefaultGroupBean("股市"));
        myCategories.add(new DefaultGroupBean("动漫"));
        myCategories.add(new DefaultGroupBean("摄影"));
        myCategories.add(new DefaultGroupBean("股市"));
        myCategories.add(new DefaultGroupBean("动漫"));
        myCategories.add(new DefaultGroupBean("动漫"));
        myCategories.add(new DefaultGroupBean("摄影"));
        myCategories.add(new DefaultGroupBean("股市"));
        myCategories.add(new DefaultGroupBean("动漫"));
        myCategories.add(new DefaultGroupBean("摄影"));
        myCategories.add(new DefaultGroupBean("股市"));
        myCategories.add(new DefaultGroupBean("动漫"));

        myCategoryAdapter = new MyCategoryAdapter(myCategories, this);
        rv_my_category.setAdapter(myCategoryAdapter);

        //rv_categories
        RecyclerView.LayoutManager categoriesManager = new GridLayoutManager(view.getContext(), AttentionDialogFragment.ROWNUM);
        rv_categories.setLayoutManager(categoriesManager);
        categories = new ArrayList<>();
//        categories.add(new DefaultGroupBean("绘画"));
//        categories.add(new DefaultGroupBean("文艺"));
//        categories.add(new DefaultGroupBean("体育"));
        categoriesAdapter = new CategoryAdapter(categories, this);
        rv_categories.setAdapter(categoriesAdapter);

        //tv_edit
        tv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myCategoryAdapter.setEnable(!myCategoryAdapter.isEnabled());
                if(myCategoryAdapter.isEnabled()) {
                    tv_edit.setText(editStr);
                }
                else {
                    tv_edit.setText(finishStr);
                }
                myCategoryAdapter.notifyDataSetChanged();
            }
        });
        iv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((HotDialogFragmentCallback) CategoryDialogFragment.this.getTargetFragment()).hideHotDialog();
            }
        });



    }

    @Override
    public void clickOn() {

    }

    int leftMargin = 0;
    int topMargin = 0;
    @Override
    public void moveCategory(RecyclerView.Adapter adapter,
                             final int index) {
        if(adapter == myCategoryAdapter) {
            moveItem(rv_categories, rv_my_category, categories, myCategories, index);
        }
        else if(adapter == categoriesAdapter) {
            moveItem(rv_my_category, rv_categories, myCategories, categories, index);
        }


    }

    public interface HotDialogFragmentCallback {
        void hideHotDialog();
    }

    public void moveItem(final RecyclerView moveInRV, final RecyclerView moveOutRV,
                         final List moveInDataList, final List moveOutDataList, final int moveOutIndex) {
        final AnimationOnMoveItem.OnMoveItemAnimationCallback onMoveItemAnimationCallback = new AnimationOnMoveItem.OnMoveItemAnimationCallback() {
            @Override
            public void onAnimationBefore(View moveOutItem, View moveInItem) {
                //设置newViewItem的TextView不显示文本数据
                final TextView tvInMoveInItem = ((TextView)moveInItem.findViewById(R.id.tv_my_name));
                contentVisible = (String) tvInMoveInItem.getText();
                tvInMoveInItem.setText("");
                ((TextView)moveOutItem.findViewById(R.id.tv_my_name)).setText("");
            }

            @Override
            public void onAnimationEnd(View moveOutItem, View moveInItem) {
                if(moveInItem!=null) {
                    ((TextView)moveInItem.findViewById(R.id.tv_my_name)).setText(contentVisible);
                }
            }
        };
        //
        if(animationUtil == null) {
            animationUtil = new AnimationOnMoveItem();
        }
        animationUtil.moveItemAnimation(moveInRV, moveOutRV,
                moveInDataList, moveOutDataList,
                moveOutIndex, onMoveItemAnimationCallback);
    }



}
