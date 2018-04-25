package com.weile.casualgames.model.entity;

import java.io.Serializable;

/**
 * Created by wfz zjj
 */
public class UserInfo implements Serializable {
    private long id;
    private String mobile;
    private String nickname;
    private int sex;
    private String region;
    private String hpurl;
    private String create_time;
    private String update_time;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getHpurl() {
        return hpurl;
    }

    public void setHpurl(String hpurl) {
        this.hpurl = hpurl;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }
}
