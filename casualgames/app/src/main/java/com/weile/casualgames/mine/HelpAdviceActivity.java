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

public class HelpAdviceActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout llBack;
    private ImageView ivBack;
    private LinearLayout llSubmit;
    private TextView ivSubmit;
    private ScrollView scrollView;
    private RelativeLayout rlSettingHelpProblem1;
    private RelativeLayout rlSettingHelpProblem2;
    private RelativeLayout rlSettingHelpProblem3;
    private RelativeLayout rlSettingHelpProblem4;
    private TextView tvSettingHelpProblem5;



    @Override
    public void setRootView() {
        setContentView(R.layout.activity_help_advice);
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
        scrollView = (ScrollView) findViewById(R.id.scrollView);
        rlSettingHelpProblem1 = (RelativeLayout) findViewById(R.id.rl_setting_help_problem1);
        rlSettingHelpProblem2 = (RelativeLayout) findViewById(R.id.rl_setting_help_problem2);
        rlSettingHelpProblem3 = (RelativeLayout) findViewById(R.id.rl_setting_help_problem3);
        rlSettingHelpProblem4 = (RelativeLayout) findViewById(R.id.rl_setting_help_problem4);
        tvSettingHelpProblem5 = (TextView) findViewById(R.id.tv_setting_help_problem5);
        llBack.setOnClickListener(this);
        ivBack.setOnClickListener(this);
        tvSettingHelpProblem5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.ll_back:
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_setting_help_problem5:
                Intent intent = new Intent(this,
                        LeaveWordQuizActivity.class);
                startActivity(intent);
                break;

        }
    }
}
