package com.weile.casualgames.login;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.temporary.network.net.HttpCB;
import com.temporary.network.util.CMd;
import com.temporary.network.util.MD5;
import com.weile.casualgames.App;
import com.weile.casualgames.MainActivity;
import com.weile.casualgames.R;
import com.weile.casualgames.find.view.MainBodyBottomView;
import com.weile.casualgames.game.model.DoubleGame2;
import com.weile.casualgames.login.model.UserInfo;
import com.weile.casualgames.login.sethead.ImitateIphoneDialog;
import com.weile.casualgames.login.sethead.ThreadUtil;
import com.weile.casualgames.login.sethead.Utils;
import com.weile.casualgames.network.BaseHeadActivity;
import com.weile.casualgames.network.BaseMiddleOrdinaryActivity;
import com.weile.casualgames.network.requesthttp.LoginUserinfoRequest;
import com.weile.casualgames.view.base.BaseActivity;
import com.weile.casualgames.view.widget.RoundImageView;
import com.weile.casualgames.view.widget.rollpopup.CityPickerView;
import com.weile.casualgames.view.widget.rollpopup.OptionsPickerView;
import com.weile.casualgames.view.widget.rollpopup.TimePickerView;

import org.apache.log4j.chainsaw.Main;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.ui.ViewInject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


/**
 * 用户登录注册基本信息填写类
 */
public class LoginUserinfoActivity extends BaseHeadActivity implements  HttpCB {


    @BindView(id = R.id.iv_user_header, click = true)
    private RoundImageView iv_user_header;
    @BindView(id = R.id.et_userinfo_nickname)
    private EditText et_userinfo_nickname;
    @BindView(id = R.id.iv_back, click = true)
    private ImageView iv_back;
    @BindView(id = R.id.ll_back, click = true)
    private LinearLayout ll_back;
    @BindView(id = R.id.iv_submit, click = true)
    private ImageView iv_submit;

    @BindView(id = R.id.rl_userinfo_age, click = true)
    private RelativeLayout rl_userinfo_age;
    @BindView(id = R.id.tv_userinfo_age)
    private TextView tv_userinfo_age;
    @BindView(id = R.id.rl_userinfo_city, click = true)
    private RelativeLayout rl_userinfo_city;
    @BindView(id = R.id.tv_userinfo_city)
    private TextView tv_userinfo_city;

    @BindView(id = R.id.radioGroup)
    private RadioGroup radioGroup;
    @BindView(id = R.id.rb_man)
    private RadioButton rb_man;
    @BindView(id = R.id.rb_woman)
    private RadioButton rb_woman;
    @BindView(id = R.id.tv_man)
    private TextView tv_man;
    @BindView(id = R.id.tv_woman)
    private TextView tv_woman;

    private CityPickerView mCityPickerView;
    private int cityOption1 = -1, cityOption2 = -1, cityOption3 = -1;//城市选择器选中下标

    private TimePickerView mTimePickerView;
    private Date selectDate;

    private int selectSex = 1;//选中性别：默认1表示女


    private LoginUserinfoRequest loginUserinfoRequest;
    private String mainnStr;


    private UserInfo userInfo;
    private String phoneStr;
    private String passwordStr;
    private String codeStr;
    private int selectBirthYear = 0;
    private int selectBirthMonth = 0;
    private int selectBirthDay = 0;
    private int selectAge;
    private String selectProvince;
    private String selectCity;
    private String selectArea;

    private App app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void setRootView() {

        setContentView(R.layout.activity_login_userinfo);
    }

    @Override
    public void initData() {
        super.initData();
        phoneStr = getIntent().getStringExtra("phone");
        passwordStr = getIntent().getStringExtra("password");
        codeStr = getIntent().getStringExtra("code");
        selectDate = null;
        app = (App) getApplication(); // 获得Application对象
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) { //在这个函数里面用来改变选择的radioButton的数值，以及与其值相关的 //任何操作，详见下文 selectRadioBtn(); } });

                switch (checkedId) {
                    case R.id.rb_man:
                        selectSex(0);
                        break;
                    case R.id.rb_woman:
                        selectSex(1);
                        break;
                }
            }
        });

        setHttpCB(this);
        mainnStr = "LoginUserinfoActivity";
        initBroadcast(mainnStr);
        loginUserinfoRequest = new LoginUserinfoRequest(this);

    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_back:
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_user_header:
                setTheme(R.style.ActionSheetStyleIOS7);
                showActionSheet();
                break;
            case R.id.rl_userinfo_age:
                selectAge();
                break;
            case R.id.rl_userinfo_city:
                selectCity();
                break;
            case R.id.iv_submit:
                startHttp();
