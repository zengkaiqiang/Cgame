package com.weile.casualgames.model.entity;

import java.io.Serializable;

/**
 * Created by zjj
 * 签到
 */
public class SignIn  implements Serializable {
    String   durable; //  连续签到
    String   value;// 签到情况 按位处理
    String   total;  // 已签到多少天--当（year == 0 && month == 0）
    String   id;
    String  year;
    String  month;
    String isSigned;

    public String getIsSigned() {
        return isSigned;
    }

    public void setIsSigned(String isSigned) {
        this.isSigned = isSigned;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }


    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getDurable() {
        return durable;
    }

    public void setDurable(String durable) {
        this.durable = durable;
    }


}
