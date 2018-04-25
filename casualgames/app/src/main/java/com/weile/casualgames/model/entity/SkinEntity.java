package com.weile.casualgames.model.entity;

import java.io.Serializable;

/**
 * Created by zjj
 * 换肤
 *
 */
public class SkinEntity implements Serializable{


    int  num;
    String chineseName;
    String  color;
    String  name;

    public SkinEntity(int num, String chineseName, String color, String name) {
        this.num = num;
        this.chineseName = chineseName;
        this.color = color;
        this.name = name;
    }
    public SkinEntity(String chineseName, String color, String name) {
        this.chineseName = chineseName;
        this.color = color;
        this.name = name;
    }
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

}
