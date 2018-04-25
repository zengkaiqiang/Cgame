package com.module.common.util;


import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bsh.EvalError;
import bsh.Interpreter;

/**
 * Created by zjj
 */
public class StringUtil {

    /**
     * 返回当前系统时间
     */
    public static String getDataTime(String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(new Date());
    }
    public static String getDataTime(String format,Date date) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }
    /**
     * 判断给定字符串是否空白串。 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
     *
     * @param input
     * @return boolean
     */
    public static boolean isEmpty(String input) {
        if (input == null || "".equals(input))
            return true;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }
    /**
     * 字符串转整数
     *
     * @param str
     * @param defValue
     * @return
     */
    public static int toInt(String str, int defValue) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
        }
        return defValue;
    }
    /**
     * 对象转整数
     *
     * @param obj
     * @return 转换异常返回 0
     */
    public static int toInt(Object obj) {
        if (obj == null)
            return 0;
        return toInt(obj.toString(), 0);
    }
    public static String friendly_time2(String sdate) {
        String res = "";
        if (isEmpty(sdate))
            return "";

        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        String currentData = getDataTime("MM-dd");
        int currentDay = toInt(currentData.substring(3));
        int currentMoth = toInt(currentData.substring(0, 2));

        int sMoth = toInt(sdate.substring(5, 7));
        int sDay = toInt(sdate.substring(8, 10));
        int sYear = toInt(sdate.substring(0, 4));
        Date dt = new Date(sYear, sMoth - 1, sDay - 1);

        if (sDay == currentDay && sMoth == currentMoth) {
            res = "今天 / " + weekDays[getWeekOfDate(new Date())];
        } else if (sDay == currentDay + 1 && sMoth == currentMoth) {
            res = "昨天 / " + weekDays[(getWeekOfDate(new Date()) + 6) % 7];
        } else {
            if (sMoth < 10) {
                res = "0";
            }
            res += sMoth + "/";
            if (sDay < 10) {
                res += "0";
            }
            res += sDay + " / " + weekDays[getWeekOfDate(dt)];
        }

        return res;
    }
    public static String getDataTime(String sdate,String format) {
        if (isEmpty(sdate))
            return "";

        int sMoth = toInt(sdate.substring(5, 7));
        int sDay = toInt(sdate.substring(8, 10));
        int sYear = toInt(sdate.substring(0, 4));
        Date dt = new Date(sYear, sMoth - 1, sDay - 1);

        return  getDataTime(format,dt);
    }
    public static String getWeekOfDateStr(String sdate) {
        if (isEmpty(sdate))
            return "";

        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};

        int sMoth = toInt(sdate.substring(5, 7));
        int sDay = toInt(sdate.substring(8, 10));
        int sYear = toInt(sdate.substring(0, 4));
        Date dt = new Date(sYear, sMoth - 1, sDay - 1);

        return  weekDays[getWeekOfDate(dt)];
    }
    /**
     * 获取当前日期是星期几<br>
     *
     * @param dt
     * @return 当前日期是星期几
     */
    public static int getWeekOfDate(Date dt) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return w;
    }

    /*
     * 通话记录时间格式化
     *
     * */
    public static String dateFormat(long callTime) {
        String value;
        final int day_m=1440;//一天1440分钟
        long newTime = new Date().getTime();
        long duration = (newTime - callTime) / (1000*60);
        if (duration < 60){
            value = duration+"分钟前";
        }else if (duration >= 60 && duration < day_m){
            value = (duration/60)+"小时前";
        }else if (duration >= day_m && duration < day_m*2){
            value = "昨天";
        }else if (duration >= day_m*2 && duration < day_m*3){
            value = "前天";
        }else if (duration >= day_m*7){
            SimpleDateFormat sdf = new SimpleDateFormat("M月dd日");
            value = sdf.format(new Date(callTime));
        }else{
            value = (duration/day_m)+"天前";
        }
        return value;

    }

    public static String getCallTypeStr(int type){
        if(type==2){
            return "呼出";
        }else if(type==3){
            return "未接";
        }else{
            return "呼进";
        }
    }


    /**
     * 将长时间格式字符串转换为字符串 yyyy-MM-dd HH:mm:ss
     *
     * @param time
     * @param pattern
     * @return
     */
    public static String longToStrng(long time, String pattern) {
        Date date = new Date(time);
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        String dateString = formatter.format(date);
        return dateString;
    }

    /**
     * 判断是否是手机号
     *
     * @param phone
     * @return
     */
    public static boolean isPhone(String phone) {
        if (phone == null || phone.equals("")) {
            return false;
        }
        Pattern p = Pattern
                .compile("^[1][3,4,5,7,8][0-9]{9}$");
        Matcher m = p.matcher(phone);
        return m.matches();
    }

    public static boolean isPassword(String password) {
        if (password == null || password.equals("")) {
            return false;
        }
        Pattern pattern = Pattern.compile("^[\\d|a-zA-Z]{6,18}$");
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
    /***
     * @param exp 算数表达式
     * @return 根据表达式返回结果
     */
    public static String getRs(String exp) {
        if (exp.equals("")) {
            return "";
        }
        Interpreter bsh = new Interpreter();
        Number result = null;
        try {
            exp = filterExp(exp);
            result = (Number) bsh.eval(exp);
        } catch (EvalError e) {
            e.printStackTrace();
            return "";
        }
        if (result==null){
            return "";
        }
        return result.floatValue() + "";
    }
    /**
     * @param exp 算数表达式
     * @return 因为计算过程中, 全程需要有小数参与.
     */
    public static String filterExp(String exp) {
        String num[] = exp.split("");
        String temp = null;
        int begin = 0, end = 0;
        for (int i = 1; i < num.length; i++) {
            temp = num[i];
            if (temp.matches("[+-/()*]")) {
                if (temp.equals(".")) continue;
                end = i - 1;
                temp = exp.substring(begin, end);
                if (temp.trim().length() > 0 && temp.indexOf(".") < 0)
                    num[i - 1] = num[i - 1] + ".0";
                begin = end + 1;
            }
        }
        return Arrays.toString(num).replaceAll("[\\[\\], ]", "");
    }

}
