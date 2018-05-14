package com.zhuandian.car;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.zhuandian.car.entity.BillEntity;
import com.zhuandian.car.entity.CarEntity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

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
    TextView tvSellerName;
    @BindView(R.id.phone)
    TextView tvSellerPhone;
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
    private String carInfo;
    private String invateName;
    private String invate;
    private String carPrice;
    private String name;
    private String sellerPhone;
    private String carTitle;

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
        carInfo = entity.getGoodsContent();
        invateName = entity.getInvate();
        sellerPhone = entity.getPhone();
        carPrice = entity.getPrice();
        name = entity.getName();
        carTitle = entity.getGoodsTiltle();

        tvDesc.setText(carInfo);
        tvInvate.setText(invateName);
        tvPrice.setText(carPrice + " 元");
        tvSellerName.setText(name);
        tvSellerPhone.setText(sellerPhone);
    }

    @OnClick(R.id.commit)
    public void onViewClicked() {
        BillEntity billEntity = new BillEntity();
        billEntity.setCarInfo(carInfo);
        billEntity.setCarName(name);
        billEntity.setCarPrice(carPrice);
        billEntity.setInvateName(invateName);
        billEntity.setPhone(sellerPhone);

        billEntity.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null) {
                    Toast.makeText(CarDetailActivity.this, "已成功报单，请等待管理员联系您....", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
