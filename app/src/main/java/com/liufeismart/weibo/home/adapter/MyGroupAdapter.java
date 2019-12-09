package com.liufeismart.weibo.home.adapter;

import android.app.Dialog;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.liufeismart.weibo.R;
import com.liufeismart.weibo.bean.DefaultGroupBean;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

public class MyGroupAdapter extends RecyclerView.Adapter {

    private List<DefaultGroupBean> data;
    private MyGroupAdapterCallback callback;

    private boolean enable = true;

    private final int NORMAL_VIEW_TYPE = 0;
    private final int ADD_VIEW_TYPE = 1;

    public MyGroupAdapter(List<DefaultGroupBean> data, MyGroupAdapterCallback callback) {
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
        if(viewType == NORMAL_VIEW_TYPE) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_group, parent, false);
            return new MyGroupViewHolder(view);
        }
        else if(viewType == ADD_VIEW_TYPE) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_add_group, parent, false);
            return new AddGroupViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        final int index = position;
        DefaultGroupBean bean = data.get(position);
        if(viewHolder instanceof AddGroupViewHolder) {
            AddGroupViewHolder holder = (AddGroupViewHolder)viewHolder;
            holder.tv_my_name.setText(bean.getName());
            if(enable) {
                holder.tv_my_name.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        callback.addGroup();
                    }
                });
            }
            else {
                holder.tv_my_name.setOnClickListener(null);
            }
        }
        else {
            MyGroupViewHolder holder = (MyGroupViewHolder)viewHolder;
            holder.tv_my_name.setText(bean.getName());
            if(enable) {
                holder.iv_delete.setVisibility(View.GONE);
                holder.iv_delete.setOnClickListener(null);
                holder.tv_my_name.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
            }
            else {
                if(bean.canDelete()) {
                    holder.iv_delete.setVisibility(View.VISIBLE);
                    holder.iv_delete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            callback.deleteGroup(index);
                        }
                    });
                }
                else {
                    holder.iv_delete.setVisibility(View.GONE);
                    holder.iv_delete.setOnClickListener(null);
                }
                holder.tv_my_name.setOnClickListener(null);
            }
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

    public class AddGroupViewHolder extends RecyclerView.ViewHolder {
        TextView tv_my_name;

        public AddGroupViewHolder(View view) {
            super(view);
            tv_my_name = view.findViewById(R.id.tv_my_name);
        }
    }

    public interface MyGroupAdapterCallback {
        void addGroup();
        void deleteGroup(int index);
    }

    @Override
    public int getItemViewType(int position) {
        return position == data.size() -1 ? ADD_VIEW_TYPE : NORMAL_VIEW_TYPE;
    }
}
