package com.zhuandian.car;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChoseCarActivity extends AppCompatActivity {

    @BindView(R.id.tv_upload)
    TextView tvUpload;
    @BindView(R.id.tv_new_list)
    TextView tvNewList;
    @BindView(R.id.tv_old_list)
    TextView tvOldList;
    @BindView(R.id.fl_container)
    FrameLayout flContainer;
    @BindView(R.id.tv_mine)
    TextView tvMine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chose_car);
        ButterKnife.bind(this);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fl_container, new CarListFragment(), "f")
                .commit();
    }

    @OnClick({R.id.tv_upload, R.id.tv_new_list, R.id.tv_old_list,R.id.tv_mine})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.tv_upload:
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.fl_container, new UploadCarFragment(), "f")
                        .commit();
//                intent = new Intent(this, CarListActivity.class);
//                intent.putExtra("type", 1);
//                startActivity(intent);
                break;
            case R.id.tv_new_list:
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.fl_container, new CarListFragment(), "f")
                        .commit();
                break;
            case R.id.tv_old_list:
                break;
            case R.id.tv_mine:
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.fl_container, new MineFragment(), "f")
                        .commit();
                break;
        }
    }
}
