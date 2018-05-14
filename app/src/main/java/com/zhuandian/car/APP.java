package com.zhuandian.car;

import android.app.Application;

import cn.bmob.v3.Bmob;

/**
 * desc :
 * author：xiedong
 * data：2018/3/10
 */

public class APP extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化Bmob的SDK
        Bmob.initialize(this, "590b24472b9780c72d3dc53f2078aa87");
    }
}
