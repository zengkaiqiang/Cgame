package com.weile.casualgames.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.weile.casualgames.MainActivity;
import com.weile.casualgames.R;
import com.weile.casualgames.user.model.UserRegisterMode;
import com.weile.casualgames.user.presenter.GetUserInfoUtils;
import com.weile.casualgames.utils.LogUtils;
import com.weile.casualgames.view.base.BaseActivity;
import com.weile.casualgames.view.widget.RoundImageView;

import org.kymjs.kjframe.utils.StringUtils;

import java.util.ArrayList;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;


/**
 * *
 * 我 的
 * * * *
 */
public class MineActivity extends BaseActivity {

    private ArrayList<Map<String, Object>> data_list;
    private GridView mine_gridview;
    private RelativeLayout mine_userinfo;
    private RoundImageView mine_userinfo_img;
    private TextView mine_userinfo_nickname, mine_userinfo_desc;
    private TextView tvMyIncomeValue;
    private RelativeLayout rlMyIncome;
    private Button btnLogout;
    UserRegisterMode.UserBean userBeans;
    UserRegisterMode.Account account;
    private MineAdapter mineAdapter;
    private boolean isNeedRefresh = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);

//        AppContext.sIsMainRefresh = true;  //返回首页刷新标记

        initTitle();
        //获取数据
        initView();
        getData();
        //新建适配器
//        String[] from = {"image", "text"};
//        int[] to = {R.id.mine_item_img, R.id.mine_item_tv};
//        SimpleAdapter sim_adapter = new SimpleAdapter(this, data_list, R.layout.mine_item, from, to);
        mineAdapter = new MineAdapter(this);
        //配置适配器
        mine_gridview.setAdapter(mineAdapter);
//        mine_gridview.setOnItemClickListener(this);
    }

    @Override
    public void setRootView() {

    }

    private void initView() {
        mine_userinfo = (RelativeLayout) findViewById(R.id.mine_userinfo);
        mine_userinfo.setOnClickListener(this);
        mine_gridview = (GridView) findViewById(R.id.mine_gridview);
        mine_userinfo_img = (RoundImageView) findViewById(R.id.mine_userinfo_img);
        mine_userinfo_nickname = (TextView) findViewById(R.id.mine_userinfo_nickname);
        mine_userinfo_desc = (TextView) findViewById(R.id.mine_userinfo_desc);
        tvMyIncomeValue = (TextView) findViewById(R.id.tvMyIncomeValue);
        rlMyIncome = (RelativeLayout)findViewById(R.id.rlMyIncome);
        btnLogout = (Button)findViewById(R.id.btn_logout);
        btnLogout.setOnClickListener(this);
//        if (!TextUtils.isEmpty(AppContext.sCityName) && AppContext.sCityName.contains("厦门")){
            rlMyIncome.setVisibility(View.VISIBLE);
            rlMyIncome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent intent = new Intent(MineActivity.this, MyIncomeActivity.class);
//                    startActivity(intent);
                    Toast.makeText(MineActivity.this, "奖励计划正在开发中…", Toast.LENGTH_SHORT).show();
                }
            });
//        }else{
//            rlMyIncome.setVisibility(View.GONE);
//        }

        //新建List
        data_list = new ArrayList<Map<String, Object>>();
