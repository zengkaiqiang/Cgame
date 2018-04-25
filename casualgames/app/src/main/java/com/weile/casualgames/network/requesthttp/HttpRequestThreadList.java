package com.weile.casualgames.network.requesthttp;

import android.content.Context;
import android.content.Intent;

import com.temporary.network.LoadingView;
import com.temporary.network.util.CMd;
import com.temporary.network.util.ErrHandler;

import org.apache.http.NameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;

public class HttpRequestThreadList {
    private Context context;
    private JSONObject body;
    ArrayList<NameValuePair> nameValuePairs;
    private String inferfaceId;
    private LoadingView loadingView;
    private HttpRequestThread httpRequestThread;
    private HttpRequestThread2 httpRequestThread2;
    private HttpRequestThread3 httpRequestThread3;

    public ErrHandler errHandler = null;

    private String returnFlag = "0";// 返回标识 ： -1:出错了 0：null 1:faile 2:success
    private String returnStr = "0";// 返回内容串

    private String distStr = "";// 不同类的识别串
    private String classnoINFStr = "";// 用户同一个类中不同接口的识别串

    public HttpRequestThreadList(Context mContext, String distStr,
                                 String classnoINFStr) {
        this.context = mContext;
        this.distStr = distStr;
        this.classnoINFStr = classnoINFStr;
        errHandler = new ErrHandler();
    }

    // 设置参数
    public void setBody(String inferfaceId, ArrayList<NameValuePair> nameValuePairs) {
        this.inferfaceId = inferfaceId;
        this.nameValuePairs = nameValuePairs;
    }


    // 设置了进度条
    public void setLoadingView(LoadingView loadingView) {
        this.loadingView = loadingView;
    }

    // 呼叫线程
    public void callHttp() {
        CMd.syo("有进入呼叫线程么=");
        httpRequestThread = new HttpRequestThread();
        httpRequestThread.setUncaughtExceptionHandler(errHandler);
        httpRequestThread.start();
    }

    // 呼叫线程
    public void callHttp2() {
        // CMd.syo("有进入呼叫线程么=");
        httpRequestThread2 = new HttpRequestThread2();
        httpRequestThread2.setUncaughtExceptionHandler(errHandler);
        httpRequestThread2.start();
    }
    // 呼叫线程
    public void callHttp3() {
        // CMd.syo("有进入呼叫线程么=");
        httpRequestThread3 = new HttpRequestThread3();
        httpRequestThread3.setUncaughtExceptionHandler(errHandler);
        httpRequestThread3.start();
    }

    // 停止线程
    public void onstopHttp() {
        httpRequestThread.interrupt();
    }

    // 停止线程
    public void onstopHttp2() {
        httpRequestThread2.interrupt();
    }

    // 停止线程
    public void onstopHttp3() {
        httpRequestThread3.interrupt();
    }


    // Http请求类(通用请求数据
    // {"code":0,"message":{"ec":0,"em":"ok","User":[--------]},"GList":{"Data":[--------]}}} )
    public class HttpRequestThread extends Thread {

        public void run() {
            HttpReuqest httpRequest = new HttpReuqest();

            String result = httpRequest.post(inferfaceId, nameValuePairs);
            CMd.syo("共用http有返回值么=" + result);
            if (loadingView != null) {
                loadingView.dismissView();
            }

            if (result == null || result.equals("")) {
                returnFlag = "0";
            } else {
                try {
                    JSONObject jsonObj = new JSONObject(result);
                    String codes = jsonObj.getString("ec");
                    if (codes == null || codes.equals("") ) {
                        returnFlag = "0";
                    } else if (codes.equals("2")) {
                        returnFlag = "1";
                    } else if (codes.equals("0")) {
                        String results = jsonObj.getString("User");

                        if (results == null || results.equals("")) {
                            returnFlag = "0";
                        } else {
                            results+="###";
                            results+=jsonObj.getString("GList");
                            returnStr = results;
                            // returnStr = returnStr.replace("\\", "");
                            returnStr = CMd.jsonString(returnStr);

                            if (returnStr == null || returnStr.equals("")) {
                                returnFlag = "1";
                            } else {
                                returnFlag = "2";
                            }

                        }
                    }
                } catch (Exception e) {// 异常
                    returnFlag = "-1";
                    e.printStackTrace();
                }
            }
            sendStickBroadCast(1);

        }
    }


    // Http请求类(通用请求数据
    // {"code":0,"message":{"ec":0,"em":"ok","User":[--------]}}} )
    public class HttpRequestThread2 extends Thread {

        public void run() {
            HttpReuqest httpRequest = new HttpReuqest();

            String result = httpRequest.post(inferfaceId, nameValuePairs);
            CMd.syo("共用http有返回值么2=" + result);
            if (loadingView != null) {
                loadingView.dismissView();
            }

            if (result == null || result.equals("")) {
                returnFlag = "0";
            } else {
                try {
                    JSONObject jsonObj = new JSONObject(result);
                    String codes = jsonObj.getString("ec");
                    if (codes == null || codes.equals("") ) {
                        returnFlag = "0";
                    } else if (codes.equals("2")) {
                        returnFlag = "1";
                    } else if (codes.equals("0")) {
                        String results = jsonObj.getString("User");

                        if (results == null || results.equals("")) {
                            returnFlag = "0";
                        } else {

                            returnStr = results;
                            // returnStr = returnStr.replace("\\", "");
                            returnStr = CMd.jsonString(returnStr);

                            if (returnStr == null || returnStr.equals("")) {
                                returnFlag = "1";
                            } else {
                                returnFlag = "2";
                            }

                        }
                    }
                } catch (Exception e) {// 异常
                    returnFlag = "-1";
                    e.printStackTrace();
                }
            }
            sendStickBroadCast(2);

        }
    }

    // Http请求类(通用请求数据
    // {"code":0,"message":{"ec":0,"em":""}} )
    public class HttpRequestThread3 extends Thread {

        public void run() {
            HttpReuqest httpRequest = new HttpReuqest();

            String result = httpRequest.post(inferfaceId, nameValuePairs);
            CMd.syo("共用http有返回值么3=" + result);
            if (loadingView != null) {
                loadingView.dismissView();
            }

            if (result == null || result.equals("")) {
                returnFlag = "0";
            } else {
                try {
                    JSONObject jsonObj = new JSONObject(result);
                    String codes = jsonObj.getString("ec");
                    if (codes == null || codes.equals("") ) {
                        returnFlag = "-1";
                    } else if (codes.equals("1")) {
                        returnFlag = "1";
                    } else if (codes.equals("0")) {
                        returnFlag = "0";




                    }
                } catch (Exception e) {// 异常
                    returnFlag = "-1";
                    e.printStackTrace();
                }
            }
            returnStr = "";
            sendStickBroadCast(3);

        }
    }


    /******************
     * 广播发送
     * *****************/
    private void sendStickBroadCast(int x) {
        Intent intents = new Intent();
        CMd.syo("有到这里发广播吗");
        intents.setAction("weile.httpRequestThread." + distStr);

        intents.putExtra("returnFlag", returnFlag);
        intents.putExtra("returnStr", returnStr);
        intents.putExtra("classnoINFStr", classnoINFStr);
        context.sendBroadcast(intents);
        switch (x) {
            case 1:
                onstopHttp();
                break;
            case 2:
                onstopHttp2();
                break;
            case 3:
                onstopHttp3();
                break;

            default:
        }
    }

}

