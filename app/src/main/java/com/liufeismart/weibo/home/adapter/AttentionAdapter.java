package com.liufeismart.weibo.home.adapter;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.liufeismart.image.ImageUtil;
import com.liufeismart.network.bean.WeiboBean;
import com.liufeismart.weibo.R;
import com.liufeismart.weibo.utils.StringUtil;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AttentionAdapter extends RecyclerView.Adapter<AttentionAdapter.WeiboViewHolder> {

    private List<WeiboBean> weiboList;

    public AttentionAdapter(List<WeiboBean> weiboList) {
        this.weiboList = weiboList;
    }

    @NonNull
    @Override
    public WeiboViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_attention_weibo, null);
        return new WeiboViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull WeiboViewHolder holder, int position) {
        Resources res =  holder.tv_createtime.getResources();
        WeiboBean bean = weiboList.get(position);
        ImageUtil.getInstance().setPortrait(holder.iv_portrait, bean.getUser().getProfile_image_url(), R.drawable.icon_protrait_default);
        holder.tv_name.setText(bean.getUser().getName());
        String createTime =StringUtil.getCreateTime(holder.tv_createtime.getContext(),bean.getCreated_at()) + "  来自" + StringUtil.getSource(holder.tv_createtime.getContext(), bean.getSource());
        holder.tv_createtime.setText(createTime);
        holder.tv_content.setText(bean.getText());
        holder.tv_transpond.setText(String.valueOf(bean.getReposts_count()));
        holder.tv_comment.setText(String.valueOf(bean.getComments_count()));
        holder.tv_like.setText(String.valueOf(bean.getAttitudes_count()));
//        holder.tv_transpond.setCompoundDrawablesWithIntrinsicBounds(0,0, 20, 20);
    }

    @Override
    public int getItemCount() {
        return weiboList.size();
    }

    class WeiboViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_portrait;
        TextView tv_name;
        TextView tv_createtime;
        ImageView iv_down;
        TextView tv_content;
        TextView tv_transpond;
        TextView tv_comment;
        TextView tv_like;

        public WeiboViewHolder(@NonNull View view) {
            super(view);
            iv_portrait = view.findViewById(R.id.iv_portrait);
            tv_name = view.findViewById(R.id.tv_name);
            tv_createtime = view.findViewById(R.id.tv_createtime);
            iv_down = view.findViewById(R.id.iv_down);
            tv_content = view.findViewById(R.id.tv_content);
            tv_transpond = view.findViewById(R.id.tv_transpond);
            tv_comment = view.findViewById(R.id.tv_comment);
            tv_like = view.findViewById(R.id.tv_like);

            Drawable[] drawables = tv_transpond.getCompoundDrawables();
            drawables[0].setBounds(110, 25,150,65);
            drawables = tv_comment.getCompoundDrawables();
            drawables[0].setBounds(110, 25,150,65);
            drawables = tv_like.getCompoundDrawables();
            drawables[0].setBounds(110, 25,150,65);
        }
    }
}
