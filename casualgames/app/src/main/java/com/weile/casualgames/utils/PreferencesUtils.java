package com.weile.casualgames.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.Map;


public class PreferencesUtils {
    /**
     * 公共key类
     *
     * @author Administrator
     */

    private static SharedPreferences preferences;
    private static Editor edit;

//	/**
//	 * cn.icnt.dinners.utils.Keys
//	 * 
//	 */
//	public static class Keys {
//
//		/**
//		 * sharedPreference 文件名
//		 */
//		public static final String USERINFO = "rehuapp";
//		
//		/**
//		 * sharedPreference 文件名
//		 */
//		public static final String UUID = "device_id";
//		/**
//		 * APP_TOKEN  用户token
//		 */
//		public static final String APP_TOKEN = "app_token";
//
//	}

    /**
     * 存储布尔值
     *
     * @param mContext
     * @param key
     * @param value
     */
    public static void putBooleanToSPMap(Context mContext, String key, boolean value) {
        preferences = mContext.getSharedPreferences(AppKeys.USERINFO, Context.MODE_PRIVATE);
        edit = preferences.edit();
        edit.putBoolean(key, value);
        edit.commit();
    }

    /**
     * 获取布尔值
     *
     * @param mContext
     * @param key
     * @return
     */
    public static Boolean getBooleanFromSPMap(Context mContext, String key) {
        preferences = mContext.getSharedPreferences(AppKeys.USERINFO, Context.MODE_PRIVATE);
        boolean value = preferences.getBoolean(key, false);
        return value;
    }

    public static Boolean getBooleanFromSPMap(Context mContext, String key, boolean def) {
        preferences = mContext.getSharedPreferences(AppKeys.USERINFO, Context.MODE_PRIVATE);
        boolean value = preferences.getBoolean(key, def);
        return value;
    }

    /**
     * 存储String
     *
     * @param mContext
     * @param key
     * @param value
     */
    public static void putValueToSPMap(Context mContext, String key, String value) {
        preferences = mContext.getSharedPreferences(AppKeys.USERINFO, Context.MODE_PRIVATE);
        edit = preferences.edit();
        edit.putString(key, value);
        edit.commit();
    }

    /**
     * 存储多个String
     *
     * @param mContext
     */
    public static void putValueToSPMap(Context mContext, Map<String, String> map) {
        preferences = mContext.getSharedPreferences(AppKeys.USERINFO, Context.MODE_PRIVATE);
        edit = preferences.edit();
        String value;
        for (String key : map.keySet()) {
            value = map.get(key);
            edit.putString(key, value);
        }
        edit.commit();
    }

    /**
     * 获取String
     *
     * @param mContext
     * @param key
     * @return value
     */
    public static String getValueFromSPMap(Context mContext, String key) {
        if (null != mContext) {
            preferences = mContext.getSharedPreferences(AppKeys.USERINFO, Context.MODE_PRIVATE);
            String value = preferences.getString(key, "");
            return value;
        } else {
            return null;
        }
    }

    public static double getDoubleFromSPMap(Context mContext, String key) {
        preferences = mContext.getSharedPreferences(AppKeys.USERINFO, Context.MODE_PRIVATE);
        double value;
        value = (double) preferences.getFloat(key, 0f);
        return value;
    }

    /**
     * 获取String
     *
     * @param mContext
     * @param key
     * @param defaults 无值时取defaults
     * @return
     */

    public static String getValueFromSPMap(Context mContext, String key, String defaults) {
        if (null != mContext) {
            preferences = mContext.getSharedPreferences(AppKeys.USERINFO, Context.MODE_PRIVATE);
            String value = preferences.getString(key, defaults);
            return value;
        } else {
            return null;
        }
    }

    /**
     * 保存int
     *
     * @param mContext
     */
    public static void putIntToSPMap(Context mContext, String key , int value) {
        preferences = mContext.getSharedPreferences(AppKeys.USERINFO, Context.MODE_PRIVATE);
        edit = preferences.edit();
        edit.putInt(key, value);
        edit.commit();
    }

    /**
     * 取 int
     *
     * @param mContext
     * @param key
     * @param defaults
     * @return
     */
    public static int getIntFromSPMap(Context mContext, String key, int defaults) {
        if (null != mContext) {
            preferences = mContext.getSharedPreferences(AppKeys.USERINFO, Context.MODE_PRIVATE);
            int value = preferences.getInt(key, defaults);
            return value;
        } else {
            return 0;
        }
    }

    /**
     * 清除全部
     *
     * @param mContext
     */
    public static void clearSPMap(Context mContext) {
        preferences = mContext.getSharedPreferences(AppKeys.USERINFO, Context.MODE_PRIVATE);
        edit = preferences.edit();
        edit.clear();
        edit.commit();
    }

    /**
     * 指定key清除
     *
     * @param mContext
     * @param key
     */
    public static void clearSpMap(Context mContext, String key) {
        putValueToSPMap(mContext, key, "");
    }


}
