package com.module.common.util;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

/**
 * 触发后倒计时
 */
public class CountTimer extends CountDownTimer {

    private Context context;
    private TextView countTextView;

    /**
     * millisInFuture：倒计时的总时间，单位为毫秒
     * countDownInterval：倒计时的时间间隔，单位为毫秒
     * */
    public CountTimer(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }

    public void setCountTextView(Context context, TextView countTextView) {
        System.out.println("进入倒计时开始设置");
        this.context = context;
        this.countTextView = countTextView;
    }

    /**
     * 倒计时过程中调用
     *
     * @param millisUntilFinished
     */
    @Override
    public void onTick(long millisUntilFinished) {
        System.out.println("开始倒计时");
        //处理后的倒计时数值
        int time = (int) (Math.round((double) millisUntilFinished / 1000) - 1);
        countTextView.setText("重发验证码(" + String.valueOf(time) + "秒)");
        //设置倒计时中的按钮外观
        countTextView.setClickable(false);//倒计时过程中将按钮设置为不可点击
        countTextView.setTextColor(ContextCompat.getColor(context, 999999));
    }

    /**
     * 倒计时完成后调用
     */
    @Override
    public void onFinish() {
        //设置倒计时结束之后的按钮样式

        countTextView.setTextColor(ContextCompat.getColor(context, android.R.color.holo_blue_light));
        countTextView.setText("重发验证码");
        countTextView.setClickable(true);
    }

}