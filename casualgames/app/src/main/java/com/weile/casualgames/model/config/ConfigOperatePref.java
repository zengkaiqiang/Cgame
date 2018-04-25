package com.weile.casualgames.model.config;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

/**
 * 通用配置文件
 * Author:wfz   用户操作信息
 * Date:2016-7-18
 */
public class ConfigOperatePref {

    public static final String NAME = "OperateConfig";
    private static Gson gson = new Gson();
    private static ConfigOperatePref ap;
    private static SharedPreferences spUserOperate;//用户帐号信息

    public static final String KEY_SIGN_IN_DATE = "SignInDate";
    public static final String KEY_CALL_MODE = "CallMode";

    private Context mContext;

    private ConfigOperatePref(Context context) {
        spUserOperate = context.getApplicationContext().getSharedPreferences(NAME, Context.MODE_PRIVATE);
        mContext = context;
    }

    public synchronized static ConfigOperatePref getInstance(Context context) {
        if (ap != null)
            return ap;

        ap = new ConfigOperatePref(context);
        return ap;
    }

    /**
     * 签到的最后一天
     */
    public String getSignInDate() {
        return spUserOperate.getString(KEY_SIGN_IN_DATE, "");
    }


    public void setSignInDate(String date) {
        spUserOperate.edit().putString(KEY_SIGN_IN_DATE, date).commit();
    }

    public long getCallModeType() {
        return spUserOperate.getLong(KEY_CALL_MODE, 0l);
    }


    public void setCallModeType(long type) {
        spUserOperate.edit().putLong(KEY_CALL_MODE, type).commit();
    }
}
