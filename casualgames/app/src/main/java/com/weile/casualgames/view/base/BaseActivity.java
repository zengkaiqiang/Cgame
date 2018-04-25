package com.weile.casualgames.view.base;

import android.annotation.TargetApi;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.module.common.AppManager.AppManager;

import org.kymjs.kjframe.KJActivity;

/**
 * Created by zjj
 */
public abstract class BaseActivity extends KJActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        // 添加Activity到堆栈
        AppManager.getAppManager().addActivity(this);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 结束Activity从堆栈中移除

        AppManager.getAppManager().finishActivity(this);
    }

    @TargetApi(19)
    protected void setTranslucentStatus() {
        Window window = getWindow();
        // Translucent status bar
        window.setFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }

    public void stopAllActivity()
    {
        AppManager.getAppManager().finishAllActivity();
    }

    public void appExit()
    {
        AppManager.getAppManager().AppExit(this);
    }
}
