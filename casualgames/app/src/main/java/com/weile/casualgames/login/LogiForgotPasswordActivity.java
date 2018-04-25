package com.weile.casualgames.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.module.common.util.ViewUtil;
import com.weile.casualgames.R;
import com.weile.casualgames.login.utils.WordReplacement;
import com.weile.casualgames.login.view.VerificationCodeView;
import com.weile.casualgames.view.base.BaseActivity;

import org.kymjs.kjframe.ui.BindView;

/**
 * 忘记密码类
 * */
public class LogiForgotPasswordActivity extends BaseActivity {

    private VerificationCodeView codeView;


    @BindView(id = R.id.iv_back, click = true)
    private ImageView iv_back;
    @BindView(id = R.id.ll_back, click = true)
    private LinearLayout ll_back;
    @BindView(id = R.id.tv_send_phone)
    private TextView tv_send_phone;
    @BindView(id = R.id.et_code1)
    private EditText et_code1;
    @BindView(id = R.id.et_code2)
    private EditText et_code2;
    @BindView(id = R.id.et_code3)
    private EditText et_code3;
    @BindView(id = R.id.et_code4)
    private EditText et_code4;
    @BindView(id = R.id.et_password)
    private EditText et_password;
    @BindView(id = R.id.tv_repeat_v_code)
    private TextView tv_repeat_v_code;

    @BindView(id = R.id.iv_next, click = true)
    private ImageView iv_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void setRootView() {

        setContentView(R.layout.activity_login_forgot_password);
    }

    @Override
    public void initData() {
        super.initData();

        et_password.setTransformationMethod(new WordReplacement());

        codeView = new VerificationCodeView(this);
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
//                Intent intent = new Intent(this,
//                        LoginUserinfoActivity.class);
//                startActivity(intent);
                break;

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
