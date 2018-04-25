package com.weile.casualgames.model.config;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.module.common.util.Base64;
import com.weile.casualgames.model.entity.UserInfo;

/**
 * 通用配置文件
 * Author:zjj   用户信息
 */
public class ConfigPreferences {

    public static final String NAME = "config";
    public static final String NAME_PLATFORM = "platform";
    private static Gson gson = new Gson();
    private static ConfigPreferences ap;
    private static SharedPreferences spUser;//用户帐号信息
    private static SharedPreferences spPlatform;//平台帐号信息

    public static final String KEY_ACCOUNT = "account";
    public static final String KEY_PSD = "psd";

    private Context mContext;

    private ConfigPreferences(Context context) {
        spUser = context.getApplicationContext().getSharedPreferences(NAME, Context.MODE_PRIVATE);
        spPlatform = context.getApplicationContext().getSharedPreferences(NAME_PLATFORM, Context.MODE_PRIVATE);
        mContext = context;
    }

    public synchronized static ConfigPreferences getInstance(Context context) {
        if (ap != null)
            return ap;

        ap = new ConfigPreferences(context);
        return ap;
    }

    /**
     * 获取账号
     *
     * @return
     */
    public String getPhone() {
        return spUser.getString(KEY_ACCOUNT, "");
    }
    public String getAccount() {
        return spUser.getString(KEY_ACCOUNT, "");
    }

    /**
     * 获取密码
     *
     * @return
     */
    public String getPassword() {
        return Base64.decrypting(spUser.getString(KEY_PSD, ""));
    }
    public void setPassword(String password) {
        spUser.edit().putString(KEY_PSD, Base64.encrypting(password)).commit();
    }
    /**
     * 保存phone、Password
     */
    public void setAccount(String phone) {
        spUser.edit().putString(KEY_ACCOUNT, phone).commit();
    }

    /**
     * 保存帐号信息
     *
     * @param userInfo
     */
    public void setUserInfo(UserInfo userInfo) {
        spPlatform.edit().putString("UserInfo", gson.toJson(userInfo)).commit();
    }
    public UserInfo getUserInfo() {
        UserInfo userInfo;
        String jsonStr=spPlatform.getString("UserInfo","");
        if (TextUtils.isEmpty(jsonStr)){
            return null;
        }
        userInfo = JSON.parseObject(jsonStr,UserInfo.class);
        return userInfo;
    }
    /**
     * 获取帐号id
     *
     * @return
     */
    public long getId() {
         UserInfo userInfo= getUserInfo();
          if(userInfo==null){
                   return  -1;
          }
        return userInfo.getId();
    }

    /**
     * 头像url
     *
     * @return
     */
    public String getHeadUrl() {
        UserInfo userInfo= getUserInfo();
        if(userInfo==null){
            return  "";
        }
        return userInfo.getHpurl();
    }

    public void setHeadUrl(String url) {
        UserInfo userInfo= getUserInfo();
        if(userInfo==null){
              userInfo=new UserInfo();
        }
        userInfo.setHpurl(url);
        setUserInfo(userInfo);
    }

    /**
     * 昵称
     *
     * @return
     */
    public String getNickName() {
        UserInfo userInfo= getUserInfo();
        if(userInfo==null){
            return  "";
        }
        return userInfo.getNickname();
    }
    public void setNickName(String nickName) {
        UserInfo userInfo= getUserInfo();
        if(userInfo==null){
            userInfo=new UserInfo();
        }
        userInfo.setNickname(nickName);
        setUserInfo(userInfo);
    }


//    public void setBalanceAccountInfo(com.weile.casualgames.model.entity.MyAccount account) {
//        spPlatform.edit().putString("BalanceAccount", gson.toJson(account)).commit();
//    }
//    public com.weile.casualgames.model.entity.MyAccount getBalanceAccountInfo() {
//        String jsonStr=spPlatform.getString("BalanceAccount","");
//        if (TextUtils.isEmpty(jsonStr)){
//            return null;
//        }
//        com.weile.casualgames.model.entity.MyAccount account = JSON.parseObject(jsonStr, com.weile.minigame.model.entity.MyAccount.class);
//        return account;
//    }
//    public void setProfitAccountInfo(com.weile.casualgames.model.entity.MyAccount account) {
//        spPlatform.edit().putString("ProfitAccount", gson.toJson(account)).commit();
//    }
//    public com.weile.casualgames.model.entity.MyAccount getProfitAccountInfo() {
//        String jsonStr=spPlatform.getString("ProfitAccount","");
//        if (TextUtils.isEmpty(jsonStr)){
//            return null;
//        }
//        com.weile.casualgames.model.entity.MyAccount account = JSON.parseObject(jsonStr, com.weile.minigame.model.entity.MyAccount.class);
//        return account;
//    }
}