//                Intent intent = new Intent(this,
//                        LoginRegisteredPhoneActivity.class);
//                startActivity(intent);
                break;

        }
    }


    /**
     * 性别选择
     * <p>
     * type=0：男
     * type=1：女
     */
    public void selectSex(int type) {
        selectSex = type;
        CMd.syo("选择性别为="+type);
        if (type == 0) {
            tv_man.setTextColor(this.getResources().getColor(R.color.common_666666));
            tv_woman.setTextColor(this.getResources().getColor(R.color.common_9d9d9d));
        } else if (type == 1) {

            tv_man.setTextColor(this.getResources().getColor(R.color.common_9d9d9d));
            tv_woman.setTextColor(this.getResources().getColor(R.color.common_666666));

        }
    }

    public void selectCity() {
        mCityPickerView = new CityPickerView(this);
        // 设置点击外部是否消失
//        mCityPickerView.setCancelable(true);
        // 设置滚轮字体大小
        mCityPickerView.setTextSize(18f);
        // 设置标题
//        mCityPickerView.setTitle("我是标题");
        // 设置取消文字
//        mCityPickerView.setCancelText("我是取消文字");
        // 设置取消文字颜色
//        mCityPickerView.setCancelTextColor(Color.GRAY);
        // 设置取消文字大小
//        mCityPickerView.setCancelTextSize(14f);
        // 设置确定文字
//        mCityPickerView.setSubmitText("我是确定文字");
        // 设置确定文字颜色
//        mCityPickerView.setSubmitTextColor(Color.BLACK);
        // 设置确定文字大小
//        mCityPickerView.setSubmitTextSize(14f);
        // 设置头部背景
//        mCityPickerView.setHeadBackgroundColor(Color.RED);
        //这两个监听会冲突
//        mCityPickerView.setOnCitySelectListener(new OnCitySelectListener() {
//            @Override
//            public void onCitySelect(String str) {
//                // 一起获取
//                tv_userinfo_city.setText(str);
//                System.out.println("没进入这里吗2");
//
//            }
//
//            @Override
//            public void onCitySelect(String prov, String city, String area) {
//                // 省、市、区 分开获取
////                Log.e(TAG, "省: " + prov + " 市: " + city + " 区: " + area);
//                System.out.println("没进入这里吗1");
//            }
//        });

        mCityPickerView.setOnOptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int option1, int option2, int option3) {
//                System.out.println("没进入这里吗3");
//                ViewInject.toast("选择了：" + option1+"   "+option2+"   "+option3);
//                ViewInject.toast("选择了：" + mCityPickerView.getAllStr(option1,option2,option3));
                cityOption1 = option1;
                cityOption2 = option2;
                cityOption3 = option3;
                selectProvince = mCityPickerView.getProvinceStr(option1);
                selectCity = mCityPickerView.getCityStr(option1, option2);
                selectArea = mCityPickerView.getAreaStr(option1, option2, option3);
                if (selectCity.equals("")) {
                    selectCity = "-";
                }
                tv_userinfo_city.setText(mCityPickerView.getAllStr(option1, option2, option3));
//                mCityPickerView.
            }

        });
        if (cityOption1 != -1 && cityOption2 != -1 && cityOption3 != -1) {
            mCityPickerView.setSelectOptions(cityOption1, cityOption2, cityOption3);
        }
        mCityPickerView.show();
    }

    public void selectAge() {
        //     TimePickerView 同样有上面设置样式的方法
        mTimePickerView = new TimePickerView(this, TimePickerView.Type.YEAR_MONTH_DAY);
        // 设置是否循环
//        mTimePickerView.setCyclic(true);
        // 设置滚轮文字大小
//        mTimePickerView.setTextSize(TimePickerView.TextSize.SMALL);
        // 设置时间可选范围(结合 setTime 方法使用,必须在)
        if (selectDate == null) {
            Calendar calendar = Calendar.getInstance();
            mTimePickerView.setRange(calendar.get(Calendar.YEAR) - 200, calendar.get(Calendar.YEAR));
            // 设置选中时间
            mTimePickerView.setTime(new Date());
        } else
            mTimePickerView.setTime(selectDate);


        mTimePickerView.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date) {
                selectDate = date;
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
//                Toast.makeText(this, format.format(date), Toast.LENGTH_SHORT).show();
//                tv_userinfo_age.setText(format.format(date));
                tv_userinfo_age.setText(yearToAge(format.format(new Date()), format.format(date)) + "岁");
            }
        });

        mTimePickerView.show();
    }

    /**
     * 通过出生日期计算年龄
     */
    public int yearToAge(String nowDate, String birthDate) {
        int nowYear = 0;
        int nowMonth = 0;
        int nowDay = 0;
        selectBirthYear = 0;
        selectBirthMonth = 0;
        selectBirthDay = 0;
        selectAge = 0;
        if (nowDate.length() == 10 && birthDate.length() == 10) {
            nowYear = Integer.parseInt(nowDate.substring(0, 4));
            nowMonth = Integer.parseInt(nowDate.substring(5, 7));
            nowDay = Integer.parseInt(nowDate.substring(8, 10));
            selectBirthYear = Integer.parseInt(birthDate.substring(0, 4));
            selectBirthMonth = Integer.parseInt(birthDate.substring(5, 7));
            selectBirthDay = Integer.parseInt(birthDate.substring(8, 10));
            selectAge = nowYear - selectBirthYear;
            if (nowYear == selectBirthYear)
                return 0;
            if (nowMonth > selectBirthMonth || (nowMonth == selectBirthMonth && nowDay >= selectBirthDay))
                ;
            else
                selectAge = selectAge - 1;
        }
        return selectAge;
    }

    public void startHttp() {
        if (!submitCheck())
            return;
        loadingView.showView();

        loginUserinfoRequest.callCreateHttp(mainnStr, userInfo);
    }

    /**
     * 提交参数检查
     */
    public boolean submitCheck() {
        if (et_userinfo_nickname == null || et_userinfo_nickname.getText() == null || et_userinfo_nickname.getText().toString().equals("")) {
            ViewInject.toast("昵称不能为空!");
            return false;
        }
        if (tv_userinfo_age == null || tv_userinfo_age.getText() == null || tv_userinfo_age.getText().toString().equals("")) {
            selectAge = 1;
        }
        if (tv_userinfo_city == null || tv_userinfo_city.getText() == null || tv_userinfo_city.getText().toString().equals("")) {
            selectProvince = "福建";
            selectCity = "厦门";
            selectArea = "思明区";

        }
        if (selectAge < 1) {
            selectAge = 1;
        }
        if (selectBirthYear < 1) {
            selectBirthYear = 1;
        }
        if (selectBirthMonth < 1) {
            selectBirthMonth = 1;
        }
        if (selectBirthDay < 1) {
            selectBirthDay = 1;
        }
        userInfo = new UserInfo();
        userInfo.setAccount(phoneStr);
        userInfo.setPwd(MD5.MD5Password(passwordStr));

        userInfo.setName(et_userinfo_nickname.getText().toString());
        userInfo.setSex(selectSex);
        userInfo.setBrhYear(selectBirthYear);
        userInfo.setBrhMonth(selectBirthMonth);
        userInfo.setBrhDay(selectBirthDay);
        userInfo.setAge(selectAge);
        userInfo.setProvince(selectProvince);
        userInfo.setCity(selectCity);
        userInfo.setArea(selectArea);
        CMd.syo("测试数据内容=" + userInfo.toString());

        return true;
    }


    @Override
    public void onHttpCB(String str1, String str2, String str3) {
        CMd.syo("有返回值用户注册吗么=" + str3 + "       " + str1 + "   " + str2 + "    ");
        if (str1.equals("1"))
            ViewInject.toast("用户已存在!");
        else if (!str1.equals("2")) {
            ViewInject.toast("创建失败，请重试!");
        } else {
            ViewInject.toast("注册成功!");
            analysis_json_userinfo(str2);

            Intent intent = new Intent(this,
                    MainActivity.class);
            startActivity(intent);
            this.stopAllActivity();
        }
        loadingView.dismiss();
    }

    /**
     * 解析返回的json串
     */
    public void analysis_json_userinfo(String jionStr) {

        String[] splitStr = jionStr.split("###");
        app.getMySharedPreferences().initEdit();
        try {
            JSONObject result = new JSONObject(splitStr[0]);
            if (!result.isNull("Account"))
                app.getMySharedPreferences().setAccount(result.getString("Account"));
            if (!result.isNull("Name"))
                app.getMySharedPreferences().setName(result.getString("Name"));
            if (!result.isNull("Uid"))
                app.getMySharedPreferences().setUid(result.getInt("Uid"));
            if (!result.isNull("JiFen"))
                app.getMySharedPreferences().setJiFen(result.getInt("JiFen"));
            if (!result.isNull("Diamond"))
                app.getMySharedPreferences().setDiamond(result.getInt("Diamond"));
            if (!result.isNull("Coin"))
                app.getMySharedPreferences().setCoin(result.getInt("Coin"));
            if (!result.isNull("Head"))
                app.getMySharedPreferences().setHead(result.getString("Head"));
            if (!result.isNull("Age"))
                app.getMySharedPreferences().setAge(result.getInt("Age"));
            if (!result.isNull("BrhYear"))
                app.getMySharedPreferences().setBrhYear(result.getInt("BrhYear"));
            if (!result.isNull("BrhMonth"))
                app.getMySharedPreferences().setBrhMonth(result.getInt("BrhMonth"));
            if (!result.isNull("BrhDay"))
                app.getMySharedPreferences().setBrhDay(result.getInt("BrhDay"));
            if (!result.isNull("Province"))
                app.getMySharedPreferences().setProvince(result.getString("Province"));
            if (!result.isNull("City"))
                app.getMySharedPreferences().setCity(result.getString("City"));
            if (!result.isNull("Area"))
                app.getMySharedPreferences().setArea(result.getString("Area"));
            if (!result.isNull("Sex"))
                app.getMySharedPreferences().setSex(result.getInt("Sex"));
            if (!result.isNull("Sign"))
                app.getMySharedPreferences().setSign(result.getString("Sign"));
//            app.getMySharedPreferences().setShenJia(result.getInt("ShenJia"));

            app.getMySharedPreferences().applyValue();

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}