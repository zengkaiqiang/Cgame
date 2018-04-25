package com.weile.casualgames.utils;

import android.util.Log;

/**
 * log打印工具
 */
public class LogUtils {

    private static boolean debug = true;
//        private static boolean debug = false; //发布模式
    private static String tag = "weile";

    public static void E(String msg) {
        if (debug) {
            Log.e(tag, msg);
        }
    }

    public static void i(String msg) {
        if (debug) {
            Log.i(tag, msg);
        }
    }

    public static void v(String msg) {
        if (debug) {
            Log.v(tag, msg);
        }
    }

    public static void d(String msg) {
        if (debug) {
            Log.d(tag, msg);
        }
    }

    public static void w(String msg) {
        if (debug) {
            Log.w(tag, msg);
        }
    }
}
