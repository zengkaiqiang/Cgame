package com.weile.casualgames.game.presenter;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.temporary.network.util.CMd;
import com.weile.casualgames.App;
import com.weile.casualgames.game.model.Player;
import com.weile.casualgames.game.view.ICelebrityListRankView;
import com.weile.casualgames.game.view.ITodayRankView;
import com.weile.casualgames.game.view.IYesterdayRankView;
import com.weile.casualgames.login.model.UserInfo;
import com.weile.casualgames.net.IRequestListener;
import com.weile.casualgames.net.NetApi;
import com.weile.casualgames.net.NetworkService;
import com.weile.casualgames.utils.LogUtils;
import com.weile.casualgames.view.widget.radarscan.utils.LogUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by zjj
 */
public class RankPresenterCompl implements IRankPresenter {

    private Context context;
    private ITodayRankView todayRankView;
    private IYesterdayRankView yesterdayRankView;
    private ICelebrityListRankView celebrityListRankView;
    private ArrayList<UserInfo> users = new ArrayList<UserInfo>();

    public RankPresenterCompl(Context context, ITodayRankView todayRankView) {
        this.context = context;
        this.todayRankView = todayRankView;
    }


    public RankPresenterCompl(Context context, IYesterdayRankView yesterdayRankView) {
        this.context = context;
        this.yesterdayRankView = yesterdayRankView;
    }

    public RankPresenterCompl(Context context, ICelebrityListRankView celebrityListRankView) {
        this.context = context;
        this.celebrityListRankView = celebrityListRankView;
    }


    @Override
    public void loadTodayRankDatas() {
//        getTodayFlower(0, 10);
        getYesterdayFlower(0,10);
        todayRankView.onGetTodayRankDatas(GameMenuUtil.gameRankToday);

    }

    @Override
    public void loadYesterdayRankDatas() {
        yesterdayRankView.onGetYesterdayRankDatas(GameMenuUtil.gameRankYesterday);
    }

    @Override
    public void loadCelebrityRankDatas() {
        getCelebrityList(0, 10);
    }


    public static ArrayList<Player> getTodayFlower(int start, int end) {
        ArrayList<Player> players = new ArrayList<Player>();
        JSONObject json = new JSONObject();
        try {
            json.put("Start", start);
            json.put("End", end);
        } catch (Exception e) {
            LogUtil.e("exception" + e.getLocalizedMessage());
            e.printStackTrace();
        }

        NetworkService.postWithLoading(App.getContext(), NetApi.GET_TODAY_FLOWER, json,
                new IRequestListener() {
                    @Override
                    public void onSuccess(JSONObject response) {
                        LogUtils.E("onSuccess=====onSuccess=1" + response);
                        Gson gson = new Gson();
                        JSONArray messages = null;
                        try {
                            messages = response.getJSONArray("Data");
//                            if (messages.length() < 10) {
////                                mPullRefreshListView.setMode(Mode.);
//                                finishRefresh();
//                            }
                            if (messages != null) {
                                int size = messages.length();
                                if (size > 0) {

                                    for (int i = 0; i < size; i++) {
                                        Player player = gson.fromJson(messages.getString(i), Player.class);
                                        if (player != null) { // && !TextUtils.isEmpty(mypm.title)
                                            players.add(player);
                                        }
                                    }



                                }
                              }
                            } catch(JSONException e){
                                e.printStackTrace();
                            }


                    }

                    @Override
                    public boolean onError(String status) {
                        return false;
                    }

                });

        return players;
        }




    public static ArrayList<Player> getYesterdayFlower(int start, int end) {
        ArrayList<Player> players = new ArrayList<Player>();
        JSONObject json = new JSONObject();
        try {
            json.put("Start", start);
            json.put("End", end);
        } catch (Exception e) {
            LogUtil.e("exception" + e.getLocalizedMessage());
            e.printStackTrace();
        }

        NetworkService.postWithLoading(App.getContext(), NetApi.GET_YESTERDAY_FLOWER, json,
                new IRequestListener() {
                    @Override
                    public void onSuccess(JSONObject response) {
                        LogUtils.E("onSuccess=====onSuccess=1" + response);
                        Gson gson = new Gson();
                        JSONArray messages = null;
                        try {
                            messages = response.getJSONArray("Data");
//                            if (messages.length() < 10) {
////                                mPullRefreshListView.setMode(Mode.);
//                                finishRefresh();
//                            }
                            if (messages != null) {
                                int size = messages.length();
                                if (size > 0) {

                                    for (int i = 0; i < size; i++) {
                                        Player player = gson.fromJson(messages.getString(i), Player.class);
                                        if (player != null) { // && !TextUtils.isEmpty(mypm.title)
                                            players.add(player);
                                        }
                                    }

                                }
                            }
                        } catch(JSONException e){
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public boolean onError(String status) {
                        return false;
                    }

                });

        return players;
    }



    public  void getCelebrityList(int start, int end) {
        JSONObject json = new JSONObject();
        try {
            json.put("Start", start);
            json.put("End", end);
        } catch (Exception e) {
            LogUtil.e("exception" + e.getLocalizedMessage());
            e.printStackTrace();
        }

        NetworkService.postWithLoading(App.getContext(), NetApi.GET_CELEBRITY_LIST, json,
                new IRequestListener() {
                    @Override
                    public void onSuccess(JSONObject response) {
                        LogUtils.E("onSuccess=====onSuccess=1" + response);
                        Gson gson = new Gson();
                        try {

                           JSONArray messages = response.getJSONArray("Data");
                            LogUtils.E("mingren" + messages.length());
                            if (messages != null) {
                                int size = messages.length();
                                if (size > 0) {

                                    for (int i = 0; i < size; i++) {
                                        UserInfo userInfo = gson.fromJson(messages.getString(i), UserInfo.class);
                                        if (userInfo != null) { // && !TextUtils.isEmpty(mypm.title)
                                        LogUtil.e("userInfo name: " + userInfo.getName());
                                            LogUtil.e("userInfo head: " + userInfo.getHead());
                                            users.add(userInfo);
                                    }
                                }

                                    celebrityListRankView.onGetCelebrityListRankDatas(users);

                                }
                            }
                        } catch(JSONException e){
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public boolean onError(String status) {
                        return false;
                    }

                });

    }


}
