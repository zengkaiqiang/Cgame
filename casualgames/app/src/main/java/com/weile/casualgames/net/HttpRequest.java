package com.weile.casualgames.net;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.JsonObjectRequest;
import com.weile.casualgames.App;
import com.weile.casualgames.utils.AppKeys;
import com.weile.casualgames.utils.PreferencesUtils;

import org.json.JSONObject;
import org.json.JSONException;
import org.kymjs.kjframe.utils.StringUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 以post形式提交数据
 */
public class HttpRequest extends JsonObjectRequest {
    private JSONObject mJsonRequest;
    private int mMethod;
    private String mUrl;
    private Context cont = App.getContext();
//    public static boolean isJson = true;

    public HttpRequest(int method, String url, JSONObject jsonRequest,
                       Listener<JSONObject> listener, ErrorListener errorListener) {
        super(method, url, jsonRequest, listener, errorListener);
        // LogUtils.E("HttpRequest" + method + "/////" + url + "mJsonRequest"
        // + mJsonRequest);
        this.mMethod = method;
        this.mUrl = url;
        this.mJsonRequest = jsonRequest;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Charset", "UTF-8");
        if (!StringUtils.isEmpty(PreferencesUtils.getValueFromSPMap(cont,
                AppKeys.APP_TOKEN))) {
            headers.put("ticket",
                    //ali
//                    "bearer HUh1zPlGCpxDgzqd1dRhujJVEb98PNGf1UKtBWVDQwg2q7ywQv1pXzE5ZappFDhgfY1a9gPVCrWIX4A81K5NvgmmNmZjgAH5GEquScZmM8YusneuHchYPX2CICeesNsw");

                    PreferencesUtils.getValueFromSPMap(cont, AppKeys.APP_TOKEN));
        }
        headers.put("Accept-Language", "zh".equals(cont.getResources()
                .getConfiguration().locale.getLanguage()) ? "zh-cn" : "en-us");
        headers.put("Content-Type", "application/x-www-form-urlencoded");
//        headers.put("Content-Type","application/json");
        return headers;
    }

    @Override
    public String getUrl() {
        if (mMethod == Method.GET && null != mJsonRequest) {
            return mUrl;
        }
        return super.getUrl();
    }

   /* @Override
    public byte[] getBody() {

        try {
            return mJsonRequest.toString().getBytes("UTF-8");

        } catch (Exception e) {
        }
        return null;
    }
*/

@Override
    public byte[] getBody() {
        String _Body = "";
        if (null != mJsonRequest && (mMethod == Method.POST || mMethod == Method.PUT)) {
            _Body = getBodyInfo();

            return _Body.getBytes();
        }

        return null;
    }



    @SuppressWarnings("unchecked")
    private String getBodyInfo() {
        String _Body = "";
        if (null != mJsonRequest) {
            Iterator<String> _Iterator = mJsonRequest.keys();
            while (_Iterator.hasNext()) {
                String key = _Iterator.next();
                try {
                    _Body += key + "=" + mJsonRequest.getString(key) + "&";
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        // _Body = _Body.substring(0, _Body.length() - 1);
        return _Body;
    }

    public static enum HttpMethod {
        GET("GET"), POST("POST"), PUT("PUT"), HEAD("HEAD"), MOVE("MOVE"), COPY(
                "COPY"), DELETE("DELETE"), OPTIONS("OPTIONS"), TRACE("TRACE"), CONNECT(
                "CONNECT");
        private final String value;

        HttpMethod(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }
}
