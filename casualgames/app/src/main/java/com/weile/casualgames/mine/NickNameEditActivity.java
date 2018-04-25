package com.weile.casualgames.mine;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.module.common.util.ViewUtil;
import com.temporary.network.net.HttpCB;
import com.temporary.network.util.CMd;
import com.weile.casualgames.App;
import com.weile.casualgames.R;
import com.weile.casualgames.mine.model.EditUserInfo;
import com.weile.casualgames.mine.util.UserDataManager;
import com.weile.casualgames.network.BaseMiddleOrdinaryActivity;
import com.weile.casualgames.network.requesthttp.MineRequest;
import com.weile.casualgames.view.base.BaseActivity;

import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.ui.ViewInject;

public class NickNameEditActivity extends BaseMiddleOrdinaryActivity implements HttpCB {

    @BindView(id = R.id.iv_back, click = true)
    private ImageView iv_back;
    @BindView(id = R.id.ll_back, click = true)
    private LinearLayout ll_back;
    @BindView(id = R.id.iv_submit, click = true)
    private TextView iv_submit;
    @BindView(id = R.id.et_nickname_edit)
    private EditText et_nickname_edit;
    @BindView(id = R.id.tv_nickname_num)
    private TextView tv_nickname_num;

    @BindView(id = R.id.iv_delete, click = true)
    private ImageView iv_delete;

    private App app;
    private String mainnStr;
    private MineRequest mineRequest;
    private EditUserInfo editUserInfo;
    private UserDataManager userDataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_nickname_edit);
    }

    @Override
    public void initData() {
        super.initData();
        ViewUtil.showSoftInputFromWindow(et_nickname_edit, this);
        et_nickname_edit.addTextChangedListener(new TextWatcher() {

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
                if (s != null && s.toString() != null && s.toString().length() > 0) {
                    tv_nickname_num.setText("" + s.toString().length());
                } else {
                    tv_nickname_num.setText("0");
                }

            }
        });

        app = (App) getApplication(); // 获得Application对象
        userDataManager = UserDataManager.getInstance(this);
        editUserInfo = userDataManager.getEditUserInfo();
        et_nickname_edit.setText(editUserInfo.getName());
        setHttpCB(this);
        mainnStr = "NickNameEditActivity";
        initBroadcast(mainnStr);
        mineRequest = new MineRequest(this);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_back:
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_delete:
                et_nickname_edit.setText("");
                break;
            case R.id.iv_submit:
                submitClick();
                break;

        }
    }

    @Override
    public void onHttpCB(String str1, String str2, String str3) {
        CMd.syo("有返回值用户信息修改么=" + str3 + "       " + str1 + "   " + str2 + "    ");
        if (str1.equals("0"))
            ViewInject.toast("修改成功!");
        else {
            ViewInject.toast("修改失败，请重试!");
        }

        loadingView.dismiss();

        finish();
    }

    public void submitClick() {
        if (et_nickname_edit == null || et_nickname_edit.getText() == null || et_nickname_edit.getText().toString().equals("")) {
            ViewInject.toast("昵称不能为空!");
        } else {
            editUserInfo.setName(et_nickname_edit.getText().toString());
            loadingView.showView();
            mineRequest.callModifyUserHttp(mainnStr, editUserInfo);
            startRunnable();
        }
    }

    public void startRunnable() {
        app.getMyExecutorService().initCachedThreadPool(new Runnable() {
            @Override
            public void run() {
                app.getMySharedPreferences().initEdit();
                app.getMySharedPreferences().setName(et_nickname_edit.getText().toString());
                app.getMySharedPreferences().applyValue();
            }
        });

    }
}
