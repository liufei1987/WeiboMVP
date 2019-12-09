package com.liufeismart.weibo.home.fragment;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liufeismart.weibo.R;
import com.liufeismart.weibo.base.BaseFragment;
import com.liufeismart.weibo.bean.DefaultGroupBean;
import com.liufeismart.weibo.home.adapter.MyCategoryAdapter;
import com.liufeismart.weibo.home.adapter.MyGroupAdapter;
import com.liufeismart.weibo.logger.util.LogUtil;
import com.liufeismart.weibo.utils.UIUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HotDialogFragment extends BaseFragment implements MyCategoryAdapter.MyCategoryAdapterCallback {

    private RecyclerView rv_my_category, rv_categories;
    private TextView tv_edit;
    private ImageView iv_cancel;
    ImageView imageView;
    ViewGroup parentView;

    private String finishStr, editStr;
    private int ANIMATION_FOR_MOVE_ITEM_DURATION = 200;

    int locationParent[] = new int[2];
    List<DefaultGroupBean> myCategories = new ArrayList<>();
    List<DefaultGroupBean> categories = new ArrayList<>();
    MyCategoryAdapter myCategoryAdapter;
    MyCategoryAdapter categoriesAdapter;

    ViewTreeObserver.OnGlobalLayoutListener mOnGlobalLayoutLinster;

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
        myCategoryAdapter = new MyCategoryAdapter(myCategories, this);
        rv_my_category.setAdapter(myCategoryAdapter);

        //rv_categories
        RecyclerView.LayoutManager categoriesManager = new GridLayoutManager(view.getContext(), AttentionDialogFragment.ROWNUM);
        rv_categories.setLayoutManager(categoriesManager);
        categories = new ArrayList<>();
//        categories.add(new DefaultGroupBean("绘画"));
//        categories.add(new DefaultGroupBean("文艺"));
//        categories.add(new DefaultGroupBean("体育"));
        categoriesAdapter = new MyCategoryAdapter(categories, this);
        categoriesAdapter.setEnable(false);
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
                ((HotDialogFragmentCallback)HotDialogFragment.this.getTargetFragment()).hideHotDialog();
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

////        imageView.setImageBitmap(bitmap);
//        imageView.setBackgroundColor(R.color.red);
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

    boolean isItemMoving;
    public void moveItem(final RecyclerView moveInRV, final RecyclerView moveOutRV,
                         final List moveInDataList, final List moveOutDataList, final int moveOutIndex) {
        final int moveOutDataListSize = moveOutDataList.size();
        moveInDataList.add(moveOutDataList.get(moveOutIndex));
        final int moveInDataListSize = moveInDataList.size();
        moveInRV.getAdapter().notifyItemRangeInserted(moveInDataList.size()-1, moveInDataList.size());
        //两种不同的方式:目的是调用Adapter.notifyXXX,RecyclerView重新布局之后,能够获取到相应的控件的位置信息
        //
        final OnMoveItemAnimationCallback onMoveItemAnimationCallback = new OnMoveItemAnimationCallback() {
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {//通过监听方式
            if(mOnGlobalLayoutLinster!= null) {
                moveInRV.getViewTreeObserver().removeOnGlobalLayoutListener(mOnGlobalLayoutLinster);
                moveOutRV.getViewTreeObserver().removeOnGlobalLayoutListener(mOnGlobalLayoutLinster);

            }
            mOnGlobalLayoutLinster = new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    //onGlobalLayout在moveOutRV.notifyXXX之前，
                    if(moveOutDataListSize == moveOutDataList.size()
                            && moveInDataListSize == moveInDataList.size()
                            && !isItemMoving) {
                        isItemMoving = true;
                        startAnimationForMoveItem(moveInRV, moveOutRV,
                                moveInDataList, moveOutDataList,
                                moveOutIndex, onMoveItemAnimationCallback);

                    }

                }
            };
            moveInRV.getViewTreeObserver().addOnGlobalLayoutListener(mOnGlobalLayoutLinster);
        }
        else {//通过延时的方式
            new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startAnimationForMoveItem(moveInRV, moveOutRV,
                        moveInDataList, moveOutDataList,
                        moveOutIndex, onMoveItemAnimationCallback);
            }
        }, 100);
        }

    }

    private int i = 0;
    private void startAnimationForMoveItem(final RecyclerView moveInRV, final RecyclerView moveOutRV,
                                           final List moveInDataList,  final List moveOutDataList,
                                           final int moveOutIndex, final OnMoveItemAnimationCallback callback) {

        //newViewItem, moveOutViewItem;
        final View newViewItem, moveOutViewItem;
        final String contentVisible;
        int newItemLocation[] = new int[2];
        int moveOutItemLocation[] = new int[2];
        //获取两个ItemView的信息：位置，Bitmap
        newViewItem = moveInRV.getChildAt(moveInDataList.size()-1);
        moveOutViewItem = moveOutRV.getChildAt(moveOutIndex);
        newViewItem.getLocationInWindow(newItemLocation);
        moveOutViewItem.getLocationInWindow(moveOutItemLocation);
        final Bitmap bitmap = UIUtil.loadBitmapFromView(moveOutViewItem);

        //设置imageView的起始位置moveOutViewItem与一致
        if(imageView == null) {
            parentView = (ViewGroup)moveOutRV.getParent();
            imageView = new ImageView(this.getContext());
            imageView.setAlpha(0.3f);
            parentView = (ViewGroup)moveOutRV.getParent();
            parentView.getLocationInWindow(locationParent);
            ViewGroup.MarginLayoutParams params = new RelativeLayout.LayoutParams(moveOutViewItem.getWidth(), moveOutViewItem.getHeight());
            params.topMargin = moveOutItemLocation[1] - locationParent[1] -parentView.getPaddingTop() ;
            params.leftMargin = moveOutItemLocation[0] - locationParent[0] -parentView.getPaddingLeft();
            parentView.addView(imageView, params);
        }
        else {
            parentView = (ViewGroup)moveOutRV.getParent();
            parentView.getLocationInWindow(locationParent);
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams)imageView.getLayoutParams();
            params.topMargin = moveOutItemLocation[1]- locationParent[1] -parentView.getPaddingTop();
            params.leftMargin = moveOutItemLocation[0]- locationParent[0] -parentView.getPaddingLeft();
            imageView.setLayoutParams(params);
        }
        imageView.setVisibility(View.VISIBLE);

        //动画开始前
        callback.onAnimationBefore(moveOutViewItem, newViewItem);

        imageView.setImageBitmap(bitmap);
        //设置动画并启动
        TranslateAnimation translateAnimation = new TranslateAnimation(0 , newItemLocation[0]-moveOutItemLocation[0],0, newItemLocation[1]-moveOutItemLocation[1]);
        translateAnimation.setDuration(ANIMATION_FOR_MOVE_ITEM_DURATION);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

//                imageView.setVisibility(View.GONE);
                if(moveOutIndex< moveOutDataList.size()) {
                    moveOutDataList.remove(moveOutIndex);
                    moveOutRV.getAdapter().notifyDataSetChanged();//notifyItemRemoved(moveOutIndex);
                }
                isItemMoving = false;

                callback.onAnimationEnd(moveOutViewItem, newViewItem);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        translateAnimation.setFillAfter(true);
        imageView.startAnimation(translateAnimation);
    }

    public interface OnMoveItemAnimationCallback {
        void onAnimationBefore(View moveOutItem, View moveInItem);
        void onAnimationEnd(View moveOutItem, View moveInItem);
    }

}
