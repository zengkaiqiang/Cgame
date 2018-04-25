package com.weile.casualgames.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.weile.casualgames.R;
import com.weile.casualgames.view.base.BaseActivity;

import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.ui.ViewInject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 用户登录类型选择类
 * */
public class LoginTypeActivity extends BaseActivity {


    @BindView(id = R.id.rl_login_type_wechat, click = true)
    private RelativeLayout rl_login_type_wechat;
    @BindView(id = R.id.rl_login_type_qq, click = true)
    private RelativeLayout rl_login_type_qq;
    @BindView(id = R.id.rl_login_type_phone, click = true)
    private RelativeLayout rl_login_type_phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void setRootView() {

        setContentView(R.layout.activity_login_type);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.rl_login_type_wechat:
                ViewInject.toast("clicked mRbtn1");
                break;
            case R.id.rl_login_type_qq:
                ViewInject.toast("clicked mRbtn2");
                break;
            case R.id.rl_login_type_phone:
                Intent intent = new Intent(this,
                        LoginPhoneActivity.class);
                startActivity(intent);
                break;
        }
    }
}
