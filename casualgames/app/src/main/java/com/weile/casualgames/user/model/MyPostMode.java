package com.weile.casualgames.user.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Administrator on 2015/3/20.
 */
public class MyPostMode implements Serializable {
    public String title;
    public String titleWithGroupName;
    public String _id;//帖子id
    public String locName;
    public String createdAt;
    public String due; //消失时间
    //    public TopicUser user;
    public String hostId;
    public int readCount;
    public int spreadTimes;
    public int commentCount;
    public ArrayList<String> photos;
    public ArrayList<String> loc;
    public String maxDistance;
    public String receiveTime;
    public boolean shareLocation = true;
    public int type = 0; //帖子类型   2，实例帖子
    public boolean isPhotoFirstLoad = true;
    public UserRegisterMode.UserBean user; //用户信息
    public int followStatus = 0;
    public int score = 0; //总顶数
    public String distance;
    public boolean haveDown;
    public boolean haveUp;
    public boolean isAnonymous = false;


}
