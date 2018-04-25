package com.module.webservice.http.Retrofit.model;



import com.module.webservice.http.HttpResult;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Url;
import rx.Observable;

/**
 *
 * Author: zjj
 */
public interface HttpService {

    @POST
    Observable<HttpResult> commonHttp(@Body RequestBody body, @Url String url);
    @PUT
    Observable<HttpResult> commonHttpPut(@Body RequestBody body, @Url String url);
    @GET
    Observable<HttpResult> commonHttpGet(@Url String url);

    @Multipart
    @POST
    Observable<HttpResult> uploadFile(@Part("file\"; filename=\"image.png") RequestBody imgs, @Url String url);

    @Multipart
    @POST
    Observable<HttpResult> uploadFileMul(@PartMap Map<String, RequestBody> params, @Url String url);
}
