package com.zhuandian.car.entity;

import cn.bmob.v3.BmobUser;

/**
 * desc :用户实体类
 * author：xiedong
 * data：2018/3/10
 */

public class MyUser extends BmobUser {
    private String invateName;
    private String mima;

    public String getMima() {
        return mima;
    }

    public void setMima(String mima) {
        this.mima = mima;
    }

    public String getInvateName() {
        return invateName;
    }

    public void setInvateName(String invateName) {
        this.invateName = invateName;
    }
}
