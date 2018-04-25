package com.weile.casualgames.net;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.weile.casualgames.App;
import com.weile.casualgames.R;
import com.weile.casualgames.utils.AppKeys;
import com.weile.casualgames.utils.DialogUtil;
import com.weile.casualgames.utils.LogUtils;
import com.weile.casualgames.utils.PreferencesUtils;
import com.weile.casualgames.view.widget.dialog.NetErrorDialog;

import org.json.JSONException;
import org.json.JSONObject;
import org.kymjs.kjframe.utils.StringUtils;

import java.io.File;
import java.util.Map;

/**
 * net
 */
public class NetworkService {

    public final static String TAG = "weile";

    public static RequestQueue mRequestQueue = Volley
            .newRequestQueue(App.getContext());

    public static void addRequest(Request request, Object tag) {
        if (tag != null) {
            request.setTag(tag);
        }
        mRequestQueue.add(request);
    }

    public static void cancelAll(Object tag) {
        mRequestQueue.cancelAll(tag);
    }

    public static void post(Context cont, String pUrl, JSONObject params,
                            IRequestListener pListener) {

        if (!NetUtil.isNetworkConnected(App.getContext())) {
//            Utility.imageToast(cont.getString(R.string.toast_check_network));
            NetErrorDialog.showProgess(cont);
        } else {
            addToQueue(Method.POST, pUrl, params, pListener, 0);
        }
    }

    public static void get(Context cont, String pUrl, IRequestListener pListener) {
        if (!NetUtil.isNetworkConnected(App.getContext())) {
//            Utility.imageToast(cont.getString(R.string.toast_check_network));
            NetErrorDialog.showProgess(cont);
        } else {
            addToQueue(Method.GET, pUrl, null, pListener, 0);
        }
    }

    public static void get(Context cont, String pUrl, JSONObject params, IRequestListener pListener) {
        if (!NetUtil.isNetworkConnected(App.getContext())) {
//            Utility.imageToast(cont.getString(R.string.toast_check_network));
            NetErrorDialog.showProgess(cont);
        } else {
            addToQueue(Method.GET, pUrl, params, pListener, 0);
        }
    }

    public static void postWithLoading(Context cont, String pUrl,
                                       JSONObject params, IRequestListener pListener) {
        // "加载中");
        if (!NetUtil.isNetworkConnected(cont)) {
//            Utility.imageToast(cont.getString(R.string.toast_check_network));
            NetErrorDialog.showProgess(cont);
        } else {
//            DialogUtil.showProgess(cont, cont.getResources().getString(R.string.pull_to_refresh_from_bottom_refreshing_label));
//            LogUtils.E("postWithLoading");
            addToQueue(Method.POST, pUrl, params, pListener, 1);
//            LogUtils.E("postWithLoading====");
        }

    }

    public static void getWithLoading(Context cont, String pUrl,
                                      IRequestListener pListener) {
        if (!NetUtil.isNetworkConnected(cont)) {
//            Utility.imageToast(cont.getString(R.string.toast_check_network));
            NetErrorDialog.showProgess(cont);
        } else {
//            DialogUtil.showProgess(cont, cont.getResources().getString(R.string.pull_to_refresh_from_bottom_refreshing_label));

            addToQueue(Method.GET, pUrl, null, pListener, 1);
        }
    }

    public static void getWithLoading(Context cont, String pUrl, boolean isNeedShowDIalog,
                                      IRequestListener pListener) {
        if (!NetUtil.isNetworkConnected(cont)) {
//            Utility.imageToast(cont.getString(R.string.toast_check_network));
            NetErrorDialog.showProgess(cont);
        } else {
            if (isNeedShowDIalog) {
                DialogUtil.showProgess(cont, cont.getResources().getString(R.string.pull_to_refresh_from_bottom_refreshing_label));
            }
            addToQueue(Method.GET, pUrl, null, pListener, 1);
        }
    }

    /**
     * put
     *
     * @param cont
     * @param pUrl
     * @param pListener
     */
    public static void putWithLoading(Context cont, String pUrl, JSONObject params,
                                      IRequestListener pListener) {
        if (!NetUtil.isNetworkConnected(cont)) {
//            Utility.imageToast(cont.getString(R.string.toast_check_network));
//            NetErrorDialog.showProgess(cont);
        } else {
//            DialogUtil.showProgess(cont, cont.getResources().getString(R.string.pull_to_refresh_from_bottom_refreshing_label));
            addToQueue(Method.PUT, pUrl, params, pListener, 0);
        }

    }

    public static void delete(Context cont, String pUrl,
                              IRequestListener pListener) {
        if (!NetUtil.isNetworkConnected(cont)) {
//            Utility.imageToast(cont.getString(R.string.toast_check_network));
            NetErrorDialog.showProgess(cont);
        } else {
            DialogUtil.showProgess(cont, cont.getResources().getString(R.string.pull_to_refresh_from_bottom_refreshing_label));
            addToQueue(Method.DELETE, pUrl, null, pListener, 1);
        }

    }

