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

import java.util.ArrayList;
import java.util.List;

public class NoticeSetActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout llBack;
    private ImageView ivBack;
    private LinearLayout llSubmit;
    private TextView ivSubmit;
    private ScrollView scrollView;
    private RelativeLayout rlSettingNoticeManage1;
    private TextView settingNoticeManageState;
    private RelativeLayout rlSettingNoticeManagePraise;
    private ImageView btnSettingNoticeManagePraise;
    private RelativeLayout rlSettingNoticeManageComment;
    private ImageView btnSettingNoticeManageComment;
    private RelativeLayout rlSettingNoticeManageFollow;
    private ImageView btnSettingNoticeManageFollow;
    private RelativeLayout rlSettingNoticeManageAll;
    private ImageView btnSettingNoticeManageAll;

    private List<Integer> switchValueList;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_notice_set);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initData() {
        super.initData();
        initFindId();
        switchValueList = new ArrayList<>();
        switchValueList.add(0);
        switchValueList.add(0);
        switchValueList.add(1);
        switchValueList.add(0);
        setSwitchBtn(0, btnSettingNoticeManagePraise, false);
        setSwitchBtn(1, btnSettingNoticeManageComment, false);
        setSwitchBtn(2, btnSettingNoticeManageFollow, true);
        setSwitchBtn(3, btnSettingNoticeManageAll, false);
    }

    public void initFindId() {
        llBack = (LinearLayout) findViewById(R.id.ll_back);
        ivBack = (ImageView) findViewById(R.id.iv_back);
        llSubmit = (LinearLayout) findViewById(R.id.ll_submit);
        ivSubmit = (TextView) findViewById(R.id.iv_submit);
        scrollView = (ScrollView) findViewById(R.id.scrollView);
        rlSettingNoticeManage1 = (RelativeLayout) findViewById(R.id.rl_setting_notice_manage_1);
        settingNoticeManageState = (TextView) findViewById(R.id.setting_notice_manage_state);
        rlSettingNoticeManagePraise = (RelativeLayout) findViewById(R.id.rl_setting_notice_manage_praise);
        btnSettingNoticeManagePraise = (ImageView) findViewById(R.id.btn_setting_notice_manage_praise);
        rlSettingNoticeManageComment = (RelativeLayout) findViewById(R.id.rl_setting_notice_manage_comment);
        btnSettingNoticeManageComment = (ImageView) findViewById(R.id.btn_setting_notice_manage_comment);
        rlSettingNoticeManageFollow = (RelativeLayout) findViewById(R.id.rl_setting_notice_manage_follow);
        btnSettingNoticeManageFollow = (ImageView) findViewById(R.id.btn_setting_notice_manage_follow);
        rlSettingNoticeManageAll = (RelativeLayout) findViewById(R.id.rl_setting_notice_manage_all);
        btnSettingNoticeManageAll = (ImageView) findViewById(R.id.btn_setting_notice_manage_all);
        llBack.setOnClickListener(this);
        ivBack.setOnClickListener(this);
        btnSettingNoticeManagePraise.setOnClickListener(this);
        btnSettingNoticeManageComment.setOnClickListener(this);
        btnSettingNoticeManageFollow.setOnClickListener(this);
        btnSettingNoticeManageAll.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.ll_back:
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_setting_notice_manage_praise:
                if (switchValueList.get(0) == 1) {
                    setSwitchBtn(0, btnSettingNoticeManagePraise, false);
                } else {
                    setSwitchBtn(0, btnSettingNoticeManagePraise, true);
                }
                break;

            case R.id.btn_setting_notice_manage_comment:
                if (switchValueList.get(1) == 1) {
                    setSwitchBtn(1, btnSettingNoticeManageComment, false);
                } else {
                    setSwitchBtn(1, btnSettingNoticeManageComment, true);
                }
                break;
            case R.id.btn_setting_notice_manage_follow:
                if (switchValueList.get(2) == 1) {
                    setSwitchBtn(2, btnSettingNoticeManageFollow, false);
                } else {
                    setSwitchBtn(2, btnSettingNoticeManageFollow, true);
                }
                break;
            case R.id.btn_setting_notice_manage_all:
                if (switchValueList.get(3) == 1) {
                    setSwitchBtn(3, btnSettingNoticeManageAll, false);
                } else {
                    setSwitchBtn(3, btnSettingNoticeManageAll, true);
                }
                break;


        }
    }

    public void setSwitchBtn(int index, ImageView imageView, boolean state) {
        if (state) {
            switchValueList.set(index, 1);
            imageView.setImageResource(R.mipmap.icon_on);
        } else {
            switchValueList.set(index, 0);
            imageView.setImageResource(R.mipmap.icon_off);
        }
    }
}
