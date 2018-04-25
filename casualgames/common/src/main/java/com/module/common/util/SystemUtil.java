package com.module.common.util;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.weile.casualgames.common.R;


/**
 * Author: zjj
 */
public class SystemUtil {
    private static final String TAG = "SystemUtil";

    /**
     * 打开一个Activity
     *
     * @param ctx
     * @param intent
     */
    public static void startActivity(Context ctx, Intent intent) {
        if (intent == null) {
            MyViewUtil.showToast(ctx, ctx.getResources().getString(R.string.component_dockbar_null_intent));
            return;
        }
        try {
            ctx.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            MyViewUtil.showToast(ctx, ctx.getResources().getString(R.string.component_activity_not_found));
            Log.e(TAG, "Unable to launch. intent=" + intent, e);
        } catch (SecurityException e) {
            MyViewUtil.showToast(ctx, ctx.getResources().getString(R.string.component_activity_not_found));
            Log.e(TAG, e.getMessage());
        } catch (Exception e) {
            MyViewUtil.showToast(ctx, ctx.getResources().getString(R.string.component_activity_not_found));
            e.printStackTrace();
        }
    }

    /**
     * 接收Activity返回结果
     *
     * @param ctx
     * @param intent
     * @param requestCode
     */
    public static void startActivityForResult(Activity ctx, Intent intent, int requestCode) {
        try {
            ctx.startActivityForResult(intent, requestCode);
        } catch (ActivityNotFoundException e) {
            MyViewUtil.showToast(ctx, ctx.getResources().getString(R.string.component_activity_not_found));
        } catch (SecurityException e) {
            MyViewUtil.showToast(ctx, ctx.getResources().getString(R.string.component_activity_not_found));
            Log.e(TAG, e.getMessage());
        } catch (Exception e) {
            MyViewUtil.showToast(ctx, ctx.getResources().getString(R.string.component_activity_not_found));
            e.printStackTrace();
        }
    }



    /**
     * 查看应用程序详细信息
     *
     * @param ctx
     * @param packageName
     */
    public static void showAppDetails(Context ctx, String packageName) {
        Intent intent = new Intent();
        if (Build.VERSION.SDK_INT >= 9) {// SDK 2.3以上
            intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            Uri uri = Uri.fromParts("package", packageName, null);
            intent.setData(uri);
        } else {
            intent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            intent.putExtra("com.android.settings.ApplicationPkgName", packageName);
            intent.putExtra("pkg", packageName); // for android 2.2
        }
        ctx.startActivity(intent);
    }

    /**
     * 2  * 获取版本名称
     * 3  * @return 当前应用的版本名称
     * 4
     */
    public static String getVersion(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            return info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return "V1.1.0";
        }
    }

    /**
     * 2  * 获取版本号
     * 3  * @return 当前应用的版本号
     * 4
     */
    public static int getVersionCode(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            return info.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }
    /**
     * 2  * 获取版本号
     * 3  * @return 当前系统的版本号
     * 4
     */
    public static int getSystemVersion() {
       return android.os.Build.VERSION.SDK_INT;
    }
    /**
     * 检测网络是否可用
     *
     * @return
     */
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return ni != null && ni.isConnectedOrConnecting();
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * <<<<<<< HEAD
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }


    /**
     * 获取屏幕高度
     */
    public static int getScreenHeigth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay().getHeight();
    }

    /**
     * 获取屏幕宽度
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay().getWidth();
    }

    /**
     * 获取Imei
     *
     * @param context
     * @return
     */
    public static String getImei(Context context) {
        String imei = "";
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

            imei = telephonyManager.getDeviceId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imei;
    }



    /**
     * 计算listview高度
     *
     * @param listView
     */
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) { // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0); // 计算子项View 的宽高
            totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }

}