//        for (int i = 0; i < titles.length; i++) {
//            Map<String, Object> map = new HashMap<String, Object>();
//            map.put("image", icons[i]);
//            map.put("text", titles[i]);
//            data_list.add(map);
//        }
    }

    /**
     * 获取个人信息
     */
    public void getData() {
        GetUserInfoUtils.getMineInfo(new GetUserInfoUtils.GetUserInfoListener() {
            @Override
            public void updateSuccess(UserRegisterMode.Account userBean, int followStatus) {
                account = userBean;
                LogUtils.E("account: " + account);
//                setData(account);
            }

            @Override
            public void updateError() {
            }
        });
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mine_userinfo:
                Intent intent = new Intent(MineActivity.this, MainActivity.class);
                intent.putExtra("mineInfo", userBeans);
                startActivity(intent);
                break;
            case R.id.btn_logout:
                logout("8618250872592", "123456", v);
                break;
        }
    }

    private void logout(String account, String password, final View v){
//        if (!StringUtils.isEmpty(account) && !StringUtils.isEmpty(password)) {
//			 btnLogin.setClickable(true);
            /*UserRegiestHelp.logout(this, account, password, new UserRegiestHelp.UserLoginListener() {
                @Override
                public void onSuccess(boolean isNewUser) {
//                    InputMethodManager imm = (InputMethodManager) MineActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
//                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
//                                takeauthcode_btn.setClickable(true);
                    if (isNewUser) {
                        LogUtils.E("isNewUser is true");
//                        startActivity(new Intent(MineActivity.this, MainActivity.class));
//                        finish();

                    } else {
                        LogUtils.E("isNewUser is false");
//							 startActivity(new Intent(LoginActivity.this, MainActivity.class));
//							 finish();
                    }
                }

                @Override
                public void onError() {
//						 takeauthcode_btn.setClickable(true);
                }
            });
        } else {
            Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
        }*/
    }


    private void initTitle() {
//        titles = new String[]{getResources().getString(R.string.menu_my_home_page_text),
//                getResources().getString(R.string.notices_page_text), getResources().getString(R.string.menu_mymessage_tv),
//                getResources().getString(R.string.menu_myspread_text), getResources().getString(R.string.feedback_title_center_tv),
//                getResources().getString(R.string.aboutus_title_text)};
//        LinearLayout title_back_ll = (LinearLayout) findViewById(R.id.title_back_ll);
//        title_back_ll.setVisibility(View.GONE);
////        title_back_ll.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                finish();
////            }
////        });
//        findViewById(R.id.title_more).setVisibility(View.GONE);
//        ((TextView) findViewById(R.id.title_back_tx)).setVisibility(View.GONE);
////                .setText("我");
//        findViewById(R.id.close_layout).setVisibility(View.VISIBLE);
//        findViewById(R.id.titleCenterImage).setVisibility(View.GONE);
//        ((TextView)findViewById(R.id.tvTitleCenter)).setText("我");
////        ((ImageView) findViewById(R.id.title_back_iv)).setImageDrawable(getResources().getDrawable(R.drawable.ic_hide));
//        LinearLayout title_submit_ll = (LinearLayout) findViewById(R.id.title_submit_ll);
//        title_submit_ll.setVisibility(View.VISIBLE);
//        TextView title_submit_tx = (TextView) findViewById(R.id.title_submit_tx);
//        title_submit_tx.setText("设置");
////        title_submit_tx.setTextColor(getResources().getColor(R.color.text_color_black));
//        title_submit_ll.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MineActivity.this, SettingActivity.class));
//            }
//        });
    }

    private void setData(UserRegisterMode.UserBean userBean) {
        if (userBean == null || userBean.profile == null) {
            return;
        }
        if (!StringUtils.isEmpty(userBean.profile.icon)) {
            if (!StringUtils.isEmpty(userBean.profile.icon)) {
                if (userBean.profile.icon.startsWith("http")) {
//                    AppContext.resetConfigImageLoader(this);
//                    ImageLoader.getInstance().displayImage(userBean.profile.icon, mine_userinfo_img);
                } else {
                    //新增动态IP解析 new test
//                    AppContext.initCustomConfigImageLoader(this);
//                    ImageLoader.getInstance().displayImage(String.format(NetApi.QINIU_ICON_2, userBean.profile.icon, AppContext.screenWidth), mine_userinfo_img);
                }
            }
        }
        mine_userinfo_nickname.setText(userBean.profile.nickname);
//        mine_userinfo_desc.setText(String.format(getResources().getString(R.string.act_mine_user_desc), userBean.beSpreadedTimes));

        //我的积分
        mine_userinfo_desc.setText(userBean.rebiCount + " 热币");

        //我的收益
        tvMyIncomeValue.setText("￥" + userBean.moneyCount);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        if (!isNeedRefresh) {
//            UserLogiHelp.getUserUnread(new UserLogiHelp.UserLoginListener() {
//                @Override
//                public void onSuccess() {
//                    mineAdapter.notifyDataSetChanged();
//                }
//            });
//        } else {
//            mineAdapter.notifyDataSetChanged();
//        }
        getData();
    }

    @Override
    protected void onPause() {
        super.onPause();
        isNeedRefresh = true;
    }

    /**
     * 菜单、返回键响应
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            exitBy2Click(); //调用双击退出函数
        }
        return false;
    }
    /**
     * 双击退出函数
     */
    private static Boolean isExit = false;

    private void exitBy2Click() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
//            Toast.makeText(this, R.string.app_exit_text, Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            finish();
            System.exit(0);
        }
    }

}
