package com.zhuandian.car;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * desc :找回密码
 * author：xiedong
 * data：2018/3/10
 */

public class GetPasswordActivity extends AppCompatActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_right_image)
    ImageView ivRightImage;
    @BindView(R.id.tv_right_text)
    TextView tvRightText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_password);
        ButterKnife.bind(this);
        tvTitle.setText("找回密码");
    }
}
