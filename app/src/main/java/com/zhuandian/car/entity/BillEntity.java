package com.zhuandian.car.entity;

import cn.bmob.v3.BmobObject;

/**
 * desc :报单实体类
 * author：xiedong
 * data：2018/3/10
 */

public class BillEntity extends BmobObject {
    private String name;
    private String invateName;
    private String carPrice;
    private String carInfo;
    private String carTitle;
    private String phone;
    private String carName;

    public String getCarTitle() {
        return carTitle;
    }

    public void setCarTitle(String carTitle) {
        this.carTitle = carTitle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInvateName() {
        return invateName;
    }

    public void setInvateName(String invateName) {
        this.invateName = invateName;
    }

    public String getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(String carPrice) {
        this.carPrice = carPrice;
    }

    public String getCarInfo() {
        return carInfo;
    }

    public void setCarInfo(String carInfo) {
        this.carInfo = carInfo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }
}
