package com.weile.casualgames.mine;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
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

public class WorthEditActivity extends BaseMiddleOrdinaryActivity implements HttpCB {

    @BindView(id = R.id.iv_back, click = true)
    private ImageView iv_back;
    @BindView(id = R.id.ll_back, click = true)
    private LinearLayout ll_back;

    @BindView(id = R.id.radioGroup)
    private RadioGroup radioGroup;
    @BindView(id = R.id.rb_worth_1, click = true)
    private RadioButton rb_worth_1;
    @BindView(id = R.id.rb_worth_2, click = true)
    private RadioButton rb_worth_2;
    @BindView(id = R.id.rb_worth_3, click = true)
    private RadioButton rb_worth_3;
    @BindView(id = R.id.rb_worth_4, click = true)
    private RadioButton rb_worth_4;
    @BindView(id = R.id.rb_worth_5, click = true)
    private RadioButton rb_worth_5;
    @BindView(id = R.id.rb_worth_6, click = true)
    private RadioButton rb_worth_6;
    @BindView(id = R.id.rb_worth_7, click = true)
    private RadioButton rb_worth_7;

    @BindView(id = R.id.rl_worth_edit_1, click = true)
    private RelativeLayout rl_worth_edit_1;
    @BindView(id = R.id.rl_worth_edit_2, click = true)
    private RelativeLayout rl_worth_edit_2;
    @BindView(id = R.id.rl_worth_edit_3, click = true)
    private RelativeLayout rl_worth_edit_3;
    @BindView(id = R.id.rl_worth_edit_4, click = true)
    private RelativeLayout rl_worth_edit_4;
    @BindView(id = R.id.rl_worth_edit_5, click = true)
    private RelativeLayout rl_worth_edit_5;
    @BindView(id = R.id.rl_worth_edit_6, click = true)
    private RelativeLayout rl_worth_edit_6;
    @BindView(id = R.id.rl_worth_edit_7, click = true)
    private RelativeLayout rl_worth_edit_7;

    @BindView(id = R.id.iv_submit, click = true)
    private TextView iv_submit;

    private int SelectWorth = 1;
    private Integer[] worthValue={0,1,5,20,50,100,200};
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
        setContentView(R.layout.activity_worth_edit);
    }

    @Override
    public void initData() {
        super.initData();
        setRadioGroup();
        app = (App) getApplication(); // 获得Application对象
        userDataManager = UserDataManager.getInstance(this);
        editUserInfo = userDataManager.getEditUserInfo();
        setHttpCB(this);
        mainnStr = "WorthEditActivity";
        initBroadcast(mainnStr);
        mineRequest = new MineRequest(this);
        for(int i=0;i<worthValue.length;i++)
        {
            if(editUserInfo.getShenJia()==worthValue[i])
            {
                selectWorth(i);
                SelectWorth=i;

                break;
            }
        }

    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_back:
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl_worth_edit_1:
            case R.id.rb_worth_1:
                selectWorth(0);
                break;
            case R.id.rl_worth_edit_2:
            case R.id.rb_worth_2:
                selectWorth(1);
                break;
            case R.id.rl_worth_edit_3:
            case R.id.rb_worth_3:
                selectWorth(2);
                break;
            case R.id.rl_worth_edit_4:
            case R.id.rb_worth_4:
                selectWorth(3);
                break;
            case R.id.rl_worth_edit_5:
            case R.id.rb_worth_5:
                selectWorth(4);
                break;
            case R.id.rl_worth_edit_6:
            case R.id.rb_worth_6:
                selectWorth(5);
                break;
            case R.id.rl_worth_edit_7:
            case R.id.rb_worth_7:
                selectWorth(6);
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
                    case R.id.rb_worth_1:
                        selectWorth(0);
                        break;
                    case R.id.rb_worth_2:
                        selectWorth(1);
                        break;
                    case R.id.rb_worth_3:
                        selectWorth(2);
                        break;
                    case R.id.rb_worth_4:
                        selectWorth(3);
                        break;
                    case R.id.rb_worth_5:
                        selectWorth(4);
                        break;
                    case R.id.rb_worth_6:
                        selectWorth(5);
                        break;
                    case R.id.rb_worth_7:
                        selectWorth(6);
                        break;
                }
            }
        });
    }

    /**
     */
    public void selectWorth(int type) {
        if (SelectWorth == type)
            return;
        switch (type) {
            case 0:
                rb_worth_1.setChecked(true);
                break;
            case 1:
                rb_worth_2.setChecked(true);
                break;
            case 2:
                rb_worth_3.setChecked(true);
                break;
            case 3:
                rb_worth_4.setChecked(true);
                break;
            case 4:
                rb_worth_5.setChecked(true);
                break;
            case 5:
                rb_worth_6.setChecked(true);
                break;
            case 6:
                rb_worth_7.setChecked(true);
                break;
            default:
                rb_worth_2.setChecked(true);
                break;
        }

        switch (SelectWorth) {
            case 0:
                rb_worth_1.setChecked(false);
                break;
            case 1:
                rb_worth_2.setChecked(false);
                break;
            case 2:
                rb_worth_3.setChecked(false);
                break;
            case 3:
                rb_worth_4.setChecked(false);
                break;
            case 4:
                rb_worth_5.setChecked(false);
                break;
            case 5:
                rb_worth_6.setChecked(false);
                break;
            case 6:
                rb_worth_7.setChecked(false);
                break;
            default:
                rb_worth_1.setChecked(false);
                break;
        }

        SelectWorth = type;
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
        editUserInfo.setShenJia(worthValue[SelectWorth]);
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
                app.getMySharedPreferences().setShenJia(worthValue[SelectWorth]);
                app.getMySharedPreferences().applyValue();
            }
        });

    }
}
