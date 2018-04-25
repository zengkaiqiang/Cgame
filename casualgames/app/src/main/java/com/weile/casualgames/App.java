package com.weile.casualgames;

/**
 * Created by zjj
 */

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.module.common.event.RxManage;
import com.module.common.log.LogDebug;
import com.weile.casualgames.utils.MyExecutorService;
import com.weile.casualgames.utils.MySharedPreferences;

//import com.umeng.analytics.MobclickAgent;

/**
 * 编写自己的Application，管理全局状态信息，比如Context
 */
public class App extends Application {
    private static final int REQUEST_CODE = 0 ;
    private static Context context;
    private MySharedPreferences mySharedPreferences;
    private MyExecutorService myExecutorService;

//    private static DBHandler dbOptions;



//    public static void setDbOptions(DBHandler dbOptions) {
//        App.dbOptions = dbOptions;
//    }

    @Override
    public void onCreate() {
        super.onCreate();
//        CrabSDK.init(this,"9f3dc787854172a5");
        //初始化友盟统计
        //initUmeng();
        context = getApplicationContext();
        mySharedPreferences = MySharedPreferences.getInstance(this);
        LogDebug.d("Appinit", "init");
//        JPushInterface.setDebugMode(false);
//        JPushInterface.init(this);

//        PlatformConfig.setWeixin("wx76bd87a2f5247256", "a4e0f6399fbf1713cc1a891d93c1e29f");
//        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
    }

    /**
     * 初始化友盟统计SDK
     * @return
     */
//    private void initUmeng() {
//        MobclickAgent.setCatchUncaughtExceptions(true);
//        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);
//        MobclickAgent.setDebugMode(false);
//    }

    public static RxManage getRxManage() {
        return RxManage.getInstance();
    }

    public static Context getContext() {
        return context;
    }

//    public static DBHandler getOption() {
//        return dbOptions;
//    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
        myExecutorService=MyExecutorService.getInstance();
    }

    public MySharedPreferences getMySharedPreferences() {
        return mySharedPreferences;
    }

    public void setMySharedPreferences(MySharedPreferences mySharedPreferences) {
        this.mySharedPreferences = mySharedPreferences;
    }

    public MyExecutorService getMyExecutorService() {
        return myExecutorService;
    }

    public void setMyExecutorService(MyExecutorService myExecutorService) {
        this.myExecutorService = myExecutorService;
    }

    public static MySharedPreferences getSharedPreferences() {
        return MySharedPreferences.getInstance(context);
    }
}
