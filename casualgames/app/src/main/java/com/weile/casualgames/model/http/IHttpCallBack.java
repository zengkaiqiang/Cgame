package com.weile.casualgames.model.http;


import com.module.webservice.http.HttpResult;

/**
 * Created by zjj
 */
public interface IHttpCallBack {
    void success(HttpResult httpResult);

    void fail(String errormsg);
}
