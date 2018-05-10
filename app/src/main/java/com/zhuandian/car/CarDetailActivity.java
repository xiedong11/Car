package com.zhuandian.car;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhuandian.car.entity.CarEntity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * desc :详细信息页
 * author：xiedong
 * data：2018/3/10
 */
public class CarDetailActivity extends AppCompatActivity {

    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.tv_desc)
    TextView tvDesc;
    @BindView(R.id.tv_invate)
    TextView tvInvate;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.maijianame)
    TextView maijianame;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.commit)
    TextView commit;
    @BindView(R.id.call_me)
    LinearLayout callMe;
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
        setContentView(R.layout.activity_car_detail);
        ButterKnife.bind(this);
        tvTitle.setText("详细信息");
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        CarEntity entity = (CarEntity) bundle.getSerializable("entity");
        Glide.with(this).load(entity.getGoodsUrl()).error(R.drawable.ic_banner_three).into(image);
//        price.setText(entity.getPrice());
        tvDesc.setText(entity.getGoodsContent());
        tvInvate.setText(entity.getInvate());
    }
}
