package com.liufeismart.weibo.home.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liufeismart.weibo.R;
import com.liufeismart.weibo.bean.DefaultGroupBean;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DefaultGroupAdapter extends RecyclerView.Adapter<DefaultGroupAdapter.DefaultGroupViewHolder> {

    private List<DefaultGroupBean> data;

    private boolean enable = true;

    public DefaultGroupAdapter(List<DefaultGroupBean> data) {
        this.data = data;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    @NonNull
    @Override
    public DefaultGroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_default_group, null);
        return new DefaultGroupViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DefaultGroupViewHolder holder, int position) {
        DefaultGroupBean bean = data.get(position);
        holder.tv_group_name.setText(bean.getName());
        holder.tv_group_name.setSelected(enable);
        holder.tv_group_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(enable) {
                    Log.v("DefaultGroupAdapter", "item click");
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class DefaultGroupViewHolder extends RecyclerView.ViewHolder {

        TextView tv_group_name;

        public DefaultGroupViewHolder(@NonNull View view) {
            super(view);
            tv_group_name = view.findViewById(R.id.tv_group_name);
        }
    }
}
