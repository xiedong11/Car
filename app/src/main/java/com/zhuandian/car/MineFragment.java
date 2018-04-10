package com.zhuandian.car;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhuandian.car.entity.MyUser;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.bmob.v3.BmobQuery;
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


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, null);
        ButterKnife.bind(this, view);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user",MODE_PRIVATE );
        String objectId = sharedPreferences.getString("user_id","");


        BmobQuery<MyUser> myUserBmobQuery = new BmobQuery<MyUser>();
        myUserBmobQuery.getObject(objectId, new QueryListener<MyUser>() {
            @Override
            public void done(MyUser myUser, BmobException e) {
                tvUsername.setText("真实姓名："+myUser.getUsername());
                tvInvatename.setText("邀请人姓名："+myUser.getInvateName());
            }
        });
        return view;
    }


}
