package com.weile.casualgames.model.entity;

import java.io.Serializable;

/**
 * Created by zjj
 *
 */
public class BaseEntity implements Serializable{
    String  id             ; //    交易ID
    public String getId() {
        return id;
    }



    public void setId(String id) {
        this.id = id;
    }







}
