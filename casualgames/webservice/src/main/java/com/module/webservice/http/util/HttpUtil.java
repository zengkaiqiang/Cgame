package com.module.webservice.http.util;



import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;


/**
 * Created by zjj
 */

public class HttpUtil {

    public static String getUrl(String url,JSONObject jsonObject) {
        if(jsonObject==null){
            return  url;
        }
        // 添加url参数
        if (jsonObject != null) {
            Iterator iterator = jsonObject.keys();
           StringBuffer sb = null;
            while(iterator.hasNext()){
                String  key = (String) iterator.next();
                String value="";
                try {
                    value = jsonObject.getString(key);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (sb == null||sb.equals("")) {
                    sb = new StringBuffer();
                    sb.append("?");
                } else {
                    sb.append("&");
                }
                sb.append(key);
                sb.append("=");
                sb.append(value);
            }
           url += sb.toString();
        }
        return url;
    }
}
