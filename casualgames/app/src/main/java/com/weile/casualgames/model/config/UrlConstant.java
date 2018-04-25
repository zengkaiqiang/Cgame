package com.weile.casualgames.model.config;

public class UrlConstant {

    public static String BaseURL = "http://192.168.67.71:9100";

//    public static String BaseURL = "http://www.baidu.com";
    public static final String AUTH_CODE = BaseURL + "fetchAuthCode";
    // 获取验证码
    public static final String VERIFY = BaseURL + "fetchCodeSafe";

    // 注册
    public static final String REGISTER = BaseURL + "create";

    // 忘记密码
    public static final String FORGET_PASSWORD = BaseURL + "forgetPassword";

    // 登录
    public static final String LOGIN = BaseURL + "login";

    // 修改昵称
    public static final String CHANGE_NICKNAME = BaseURL + "setNickname";

    // 获取今日鲜花排行榜
    public static final String GET_TODAY_FLOWER = BaseURL + "/get_today_flower";

    // 获取昨日鲜花排行榜
    public static final String GET_YESTERDAY_FLOWER = BaseURL + "/get_yesterday_flower";




}
