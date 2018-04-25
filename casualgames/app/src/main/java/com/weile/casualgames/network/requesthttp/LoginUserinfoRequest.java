package com.weile.casualgames.network.requesthttp;

import android.content.Context;

import com.temporary.network.util.CMd;
import com.temporary.network.util.MD5;
import com.weile.casualgames.login.model.UserInfo;
import com.weile.casualgames.network.CommonConfigure;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;

public class LoginUserinfoRequest {
    private Context context;
    private HttpRequestThreadList httpRequestThreadList;

    public LoginUserinfoRequest(Context mContext) {
        this.context = mContext;
    }

    /*********调用创建用户请求****************/
    public void callCreateHttp(String str, UserInfo userInfo) {
        httpRequestThreadList = new HttpRequestThreadList(context,
                str, "1");
        JSONObject body = new JSONObject();
        CMd.syo("此处提交的用户注册性别="+userInfo.toString());
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        try {
            nameValuePairs.add(new BasicNameValuePair("Style",
                    userInfo.getStyle() + ""));
            nameValuePairs.add(new BasicNameValuePair("Account", userInfo.getAccount()));
            nameValuePairs.add(new BasicNameValuePair("Pwd", userInfo.getPwd()));
            nameValuePairs.add(new BasicNameValuePair("Name", userInfo.getName()));
            nameValuePairs.add(new BasicNameValuePair("Sex", userInfo.getSex() + ""));
            nameValuePairs.add(new BasicNameValuePair("Head", userInfo.getHead()));
            nameValuePairs.add(new BasicNameValuePair("Age", userInfo.getAge() + ""));
            nameValuePairs.add(new BasicNameValuePair("BrhYear", userInfo.getBrhYear() + ""));
            nameValuePairs.add(new BasicNameValuePair("BrhMonth", userInfo.getBrhMonth() + ""));
            nameValuePairs.add(new BasicNameValuePair("BrhDay", userInfo.getBrhDay() + ""));
            nameValuePairs.add(new BasicNameValuePair("Province", userInfo.getProvince()));
            nameValuePairs.add(new BasicNameValuePair("City", userInfo.getCity()));
            nameValuePairs.add(new BasicNameValuePair("Area", userInfo.getArea()));
            nameValuePairs.add(new BasicNameValuePair("SatX", userInfo.getSatX() + ""));
            nameValuePairs.add(new BasicNameValuePair("SatY", userInfo.getSatY() + ""));


        } catch (Exception e) {
            e.printStackTrace();
        }

        httpRequestThreadList.setBody(CommonConfigure.CREATE, nameValuePairs);
        httpRequestThreadList.callHttp2();

    }

    /*********调用用户登录请求****************/
    public void callLoginHttp(String str, int Style, String Account, String Pwd) {
        httpRequestThreadList = new HttpRequestThreadList(context,
                str, "2");
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        try {
            nameValuePairs.add(new BasicNameValuePair("Style",
                    Style + ""));
            nameValuePairs.add(new BasicNameValuePair("Account", Account));
            nameValuePairs.add(new BasicNameValuePair("Pwd", MD5.MD5Password(Pwd)));


        } catch (Exception e) {
            e.printStackTrace();
        }

        httpRequestThreadList.setBody(CommonConfigure.LOGIN, nameValuePairs);
        httpRequestThreadList.callHttp();

    }

    /*********调用判断号码是否注册请求****************/
    public void callCheckUserHttp(String str, String Account) {
        httpRequestThreadList = new HttpRequestThreadList(context,
                str, "3");
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        try {
            nameValuePairs.add(new BasicNameValuePair("Account", Account));


        } catch (Exception e) {
            e.printStackTrace();
        }

        httpRequestThreadList.setBody(CommonConfigure.CHECK_USER, nameValuePairs);
        httpRequestThreadList.callHttp3();

    }


//    /*********调用活动内容项请求****************/
//    public void callEventListHttp(String str,String offset,String rows,String state) {
//        httpRequestThreadList = new HttpRequestThreadList(context,
//                str, state);
//        JSONObject body = new JSONObject();
//        try {
//            body.put("offset", offset);
//            body.put("rows", rows);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        httpRequestThreadList.setBody(CommonConfigure.FUTU_INF_33, body);
//        httpRequestThreadList.callHttp();
//
//    }

}

