package com.weile.casualgames.model.entity;

import java.io.Serializable;

/**
 * Created zjj
 * 套餐
 */
public class Scheme implements Serializable{

    String id;
    String rawprice;//  原价
    String discountprice;//  折扣后价格

    String duration;//时长分钟数
    String hot;
    public String getHot() {
        return hot;
    }

    public void setHot(String hot) {
        this.hot = hot;
    }


    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRawprice() {
        return rawprice;
    }

    public void setRawprice(String rawprice) {
        this.rawprice = rawprice;
    }

    public String getDiscountprice() {
        return discountprice;
    }

    public void setDiscountprice(String discountprice) {
        this.discountprice = discountprice;
    }




}
