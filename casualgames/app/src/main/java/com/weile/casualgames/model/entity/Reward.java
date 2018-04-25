package com.weile.casualgames.model.entity;

import java.io.Serializable;

/**
 * Created by zjj
 * 任务奖励
 */
public class Reward implements Serializable {
    long   id;
    String   maximum;
    String   reward;
    String  source;
    int  balanceType;
    public int getBalanceType() {
        return balanceType;
    }

    public void setBalanceType(int balanceType) {
        this.balanceType = balanceType;
    }


    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMaximum() {
        return maximum;
    }

    public void setMaximum(String maximum) {
        this.maximum = maximum;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }



}
