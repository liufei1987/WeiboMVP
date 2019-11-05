package com.liufeismart.weibo.me.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.liufeismart.weibo.R;
import com.liufeismart.weibo.base.BaseFragment;
import com.liufeismart.weibo.bean.UserInfoBean;
import com.liufeismart.weibo.bean.test.ServiceBeanTest;
import com.liufeismart.weibo.bean.test.UserBeanTest;
import com.liufeismart.weibo.image.ImageUtil;
import com.liufeismart.weibo.login.util.LoginUtil;
import com.liufeismart.weibo.me.presenter.UserCountInfoPresenter;
import com.liufeismart.weibo.me.presenter.UserCountInfoPresenterImpl;
import com.liufeismart.weibo.me.presenter.UserInfoPresenter;
import com.liufeismart.weibo.me.presenter.UserInfoPresenterImpl;
import com.liufeismart.weibo.me.presenter.UserOftenVisitPresenter;
import com.liufeismart.weibo.me.presenter.UserOftenVisitPresenterImpl;
import com.liufeismart.weibo.me.view.UserCountInfoView;
import com.liufeismart.weibo.me.view.UserInfoView;
import com.liufeismart.weibo.me.view.UserOftenVisitView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MeFragment extends BaseFragment implements UserInfoView, UserCountInfoView, UserOftenVisitView {

    RecyclerView rlv_service;
    RecyclerView rlv_often_visit;
    ImageView iv_portrait;
    TextView tv_username;
    TextView tv_info;
    TextView tv_weibo_count;
    TextView tv_follow_count;
    TextView tv_follower_count;

    UserInfoPresenter userInfoPresenter;
    UserCountInfoPresenter userCountInfoPresenter;
    UserOftenVisitPresenter userOftenVisitPresenter;

    private int[] icons_weiboservice = {R.drawable.icon_albums, R.drawable.icon_my_story,
                R.drawable.icon_likes, R.drawable.icon_fans_service,
                R.drawable.icon_weibo_wallet, R.drawable.icon_weibo_store,
                R.drawable.icon_fanstop, R.drawable.icon_help};

    private int[] titles_weiboservice = {R.string.albums, R.string.my_story,
            R.string.likes, R.string.fans_service,
            R.string.weibo_wallet, R.string.weibo_store,
            R.string.fanstop, R.string.help
    };
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        weiboServiceView();
        userInfoPresenter = new UserInfoPresenterImpl(this);
        userInfoPresenter.loadData();
        userCountInfoPresenter = new UserCountInfoPresenterImpl(this);
        String uidStr = LoginUtil.getInstance().mAccessToken.getUid();
        long uidL = Long.parseLong(uidStr);
        userCountInfoPresenter.loadUserCountInfo(uidStr);
        userOftenVisitPresenter = new UserOftenVisitPresenterImpl(this);
        userOftenVisitPresenter.loadUserOftenVisit(uidL);
    }



    @Override
    protected void initView(View view) {
        rlv_service = view.findViewById(R.id.rlv_service);
        GridLayoutManager layoutManager = new GridLayoutManager(this.getActivity(), 4);
        rlv_service.setLayoutManager(layoutManager);
        rlv_often_visit = view.findViewById(R.id.rlv_often_visit);
        LinearLayoutManager layoutManager_rlv_often_visit = new LinearLayoutManager(this.getActivity());
        layoutManager_rlv_often_visit.setOrientation(LinearLayoutManager.HORIZONTAL);
        rlv_often_visit.setLayoutManager(layoutManager_rlv_often_visit);
        //UserInfo
        iv_portrait = view.findViewById(R.id.iv_portrait);
        tv_username = view.findViewById(R.id.tv_username);
        tv_info = view.findViewById(R.id.tv_info);
        tv_weibo_count = view.findViewById(R.id.tv_weibo_count);
        tv_follow_count = view.findViewById(R.id.tv_follow_count);
        tv_follower_count = view.findViewById(R.id.tv_follower_count);

    }

    @Override
    public void showUserInfo(UserInfoBean userInfoBean) {
        ImageUtil.getInstance().setPortrait(iv_portrait, userInfoBean.getProfile_image_url());
        tv_username.setText(userInfoBean.getName());
        String desc = userInfoBean.getDescription();
        if(desc.equals("")) {
            desc = getResources().getString(R.string.no_user_desc);
        }
        tv_info.setText(desc);
        tv_weibo_count.setText(Long.toString(userInfoBean.getStatuses_count()));
        tv_follow_count.setText(Long.toString(userInfoBean.getFriends_count()));
        tv_follower_count.setText(Long.toString(userInfoBean.getFollowers_count()));
    }

    @Override
    public void showUserCountInfo(List<UserInfoBean> bean) {

    }

    @Override
    public void showUserOftenVisit(List<UserInfoBean> result) {
        rlv_often_visit.setAdapter(new UserAdapter(result));
    }


    class ServiceAdapter extends RecyclerView.Adapter<ServiceViewHolder> {


        @NonNull
        @Override
        public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view  = LayoutInflater.from(MeFragment.this.getContext()).inflate(R.layout.item_service_fragment_me,null);
            ServiceViewHolder viewholder = new ServiceViewHolder(view);
            return viewholder;
        }

        @Override
        public void onBindViewHolder(@NonNull ServiceViewHolder holder, int i) {
            holder.iv_service_img.setImageResource(icons_weiboservice[i]);
            holder.tv_service_name.setText(titles_weiboservice[i]);
        }


        @Override
        public int getItemCount() {
            return icons_weiboservice.length;
        }
    }

    class ServiceViewHolder extends RecyclerView.ViewHolder {

        TextView tv_service_name;
        ImageView iv_service_img;

        public ServiceViewHolder(View view) {
            super(view);
            tv_service_name = (TextView)view.findViewById(R.id.tv_service_name);
            iv_service_img = (ImageView)view.findViewById(R.id.iv_service_img);
        }
    }


    class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {
        List<UserInfoBean> users;

        UserAdapter(List<UserInfoBean> users) {
            this.users = users;
        }

        @NonNull
        @Override
        public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(MeFragment.this.getActivity())
                    .inflate(R.layout.item_user_often_visit_fragment_me, null);
            UserViewHolder holder = new UserViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull UserViewHolder viewholder, int i) {
            UserInfoBean bean = users.get(i);
            ImageUtil.getInstance().setPortrait(viewholder.iv_user_img, bean.getProfile_image_url());
            viewholder.tv_user_name.setText(bean.getName());
        }

        @Override
        public int getItemCount() {
            return users.size();
        }
    }

    class UserViewHolder extends RecyclerView.ViewHolder {
        TextView tv_user_name;
        ImageView iv_user_img;

        public UserViewHolder(View view) {
            super(view);
            tv_user_name = view.findViewById(R.id.tv_user_name);
            iv_user_img = view.findViewById(R.id.iv_user_img);
        }
    }


    private void weiboServiceView() {
        rlv_service.setAdapter(new ServiceAdapter());
    }
    private void test() {
//        List<ServiceBeanTest> services = new ArrayList<>();
//        for(int i=0; i<8; i++ ) {
//            ServiceBeanTest service = new ServiceBeanTest();
//            service.setImage(R.drawable.btn_find_user);
//            service.setName("Fan Service");
//            services.add(service);
//        }
//        rlv_service.setAdapter(new ServiceAdapter(services));
//
//        List<UserBeanTest> users = new ArrayList<>();
//        for(int i=0; i<5; i++ ) {
//            UserBeanTest userBean = new UserBeanTest();
//            userBean.setUserName("femaleteacherLi");
//            users.add(userBean);
//        }
//        rlv_often_visit.setAdapter(new UserAdapter(users));

    }
}
