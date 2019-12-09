package com.liufeismart.weibo.home.adapter;

import android.graphics.Bitmap;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.liufeismart.weibo.R;
import com.liufeismart.weibo.bean.DefaultGroupBean;
import com.liufeismart.weibo.utils.UIUtil;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyCategoryAdapter  extends RecyclerView.Adapter {

    private List<DefaultGroupBean> data;
    private MyCategoryAdapter.MyCategoryAdapterCallback callback;

    private boolean enable = true;


    public MyCategoryAdapter(List<DefaultGroupBean> data, MyCategoryAdapter.MyCategoryAdapterCallback callback) {
        this.data = data;
        this.callback = callback;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public boolean isEnabled() {
        return enable;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_group, parent, false);
            return new MyCategoryAdapter.CategoryViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        final int index = position;
        DefaultGroupBean bean = data.get(position);
        if(viewHolder instanceof MyCategoryAdapter.CategoryViewHolder) {
            MyCategoryAdapter.CategoryViewHolder holder = (MyCategoryAdapter.CategoryViewHolder)viewHolder;
            holder.tv_my_name.setText(bean.getName());
            if(enable) {
                holder.iv_delete.setVisibility(View.GONE);
                holder.tv_my_name.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(callback!=null) {
                            callback.clickOn();
                        }

                    }
                });
            }
            else {
                holder.iv_delete.setVisibility(View.VISIBLE);
                holder.tv_my_name.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(callback!=null) {
                            final int size[] = {view.getWidth(), view.getHeight()};
                            callback.moveCategory(MyCategoryAdapter.this, index);

                        }
                    }
                });
            }
        }
        else {
//            MyGroupAdapter.AddGroupViewHolder holder = (MyGroupAdapter.AddGroupViewHolder)viewHolder;
//            holder.tv_my_name.setText(bean.getName());
//            if(enable) {
//                holder.iv_delete.setVisibility(View.GONE);
//                holder.iv_delete.setOnClickListener(null);
//                holder.tv_my_name.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
//                    }
//                });
//            }
//            else {
//                if(bean.canDelete()) {
////                    holder.iv_delete.setVisibility(View.VISIBLE);
////                    holder.iv_delete.setOnClickListener(new View.OnClickListener() {
////                        @Override
////                        public void onClick(View view) {
////                            callback.deleteCategory(index);
////                        }
////                    });
//                }
//                else {
//                    holder.iv_delete.setVisibility(View.GONE);
//                    holder.iv_delete.setOnClickListener(null);
//                }
//                holder.tv_my_name.setOnClickListener(null);
//            }
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyGroupViewHolder extends RecyclerView.ViewHolder {
        TextView tv_my_name;
        ImageView iv_delete;
        public MyGroupViewHolder(@NonNull View view) {
            super(view);
            tv_my_name = view.findViewById(R.id.tv_my_name);
            iv_delete = view.findViewById(R.id.iv_delete);

        }
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView tv_my_name;
        ImageView iv_delete;

        public CategoryViewHolder(View view) {
            super(view);
            tv_my_name = view.findViewById(R.id.tv_my_name);
            iv_delete = view.findViewById(R.id.iv_delete);
        }
    }

    public interface MyCategoryAdapterCallback {
        void clickOn();
        void moveCategory(RecyclerView.Adapter adapter, int index);
    }
}
