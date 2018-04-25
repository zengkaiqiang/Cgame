package com.weile.casualgames.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.weile.casualgames.R;
import com.weile.casualgames.view.base.BaseActivity;

public class PrivacySetActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout llBack;
    private ImageView ivBack;
    private LinearLayout llSubmit;
    private TextView ivSubmit;
    private ScrollView scrollView;
    private RelativeLayout rlSettingPrivacy1;
    private ImageView btnSettingPrivacy1;
    private RelativeLayout rlSettingPrivacy2;


    private int switchValue;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_privacy_set);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initData() {
        super.initData();
        initFindId();
        switchValue=0;
        setSwitchBtn(0, btnSettingPrivacy1, false);
    }

    public void initFindId() {
        llBack = (LinearLayout) findViewById(R.id.ll_back);
        ivBack = (ImageView) findViewById(R.id.iv_back);
        llSubmit = (LinearLayout) findViewById(R.id.ll_submit);
        ivSubmit = (TextView) findViewById(R.id.iv_submit);
        scrollView = (ScrollView) findViewById(R.id.scrollView);
        rlSettingPrivacy1 = (RelativeLayout) findViewById(R.id.rl_setting_privacy1);
        btnSettingPrivacy1 = (ImageView) findViewById(R.id.btn_setting_privacy1);
        rlSettingPrivacy2 = (RelativeLayout) findViewById(R.id.rl_setting_privacy2);

        llBack.setOnClickListener(this);
        ivBack.setOnClickListener(this);
        btnSettingPrivacy1.setOnClickListener(this);
        rlSettingPrivacy2.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.ll_back:
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_setting_privacy1:
                if (switchValue == 1) {
                    setSwitchBtn(0, btnSettingPrivacy1, false);
                } else {
                    setSwitchBtn(0, btnSettingPrivacy1, true);
                }
                break;
            case R.id.rl_setting_privacy2:
                Intent intent = new Intent(this,
                        BlackListActivity.class);
                startActivity(intent);
                break;

        }
    }

    public void setSwitchBtn(int index, ImageView imageView, boolean state) {
        if (state) {
            switchValue=1;
            imageView.setImageResource(R.mipmap.icon_on);
        } else {
            switchValue=0;
            imageView.setImageResource(R.mipmap.icon_off);
        }
    }
}
