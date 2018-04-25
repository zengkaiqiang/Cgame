package com.weile.casualgames.mine.model;

public class FansVo {

    private String Head;
    private String Name;       //名字
    private String sign;//个性签名
    private boolean isFollow;

    public FansVo() {

    }

    public FansVo(String Name, String sign, boolean isFollow) {
        this.Name = Name;
        this.sign = sign;
        this.isFollow = isFollow;
    }

    public String getHead() {
        return Head;
    }

    public void setHead(String head) {
        Head = head;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public boolean isFollow() {
        return isFollow;
    }

    public void setFollow(boolean follow) {
        isFollow = follow;
    }

    @Override
    public String toString() {
        return "FansVo{" +
                "Head='" + Head + '\'' +
                ", Name='" + Name + '\'' +
                ", sign='" + sign + '\'' +
                ", isFollow=" + isFollow +
                '}';
    }
}
