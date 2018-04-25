package com.weile.casualgames.user.model;

import java.io.Serializable;

public class UserRegisterMode implements Serializable {
    public String token;
    public String userId;  //1.5
    public UserBean user;
    public int followStatus;  //关注状态
    public boolean isNewUser; //是否新用户

    public class UserBean implements Serializable {

        public UserProFile profile;
        public int gradeCount;//等级
        public int beSpreadedTimes;//被赞数
        public int topicCount;//发帖数
        public String createdAt;//注册时间
        public String locName;//最后活跃位置
        public int loginType;//登录方式
        public int followingsNum; //关注数
        public int followersNum;  //粉丝数
        public int followStatus;  //关注状态
        public int canPublishCountToday;
        public float rebiCount;  //热币数 数值类型
        public float moneyCount;  //金钱 数值类型
        public String _id;

        @Override
        public String toString() {
            return "UserBean{" +
                    "id='" + _id + '\'' +
                    ", profile=" + profile +
                    ", gradeCount=" + gradeCount +
                    ", beSpreadedTimes=" + beSpreadedTimes +
                    ", topicCount=" + topicCount +
                    ", createdAt='" + createdAt + '\'' +
                    ", loginType=" + loginType + '\'' +
                    ", followingsNum=" + followingsNum + '\'' +
                    ", followersNum=" + followersNum + '\'' +
                    ", followStatus=" + followStatus + '\'' +
                    ", rebiCount=" + rebiCount + '\'' +
                    ", moneyCount=" + moneyCount + '\'' +
                    ", canPublishCountToday=" + canPublishCountToday +
                    '}';
        }
    }


    public class UserProFile implements Serializable {
        public String nickname; //昵称
        public String phone; //手机
        public String birthday;//生日
        public String region;//地区
        public String sign;//个性签名
        public int sex; //
        public String icon = "";//头像
        public boolean sexCanModify; //
        public int identity; //认证

        @Override
        public String toString() {
            return "UserProFile{" +
                    "birthday='" + birthday + '\'' +
                    ", nickname='" + nickname + '\'' +
                    ", phone='" + phone + '\'' +
                    ", region='" + region + '\'' +
                    ", sign='" + sign + '\'' +
                    ", sex=" + sex +
                    ", icon='" + icon + '\'' +
                    ", identity='" + identity + '\'' +
                    ", sexCanModify=" + sexCanModify +
                    '}';
        }
    }

    public int merchantId;
    public String ticket;
    public Account account;

    public class Account implements Serializable {
        public String account;
        public boolean appstore;
        public String birthday;
        public String createTime;
        public boolean deleted;
        public String hpurl;
        public String id;//用户id
        public boolean mainAccount;
        public String mobile;
        public String nickname;
        public String pwd;
        public String region;
        public String salt;
        public int sex;
        public int state;
        public int type;
        public String updateTime;

        @Override
        public String toString() {
            return new StringBuilder()
                    .append("account{")
                    .append("account='").append(account).append('\'')
                    .append(", appstore='").append(appstore).append('\'')
                    .append(", birthday='").append(birthday).append('\'')
                    .append(", createTime='").append(createTime).append('\'')
                    .append(", deleted='").append(deleted).append('\'')
                    .append(", hpurl='").append(hpurl).append('\'')
                    .append(", id='").append(id).append('\'')
                    .append(", mainAccount='").append(mainAccount).append('\'')
                    .append(", mobile='").append(mobile).append('\'')
                    .append(", nickname='").append(nickname).append('\'')
                    .append(", pwd='").append(pwd).append('\'')
                    .append(", region='").append(region).append('\'')
                    .append(", salt='").append(salt).append('\'')
                    .append(", sex=").append(sex)
                    .append(", state='").append(state).append('\'')
                    .append(", type='").append(type).append('\'')
                    .append(", updateTime=").append(updateTime)
                    .append('}').toString();
        }
    }


    @Override
    public String toString() {
//        return "UserRegisterMode{" +
//                "token='" + token + '\'' +
//                ", userId='" + userId + '\'' +
//                ", user=" + user + '\'' +
//                ", followStatus=" + followStatus +
//                '}';
        return new StringBuilder()
                .append("UserRegisterMode{")
                .append("account'").append(account).append('\'')
                .append(", merchantId='").append(merchantId).append('\'')
                .append(", ticket=").append(ticket)
                .append('}').toString();
    }
}

