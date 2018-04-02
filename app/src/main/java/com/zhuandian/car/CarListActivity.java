package com.zhuandian.car;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CarListActivity extends AppCompatActivity {

    @BindView(R.id.fl_container)
    FrameLayout flContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_list);
        ButterKnife.bind(this);
        int type =(int) getIntent().getExtras().get("type");
       if (type==1){
           getSupportFragmentManager()
                   .beginTransaction()
                   .add(R.id.fl_container,new UploadCarFragment(), "f")
                   .commit();
       }else if (type==2){
           getSupportFragmentManager()
                   .beginTransaction()
                   .add(R.id.fl_container,new CarListFragment(), "f")
                   .commit();
       }

    }
}
