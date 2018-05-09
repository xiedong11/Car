package com.zhuandian.car;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

import cn.bmob.v3.BmobUser;

/**
 * desc :启动页
 * author：xiedong
 * data：2018/5/9
 */
public class SplashActivity extends AppCompatActivity {

    private BmobUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                user = BmobUser.getCurrentUser();
                startActivity(new Intent(SplashActivity.this, user == null ? LoginActivity.class : MainActivity.class));
                finish();
            }
        }, 2000);
    }
}
