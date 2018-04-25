package com.weile.casualgames.mine.util;

import android.content.Context;

import com.weile.casualgames.App;
import com.weile.casualgames.mine.model.EditUserInfo;

public class UserDataManager {

    //-单例模式 ------------------------------------------------------------------------------------
    private static UserDataManager instance;
    private Context mContext;

    private App app;

    private EditUserInfo editUserInfo;

    public static UserDataManager getInstance(Context context) {
        if (instance == null) {
            synchronized (UserDataManager.class) {
                if (instance == null) {
                    instance = new UserDataManager(context.getApplicationContext());
                }
            }
        }
        return instance;
    }

    private UserDataManager(Context context) {
        this.mContext = context;
        app = (App) context.getApplicationContext(); // 获得Application对象
    }

    /**
     * 获取本地缓存的——用户编辑数据
     * */
    public EditUserInfo  getEditUserInfo()
    {
        editUserInfo = new EditUserInfo();
        editUserInfo.setUid(app.getMySharedPreferences().getUid());
        editUserInfo.setName(app.getMySharedPreferences().getName());
        editUserInfo.setSex(app.getMySharedPreferences().getSex());
        editUserInfo.setAge(app.getMySharedPreferences().getAge());
        editUserInfo.setProvince(app.getMySharedPreferences().getProvince());
        editUserInfo.setCity(app.getMySharedPreferences().getCity());
        editUserInfo.setArea(app.getMySharedPreferences().getArea());
        editUserInfo.setSign(app.getMySharedPreferences().getSign());
        editUserInfo.setShenJia(app.getMySharedPreferences().getShenJia());
        editUserInfo.setBrhYear(app.getMySharedPreferences().getBrhYear());
        editUserInfo.setBrhMonth(app.getMySharedPreferences().getBrhMonth());
        editUserInfo.setBrhDay(app.getMySharedPreferences().getBrhDay());

        return editUserInfo;
    }

    /**
     * 通过出生日期计算年龄
     */
    public void yearToAge(EditUserInfo editUserInfos,String nowDate, String birthDate) {
        int nowYear = 0;
        int nowMonth = 0;
        int nowDay = 0;
        editUserInfos.setBrhYear(0);
        editUserInfos.setBrhMonth(0);
        editUserInfos.setBrhDay(0);
        editUserInfos.setAge(0);
        if (nowDate.length() == 10 && birthDate.length() == 10) {
            nowYear = Integer.parseInt(nowDate.substring(0, 4));
            nowMonth = Integer.parseInt(nowDate.substring(5, 7));
            nowDay = Integer.parseInt(nowDate.substring(8, 10));
            editUserInfos.setBrhYear(Integer.parseInt(birthDate.substring(0, 4)));
            editUserInfos.setBrhMonth(Integer.parseInt(birthDate.substring(5, 7)));
            editUserInfos.setBrhDay(Integer.parseInt(birthDate.substring(8, 10)));
            editUserInfos.setAge(nowYear - editUserInfos.getBrhYear());
            if (nowYear == editUserInfos.getBrhYear()) {
                editUserInfos.setAge(0);
                return ;

            }


            if (nowMonth > editUserInfos.getBrhMonth() || (nowMonth == editUserInfos.getBrhMonth() && nowDay >= editUserInfos.getBrhDay()))
                ;
            else
                editUserInfos.setAge(editUserInfos.getAge() - 1);
        }

    }

}
