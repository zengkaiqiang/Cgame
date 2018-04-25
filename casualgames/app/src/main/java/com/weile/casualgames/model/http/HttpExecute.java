package com.weile.casualgames.model.http;

import com.alibaba.fastjson.JSON;
import com.module.common.event.RxManage;
import com.module.common.log.LogDebug;
import com.module.common.log.LogUtil;
import com.module.webservice.http.HttpResult;
import com.weile.casualgames.App;
import com.weile.casualgames.R;
import com.weile.casualgames.model.config.Const;

import org.json.JSONObject;

import java.io.File;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by zjj
 */
public class HttpExecute {
    /**
     * http请求
     *
     * @param callBack 页面回调
     */
    private static void doExecute(Observable<HttpResult> observable, final com.weile.casualgames.model.http.IHttpCallBackEx callBack) {
        callBack.beforeRequest();
        RxManage.getInstance().add(observable.subscribe(new Subscriber<HttpResult>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                LogDebug.i( "error :" + e.toString());
                LogUtil.e("weile", "onError null");
                callBack.fail(App.getContext().getResources().getString(R.string.connectionerror),"0");
            }

            @Override
            public void onNext(com.module.webservice.http.HttpResult httpResult) {
                try {
                    if (httpResult == null) {
                        LogUtil.e("weile", "httpResult null");
                        callBack.fail(App.getContext().getResources().getString(R.string.connectionerror),"0");
                        return;
                    }

                    if (httpResult == null) {
                        LogUtil.e("weile", "httpResult null");
                        callBack.fail(App.getContext().getResources().getString(R.string.connectionerror),"0");
                    } else{
                        String data="";
                        if (httpResult.getEm()!=null){
                            data= JSON.toJSONString(httpResult.getData());
                        }
                        if (httpResult.getEc().equals(Const.SUCCESS)){
                            callBack.success(data,httpResult.getEm());
                        }else {
                            callBack.fail(httpResult.getEm(),httpResult.getEc());
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }));
    }
    /**
     * http请求
     *
     * @param json
     * @param callBack 页面回调
     */
    public static void execute(JSONObject json, String URL, final com.weile.casualgames.model.http.IHttpCallBack callBack) {
        RxManage.getInstance().add(com.weile.casualgames.model.http.WebService.requestPost(json,URL).subscribe(new Subscriber<HttpResult>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                LogDebug.i( "error :" + e.toString());
                callBack.fail(App.getContext().getResources().getString(R.string.connectionerror));
            }

            @Override
            public void onNext(com.module.webservice.http.HttpResult httpResult) {
                try {
                    if (httpResult == null) {
                        callBack.fail(App.getContext().getResources().getString(R.string.connectionerror));
                        return;
                    }
                    if (httpResult.getEc().equals(Const.SUCCESS)){
                        callBack.success(httpResult);
                    }else {
                        callBack.fail(httpResult.getMessage());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }));

    }
    public static void execute(JSONObject json,String url, final com.weile.casualgames.model.http.IHttpCallBackEx callBack) {
        doExecute(com.weile.casualgames.model.http.WebService.requestPost(json,url),callBack);
    }

    public static void executePost(JSONObject json,String url, final com.weile.casualgames.model.http.IHttpCallBackEx callBack) {
        doExecute(com.weile.casualgames.model.http.WebService.requestPost(json,url),callBack);
    }
    public static void executeGet(JSONObject json,String url, final com.weile.casualgames.model.http.IHttpCallBackEx callBack) {
        doExecute(com.weile.casualgames.model.http.WebService.requestGet(json,url),callBack);
    }
    public static void executePut(JSONObject json,String url, final com.weile.casualgames.model.http.IHttpCallBackEx callBack) {
        doExecute(com.weile.casualgames.model.http.WebService.requestPut(json,url),callBack);
    }
    public static void execute(File file, String url, final com.weile.casualgames.model.http.IHttpCallBackEx callBack) {
        doExecute(com.weile.casualgames.model.http.WebService.uploadFile(file,url),callBack);
    }
    public static Observable<HttpResult> executeGet(JSONObject json,String url) {
        return   com.weile.casualgames.model.http.WebService.requestGet(json,url);
    }
    public static Observable<HttpResult> executePost(JSONObject json,String url) {
        return   com.weile.casualgames.model.http.WebService.requestPost(json,url);
    }
}
