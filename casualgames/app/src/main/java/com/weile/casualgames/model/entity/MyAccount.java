package com.weile.minigame.model.entity;

import java.io.Serializable;

/**
 * Created by zjj
 *
 */
public class MyAccount  implements Serializable{

   String  id             ; //    交易ID
   String  fee            ; //    当前剩余
    int  type           ; //    资金账户类型


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }







}
