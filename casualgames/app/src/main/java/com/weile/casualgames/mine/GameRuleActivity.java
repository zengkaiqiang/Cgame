package com.weile.casualgames.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.weile.casualgames.R;
import com.weile.casualgames.view.base.BaseActivity;

public class GameRuleActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout llBack;
    private ImageView ivBack;
    private LinearLayout llSubmit;
    private TextView ivSubmit;
    private RelativeLayout rlSettingGameRuleIntroduce;
    private RelativeLayout rlSettingGameRuleFriends;
    private RelativeLayout rlSettingGameRuleGold;
    private RelativeLayout rlSettingGameRuleKitchen;
    private RelativeLayout rlSettingGameRuleContact;





    @Override
    public void setRootView() {
        setContentView(R.layout.activity_game_rule);
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

    public void initFindId() {
        llBack = (LinearLayout) findViewById(R.id.ll_back);
        ivBack = (ImageView) findViewById(R.id.iv_back);
        llSubmit = (LinearLayout) findViewById(R.id.ll_submit);
        ivSubmit = (TextView) findViewById(R.id.iv_submit);
        rlSettingGameRuleIntroduce = (RelativeLayout) findViewById(R.id.rl_setting_game_rule_introduce);
        rlSettingGameRuleFriends = (RelativeLayout) findViewById(R.id.rl_setting_game_rule_friends);
        rlSettingGameRuleGold = (RelativeLayout) findViewById(R.id.rl_setting_game_rule_gold);
        rlSettingGameRuleKitchen = (RelativeLayout) findViewById(R.id.rl_setting_game_rule_kitchen);
        rlSettingGameRuleContact = (RelativeLayout) findViewById(R.id.rl_setting_game_rule_contact);

        llBack.setOnClickListener(this);
        ivBack.setOnClickListener(this);
        rlSettingGameRuleIntroduce.setOnClickListener(this);
        rlSettingGameRuleFriends.setOnClickListener(this);
        rlSettingGameRuleGold.setOnClickListener(this);
        rlSettingGameRuleKitchen.setOnClickListener(this);
        rlSettingGameRuleContact.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.ll_back:
            case R.id.iv_back:
                finish();
                break;

        }
    }


}