    private static void addToQueue(int pMethod, String pUrl, JSONObject params,
                                   final IRequestListener pListener, int tag) {
        // StringRequest _req = new
        // StringRequest(pMethod,pUrl,newResponseListener(pListener),
        // newErrorListener(pListener));
//		if ( 0!=pMethod) {
//
//		}
        if (params == null) {
            params = new JSONObject();
        }

        LogUtils.E("HttpRequest=" + pMethod + "//pUrl=" + pUrl + "_________params=" + params);
        HttpRequest req = new HttpRequest(pMethod, pUrl, params,
                newResponseListener(tag, pListener), newErrorListener(tag,
                pListener));
        req.setRetryPolicy(new DefaultRetryPolicy(9 * 1000, 1, 1.0f));
        addRequest(req, tag);
    }

    private static Response.Listener<JSONObject> newResponseListener(
            final int tag, final IRequestListener pListener) {
        return new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                DialogUtil.dismissProgessDirectly();
                LogUtils.E("Response-Listener===" + response);
                int result = 1;
                if (response.has("result")) {
                    try {
                        result = response.getInt("result");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                LogUtils.E("Response-result=" + result);
                if (null != pListener) {
                    if (result == 1) {
                        pListener.onSuccess(response);
//                    }
//                    else if (result == 401) {
//                        LogUtils.E("result == 401");
//                        PreferencesUtils.putValueToSPMap(AppContext.mContext, AppKeys.APP_TOKEN, "");
//                        if (AppContext.hasSomeActivityRunning)
//                            UserLogiHelp.userRegister(ActManager.getAppManager().currentActivity(), AppContext.latitude, AppContext.longitude);
                    } else if (result == 0) {
                        Toast.makeText(App.getContext(), "网络出错", Toast.LENGTH_SHORT).show();
                        pListener.onError("");
                    } else if (result == 101) {
                        pListener.onError("");
                    } else if (result == 301) {
//                        Activity activity = ActManager.getAppManager().currentActivity();
//                        if (activity != null && activity instanceof TopicDetailActivity_) {
//                            ((TopicDetailActivity_) activity).noFoundTopic();
//                        }
//                        if (activity != null && activity instanceof _TopicDetailActivity) {
//                            ((_TopicDetailActivity) activity).noFoundTopic();
//                        }
                        pListener.onError("");
                    } else if (result == 211 || result == 212 || result == 213) {
                        try {
                            String str = response.getString("message");
                            if (!StringUtils.isEmpty(str)) {
                                Toast.makeText(App.getContext(), str, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
//                        Activity activity = ActManager.getAppManager().currentActivity();
//                        if (activity != null && activity instanceof UserRegiestActivity) {
//                            ((UserRegiestActivity) activity).alterNickName();
//                        }
                        pListener.onError("");
                    } else if (response.has("message")) {
                        try {
                            String str = response.getString("message");
                            if (!StringUtils.isEmpty(str)) {
                                Toast.makeText(App.getContext(), str, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        pListener.onError("");
//                        Toast.makeText(AppContext.mContext, )
                    } else {
                        pListener.onError(response.toString());
                        LogUtils.E("error====" + response);
                    }
                }
            }
        };
    }

    private static ErrorListener newErrorListener(final int tag,
                                                  final IRequestListener pListener) {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtil.dismissProgess();
                if (error != null && error.networkResponse != null) {
                    pListener.onError(error.toString());
                    LogUtils.E("newErrorListener" + error.getMessage() + "////error.toString()" + error.networkResponse.statusCode);

                    if (error.networkResponse.statusCode == 401) {
                        PreferencesUtils.putValueToSPMap(App.getContext(), AppKeys.APP_TOKEN, "");
//                        if (App.hasSomeActivityRunning)
//                            UserLogiHelp.userRegister();
                    }
                }
            }
        };
    }


    public static void addPutUploadFileRequest(String pUrl, final Map<String, File> files,
                                               final Map<String, String> params,
                                               final Listener<String> responseListener,
                                               final ErrorListener errorListener) {
        if (null == pUrl || null == responseListener) {
            return;
        }

        MultiPartStringRequest multiPartRequest = new MultiPartStringRequest(
                Request.Method.POST, pUrl, responseListener, errorListener) {

            @Override
            public Map<String, File> getFileUploads() {
                return files;
            }

            @Override
            public Map<String, String> getStringUploads() {
                return params;
            }

        };
        RequestQueue SingleQueue = Volley.newRequestQueue(App.getContext(), new MultiPartStack());
        SingleQueue.add(multiPartRequest);
    }



}
