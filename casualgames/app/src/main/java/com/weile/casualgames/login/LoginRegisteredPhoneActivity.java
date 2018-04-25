package com.weile.casualgames.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.module.common.util.MyViewUtil;
import com.module.common.util.StringUtil;
import com.temporary.network.util.CMd;
import com.weile.casualgames.R;
import com.weile.casualgames.view.base.BaseActivity;

import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.ui.ViewInject;

/**
 * 用户登录注册过 输入手机号码类
 */
public class LoginRegisteredPhoneActivity extends BaseActivity {


    @BindView(id = R.id.et_phone)
    private EditText et_phone;
    @BindView(id = R.id.iv_back, click = true)
    private ImageView iv_back;
    @BindView(id = R.id.ll_back, click = true)
    private LinearLayout ll_back;
    @BindView(id = R.id.iv_next, click = true)
    private ImageView iv_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void setRootView() {

        setContentView(R.layout.activity_login_registered_phone);
    }

    @Override
    public void initData() {
        super.initData();
        MyViewUtil.showSoftInputFromWindow(this, et_phone);
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

    /**
     * 接下来按钮的响应事件
     */
    public void nextBtnClick() {
        try {
            if (et_phone == null || et_phone.getText() == null) {
                ViewInject.toast("错误请检查");
            } else if (StringUtil.isEmpty(et_phone.getText().toString())) {
                ViewInject.toast("手机号不能为空!");
            } else if (!StringUtil.isPhone(et_phone.getText().toString())) {
                ViewInject.toast("手机号格式错误!");
            } else {
                Intent intent = new Intent(this,
                        LoginRegisteredPasswordActivity.class);
                intent.putExtra("phone", et_phone.getText().toString());
                startActivity(intent);
            }
        } catch (Exception e) {
            CMd.syo("错误=" + e.getMessage());
        }
    }
}
