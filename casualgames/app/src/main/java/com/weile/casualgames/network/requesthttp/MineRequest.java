package com.weile.casualgames.network.requesthttp;

import android.content.Context;

import com.temporary.network.util.MD5;
import com.weile.casualgames.mine.model.EditUserInfo;
import com.weile.casualgames.network.CommonConfigure;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;

public class MineRequest {
    private Context context;
    private HttpRequestThreadList httpRequestThreadList;

    public MineRequest(Context mContext) {
        this.context = mContext;
    }

    /*********调用修改用户信息请求****************/
    public void callModifyUserHttp(String str, EditUserInfo editUserInfo) {
        httpRequestThreadList = new HttpRequestThreadList(context,
                str, "1");
        JSONObject body = new JSONObject();
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        try {
            nameValuePairs.add(new BasicNameValuePair("Uid",editUserInfo.getUid()+""));
            nameValuePairs.add(new BasicNameValuePair("Name", editUserInfo.getName()));
            nameValuePairs.add(new BasicNameValuePair("Sex", editUserInfo.getSex()+""));
            nameValuePairs.add(new BasicNameValuePair("Age", editUserInfo.getAge()+""));
            nameValuePairs.add(new BasicNameValuePair("BrhYear", editUserInfo.getBrhYear() + ""));
            nameValuePairs.add(new BasicNameValuePair("BrhMonth", editUserInfo.getBrhMonth()+""));
            nameValuePairs.add(new BasicNameValuePair("BrhDay", editUserInfo.getBrhDay() + ""));
            nameValuePairs.add(new BasicNameValuePair("Province", editUserInfo.getProvince() ));
            nameValuePairs.add(new BasicNameValuePair("City", editUserInfo.getCity()));
            nameValuePairs.add(new BasicNameValuePair("Area", editUserInfo.getArea()));
            nameValuePairs.add(new BasicNameValuePair("Sign", editUserInfo.getSign()));
            nameValuePairs.add(new BasicNameValuePair("ShenJia", editUserInfo.getShenJia()+""));

        } catch (Exception e) {
            e.printStackTrace();
        }

        httpRequestThreadList.setBody(CommonConfigure.MODIFY_USER, nameValuePairs);
        httpRequestThreadList.callHttp3();

    }

    /*********调用用户修改头像请求****************/
    public void callModifyUserHeadHttp(String str, int Uid, String Head) {
        httpRequestThreadList = new HttpRequestThreadList(context,
                str, "2");
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        try {
            nameValuePairs.add(new BasicNameValuePair("Uid",
                    Uid + ""));
            nameValuePairs.add(new BasicNameValuePair("Head", Head));


        } catch (Exception e) {
            e.printStackTrace();
        }

        httpRequestThreadList.setBody(CommonConfigure.MODIFY_USER_HEAD, nameValuePairs);
        httpRequestThreadList.callHttp3();

    }



}


