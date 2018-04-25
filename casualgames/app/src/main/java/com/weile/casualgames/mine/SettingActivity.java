package com.weile.casualgames.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.weile.casualgames.R;
import com.weile.casualgames.view.base.BaseActivity;
import com.weile.casualgames.view.widget.ReboundScrollview;

public class SettingActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout llBack;
    private ImageView ivBack;
    private LinearLayout llSubmit;
    private TextView ivSubmit;
    private ReboundScrollview scrollView;
    private RelativeLayout rlSettingNoticeSet;
    private RelativeLayout rlSettingWorthSet;
    private RelativeLayout rlSettingPrivacy;
    private RelativeLayout rlSettingCacheSize;
    private TextView settingCacheSize;
    private RelativeLayout rlSettingHelpAdvice;
    private RelativeLayout rlSettingUserProtocol;
    private RelativeLayout rlSettingAbout;
    private TextView tvSettingExitLogon;



    @Override
    public void setRootView() {
        setContentView(R.layout.activity_setting);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initData() {
        super.initData();
        initFindId();
    }

    public  void initFindId()
    {
        llBack = (LinearLayout) findViewById(R.id.ll_back);
        ivBack = (ImageView) findViewById(R.id.iv_back);
        llSubmit = (LinearLayout) findViewById(R.id.ll_submit);
        ivSubmit = (TextView) findViewById(R.id.iv_submit);
        scrollView = (ReboundScrollview) findViewById(R.id.scrollView);
        rlSettingNoticeSet = (RelativeLayout) findViewById(R.id.rl_setting_notice_set);
        rlSettingWorthSet = (RelativeLayout) findViewById(R.id.rl_setting_worth_set);
        rlSettingPrivacy = (RelativeLayout) findViewById(R.id.rl_setting_privacy);
        rlSettingCacheSize = (RelativeLayout) findViewById(R.id.rl_setting_cache_size);
        settingCacheSize = (TextView) findViewById(R.id.setting_cache_size);
        rlSettingHelpAdvice = (RelativeLayout) findViewById(R.id.rl_setting_help_advice);
        rlSettingUserProtocol = (RelativeLayout) findViewById(R.id.rl_setting_user_protocol);
        rlSettingAbout = (RelativeLayout) findViewById(R.id.rl_setting_about);
        tvSettingExitLogon = (TextView) findViewById(R.id.tv_setting_exit_logon);
        llBack.setOnClickListener(this);
        ivBack.setOnClickListener(this);
        rlSettingNoticeSet.setOnClickListener(this);
        rlSettingWorthSet.setOnClickListener(this);
        rlSettingPrivacy.setOnClickListener(this);
        rlSettingHelpAdvice.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.ll_back:
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl_setting_notice_set:
                Intent intent = new Intent(this,
                        NoticeSetActivity.class);
                startActivity(intent);
                break;

            case R.id.rl_setting_worth_set:
                Intent intent3 = new Intent(this,
                        WorthEditActivity.class);
                startActivity(intent3);
                break;
            case R.id.rl_setting_privacy:
                Intent intent4 = new Intent(this,
                        PrivacySetActivity.class);
                startActivity(intent4);
                break;


            case R.id.rl_setting_help_advice:
                Intent intent2 = new Intent(this,
                        HelpAdviceActivity.class);
                startActivity(intent2);
                break;


        }
    }
}
