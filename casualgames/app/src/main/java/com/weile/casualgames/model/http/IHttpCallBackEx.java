package com.weile.casualgames.model.http;

/**
 * Created by zjj
 */
public interface IHttpCallBackEx {
    public  void success(String data, String msg);

    public  void fail(String msg, String code);

    public  void beforeRequest();
}
