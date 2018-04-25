package com.weile.casualgames.login.model;

public class UserInfo {

    private int Style;//登录方式：0：手机，1：微信，2：qq

    private String Account;     //账号
    private String Pwd;         //密码
    private String Name;       //名字
    private int Sex;           //性别：0：男，1：女
    private String Head;       //头像地址
    private int Age;           //年龄
    private int BrhYear;       //生日，年
    private int BrhMonth;      //生日，月
    private int BrhDay;        //生日，日
    private String Province;   //地址，省
    private String City;       //地址，市
    private String Area;       //地址，区
    private Float SatX;        //经纬度
    private Float SatY;        //经纬度


    private int Num;           //送花数
    private int Lv;            //积分

    public UserInfo() {
        Style=2;
        Account = "1";
        Pwd = "1";
        Name = "1";
        Sex = 1;
        Head = "1";
        Age = 1;
        BrhYear = 1;
        BrhMonth = 1;
        BrhDay = 1;
        Province = "1";
        City = "1";
        Area = "1";
        SatX = 1.0f;
        SatY = 1.0f;
    }

    public int getStyle() {
        return Style;
    }

    public void setStyle(int style) {
        Style = style;
    }


    public String getAccount() {
        return Account;
    }

    public void setAccount(String account) {
        Account = account;
    }

    public String getPwd() {
        return Pwd;
    }

    public void setPwd(String pwd) {
        Pwd = pwd;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getSex() {
        return Sex;
    }

    public void setSex(int sex) {
        Sex = sex;
    }

    public String getHead() {
        return Head;
    }

    public void setHead(String head) {
        Head = head;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public int getBrhYear() {
        return BrhYear;
    }

    public void setBrhYear(int brhYear) {
        BrhYear = brhYear;
    }

    public int getBrhMonth() {
        return BrhMonth;
    }

    public void setBrhMonth(int brhMonth) {
        BrhMonth = brhMonth;
    }

    public int getBrhDay() {
        return BrhDay;
    }

    public void setBrhDay(int brhDay) {
        BrhDay = brhDay;
    }

    public String getProvince() {
        return Province;
    }

    public void setProvince(String province) {
        Province = province;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getArea() {
        return Area;
    }

    public void setArea(String area) {
        Area = area;
    }

    public Float getSatX() {
        return SatX;
    }

    public void setSatX(Float satX) {
        SatX = satX;
    }

    public Float getSatY() {
        return SatY;
    }

    public void setSatY(Float satY) {
        SatY = satY;
    }

    public void setNum(int num) {
        Num = num;
    }

    public int getNum() {
        return Num;
    }

    public void setLv(int lv) {
        Lv = lv;
    }

    public int getLv() {
        return Lv;
    }


    @Override
    public String toString() {
        return "UserInfo{" +
                "Style=" + Style +
                ", Account='" + Account + '\'' +
                ", Pwd='" + Pwd + '\'' +
                ", Name='" + Name + '\'' +
                ", Sex=" + Sex +
                ", Head='" + Head + '\'' +
                ", Age=" + Age +
                ", BrhYear=" + BrhYear +
                ", BrhMonth=" + BrhMonth +
                ", BrhDay=" + BrhDay +
                ", Province='" + Province + '\'' +
                ", City='" + City + '\'' +
                ", Area='" + Area + '\'' +
                ", SatX=" + SatX +
                ", SatY=" + SatY +
                '}';
    }
}
