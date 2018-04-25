package com.weile.casualgames.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

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

public class SexEditActivity extends BaseMiddleOrdinaryActivity implements HttpCB {

    @BindView(id = R.id.iv_back, click = true)
    private ImageView iv_back;
    @BindView(id = R.id.ll_back, click = true)
    private LinearLayout ll_back;

    @BindView(id = R.id.radioGroup)
    private RadioGroup radioGroup;
    @BindView(id = R.id.rb_man, click = true)
    private RadioButton rb_man;
    @BindView(id = R.id.rb_woman, click = true)
    private RadioButton rb_woman;

    @BindView(id = R.id.rl_man_edit, click = true)
    private RelativeLayout rl_man_edit;
    @BindView(id = R.id.rl_woman_edit, click = true)
    private RelativeLayout rl_woman_edit;

    @BindView(id = R.id.iv_submit, click = true)
    private TextView iv_submit;

    private int SelectSex = 1;

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
        setContentView(R.layout.activity_sex_edit);
    }

    @Override
    public void initData() {
        super.initData();
        setRadioGroup();
        app = (App) getApplication(); // 获得Application对象
        userDataManager = UserDataManager.getInstance(this);
        editUserInfo = userDataManager.getEditUserInfo();
        setHttpCB(this);
        mainnStr = "SexEditActivity";
        initBroadcast(mainnStr);
        mineRequest = new MineRequest(this);
        selectSex(editUserInfo.getSex());
        SelectSex=editUserInfo.getSex();

    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_back:
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl_man_edit:
            case R.id.rb_man:
                selectSex(0);
                break;
            case R.id.rl_woman_edit:
            case R.id.rb_woman:
                selectSex(1);
                break;

            case R.id.iv_submit:
                submitClick();
                break;

        }
    }

    public void setRadioGroup() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) { //在这个函数里面用来改变选择的radioButton的数值，以及与其值相关的 //任何操作，详见下文 selectRadioBtn(); } });

                switch (checkedId) {
                    case R.id.rb_man:
                        selectSex(0);
                        break;
                    case R.id.rb_woman:
                        selectSex(1);
                        break;

                }
            }
        });
    }

    /**
     */
    public void selectSex(int type) {
        CMd.syo("勾选性格修改SelectSex=" + type);
        if (SelectSex == type)
            return;
        switch (type) {
            case 0:
                rb_man.setChecked(true);
                break;
            case 1:
                rb_woman.setChecked(true);
                break;

            default:
                rb_woman.setChecked(true);
                break;
        }

        switch (SelectSex) {
            case 0:
                rb_man.setChecked(false);
                break;
            case 1:
                rb_woman.setChecked(false);
                break;
            default:
                rb_man.setChecked(false);
                break;
        }

        SelectSex = type;
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
//        CMd.syo("提交性格修改SelectSex=" + SelectSex);
        editUserInfo.setSex(SelectSex);
        loadingView.showView();
//        CMd.syo("写入本地缓存的内容=" + editUserInfo.toString());
        mineRequest.callModifyUserHttp(mainnStr, editUserInfo);
        startRunnable();

    }

    public void startRunnable() {
        app.getMyExecutorService().initCachedThreadPool(new Runnable() {
            @Override
            public void run() {
                app.getMySharedPreferences().initEdit();
                app.getMySharedPreferences().setSex(SelectSex);
                app.getMySharedPreferences().applyValue();
            }
        });

    }
}
