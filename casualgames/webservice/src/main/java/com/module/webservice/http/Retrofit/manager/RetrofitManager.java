package com.module.webservice.http.Retrofit.manager;


import android.content.Context;
import android.util.Log;

import com.module.webservice.http.Retrofit.model.HttpService;
import com.module.webservice.http.util.NetUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;


/**
 * ClassName: RetrofitManager<p>
 * Author: zjj
 * Fuction: Retrofit请求管理类<p>
 * UpdateUser:<p>
 * UpdateDate:<p>
 */
public class RetrofitManager {

    //设缓存有效期为两天
    protected static final long CACHE_STALE_SEC = 60 * 60 * 24 * 2;
    //查询缓存的Cache-Control设置，为if-only-cache时只查询缓存而不会请求服务器，max-stale可以配合设置缓存失效时间
    protected static final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;
    //查询网络的Cache-Control设置，头部Cache-Control设为max-age=0时则不会使用缓存而请求服务器
    protected static final String CACHE_CONTROL_NETWORK = "max-age=0";


    public HttpService service;
    Context mContext;
    private static OkHttpClient mOkHttpClient;
    private static final Interceptor mInterceptor = (chain) -> chain.proceed(chain.request().newBuilder()
//            .addHeader("ticket", Header.getTicket())
//            .addHeader("Sign", Utils.getSign())
//            .addHeader("User-Agent", "android")
//            .addHeader("app", "android")
            .addHeader("Content-Type", "application/json")
//            .addHeader("Content-Type", "application/x-www-form-urlencoded")
            .build());

    public static RetrofitManager builder(String url,Context context) {
        Log.e("RF", "RetrofitManager builder");
        return new RetrofitManager(url,context);
    }

    private RetrofitManager(String url, Context context) {
        mContext=context;
        initOkHttpClient(context);

        Retrofit retrofit = new Retrofit.Builder().baseUrl(url).client(mOkHttpClient)
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();

        Log.e("RF", "baseUrl(url):" + url);
        service =retrofit.create(HttpService.class);
    }

    // 配置OkHttpClient
    private void initOkHttpClient(Context context) {
        Log.e("RF", "initOkHttpClient");
        if (mOkHttpClient == null) {
            synchronized (RetrofitManager.class) {

                if (mOkHttpClient == null) {
                    Log.e("RF", "new OkHttpClient");
                    // 因为BaseUrl不同所以这里Retrofit不为静态，但是OkHttpClient配置是一样的,静态创建一次即可
//                    File cacheFile = new File(context.getCacheDir(),
//                            "HttpCache"); // 指定缓存路径
//                    Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); // 指定缓存大小100Mb
//.cache(cache)
                    mOkHttpClient = new OkHttpClient.Builder()
                              //   .addNetworkInterceptor(rewriteCacheControlInterceptor)
                            // .addInterceptor(rewriteCacheControlInterceptor)
                            .addInterceptor(mInterceptor)
                            .connectTimeout(30, TimeUnit.SECONDS).build();
                }
            }
        }
    }



    // 云端响应头拦截器，用来配置缓存策略
    Interceptor rewriteCacheControlInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetUtil.isConnected(mContext)) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE).build();
            }
            Response originalResponse = chain.proceed(request);
            if (NetUtil.isConnected(mContext)) {
                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                String cacheControl = request.cacheControl().toString();
                return originalResponse.newBuilder()
                        .header("Cache-Control", cacheControl)
                        .removeHeader("Pragma").build();
            } else {
                return originalResponse.newBuilder().header("Cache-Control",
                        "public, only-if-cached," + CACHE_STALE_SEC)
                        .removeHeader("Pragma").build();
            }
        }
    };

}
