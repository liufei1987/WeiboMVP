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

public class MyCategoryAdapter extends RecyclerView.Adapter<MyCategoryAdapter.CategoryViewHolder> {

    private List<DefaultGroupBean> data;
    private MyCategoryAdapterCallback callback;

    private boolean enable = true;


    public MyCategoryAdapter(List<DefaultGroupBean> data, MyCategoryAdapterCallback callback) {
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
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_group, parent, false);
        return new CategoryViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder viewHolder, int position) {
        final int index = position;
        DefaultGroupBean bean = data.get(position);
        CategoryViewHolder holder = (CategoryViewHolder) viewHolder;
        holder.tv_my_name.setText(bean.getName());
        if (enable) {
            holder.iv_delete.setVisibility(View.GONE);
            holder.tv_my_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (callback != null) {
                        callback.clickOn();
                    }

                }
            });
        } else {
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

    @Override
    public int getItemCount() {
        return data.size();
    }



    class CategoryViewHolder extends RecyclerView.ViewHolder {
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
