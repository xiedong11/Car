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

public class CarDetailActivity extends AppCompatActivity {

    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.price)
    TextView price;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_detail);
        ButterKnife.bind(this);
        Intent intent=getIntent();
        Bundle bundle = intent.getExtras();
        CarEntity entity = (CarEntity) bundle.getSerializable("entity");
        Glide.with(this).load(entity.getGoodsUrl()).into(image);
        price.setText(entity.getPrice());
        tvDesc.setText(entity.getGoodsContent());
        tvInvate.setText(entity.getInvate());
    }
}
