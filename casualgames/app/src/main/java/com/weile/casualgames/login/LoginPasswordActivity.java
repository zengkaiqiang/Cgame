package com.weile.casualgames.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.module.common.util.CountTimer;
import com.module.common.util.MyViewUtil;
import com.module.common.util.StringUtil;
import com.module.common.util.ViewUtil;
import com.temporary.network.net.HttpCB;
import com.temporary.network.util.CMd;
import com.weile.casualgames.R;
import com.weile.casualgames.login.utils.CommonViewUtil;
import com.weile.casualgames.login.utils.WordReplacement;
import com.weile.casualgames.login.view.VerificationCodeView;
import com.weile.casualgames.network.BaseMiddleOrdinaryActivity;
import com.weile.casualgames.view.base.BaseActivity;
import com.weile.casualgames.view.widget.LineEditText;
import com.weile.casualgames.view.widget.ThumbnailView;

import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.ui.ViewInject;

import java.util.jar.Manifest;

/**
 * 用户登录注册输入密码类
 */
public class LoginPasswordActivity extends BaseActivity  {

    private VerificationCodeView codeView;


    @BindView(id = R.id.iv_back, click = true)
    private ImageView iv_back;
    @BindView(id = R.id.ll_back, click = true)
    private LinearLayout ll_back;
    @BindView(id = R.id.tv_send_phone)
    private TextView tv_send_phone;
    @BindView(id = R.id.icv)
    private VerificationCodeView icv;
    @BindView(id = R.id.et_password)
    private EditText et_password;
    @BindView(id = R.id.et_code1)
    private EditText et_code1;
    @BindView(id = R.id.et_code2)
    private EditText et_code2;
    @BindView(id = R.id.et_code3)
    private EditText et_code3;
    @BindView(id = R.id.et_code4)
    private EditText et_code4;
    @BindView(id = R.id.tv_repeat_v_code)
    private TextView tv_repeat_v_code;

    @BindView(id = R.id.iv_next, click = true)
    private ThumbnailView iv_next;


    private String phoneStr;
    private String passwordStr;
    private String codeStr;

    private CountTimer countTimer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void setRootView() {

        setContentView(R.layout.activity_login_password);
    }

    @Override
    public void initData() {
        super.initData();
        phoneStr = getIntent().getStringExtra("phone");
        codeStr="";
        if (phoneStr != null) {
            tv_send_phone.setText(phoneStr);
        }
        initCode();

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
                CMd.syo("afterTextChanged内容=" + s.toString() + "    " + s.toString().length());
                if (s != null && s.toString().length() >0) {
                    iv_next.setImageResource(R.mipmap.login_next_pressed);
                } else {
                    iv_next.setImageResource(R.mipmap.login_next_normal);
                }

            }
        });

//        System.out.println("输入的电话号码是多少="+phoneStr);
//        startCountDown();
//        icv.setInputCompleteListener(new VerificationCodeView.InputCompleteListener() {
//            @Override
//            public void inputComplete() {
//                codeStr=icv.getInputContent();
//
//            }
//
//            @Override
//            public void deleteContent() {
//                codeStr=icv.getInputContent();
//            }
//        });
        et_password.setTransformationMethod(new WordReplacement());
//        icv.showSoftInputFromWindow(this);
//        CommonViewUtil.showSoftInputFromWindow(this,icv);
//        codeView = new VerificationCodeView(this);


    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        icv.clearInputContent();
        switch (v.getId()) {
            case R.id.ll_back:
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_repeat_v_code:
                startCountDown();
                break;
            case R.id.iv_next:
                nextBtnClick();
                break;

        }
    }

    /**
     * 开始倒计时
     */
    public void startCountDown() {
        countTimer = new CountTimer(10000, 1000);
        countTimer.setCountTextView(this, tv_repeat_v_code);
//        countTimer.start();
    }

    /**
     * 接下来按钮的响应事件
     */
    public void nextBtnClick() {

        System.out.println("输入的检验验证码2是多少="+codeStr);
        if (et_password == null || et_password.getText() == null) {
            ViewInject.toast("错误请检查");
        } else if (StringUtil.isEmpty(et_password.getText().toString())) {
            ViewInject.toast("密码不能为空!");
        }  else {
            Intent intent = new Intent(this,
                    LoginUserinfoActivity.class);
            intent.putExtra("phone",phoneStr);
            intent.putExtra("password",et_password.getText().toString());
            intent.putExtra("code","");
            startActivity(intent);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(countTimer != null) {
            countTimer.cancel();
            countTimer = null;
        }
    }



    /**
     * 初始化验证码相关设置
     * */
    public void initCode()
    {
        ViewUtil.showSoftInputFromWindow(et_code1,this);
        et_code1.addTextChangedListener(new TextWatcher() {

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
                et_code2.requestFocus();

            }
        });
        et_code2.addTextChangedListener(new TextWatcher() {

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
                et_code3.requestFocus();

            }
        });
        et_code3.addTextChangedListener(new TextWatcher() {

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
                et_code4.requestFocus();

            }
        });
        et_code4.addTextChangedListener(new TextWatcher() {

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
                et_password.requestFocus();

            }
        });
    }

}
