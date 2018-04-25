package com.weile.casualgames.view.widget.pulltorefresh;


import com.weile.casualgames.utils.LogUtils;

public class Utils {

    static final String LOG_TAG = "PullToRefresh";

    public static void warnDeprecation(String depreacted, String replacement) {
        LogUtils.E("You're using the deprecated " + depreacted + " attr, please switch over to " + replacement);
    }

}
