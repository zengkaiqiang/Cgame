package com.weile.casualgames.game.util;

import android.webkit.JavascriptInterface;

import org.kymjs.kjframe.ui.ViewInject;

/**
 * JS通过WebView调用 Android 代码
 * */
public class AndroidtoJs extends Object {

    // 定义JS需要调用的方法
    // 被JS调用的方法必须加入@JavascriptInterface注解
    @JavascriptInterface
    public void AddFrind(int uid,int oid)// 加好友方法名   用户的Id,好友的Id
    {
        ViewInject.toast("加好友方法名="+uid+"   "+oid);
        System.out.println("加好友方法名="+uid+"   "+oid);
    }

    @JavascriptInterface
    public void gameOver(int uid,int oid,int state)//比赛结果方法名     用户的Id,对方的Id 比赛情况state 0 输 1平 2 胜利
    {
        ViewInject.toast("比赛结果方法名="+uid+"   "+oid+"    "+state);
        System.out.println("比赛结果方法名="+uid+"   "+oid+"    "+state);
    }

    @JavascriptInterface
    public void giveup(int uid,int oid)// 认输方法名   用户的Id,对方的Id
    {
        ViewInject.toast("认输方法名="+uid+"   "+oid);
        System.out.println("认输方法名="+uid+"   "+oid);
    }
}
