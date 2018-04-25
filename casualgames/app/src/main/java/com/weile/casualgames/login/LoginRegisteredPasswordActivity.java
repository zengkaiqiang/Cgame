package com.weile.casualgames.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.module.common.util.CountTimer;
import com.module.common.util.StringUtil;
import com.module.common.util.ViewUtil;
import com.temporary.network.net.HttpCB;
import com.temporary.network.util.CMd;
import com.weile.casualgames.App;
import com.weile.casualgames.MainActivity;
import com.weile.casualgames.R;
import com.weile.casualgames.game.model.DoubleGame2;
import com.weile.casualgames.login.utils.WordReplacement;
import com.weile.casualgames.login.view.VerificationCodeView;
import com.weile.casualgames.network.BaseMiddleOrdinaryActivity;
import com.weile.casualgames.network.requesthttp.LoginUserinfoRequest;
import com.weile.casualgames.utils.MySharedPreferences;
import com.weile.casualgames.view.base.BaseActivity;
import com.weile.casualgames.view.widget.ThumbnailView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.ui.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户登录注册过 输入密码类
 */
public class LoginRegisteredPasswordActivity extends BaseMiddleOrdinaryActivity implements HttpCB {

    @BindView(id = R.id.iv_back, click = true)
    private ImageView iv_back;
    @BindView(id = R.id.ll_back, click = true)
    private LinearLayout ll_back;
    @BindView(id = R.id.et_password)
    private EditText et_password;
    @BindView(id = R.id.tv_forgot_password, click = true)
    private TextView tv_forgot_password;
    @BindView(id = R.id.iv_next, click = true)
    private ThumbnailView iv_next;

    private String phoneStr;
    private String passwordStr;

    private App app;

    private CountTimer countTimer;

    private LoginUserinfoRequest loginUserinfoRequest;
    private String mainnStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void setRootView() {

        setContentView(R.layout.activity_login_registered_password);
    }


    @Override
    public void initData() {
        super.initData();
        phoneStr = getIntent().getStringExtra("phone");
        app = (App) getApplication(); // 获得Application对象
//        System.out.println("输入的电话号码是多少="+phoneStr);
//        startCountDown();

        et_password.setTransformationMethod(new WordReplacement());
        et_password.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 输入的内容变化的监听

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                // 输入后的监听
                if (s != null && s.toString().length() > 0) {
                    iv_next.setImageResource(R.mipmap.login_next_pressed);
                } else {
                    iv_next.setImageResource(R.mipmap.login_next_normal);
                }

            }
        });
        ViewUtil.showSoftInputFromWindow(et_password, this);
//        CommonViewUtil.showSoftInputFromWindow(this,icv);
//        codeView = new VerificationCodeView(this);

        setHttpCB(this);
        mainnStr = "LoginRegisteredPasswordActivity";
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
            case R.id.tv_forgot_password:
                Intent intent = new Intent(this,
                        LogiForgotPasswordActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_next:
                nextBtnClick();
//                Intent intent = new Intent(this,
//                        LogiForgotPasswordActivity.class);
//                startActivity(intent);
                break;

        }
    }

    /**
     * 接下来按钮的响应事件
     */
    public void nextBtnClick() {

//        System.out.println("输入的检验验证码2是多少="+codeStr);
        if (et_password == null || et_password.getText() == null) {
            ViewInject.toast("错误请检查");
        } else if (StringUtil.isEmpty(et_password.getText().toString())) {
            ViewInject.toast("密码不能为空!");
        } else {
            startHttp();

        }
    }

    public void startHttp() {

        loadingView.showView();

        loginUserinfoRequest.callLoginHttp(mainnStr, 2, phoneStr, et_password.getText().toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countTimer != null) {
            countTimer.cancel();
            countTimer = null;
        }
    }

    @Override
    public void onHttpCB(String str1, String str2, String str3) {
        CMd.syo("有返回值用户登录么=" + str3 + "       " + str1 + "   " + str2 + "    ");
        if (str1.equals("1"))
            ViewInject.toast("账号或密码错误!");
        else if (!str1.equals("2")) {
            ViewInject.toast("登录失败，请重试!");
        } else {
            ViewInject.toast("登录成功!");
            analysis_json_userinfo(str2);
            Intent intent = new Intent(this,
                    MainActivity.class);
            startActivity(intent);
//            finish();
            this.stopAllActivity();
        }
        loadingView.dismiss();
    }


    /**
     * 解析返回的json串
     */
    public void analysis_json_userinfo(String jionStr) {
        String gListStr = "";

        String[] splitStr = jionStr.split("###");
        app.getMySharedPreferences().initEdit();
        try {
            JSONObject result = new JSONObject(splitStr[0]);
            if (!result.isNull("Name"))
                app.getMySharedPreferences().setName(result.getString("Name"));
            if (!result.isNull("Account"))
                app.getMySharedPreferences().setAccount(result.getString("Account"));
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
            if (!result.isNull("ShenJia"))
                app.getMySharedPreferences().setShenJia(result.getInt("ShenJia"));
            if (!result.isNull("Sign"))
                app.getMySharedPreferences().setSign(result.getString("Sign"));

            JSONObject jsonObj = new JSONObject(splitStr[1]);
            gListStr = jsonObj.getString("Data");
            JSONArray gListJson = new JSONArray(gListStr);
            List<DoubleGame2> glist = new ArrayList<>();
            for (int i = 0; i < gListJson.length(); i++) {
                Object gListItem = gListJson.get(i);
                JSONObject gListItemJson = new JSONObject(
                        gListItem.toString());
                DoubleGame2 doubleGame2 = new DoubleGame2(gListItemJson.getString("Ip"),
                        gListItemJson.getInt("Id"),
                        gListItemJson.getInt("Num"),
                        gListItemJson.getString("Name"),
                        gListItemJson.getString("Img"),
                        gListItemJson.getString("C1"),
                        gListItemJson.getString("C2"));

                glist.add(doubleGame2);

            }
            app.getMySharedPreferences().setGList(glist);
            app.getMySharedPreferences().applyValue();

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
