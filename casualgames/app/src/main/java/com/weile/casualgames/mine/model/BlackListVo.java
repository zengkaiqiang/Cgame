package com.weile.casualgames.mine.model;

public class BlackListVo {

    private String head;
    private String name;
    private boolean isSelect;
    public String createdAt;

    public BlackListVo()
    {
        head="";
        name="";
        isSelect=true;
        createdAt=String.valueOf(System.currentTimeMillis());
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    @Override
    public String toString() {
        return "BlackListVo{" +
                "head='" + head + '\'' +
                ", name='" + name + '\'' +
                ", isSelect=" + isSelect +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }
}
