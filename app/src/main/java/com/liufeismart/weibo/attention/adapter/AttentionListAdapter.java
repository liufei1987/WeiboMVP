package com.liufeismart.weibo.attention.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liufeismart.weibo.R;
import com.liufeismart.weibo.bean.WeiboBeanInWeibo;

import java.util.List;

public class AttentionListAdapter extends RecyclerView.Adapter<AttentionListAdapter.VH> {
    private List<WeiboBeanInWeibo> data;


    public AttentionListAdapter(List<WeiboBeanInWeibo> data) {
        this.data = data;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_attention_list, viewGroup, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(VH vh, int i) {
        WeiboBeanInWeibo bean = data.get(i);
        vh.tv_content.setText(bean.getText());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class VH extends RecyclerView.ViewHolder {
        public TextView tv_content;

        public VH(View itemView) {
            super(itemView);
            tv_content = itemView.findViewById(R.id.tv_content);
        }
    }
}
