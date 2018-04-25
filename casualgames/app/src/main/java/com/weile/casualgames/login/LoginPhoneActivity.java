package com.weile.casualgames.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.module.common.util.MyViewUtil;
import com.module.common.util.StringUtil;
import com.temporary.network.net.HttpCB;
import com.temporary.network.util.CMd;
import com.weile.casualgames.R;
import com.weile.casualgames.network.BaseMiddleOrdinaryActivity;
import com.weile.casualgames.network.requesthttp.LoginUserinfoRequest;
import com.weile.casualgames.view.base.BaseActivity;
import com.weile.casualgames.view.widget.ThumbnailView;

import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.ui.ViewInject;

/**
 * 用户登录注册输入手机号码类
 */
public class LoginPhoneActivity extends BaseMiddleOrdinaryActivity implements HttpCB {


    @BindView(id = R.id.et_phone)
    private EditText et_phone;
    @BindView(id = R.id.iv_back, click = true)
    private ImageView iv_back;
    @BindView(id = R.id.ll_back, click = true)
    private LinearLayout ll_back;
    @BindView(id = R.id.iv_next, click = true)
    private ThumbnailView iv_next;


    private String mainnStr;
    private LoginUserinfoRequest loginUserinfoRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void setRootView() {

        setContentView(R.layout.activity_login_phone);
    }

    @Override
    public void initData() {
        super.initData();
        MyViewUtil.showSoftInputFromWindow(this, et_phone);

        et_phone.addTextChangedListener(new TextWatcher() {

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
//                CMd.syo("afterTextChanged内容=" + s.toString() + "    " + s.toString().length());
                if (s != null && s.toString().length() == 11) {
                    iv_next.setImageResource(R.mipmap.login_next_pressed);
                } else {
                    iv_next.setImageResource(R.mipmap.login_next_normal);
                }

            }
        });

        setHttpCB(this);
        mainnStr = "LoginPhoneActivity";
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
            case R.id.iv_next:
                nextBtnClick();
                break;

        }
    }

    public void startHttp() {

        loadingView.showView();

        loginUserinfoRequest.callCheckUserHttp(mainnStr, et_phone.getText().toString());
    }

    /**
     * 接下来按钮的响应事件
     */
    public void nextBtnClick() {
        if (et_phone == null || et_phone.getText() == null) {
            ViewInject.toast("错误请检查");
        } else if (StringUtil.isEmpty(et_phone.getText().toString())) {
            ViewInject.toast("手机号不能为空!");
        } else if (!StringUtil.isPhone(et_phone.getText().toString())) {
            ViewInject.toast("手机号格式错误!");
        } else {
            startHttp();

        }
    }

    @Override
    public void onHttpCB(String str1, String str2, String str3) {
        CMd.syo("有返回值判断号码是否注册么=" + str3 + "       " + str1 + "   " + str2 + "    ");

        if (str1.toString().trim().equals("1"))//用户不存在
        {
            Intent intent = new Intent(this,
                    LoginPasswordActivity.class);
            intent.putExtra("phone", et_phone.getText().toString());
            startActivity(intent);
        } else if (str1.toString().trim().equals("0"))//用户已注册
        {
            Intent intent = new Intent(this,
                    LoginRegisteredPasswordActivity.class);
            intent.putExtra("phone", et_phone.getText().toString());
            startActivity(intent);
        }
        loadingView.dismiss();
    }
}
