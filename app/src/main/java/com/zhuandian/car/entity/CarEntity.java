package com.zhuandian.car.entity;

import android.graphics.Bitmap;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

/**
 * Created by xiedong
 */
public class CarEntity extends BmobObject implements Serializable{

    private String goodsUrl;
    private String goodsTiltle;
    private String goodsContent;
    private Bitmap goodsBitmap;
    private String name;
    private String phone;
    private String invate;

    public String getInvate() {
        return invate;
    }

    public void setInvate(String invate) {
        this.invate = invate;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    private String price;


    public String getGoodsUrl() {
        return goodsUrl;
    }

    public void setGoodsUrl(String goodsUrl) {
        this.goodsUrl = goodsUrl;
    }

    public String getGoodsTiltle() {
        return goodsTiltle;
    }

    public void setGoodsTiltle(String goodsTiltle) {
        this.goodsTiltle = goodsTiltle;
    }

    public String getGoodsContent() {
        return goodsContent;
    }

    public void setGoodsContent(String goodsContent) {
        this.goodsContent = goodsContent;
    }

    public Bitmap getGoodsBitmap() {
        return goodsBitmap;
    }

    public void setGoodsBitmap(Bitmap goodsBitmap) {
        this.goodsBitmap = goodsBitmap;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
