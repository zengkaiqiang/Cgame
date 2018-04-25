package com.weile.casualgames.network.requesthttp;

import android.util.Log;

import com.temporary.network.util.CMd;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;


public class HttpReuqest {
    public final static String HTTP_ = "http://";

    //内网
//    public final static String IP = "192.168.67.71";
//    public final static String PORT = "9100";

    //外网
    public final static String IP = "39.105.35.10";
    public final static String PORT = "9100";

    private String uri = HTTP_ + IP + ":" + PORT ;

    public HttpReuqest(){

    }

    public String post(String inferfaceId, ArrayList<NameValuePair> list)
    {
        return getPost(uri+ inferfaceId, list);
    }


    public  String getPost(String uri, ArrayList<NameValuePair> list) {

//		list.add(new BasicNameValuePair("key", UserDataApp.KEY));

        String result = "";
        HttpPost httpPost = new HttpPost(uri);
        HttpClient httpClient = new DefaultHttpClient();
        // 请求超时
        httpClient.getParams().setParameter(
                CoreConnectionPNames.CONNECTION_TIMEOUT, 10000);
        // 读取超时
        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
                10000);

        try {
            httpPost.setEntity(new UrlEncodedFormEntity(list,
                    HTTP.UTF_8));
//            CMd.syo("http请求2="+list.toString());
            HttpResponse httpResponse = httpClient.execute(httpPost);

            StatusLine statusLine = httpResponse.getStatusLine();
//            CMd.syo("http请求="+httpResponse.getStatusLine().toString());
//            CMd.syo("http请求2="+httpResponse.getEntity().getContent().toString());
//            CMd.syo("http请求3="+httpResponse.getParams().toString());
            int statusCode = statusLine.getStatusCode();
            if (statusCode == 200) {
                result = EntityUtils.toString(httpResponse.getEntity(),
                        HTTP.UTF_8);
            } else {
                Log.w("warn", "访问失败" + statusCode);
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 关闭连接
        httpClient.getConnectionManager().shutdown();
        // Log.e("result", result);
        return result;
    }

}

