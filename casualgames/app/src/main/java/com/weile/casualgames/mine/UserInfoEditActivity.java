package com.weile.casualgames.mine;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.temporary.network.net.HttpCB;
import com.temporary.network.util.CMd;
import com.weile.casualgames.App;
import com.weile.casualgames.R;
import com.weile.casualgames.mine.model.EditUserInfo;
import com.weile.casualgames.mine.util.UserDataManager;
import com.weile.casualgames.net.FormFile;
import com.weile.casualgames.net.NetApi;
import com.weile.casualgames.net.SocketHttpRequester;
import com.weile.casualgames.network.BaseHeadActivity;
import com.weile.casualgames.network.requesthttp.MineRequest;
import com.weile.casualgames.view.widget.ReboundScrollview;
import com.weile.casualgames.view.widget.RoundImageView;
import com.weile.casualgames.view.widget.rollpopup.CityPickerView;
import com.weile.casualgames.view.widget.rollpopup.OptionsPickerView;
import com.weile.casualgames.view.widget.rollpopup.TimePickerView;

import org.json.JSONException;
import org.json.JSONObject;
import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.ui.ViewInject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class UserInfoEditActivity extends BaseHeadActivity implements HttpCB {

    @BindView(id = R.id.iv_user_edit_header, click = true)
    private RoundImageView iv_user_edit_header;
    @BindView(id = R.id.iv_back, click = true)
    private ImageView iv_back;
    @BindView(id = R.id.ll_back, click = true)
    private LinearLayout ll_back;
    @BindView(id = R.id.rl_userinfo_edit_nickname, click = true)
    private RelativeLayout rl_userinfo_edit_nickname;
    @BindView(id = R.id.et_userinfo_edit_nickname, click = true)
    private EditText et_userinfo_edit_nickname;
    @BindView(id = R.id.rl_userinfo_edit_sex, click = true)
    private RelativeLayout rl_userinfo_edit_sex;
    @BindView(id = R.id.userinfo_edit_sex, click = true)
    private TextView userinfo_edit_sex;


    @BindView(id = R.id.rl_userinfo_edit_age, click = true)
    private RelativeLayout rl_userinfo_edit_age;
    @BindView(id = R.id.userinfo_edit_age)
    private TextView userinfo_edit_age;
    @BindView(id = R.id.rl_userinfo_edit_city, click = true)
    private RelativeLayout rl_userinfo_edit_city;
    @BindView(id = R.id.tv_userinfo_edit_city)
    private TextView tv_userinfo_edit_city;
    @BindView(id = R.id.rl_userinfo_edit_signature, click = true)
    private RelativeLayout rl_userinfo_edit_signature;
    @BindView(id = R.id.userinfo_edit_signature, click = true)
    private TextView userinfo_edit_signature;
    @BindView(id = R.id.rl_userinfo_edit_worth, click = true)
    private RelativeLayout rl_userinfo_edit_worth;
    @BindView(id = R.id.userinfo_edit_worth, click = true)
    private TextView userinfo_edit_worth;
    @BindView(id = R.id.userinfo_edit_wealth)
    private TextView userinfo_edit_wealth;
    @BindView(id = R.id.scrollView)
    private ReboundScrollview scrollView;


    private CityPickerView mCityPickerView;
    private int cityOption1 = -1, cityOption2 = -1, cityOption3 = -1;//城市选择器选中下标

    private TimePickerView mTimePickerView;
    private Date selectDate;

//    private int selectSex = 1;//选中性别：默认1表示女


    private int wealth;

    private App app;
    private String mainnStr;
    private MineRequest mineRequest;
    private EditUserInfo editUserInfo;
    private UserDataManager userDataManager;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_user_info_edit);
    }


    @Override
    public void initData() {
        super.initData();
        app = (App) getApplication(); // 获得Application对象
        setImageView(iv_user_edit_header);
        userDataManager = UserDataManager.getInstance(this);

        wealth = app.getMySharedPreferences().getCoin();
        getEditUserInfoRefresh();

        setHttpCB(this);
        mainnStr = "UserInfoEditActivity";
        initBroadcast(mainnStr);
        mineRequest = new MineRequest(this);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_back:
            case R.id.iv_back:
                finish();
                break;

            case R.id.iv_user_edit_header:
                setTheme(R.style.ActionSheetStyleIOS7);
                showActionSheet();
                break;
            case R.id.et_userinfo_edit_nickname:
            case R.id.rl_userinfo_edit_nickname:
                Intent intent3 = new Intent(this,
                        NickNameEditActivity.class);
                startActivity(intent3);
                break;
            case R.id.userinfo_edit_sex:
            case R.id.rl_userinfo_edit_sex:
                Intent intent4 = new Intent(this,
                        SexEditActivity.class);
                startActivity(intent4);
                break;
            case R.id.rl_userinfo_edit_age:
                selectAge();
                break;
            case R.id.rl_userinfo_edit_city:
                selectCity();
                break;
            case R.id.userinfo_edit_signature:
            case R.id.rl_userinfo_edit_signature:
                Intent intent = new Intent(this,
                        SignEditActivity.class);
                startActivity(intent);
                break;
            case R.id.userinfo_edit_worth:
            case R.id.rl_userinfo_edit_worth:
                Intent intent2 = new Intent(this,
                        WorthEditActivity.class);
                startActivity(intent2);
                break;


        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (userDataManager != null) {
            getEditUserInfoRefresh();
        }
    }

    public void startHttp() {

        loadingView.showView();

        mineRequest.callModifyUserHttp(mainnStr, editUserInfo);
    }

    @Override
    public void selectHeadClick() {
        super.selectHeadClick();
        startHeadHttp();
    }

    public void startHeadHttp() {

//        loadingView.showView();
        app.getMyExecutorService().initCachedThreadPool(UpUserHeadRun);

    }

    Runnable UpUserHeadRun = new Runnable() {

        @Override
        public void run() {


            uploadUserHead(file);

        }
    };


    public void uploadUserHead(File file) {
//        CMd.syo("有到这里执行uploadUserHead吗");
        try {

            String requestUrl = NetApi.UPLOAD_Head;
            Map<String, String> params = new HashMap<String, String>();
            FormFile formfile = new FormFile(file.getName(), file, "FileData", "multipart/form-data");
            String result = SocketHttpRequester.post(requestUrl, params, formfile);
            CMd.syo("结束到达这里=" + result);
            postMsg(result, 101);
        } catch (Exception e) {
            e.printStackTrace();
//            CMd.syo("上传错误消息返回=" + e.getMessage());
        }
    }


    public void selectCity() {
        mCityPickerView = new CityPickerView(this);

        // 设置滚轮字体大小
        mCityPickerView.setTextSize(18f);

        mCityPickerView.setOnOptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int option1, int option2, int option3) {
//                System.out.println("没进入这里吗3");
//                ViewInject.toast("选择了：" + option1+"   "+option2+"   "+option3);
//                ViewInject.toast("选择了：" + mCityPickerView.getAllStr(option1,option2,option3));
                cityOption1 = option1;
                cityOption2 = option2;
                cityOption3 = option3;
                editUserInfo.setProvince(mCityPickerView.getProvinceStr(option1));
                editUserInfo.setCity(mCityPickerView.getCityStr(option1, option2));
                editUserInfo.setArea(mCityPickerView.getAreaStr(option1, option2, option3));
                if (editUserInfo.getCity().equals("")) {
                    editUserInfo.setCity("-");
                }
                tv_userinfo_edit_city.setText(mCityPickerView.getAllStr(option1, option2, option3));
                startPositionRunnable();

                startHttp();
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
                userDataManager.yearToAge(editUserInfo, format.format(new Date()), format.format(date));
                startAgeRunnable();

                userinfo_edit_age.setText(editUserInfo.getAge() + "岁");
                startHttp();
            }
        });

        mTimePickerView.show();
    }

    /**
     * 获取本地数据EditUserInfo的数据并更新界面显示
     */
    public void getEditUserInfoRefresh() {
        editUserInfo = userDataManager.getEditUserInfo();

        et_userinfo_edit_nickname.setText(editUserInfo.getName());
        if (editUserInfo.getSex() == 0) {
            userinfo_edit_sex.setText("男");
        } else {
            userinfo_edit_sex.setText("女");
        }
        userinfo_edit_age.setText(editUserInfo.getAge() + "岁");
        tv_userinfo_edit_city.setText(editUserInfo.getProvince() + " " + editUserInfo.getCity() + " " + editUserInfo.getArea());
        userinfo_edit_signature.setText(editUserInfo.getSign());
        userinfo_edit_wealth.setText(wealth + "金币");
        userinfo_edit_worth.setText(editUserInfo.getShenJia() + "朵");
    }

    public void startPositionRunnable() {
        app.getMyExecutorService().initCachedThreadPool(new Runnable() {
            @Override
            public void run() {
                app.getMySharedPreferences().initEdit();
                app.getMySharedPreferences().setProvince(editUserInfo.getProvince());
                app.getMySharedPreferences().setCity(editUserInfo.getCity());
                app.getMySharedPreferences().setArea(editUserInfo.getArea());
                app.getMySharedPreferences().applyValue();
            }
        });

    }

    public void startAgeRunnable() {
        app.getMyExecutorService().initCachedThreadPool(new Runnable() {
            @Override
            public void run() {
                app.getMySharedPreferences().initEdit();
                app.getMySharedPreferences().setAge(editUserInfo.getAge());
                app.getMySharedPreferences().setBrhYear(editUserInfo.getBrhYear());
                app.getMySharedPreferences().setBrhMonth(editUserInfo.getBrhMonth());
                app.getMySharedPreferences().setBrhDay(editUserInfo.getBrhDay());
                app.getMySharedPreferences().applyValue();
            }
        });

    }


    @Override
    public void onHttpCB(String str1, String str2, String str3) {
        CMd.syo("有返回值用户信息修改么=" + str3 + "       " + str1 + "   " + str2 + "    ");
        if (str2.equals("1")) {
            if (str1.equals("0"))
                ViewInject.toast("修改成功!");
            else {
                ViewInject.toast("修改失败，请重试!");
            }
        } else if (str2.equals("2")) {
            if (str1.equals("0"))
                ViewInject.toast("头像修改成功!");
            else {
                ViewInject.toast("修改失败，请重试!");
            }
        }

//            analysis_json_userinfo(str2);
//
//            Intent intent = new Intent(this,
//                    MainActivity.class);
//            startActivity(intent);

        loadingView.dismiss();
    }


    /**
     * handler 类处理
     *
     * @return
     */
    public void postMsg(String s, int what) {
        Message msg = Message.obtain();
        msg.obj = s;
        msg.what = what;
        myHandler.sendMessage(msg);
    }

    /**
     * 处理Hander消息
     */

    private Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            final String m = (String) msg.obj;
            int what = msg.what;
            switch (what) {
                case 101:// 上传图片返回值
                    String HeadStr = "";
                    try {
                        if (m != null && !m.equals("")) {
                            JSONObject result = new JSONObject(m);
                            if (!result.isNull("Url"))
                                HeadStr = result.getString("Url");
//                            CMd.syo("成功上传头像并返回url进行头像地址修改="+HeadStr);
                            mineRequest.callModifyUserHeadHttp(mainnStr, editUserInfo.getUid(), HeadStr);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

            }
        }
    };


}
