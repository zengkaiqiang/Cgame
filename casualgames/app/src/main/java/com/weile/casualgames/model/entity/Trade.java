package com.weile.casualgames.model.entity;

/**
 * Created by zjj
 *
 */
public class Trade extends BaseEntity{


   String  id             ; //    交易ID
    long  accountId     ; //    用户id
   String  balanceId     ; //    虚拟资金账号ID
   String  fee            ; //    交易金额
   String  curfee         ; //    剩余金额
   int   operateType   ; //    操作类型  1：收入  2：支出
   String  operateMode   ; //    操作方式  当操作类型为1时：0：系统 赠送 1：支付宝 充值 2：微信 充值  3： 应用内  充值  4： 转账收入  5：分润转化  6：签到 赠送 7：分享 赠送  当操作类型为2时： 1：直拨 消费 2：回拨 消费 4：转账 支出
   String  relationId    ; //    关联ID
   String  memo           ; //    备注
   String  createtime    ; //    日期
   String  updatetime    ; //    日期

    long    payerId       ; //    支付用户
    String  account        ; //    账户
    String  nickname       ; //    昵称
    String  sex            ; //    性别
    String  hpurl          ; //    头像URL
    String  type           ; //    用户类型
    public long getPayerId() {
        return payerId;
    }

    public void setPayerId(long payerId) {
        this.payerId = payerId;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public String getBalanceId() {
        return balanceId;
    }

    public void setBalanceId(String balanceId) {
        this.balanceId = balanceId;
    }

    public int getOperateType() {
        return operateType;
    }

    public void setOperateType(int operateType) {
        this.operateType = operateType;
    }

    public String getOperateMode() {
        return operateMode;
    }

    public void setOperateMode(String operateMode) {
        this.operateMode = operateMode;
    }

    public String getRelationId() {
        return relationId;
    }

    public void setRelationId(String relationId) {
        this.relationId = relationId;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

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

    public String getCurfee() {
        return curfee;
    }

    public void setCurfee(String curfee) {
        this.curfee = curfee;
    }



    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }



    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHpurl() {
        return hpurl;
    }

    public void setHpurl(String hpurl) {
        this.hpurl = hpurl;
    }






}
