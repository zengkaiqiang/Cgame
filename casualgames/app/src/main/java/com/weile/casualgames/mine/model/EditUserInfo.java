package com.weile.casualgames.mine.model;

public class EditUserInfo {


    private int Uid;     //
    private String Name;       //名字
    private int Sex;           //性别：0：男，1：女
    private int Age;           //年龄
    private int BrhYear;       //生日，年
    private int BrhMonth;      //生日，月
    private int BrhDay;        //生日，日
    private String Province;   //地址，省
    private String City;       //地址，市
    private String Area;       //地址，区
    private String Sign;       //签名
    private int ShenJia;           //身价

    public EditUserInfo() {
        Uid = 1;
        Name = "1";
        Sex = 1;
        Age = 1;
        BrhYear = 1;
        BrhMonth = 1;
        BrhDay = 1;
        Province = "福建";
        City = "厦门";
        Area = "思明区";
        Sign = "";
        ShenJia = 1;
    }

    public int getUid() {
        return Uid;
    }

    public void setUid(int uid) {
        Uid = uid;
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
        if (City != null && City.equals("1"))
            return "";
        else
            return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getArea() {
        if (Area != null && Area.equals("1"))
            return "";
        else
            return Area;
    }

    public void setArea(String area) {
        Area = area;
    }

    public String getSign() {
        return Sign;
    }

    public void setSign(String sign) {
        Sign = sign;
    }

    public int getShenJia() {
        return ShenJia;
    }

    public void setShenJia(int shenJia) {
        ShenJia = shenJia;
    }

    @Override
    public String toString() {
        return "EditUserInfo{" +
                "Uid=" + Uid +
                ", Name='" + Name + '\'' +
                ", Sex=" + Sex +
                ", Age=" + Age +
                ", BrhYear=" + BrhYear +
                ", BrhMonth=" + BrhMonth +
                ", BrhDay=" + BrhDay +
                ", Province='" + Province + '\'' +
                ", City='" + City + '\'' +
                ", Area='" + Area + '\'' +
                ", Sign='" + Sign + '\'' +
                ", ShenJia=" + ShenJia +
                '}';
    }
}
