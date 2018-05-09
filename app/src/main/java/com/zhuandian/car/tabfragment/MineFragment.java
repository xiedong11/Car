package com.zhuandian.car.tabfragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhuandian.car.LoginActivity;
import com.zhuandian.car.R;
import com.zhuandian.car.entity.MyUser;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;

import static android.content.Context.MODE_PRIVATE;

/**
 * desc :
 * author：xiedong
 * data：2018/3/10
 */

public class MineFragment extends Fragment {
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.tv_invatename)
    TextView tvInvatename;
    @BindView(R.id.tv_logout)
    TextView tvLogout;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_right_image)
    ImageView ivRightImage;
    @BindView(R.id.tv_right_text)
    TextView tvRightText;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, null);
        ButterKnife.bind(this, view);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user", MODE_PRIVATE);
        String objectId = sharedPreferences.getString("user_id", "");


        tvTitle.setText("个人中心");
        BmobQuery<MyUser> myUserBmobQuery = new BmobQuery<MyUser>();
        myUserBmobQuery.getObject(objectId, new QueryListener<MyUser>() {
            @Override
            public void done(MyUser myUser, BmobException e) {
                tvUsername.setText("用户：" + myUser.getUsername());
                tvInvatename.setText("邀请人：" + myUser.getInvateName());
            }
        });
        return view;
    }


    @OnClick(R.id.tv_logout)
    public void onViewClicked() {
        startActivity(new Intent(getActivity(), LoginActivity.class));
        BmobUser.logOut();
        getActivity().finish();
    }

}
