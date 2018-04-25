package com.module.webservice.http.Retrofit.manager;


import android.content.Context;

import com.module.webservice.http.HttpResult;
import com.module.webservice.http.util.HttpUtil;

import org.json.JSONObject;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Observable;

/**
 *
 * Author: zjj
 */
public class DataService {

    public static Observable<HttpResult> request(JSONObject json,  String baseUrl,String url,Context context) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("Content-Type, application/json"),
                json.toString());
        return RetrofitManager.builder(baseUrl, context).service
                .commonHttp(requestBody,url).compose(RxSchedulers.io_main());
    }

    public static Observable<HttpResult> requestGet(String baseUrl,String url,Context context) {
        return RetrofitManager.builder(baseUrl, context).service
                .commonHttpGet(url).compose(RxSchedulers.io_main());
    }
    public static Observable<HttpResult> requestPut(JSONObject json,  String baseUrl,String url,Context context) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("Content-Type, application/json"),
                json.toString());
        return RetrofitManager.builder(baseUrl, context).service
                .commonHttpPut(requestBody,url).compose(RxSchedulers.io_main());
    }

    public static Observable<HttpResult> requestGet(JSONObject json,String baseUrl,String url,Context context) {
        url= HttpUtil.getUrl(url,json);
        return RetrofitManager.builder(baseUrl, context).service
                .commonHttpGet(url).compose(RxSchedulers.io_main());
    }
    public static Observable<HttpResult> uploadFile(File file, String baseUrl, String url, Context context) {
        RequestBody requestBody =
                RequestBody.create(MediaType.parse("multipart/form-data;name=\"file\""), file);
        return RetrofitManager.builder(baseUrl, context).service
                .uploadFile(requestBody,url).compose(RxSchedulers.io_main());
}
}
