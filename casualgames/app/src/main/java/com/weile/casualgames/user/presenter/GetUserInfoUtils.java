package com.weile.casualgames.user.presenter;

import com.google.gson.Gson;
import com.weile.casualgames.App;
import com.weile.casualgames.net.IRequestListener;
import com.weile.casualgames.net.NetworkService;
import com.weile.casualgames.user.model.UserRegisterMode;
import com.weile.casualgames.utils.LogUtils;

import org.json.JSONException;
import org.json.JSONObject;


public class GetUserInfoUtils {
    /**
     * 获取我的个人信息
     */
    public static void getMineInfo(final GetUserInfoListener listener) {
        NetworkService.get(App.getContext(), "",
                new IRequestListener() {
                    @Override
                    public void onSuccess(JSONObject response) {
                        LogUtils.E("getMineInfo" + response.toString());
                        if (null != response) {
                            Gson gson = new Gson();
                            try {
                                UserRegisterMode.Account userBean = gson.fromJson(response.getString("data"), UserRegisterMode.Account.class);
                                listener.updateSuccess(userBean, 0);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public boolean onError(String status) {
                        listener.updateError();
                        return false;
                    }
                });
    }

    /**
     * 获取某个用户信息
     *
     * @param listener
     */
    public static void getOtherUserInfo(String userId, final GetUserInfoListener listener) {
        NetworkService.get(App.getContext(), String.format("", userId),
                new IRequestListener() {
                    @Override
                    public void onSuccess(JSONObject response) {
                        if (null != response) {
                            Gson gson = new Gson();
                            try {
                                UserRegisterMode mode = gson.fromJson(response.getJSONArray("message").getString(0), UserRegisterMode.class);

                                listener.updateSuccess(mode.account, mode.followStatus);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public boolean onError(String status) {
                        listener.updateError();
                        return false;
                    }
                });
    }


    public interface GetUserInfoListener {
        void updateSuccess(UserRegisterMode.Account userBean, int followStatus);
        void updateError();
    }
}
