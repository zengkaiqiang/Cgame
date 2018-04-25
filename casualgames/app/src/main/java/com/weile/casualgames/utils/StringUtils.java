package com.weile.casualgames.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.telephony.TelephonyManager;

import com.weile.casualgames.App;
import com.weile.casualgames.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串操作工具包
 */
public class StringUtils {

    public static boolean isFile(String path) {

        return new File(path).isFile();
    }

    public static String getRealPath() {
        String sdDir = null;
        if (Environment.getExternalStorageState()
                .equals(Environment.MEDIA_MOUNTED)) {
            sdDir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Rehu/";//获取跟目录
            File src = new File(sdDir);
            if (!src.exists()) {
                src.mkdirs();
            }
            return sdDir;
        } else {
            File src = new File("/sdcard/Rehu/");
            if (!src.exists()) {
                src.mkdirs();
            }
            return src.getAbsolutePath();
        }
    }

    public static String getRealPath(String fileName) {
        return getRealPath() + fileName;
    }

    /**
     * json 本地保存
     *
     * @param response
     */
    public static void SaveLoca(String response) {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            File file_name = null;
            try {
                file_name = new File(getRealPath() + "weile.txt");
                if (!file_name.exists()) {
                    File dir = new File(file_name.getParent());
                    dir.mkdirs();
                    file_name.createNewFile();
                }
                LogUtils.E(file_name.toString());
            } catch (final IOException e1) {
                e1.printStackTrace();
            }
            try {
                final OutputStream os = new FileOutputStream(file_name);
                os.write(response.getBytes());
                os.close();
                LogUtils.E("save finish!!!!!");
            } catch (final Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 将一个InputStream流转换成字符串
     *
     * @param is
     * @return
     */
    public static String toConvertString(InputStream is) {
        StringBuffer res = new StringBuffer();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader read = new BufferedReader(isr);
        try {
            String line;
            line = read.readLine();
            while (line != null) {
                res.append(line);
                line = read.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != isr) {
                    isr.close();
                    isr.close();
                }
                if (null != read) {
                    read.close();
                    read = null;
                }
                if (null != is) {
                    is.close();
                    is = null;
                }
            } catch (IOException e) {
            }
        }
        return res.toString();
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static String getMyUUID(Context context) {
        String MyUUID = PreferencesUtils.getValueFromSPMap(context,
                AppKeys.UUID);
        if (StringUtils.isEmpty(MyUUID)) {
            TelephonyManager tm = (TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);
            String tmDevice, tmSerial, tmPhone, androidId;
            tmDevice = "" ;//+ tm.getDeviceId();
            tmSerial = "" ;//+ tm.getSimSerialNumber();
            androidId = ""
                    + android.provider.Settings.Secure.getString(
                    context.getContentResolver(),
                    android.provider.Settings.Secure.ANDROID_ID);
            UUID deviceUuid = new UUID(androidId.hashCode(),
                    ((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
            String uniqueId = deviceUuid.toString();
            PreferencesUtils.putValueToSPMap(context, AppKeys.UUID, uniqueId);
            LogUtils.E("uuid=" + uniqueId);
            return uniqueId;
        } else {
            return MyUUID;
        }
    }

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

    public static int getNotificationID(long currentTime) {
        String longtime = String.valueOf(currentTime);
        int l = longtime.length() - 1;
        longtime = longtime.substring(l - 9, l);
        return Integer.valueOf(longtime);
    }

    public static String getTimer(String timers) {
        return "2018-04-10";
//        SimpleDateFormat utcFormater = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
//        utcFormater.setTimeZone(TimeZone.getTimeZone("UTC"));
//        Date gpsUTCDate = null;
//        try {
//            gpsUTCDate = utcFormater.parse(timers);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        LogUtils.E(timers + "utc2Local=2=" + gpsUTCDate.getTime());
//        return getTimeIntervalDesc(System.currentTimeMillis() - gpsUTCDate.getTime(), App.getContext());
    }

    public static String getTimeIntervalDesc(long _time, Context cont) {
        int s = (int) (_time / 1000);
//        if (s < 30) {
//            return "刚刚";
//        }
        if (s < 60) {
            return cont.getString(R.string.mypost_timer_now);
        }
        if (s < 3600) {
            int m = (int) (s / 60);

            return m + cont.getString(m > 1 ? R.string.mypost_timer_minutes : R.string.mypost_timer_minute);
        }
        if (s < 86400) {
            int h = (int) (s / 3600);
            return h + cont.getString(h > 1 ? R.string.mypost_timer_hours : R.string.mypost_timer_hour);
        }else{
                int d = (int) (s / 86400);
                return d + cont.getString(d > 1 ? R.string.mypost_timer_days : R.string.mypost_timer_day);
        }
//        if (s < 86400 * 30) {
//            int d = (int) (s / 86400);
//            return d + cont.getString(d > 1 ? R.string.mypost_timer_days : R.string.mypost_timer_day);
//        }
//        if (s < 86400 * 365) {
//            int m = (int) (s / (86400 * 30));
//            return m + cont.getString(R.string.mypost_timer_months);
//        }
//        int y = (int) (s / (86400 * 365));
//        return y + cont.getString(R.string.mypost_timer_years);
    }

    public static String getStoreyText(int position, Context cont) {
        String storey = "";
        if (position < 4) {
            switch (position) {
                case 1:
                    storey = cont.getString(R.string.topic_detail_storey_1);
                    break;
                case 2:
                    storey = cont.getString(R.string.topic_detail_storey_2);
                    break;
                case 3:
                    storey = cont.getString(R.string.topic_detail_storey_3);
                    break;
            }
        } else {
            storey = position + cont.getString(R.string.topic_detail_storey);
        }
        return storey;
    }

    /**
     * utc 转时间戳
     *
     * @param ts
     * @return 时间戳
     * long epoch = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("01/01/1970 01:00:00")
     */
    public static String getUTC2TimeStamp(String ts) {
        SimpleDateFormat utcFormater = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        utcFormater.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date gpsUTCDate = null;
        try {
            gpsUTCDate = utcFormater.parse(ts);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        LogUtils.E(ts + "utc2Local=2=" + gpsUTCDate.getTime());
        return String.valueOf(gpsUTCDate.getTime());
    }

    /**
     * utc时间 转yyyy-MM-dd
     *
     * @param dateTime utc时间
     * @return
     */
    public static String getFormatedDateTime(String dateTime) {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return sDateFormat.format(new Date(Long.parseLong(getUTC2TimeStamp(dateTime))));
    }

    public static String getCurrentDate(String dateTime) {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        return sDateFormat.format(new Date(Long.parseLong(getUTC2TimeStamp(dateTime))));
    }

    public static String getLongDateTime(int year, int month, int day) {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = sDateFormat.getCalendar();
        c.set(year, month, day);
        return String.valueOf(c.getTime().getTime());
    }


    public static String getUTC2TimeStamp_t(String ts) {
        SimpleDateFormat sdfs = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSzzz");
        ts = ts.replace("Z", TimeZone.getDefault().getDisplayName(false, TimeZone.SHORT));
        long millionSeconds = 0;
        try {
            millionSeconds = sdfs.parse(ts).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        LogUtils.E("getUTC2TimeStamps=" + ts + "==" + millionSeconds);
        return String.valueOf(millionSeconds);
    }


    /**
     * 正则－－邮箱验证
     *
     * @param email 邮箱地址
     * @return
     */
    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }


    private static double EARTH_RADIUS = 6378.137;

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    public static double GetDistance(double lat1, double lng1, double lat2, double lng2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
                Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000;
        return s; //千米
    }


    public static String getSuitableDistance(double lat1, double lng1, double lat2, double lng2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
                Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000;
        if (s > 0){
            return String.valueOf(Math.round(s)).concat("km");
        }else{
            long dis = Math.round(s * 1000);
            return String.valueOf(dis < 100 ? 100 : dis).concat("m");
        }
    }

    public static float getMapZoom(double maxDistance) {
//        if (maxDistance < 10) {
//            return 14;
//        }
        if (maxDistance < 50) {
            return 12.5f;
        }
        if (maxDistance < 200) {
            return 11f;
        }
        if (maxDistance < 500) {
            return 9f;
        }
        if (maxDistance < 1000) {
            return 8.5f;
        }
        if (maxDistance < 2000) {
            return 8;
        }
        if (maxDistance < 5000) {
            return 7;
        }
        if (maxDistance < 8000) {
            return 6;
        }
        if (maxDistance < 15000)
            return 4.7f;
        if (maxDistance < 100000) {
            return 4.5f;
        }
        return 12;
    }


    public static String getVersion(Context context)//获取版本号
    {
        try {
            PackageInfo pi = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return context.getString(R.string.version_unknown);
        }
    }

    public static String getTimeHM() {
        final Calendar mCalendar = Calendar.getInstance();
        mCalendar.setTimeInMillis(System.currentTimeMillis());
        int mHour = mCalendar.get(Calendar.HOUR);
        int mMinuts = mCalendar.get(Calendar.MINUTE);
        return mHour + ":" + mMinuts;
    }

    public static String getStringData() {
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone(TimeZone.getDefault().getDisplayName(false, TimeZone.SHORT)));
        String mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
        if ("1".equals(mWay)) {
            mWay = "天";
        } else if ("2".equals(mWay)) {
            mWay = "一";
        } else if ("3".equals(mWay)) {
            mWay = "二";
        } else if ("4".equals(mWay)) {
            mWay = "三";
        } else if ("5".equals(mWay)) {
            mWay = "四";
        } else if ("6".equals(mWay)) {
            mWay = "五";
        } else if ("7".equals(mWay)) {
            mWay = "六";
        }
        return StringUtils.getTimeHM() + " \n星期" + mWay;
    }

    /**
     * 百分比
     *
     * @param y
     * @param total
     * @return
     */
    public static String myPercent(int y, int total) {
        String baifenbi = "";//接受百分比的值
        if (y == 0) {
            baifenbi = "0.00%";
        } else {
            double baiy = y * 1.0;
            double baiz = total * 1.0;
            double fen = baiy / baiz;
            DecimalFormat df1 = new DecimalFormat("##.00%");    //##.00%   百分比格式，后面不足2位的用0补齐
            //baifenbi=nf.format(fen);
            baifenbi = df1.format(fen);
        }
        return baifenbi;
    }

    public static String getMonthDays(String ts) {
        ts = ts.replace("Z", " UTC");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
        try {
            Date dt = sdf.parse(ts);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar c = sdf.getCalendar();
//        String str = "";
        int mon = c.get(Calendar.MONTH) + 1;
        int days = c.get(Calendar.DAY_OF_MONTH);
        String str = mon >= 10 ? mon + "" : "0" + mon;
        str = str + "/";
        str = str + (days >= 10 ? days + "" : "0" + days);
        return str;
    }

    public static String getMonthDays(long time) {
        final Calendar c = Calendar.getInstance();
        c.setTimeInMillis(time);
        int mon = c.get(Calendar.MONTH) + 1;
        int days = c.get(Calendar.DAY_OF_MONTH);
        String str = mon >= 10 ? mon + "" : "0" + mon;
        str = str + "/";
        str = str + (days >= 10 ? days + "" : "0" + days);
        return str;
    }

    /**
     * 获取经纬度距离
     *

     * @return
     */
   /* public static String GetDistances(double lat1s, double lng1s, double lat2s, double lng2s) {
        int s = (int) AMapUtils.calculateLineDistance(new LatLng(lat1s, lng1s), new LatLng(lat2s, lng2s));
        String str = "100m";
        if (s <= 100) {
            str = "100m";
        }
        if (s < 1000 && s > 100) {
            str = s + "m";
        }
        if (s > 1000) {
            str = s / 1000 + "km";
        }
        return str; //千米
    }*/

   /* public static String GetDistances(String s, String s1, double latitude, double longitude) {
        return StringUtils.GetDistances(Double.valueOf(s), Double.valueOf(s1), latitude, longitude);
    }*/

    public static String utc2Local(String utcTime) {
        SimpleDateFormat utcFormater = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        utcFormater.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date gpsUTCDate = null;
        try {
            gpsUTCDate = utcFormater.parse(utcTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat localFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        localFormater.setTimeZone(TimeZone.getDefault());
        String localTime = localFormater.format(gpsUTCDate.getTime());
        SimpleDateFormat utcFormaters = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date rgpsUTCDate = null;
        try {
            rgpsUTCDate = utcFormaters.parse(localTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        LogUtils.E("utc2Local=2=" + gpsUTCDate.getTime() + "////" + rgpsUTCDate.getTime());
        return localTime;
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
        if (obj == null) return 0;
        return toInt(obj.toString(), 0);
    }

    private final static String phonePattern = "^[1][34578][0-9]{9}$";

    /**
     * 正则判断手机号
     *
     * @param phone phone number
     * @return false if not Validate
     */
    public static boolean isPhoneNum(String phone) {
        Pattern p = Pattern.compile(phonePattern);
        Matcher m = p.matcher(phone);
        return m.matches();
    }

    public static String getDueTimer(String timers) {
        SimpleDateFormat utcFormater = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        utcFormater.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date gpsUTCDate = null;
        try {
            gpsUTCDate = utcFormater.parse(timers);
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        LogUtils.E(timers + "utc2Local=2=" + gpsUTCDate.getTime());
        if (gpsUTCDate.getTime() > System.currentTimeMillis()) {
            return getDueTimeIntervalDesc(gpsUTCDate.getTime() - System.currentTimeMillis(), App.getContext());
        } else {
            return "即将消失";
        }
    }

    public static String getDueTimeIntervalDesc(long _time, Context cont) {
        int s = (int) (_time / 1000);
//        if (s < 30) {
//            return "刚刚";
//        }
//        if (s < 60) {
//            return cont.getString(R.string.period_now);
//        }
        if (s < 3600) {
//            int m = (int) (s / 60);
            return "即将消失";
        }
        if (s < 86400) {
            int h = (int) (s / 3600);
            return h + "小时消失";
        }
        if (s < 86400 * 30) {
            int d = (int) (s / 86400);
            return d + "天消失";
        }
        if (s < 86400 * 365) {
            int m = (int) (s / (86400 * 30));
            return m + "个月消失";
        }
        int y = (int) (s / (86400 * 365));
        return y + "年消失";
    }

    /**
     * 返回资源字符串的自定义通用方法
     * @param context
     * @param resId
     * @return
     */
    public static String getString(Context context, int resId){
        return context.getResources().getString(resId);
    }
}
