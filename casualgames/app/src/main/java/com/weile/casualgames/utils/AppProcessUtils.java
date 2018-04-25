package com.weile.casualgames.utils;

import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.content.Context;
import android.util.Log;


import com.weile.casualgames.App;

import java.util.List;

/**
 * Created by rehu_Chs on 15/6/8.
 */
public class AppProcessUtils {
    public static boolean isBackgroundRunning() {
        String processName = "com.weile.casualgames";
        Context mContext = App.getContext();
        ActivityManager activityManager = (ActivityManager) mContext.getSystemService(mContext.ACTIVITY_SERVICE);
        KeyguardManager keyguardManager = (KeyguardManager) mContext.getSystemService(mContext.KEYGUARD_SERVICE);

        if (activityManager == null) return false;
// get running application processes
        List<ActivityManager.RunningAppProcessInfo> processList = activityManager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo process : processList) {
            if (process.processName.startsWith(processName)) {
                boolean isBackground = process.importance != ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND && process.importance != ActivityManager.RunningAppProcessInfo.IMPORTANCE_VISIBLE;
                boolean isLockedState = keyguardManager.inKeyguardRestrictedInputMode();
                if (isBackground || isLockedState) {
                    LogUtils.E("isBackgroundRunning=true");
                    return true;
                } else {
                    LogUtils.E("isBackgroundRunning=false");
                    return false;
                }
            }
        }
        return false;
    }


    public static boolean isRunning() {
        ActivityManager am = (ActivityManager) App.getContext().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(100);
        boolean isAppRunning = false;
        String MY_PKG_NAME = "cn.rehu.duang";
        for (ActivityManager.RunningTaskInfo info : list) {
            if (info.topActivity.getPackageName().equals(MY_PKG_NAME) || info.baseActivity.getPackageName().equals(MY_PKG_NAME)) {
                isAppRunning = true;
                break;
            }
        }

        return isAppRunning;
    }


    public static boolean isBackground() {
        Context context = App.getContext();
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager
                .getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(context.getPackageName())) {
                /*
				BACKGROUND=400 EMPTY=500 FOREGROUND=100
				GONE=1000 PERCEPTIBLE=130 SERVICE=300 ISIBLE=200
				 */

                if (appProcess.importance != ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    Log.i(context.getPackageName(), "处于后台"
                            + appProcess.processName);
                    return true;
                } else {
                    Log.i(context.getPackageName(), "处于前台"
                            + appProcess.processName);
                    return false;
                }
            }
        }
        return false;
    }

    /**
     * 返回当前的应用是否处于前台显示状态
     *
     * @return
     */
    public static boolean isTopActivity() {
        ActivityManager am = (ActivityManager) App.getContext().getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> list = am.getRunningAppProcesses();
        if (list.size() == 0) {
            return false;
        }

        for (ActivityManager.RunningAppProcessInfo process : list) {

            if (process.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND &&
                    process.processName.equals("cn.rehu.duang")) {
                return true;
            }
        }
        return false;
    }
}
