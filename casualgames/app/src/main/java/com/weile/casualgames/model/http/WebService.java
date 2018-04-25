package com.weile.casualgames.model.http;


import com.module.webservice.http.Retrofit.manager.DataService;
import com.weile.casualgames.App;
import com.weile.casualgames.model.config.UrlConstant;

import org.json.JSONObject;

import java.io.File;

import rx.Observable;

/**
 *
 * Author: zjj
 */
public class WebService {
    public static Observable<com.module.webservice.http.HttpResult> request(JSONObject json, String url) {
        return DataService.request(json, UrlConstant.BaseURL,url, App.getContext());    }
    public static Observable<com.module.webservice.http.HttpResult> requestPost(JSONObject json, String url) {
        return DataService.request(json,UrlConstant.BaseURL,url, App.getContext());    }
    public static Observable<com.module.webservice.http.HttpResult> requestGet(JSONObject json, String url) {
        return DataService.requestGet(UrlConstant.BaseURL,url, App.getContext());    }

    protected static Observable<com.module.webservice.http.HttpResult> requestPut(JSONObject json, String url) {
        return DataService.requestPut(json, UrlConstant.BaseURL,url, App.getContext());    }


    protected static Observable<com.module.webservice.http.HttpResult> uploadFile(File file, String url) {
        return DataService.uploadFile(file, UrlConstant.BaseURL,url, App.getContext());    }

    public static Observable<com.module.webservice.http.HttpResult> CustomrequestPut(JSONObject json, String url) {
        return DataService.requestPut(json, UrlConstant.BaseURL,url, App.getContext());    }

}
