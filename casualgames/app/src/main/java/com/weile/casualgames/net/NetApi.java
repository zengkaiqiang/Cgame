package com.weile.casualgames.net;

/**
 * netApi
 */
public class NetApi {

    public static int NetStatus = 0;
    //    public static String ALiCloud = "http://121.42.143.55:8080/1.7"; // 0   new 1.61
//    public static String ALiCloud = "http://clover.myvnc.com:8000/2.0"; // 0   new 2.0
    public static String ALiCloud = "http://39.105.35.10:9100"; // 0   new 2.0
    //http://clover.myvnc.com
    public static String UCloud = "http://39.105.35.10:9100"; // 1
    public static String Debug = "http://39.105.35.10:9100"; // 2
    public static String Tx = "http://39.105.35.10:9100"; // 3
    // http://121.42.143.55:8000/1.0 //ALiCloud
    // http://123.59.57.113/1.0 // UCloud
    public static String Url = NetStatus == 0 ? ALiCloud
            : NetStatus == 1 ? UCloud : NetStatus == 2 ? Debug : Tx;

    public static String GET_TODAY_FLOWER = Url.concat("/get_today_flower"); //获取今日鲜花排行榜
    public static String GET_YESTERDAY_FLOWER = Url.concat("/get_yestoday_flower"); //获取昨日鲜花排行榜
    public static String GET_CELEBRITY_LIST = Url.concat("/get_mingren"); //获取昨日鲜花排行榜
    public static String RANDOM_MATCHING = Url.concat("/pipei_rand"); //随机匹配
    public static String RANDOM_MATCHING_RESULT = Url.concat("/pipei_result"); //随机匹配结果

    public static String UPLOAD_USER_HEAD = Url.concat("/modify_user_head");

//    public static String UPLOAD_Head = "http://192.168.67.71:9001/headimg/"; //上传头像到
//   public static String UPLOAD_Head = "http://192.168.67.71:9001/headImg"; //上传头像到
    public static String UPLOAD_Head = "http://192.168.67.71:9001/UploadHeadImg"; //上传头像到

//public static String UPLOAD_Head = "http://39.105.35.10:9001/headImg"; //上传头像到
}
