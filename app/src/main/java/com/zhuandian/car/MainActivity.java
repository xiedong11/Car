package com.zhuandian.car;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.fl_container)
    FrameLayout flContainer;
    @BindView(R.id.ll_main)
    LinearLayout llMain;
    @BindView(R.id.ll_car_shop)
    LinearLayout llCarShop;
    @BindView(R.id.ll_mine)
    LinearLayout llMine;
    @BindView(R.id.iv_home)
    ImageView ivHome;
    @BindView(R.id.tv_home)
    TextView tvHome;
    @BindView(R.id.iv_car_shop)
    ImageView ivCarShop;
    @BindView(R.id.tv_car_shop)
    TextView tvCarShop;
    @BindView(R.id.iv_mime)
    ImageView ivMime;
    @BindView(R.id.tv_mine)
    TextView tvMine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        ButterKnife.bind(this);
        initTabState(R.id.ll_main);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fl_container, new HomeFragment(), "f")
                .commit();
    }

    @OnClick({R.id.ll_main, R.id.ll_car_shop, R.id.ll_mine})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.ll_main:
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.fl_container, new HomeFragment(), "f")
                        .commit();
//                intent = new Intent(this, CarListActivity.class);
//                intent.putExtra("type", 1);
//                startActivity(intent);
                initTabState(R.id.ll_main);
                break;
            case R.id.ll_car_shop:
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.fl_container, new HomeFragment(), "f")
                        .commit();
                initTabState(R.id.ll_car_shop);
                break;

            case R.id.ll_mine:
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.fl_container, new MineFragment(), "f")
                        .commit();
                initTabState(R.id.ll_mine);
                break;
        }
    }

    /**
     * 初始化底部tab选中状态
     *
     * @param tabId
     */
    private void initTabState(int tabId) {
        setNormalState();
        switch (tabId) {
            case R.id.ll_main:
                ivHome.setImageResource(R.drawable.ic_home_select);
                tvHome.setTextColor(getResources().getColor(R.color.text_color_select));
                break;
            case R.id.ll_car_shop:
                ivCarShop.setImageResource(R.drawable.ic_car_shop_select);
                tvCarShop.setTextColor(getResources().getColor(R.color.text_color_select));
                break;
            case R.id.ll_mine:
                ivMime.setImageResource(R.drawable.ic_mine_select);
                tvMine.setTextColor(getResources().getColor(R.color.text_color_select));
                break;
        }
    }

    /**
     * 全部置为正常状态
     */
    private void setNormalState() {
        tvCarShop.setTextColor(getResources().getColor(R.color.text_color_normal));
        tvHome.setTextColor(getResources().getColor(R.color.text_color_normal));
        tvMine.setTextColor(getResources().getColor(R.color.text_color_normal));

        ivCarShop.setImageResource(R.drawable.ic_car_shop_normal);
        ivMime.setImageResource(R.drawable.ic_mine_normal);
        ivHome.setImageResource(R.drawable.ic_home_normal);
    }
}
