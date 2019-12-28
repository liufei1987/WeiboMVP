package com.liufeismart.weibo.home.fragment;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.liufeismart.weibo.R;
import com.liufeismart.weibo.base.BaseFragment;
import com.liufeismart.weibo.bean.DefaultGroupBean;
import com.liufeismart.weibo.home.adapter.DefaultGroupAdapter;
import com.liufeismart.weibo.home.adapter.MyGroupAdapter;
import com.liufeismart.weibo.utils.GridItemDecoration;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AttentionDialogFragment extends BaseFragment {


    private View ll_dialog_attention;
    private View rl_dialog_content;
    private RecyclerView rv_default_group;
    private RecyclerView rv_my_group;

    public static final int ROWNUM = 4;

    private Dialog addGroupDialog;

    private MyGroupAdapter myGroupAdapter;
    private DefaultGroupAdapter defaultGroupAdapter;

    private TextView tv_edit;

    //状态相关
    private final int STATUS_EDIT = 0;
    private final int STATUS_FINISH = 1;
    private int status = STATUS_EDIT;

    String finishStr;
    String editStr;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_attention_fragment_home, container, false);
        return view;

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(hidden) {
            initDialog();
        }

    }



    @Override
    protected void initView(View view) {
        finishStr = getResources().getString(R.string.finish);
        editStr = getResources().getString(R.string.edit);
        ll_dialog_attention = view.findViewById(R.id.ll_dialog_attention);
        rl_dialog_content = view.findViewById(R.id.rl_dialog_content);
        rv_default_group = view.findViewById(R.id.rv_default_group);
        rv_my_group = view.findViewById(R.id.rv_my_group);
        tv_edit = view.findViewById(R.id.tv_edit);
        rl_dialog_content.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                Rect hitRect = new Rect();
                ll_dialog_attention.getHitRect(hitRect);
                if(!hitRect.contains((int)motionEvent.getX(), (int)motionEvent.getY())) {
//                    ((AttentDilaogCallback)(AttentionDialogFragment.this.getParentFragment())).hideAttentionDialog();
//                    List<Fragment> fragments = AttentionDialogFragment.this.getFragmentManager().getFragments();
//                    for(int i=0; i<fragments.size(); i++) {
//                        Fragment fragment = fragments.get(i);
//                        if(fragment instanceof HomeFragment) {
//                            ((AttentDilaogCallback)fragment).hideAttentionDialog();
//                        }
//                    }
                    ((AttentDilaogCallback)(AttentionDialogFragment.this.getTargetFragment())).hideAttentionDialog();
                }
                return false;
            }
        });
        //rv_default_group
        List<DefaultGroupBean> default_groups = new ArrayList<>();
        default_groups.add(new DefaultGroupBean("全部关注"));
        default_groups.add(new DefaultGroupBean("最新微博"));
        default_groups.add(new DefaultGroupBean("特别关注"));
        default_groups.add(new DefaultGroupBean("V+微博"));
        default_groups.add(new DefaultGroupBean("好友圈"));
        default_groups.add(new DefaultGroupBean("群微博"));
        DefaultGroupBean bean = new DefaultGroupBean("悄悄关注");
        bean.setCanDelete(false);
        default_groups.add(bean);
        defaultGroupAdapter = new
                DefaultGroupAdapter(default_groups);
        RecyclerView.LayoutManager defaultGroupManager = new GridLayoutManager(view.getContext(), ROWNUM);
        rv_default_group.setLayoutManager(defaultGroupManager);

        RecyclerView.ItemDecoration divider = new GridItemDecoration(
                (int)(view.getContext().getResources().getDimension(R.dimen.itemdecoration_default_group)),
                ROWNUM);
        rv_default_group.addItemDecoration(divider);
        rv_default_group.setAdapter(defaultGroupAdapter);

        //rv_my_group
        RecyclerView.LayoutManager myGroupManager = new GridLayoutManager(view.getContext(), ROWNUM);
        rv_my_group.setLayoutManager(myGroupManager);
        final RecyclerView.ItemDecoration myGroupDivider = new GridItemDecoration(
                (int)(view.getContext().getResources().getDimension(R.dimen.itemdecoration_default_group)),
                ROWNUM);
//        rv_my_group.addItemDecoration(myGroupDivider);
        final List<DefaultGroupBean> my_groups = new ArrayList<>();
        my_groups.addAll(default_groups);
        my_groups.addAll(default_groups);
        my_groups.add(new DefaultGroupBean(getResources().getString(R.string.title_add_group)));
        myGroupAdapter = new MyGroupAdapter(my_groups, new MyGroupAdapter.MyGroupAdapterCallback() {
            private EditText et_content;
            private TextView tv_hint_no_content;
            @Override
            public void addGroup() {
                Context context = AttentionDialogFragment.this.getContext();
                if(addGroupDialog == null) {
                    addGroupDialog = new Dialog(context, R.style.dialog);
                    View rootView = LayoutInflater.from(addGroupDialog.getContext()).inflate(R.layout.dialog_add_group, null);
                    addGroupDialog.setContentView(rootView);
                    WindowManager.LayoutParams params = addGroupDialog.getWindow().getAttributes();
                    params.width = (int)(getResources().getDimension(R.dimen.width_dialog_add_group));
                    params.height = (int)(getResources().getDimension(R.dimen.height_dialog_add_group));
                    //                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));\
                    et_content = rootView.findViewById(R.id.et_content);
                    tv_hint_no_content = rootView.findViewById(R.id.tv_hint_no_content);
                    rootView.findViewById(R.id.sure).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                             String content = et_content.getText().toString();
                            if(content.equals("")) {
                                tv_hint_no_content.setVisibility(View.VISIBLE);
                                tv_hint_no_content.setText(getResources().getString(R.string.add_group_no_content));
                            }
                            else {
                                if(my_groups.contains(new DefaultGroupBean(content))) {
                                    tv_hint_no_content.setVisibility(View.VISIBLE);
                                    tv_hint_no_content.setText(getResources().getString(R.string.group_name_exists));
                                }
                                else {
                                    my_groups.add(my_groups.size()-1, new DefaultGroupBean(content));
                                    myGroupAdapter.notifyDataSetChanged();
                                    dismissDialog();

                                }
                            }
                        }
                    });
                    rootView.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dismissDialog();
                        }
                    });
                }
                addGroupDialog.show();
            }

            private void dismissDialog() {
                addGroupDialog.dismiss();
                et_content.setText("");
                tv_hint_no_content.setVisibility(View.GONE);
            }

            @Override
            public void deleteGroup(int index) {
                my_groups.remove(index);
                myGroupAdapter.notifyDataSetChanged();
            }
        });
        rv_my_group.setAdapter(myGroupAdapter);

        //tv_edit
        tv_edit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                status = status == STATUS_EDIT ? STATUS_FINISH : STATUS_EDIT;
                boolean enable = status == STATUS_EDIT ? true: false;
                myGroupAdapter.setEnable(enable);
                defaultGroupAdapter.setEnable(enable);
                tv_edit.setText(enable ? editStr: finishStr);

                defaultGroupAdapter.notifyDataSetChanged();
                myGroupAdapter.notifyDataSetChanged();
            }
        });

    }

    public interface AttentDilaogCallback {
        void hideAttentionDialog();
    }

    public void initDialog() {
        tv_edit.setTag(STATUS_EDIT);
        tv_edit.setText(editStr);
        defaultGroupAdapter.setEnable(true);
        myGroupAdapter.setEnable(true);
    }
}
