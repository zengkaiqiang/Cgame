package com.weile.casualgames.network;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.temporary.network.LoadingView;
import com.temporary.network.net.HttpCB;
import com.temporary.network.util.Connectivity;
import com.temporary.network.util.ErrHandler;
import com.weile.casualgames.view.base.BaseActivity;

public class BaseMiddleOrdinaryActivity extends BaseActivity {

    public LoadingView loadingView;
//    public ActivityManager exitM;
    public ErrHandler errHandler = null;
    private MyBroadcast myBroadcast;
    private HttpCB httpCB;
    public Connectivity connectivity;
    @Override
    public void setRootView() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadingView = new LoadingView(this);
//        exitM = ActivityManager.getInstance();
        errHandler= new ErrHandler();
        // exitVideo=ActivityManager.getInstance();

//        exitM.addActivity(this);
//        myDialog = new DialogMain(this);
        connectivity = new Connectivity();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
    }

    public void setHttpCB(HttpCB httpCB)
    {
        this.httpCB=httpCB;
    }

    /*********初始化广播********/
    public void initBroadcast(String distStr)
    {
        // 广播注册
        myBroadcast = new MyBroadcast();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter
                .addAction("weile.httpRequestThread."+distStr);
        registerReceiver(myBroadcast, intentFilter);
    }

    /*********停止广播********/
    public void stopBroadcast()
    {
        if(myBroadcast!=null)
            unregisterReceiver(myBroadcast);
    }

    /*************************
     * 广播接收器
     **************************/
    private class MyBroadcast extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String returnFlag = intent.getStringExtra("returnFlag");
            String returnStr = intent.getStringExtra("returnStr");
            String classnoINFStr = intent.getStringExtra("classnoINFStr");

            httpCB.onHttpCB(returnFlag, returnStr, classnoINFStr);
        }
    }
}
