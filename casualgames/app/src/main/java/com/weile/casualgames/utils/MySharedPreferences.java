package com.weile.casualgames.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.weile.casualgames.game.model.DoubleGame;

import java.util.ArrayList;
import java.util.List;

public class MySharedPreferences {

    private Context mContext;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    private final String Account = "Account";//账号
    private final String Uid = "Uid";//
    private final String JiFen = "JiFen";//积分
    private final String Diamond = "Diamond";//砖石
    private final String Coin = "Coin";//
    private final String Head = "Head";//头像地址
    private final String Age = "Age";//年龄
    private final String BrhYear = "BrhYear";//生日，年
    private final String BrhMonth = "BrhMonth";//生日，月
    private final String BrhDay = "BrhDay";//生日，日
    private final String Province = "Province";//地址，省
    private final String City = "City";//地址，市
    private final String Area = "Area";//地址，区
    private final String ShenJia = "ShenJia";//身价
    private final String GList="GList";//首页游戏数据

    private final String Name = "Name";//昵称
    private final String Sex = "Sex";//性别
    private final String Sign = "Sign";//签名


    private static MySharedPreferences instance = null;

    public static MySharedPreferences getInstance(Context mContext) {
        if (instance == null) {
            synchronized (MySharedPreferences.class) {
                if (instance == null) {
                    instance = new MySharedPreferences(mContext.getApplicationContext());
                }
            }
        }
        return instance;
    }

    private MySharedPreferences(Context mContext) {
        this.mContext = mContext;
        sp = mContext.getSharedPreferences("CasualGames", Context.MODE_PRIVATE);
    }

    public void initEdit() {
        editor = sp.edit();
    }

    public void applyValue() {
        editor.apply();
    }

    public void setAccount(String str) {
        editor.putString(Account, str);
    }

    public String getAccount() {
        return sp.getString(Account, "");

    }

    public void setName(String str) {
        editor.putString(Name, str);
    }

    public String getName() {
        return sp.getString(Name, "");

    }

    public void setUid(int value) {
        editor.putInt(Uid, value);
    }

    public int getUid() {
        return sp.getInt(Uid, 0);
    }

    public void setJiFen(int value) {
        editor.putInt(JiFen, value);
    }

    public int getJiFen() {
        return sp.getInt(JiFen, 0);
    }

    public void setDiamond(int value) {
        editor.putInt(Diamond, value);
    }

    public int getDiamond() {
        return sp.getInt(Diamond, 0);
    }

    public void setCoin(int value) {
        editor.putInt(Coin, value);
    }

    public int getCoin() {
        return sp.getInt(Coin, 1);
    }

    public void setHead(String value) {
        editor.putString(Head, value);
    }

    public String getHead() {
        return sp.getString(Head, "");
    }

    public void setAge(int value) {
        editor.putInt(Age, value);
    }

    public int getAge() {
        return sp.getInt(Age, 1);
    }

    public void setBrhYear(int value) {
        editor.putInt(BrhYear, value);
    }

    public int getBrhYear() {
        return sp.getInt(BrhYear, 0);
    }

    public void setBrhMonth(int value) {
        editor.putInt(BrhMonth, value);
    }

    public int getBrhMonth() {
        return sp.getInt(BrhMonth, 0);
    }

    public void setBrhDay(int value) {
        editor.putInt(BrhDay, value);
    }

    public int getBrhDay() {
        return sp.getInt(BrhDay, 0);
    }

    public void setProvince(String value) {
        editor.putString(Province, value);
    }

    public String getProvince() {
        return sp.getString(Province, "福建");
    }

    public void setCity(String value) {
        editor.putString(City, value);
    }

    public String getCity() {
        return sp.getString(City, "厦门");
    }

    public void setArea(String value) {
        editor.putString(Area, value);
    }

    public String getArea() {
        return sp.getString(Area, "思明区");
    }

    public void setShenJia(int value) {
        editor.putInt(ShenJia, value);
    }

    public int getShenJia() {
        return sp.getInt(ShenJia, 0);
    }

    public void setSex(int value) {
        editor.putInt(Sex, value);
    }

    public int getSex() {
        return sp.getInt(Sex, 1);
    }

    public void setSign(String value) {
        editor.putString(Sign, value);
    }

    public String getSign() {
        return sp.getString(Sign, "");
    }

    /**
     * 保存首页游戏List
     * @param datalist
     */
    public void setGList(List datalist) {
        if (null == datalist || datalist.size() <= 0)
            return;

        Gson gson = new Gson();
        //转换成json数据，再保存
        String strJson = gson.toJson(datalist);
        editor.clear();
        editor.putString(GList, strJson);

    }

    /**
     * 获取首页游戏List
     * @return
     */
    public List<DoubleGame> getGList() {
        List<DoubleGame> datalist=new ArrayList<>();
        String strJson = sp.getString(GList, null);
        if (null == strJson) {
            return datalist;
        }
        Gson gson = new Gson();
        datalist = gson.fromJson(strJson, new TypeToken<List<DoubleGame>>() {
        }.getType());
        return datalist;
    }
}
