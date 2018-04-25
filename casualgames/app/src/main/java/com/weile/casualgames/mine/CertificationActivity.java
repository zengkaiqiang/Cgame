package com.weile.casualgames.mine;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.weile.casualgames.R;
import com.weile.casualgames.view.base.BaseActivity;

public class CertificationActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout llBack;
    private ImageView ivBack;
    private LinearLayout llSubmit;
    private TextView ivSubmit;
    private RelativeLayout rlCertificationName;
    private EditText etCertificationName;
    private RelativeLayout rlCertificationIdNumber;
    private EditText etCertificationIdNumber;
    private TextView tvCertificationAuthentication;







    @Override
    public void setRootView() {
        setContentView(R.layout.activity_certification);
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
        rlCertificationName = (RelativeLayout) findViewById(R.id.rl_certification_name);
        etCertificationName = (EditText) findViewById(R.id.et_certification_name);
        rlCertificationIdNumber = (RelativeLayout) findViewById(R.id.rl_certification_id_number);
        etCertificationIdNumber = (EditText) findViewById(R.id.et_certification_id_number);
        tvCertificationAuthentication = (TextView) findViewById(R.id.tv_certification_authentication);

        llBack.setOnClickListener(this);
        ivBack.setOnClickListener(this);
        tvCertificationAuthentication.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.ll_back:
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_certification_authentication:

                break;

        }
    }

}
