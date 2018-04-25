package com.weile.casualgames.game;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.module.common.log.LogUtil;
import com.weile.casualgames.App;
import com.weile.casualgames.R;
import com.weile.casualgames.game.model.MatchingModel;
import com.weile.casualgames.game.presenter.GameMatchingPresenterCompl;
import com.weile.casualgames.game.presenter.IGameMatchingPresenter;
import com.weile.casualgames.game.view.IGameMatchingView;
import com.weile.casualgames.view.base.BaseActivity;

import org.kymjs.kjframe.ui.BindView;

public class GameActivity extends BaseActivity implements IGameMatchingView {

//    private RelativeLayout rlFind;
//    private LinearLayout llMy;



    @BindView(id = R.id.tv_matching_cancel)
    TextView tv_matching_cancel;
    @BindView(id = R.id.tv_waiting_time)
     TextView tv_waiting_time;
    @BindView(id = R.id.rl_game_matching)
     RelativeLayout rl_game_matching;
    @BindView(id = R.id.ll_my)
     LinearLayout ll_my;
    @BindView(id= R.id.tv_matching_tips)
    TextView tv_matching_tips;
    @BindView(id = R.id.tv_myName)
    TextView tv_myName;

    IGameMatchingPresenter gameMatchingPresenter;

    private String key;
    private String ip;
    private int gameId;

    @Override
    public void initWidget() {
        super.initWidget();

    }

    @Override
    public void onGetRandomMatchingResult(MatchingModel matchingModel) {
        key = matchingModel.getKey();
        ip = matchingModel.getIp();
        changeViewState();
    }

    @Override
    public void onGetRandomMatching() {

    }

    @Override
    public void initData() {
        super.initData();
        tv_myName.setText(App.getSharedPreferences().getName());
        Intent intent = getIntent();
        if (intent != null){
            gameId = intent.getIntExtra("gameId", 1);
        }
        waitTimer.start();
        gameMatchingPresenter = new GameMatchingPresenterCompl(this,this);
        gameMatchingPresenter.loadRandomMatching();
    }

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_game);
    }



    /**
     * 菜单、返回键响应
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            gameMatchingPresenter.cancelMatchingTimer();
            waitTimer.cancel();
            finish();
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        gameMatchingPresenter.cancelMatchingTimer();
        waitTimer.cancel();
    }


    private void changeViewState() {
//        llMy = (LinearLayout)findViewById(R.id.ll_my);
        // 初始化需要加载的动画资源
//        Animation animation = AnimationUtils
//                .loadAnimation(this, R.anim.main_top_moveto_bottom);
         Animation animation = AnimationUtils
                .loadAnimation(GameActivity.this, R.anim.out_transparent);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//        TranslateAnimation translate = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
//                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
//                -1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
//        translate.setDuration(500);//动画时间500毫秒
//        translate.setFillAfter(true);//动画出来控件可以点击

                ll_my.startAnimation(moveToViewBottom());//开始动画

                tv_matching_cancel.setAnimation(animation);
                tv_waiting_time.setAnimation(animation);
                tv_matching_cancel.startAnimation(animation);
                tv_waiting_time.startAnimation(animation);
                ll_my.setVisibility(View.GONE);
                tv_waiting_time.setVisibility(View.GONE);
                tv_matching_cancel.setVisibility(View.GONE);
                showMatchingGame();
//        llMy.setVisibility(View.VISIBLE);//设置可见
                // 将TextView执行Animation动画
//        llMy.startAnimation(animation);
            }
        }, 1000);

    }

    private void showMatchingGame(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                tv_matching_tips.setText(getString(R.string.matching_player_matching_sucess_tips));
                Animation animation2 = AnimationUtils
                        .loadAnimation(GameActivity.this, R.anim.alpha);
                rl_game_matching.setAnimation(animation2);
                rl_game_matching.setVisibility(View.VISIBLE);

            }
        }, 1000);

        startGame();

    }

    private void startGame(){

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                jumpToH5GameActivity();
            }
        }, 3000);
    }

    private void jumpToH5GameActivity(){
        Intent intent = new Intent(GameActivity.this, H5GameActivity.class);
        String url = new StringBuilder("http://39.105.35.10:7456/?uid=")
                                .append(App.getSharedPreferences().getUid())
                                .append("&ip=").append(ip)
                                .append("&name=").append(App.getSharedPreferences().getName())
                                .append("&sex=").append(0)
                                .append("&gameid=").append(gameId)
                                .append("&key=").append(key)
                                .append("&head=").append(App.getSharedPreferences().getHead()).toString();
        LogUtil.e("game matching url: ", url);
        intent.putExtra("url", url);
        startActivity(intent);
        finish();
    }

    /**
     * 从控件所在位置移动到控件的底部
     *
     * @return
     */
    public static TranslateAnimation moveToViewBottom() {
        TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 1.0f);
        mHiddenAction.setDuration(1000);
        return mHiddenAction;
    }


    private CountDownTimer waitTimer = new CountDownTimer(20000, 1000) {

        @Override
        public void onTick(long millisUntilFinished) {
            tv_waiting_time.setText("预计等待时间".concat(String.valueOf(millisUntilFinished / 1000)).concat("s"));
        }

        @Override
        public void onFinish() {
            tv_waiting_time.setText("预计等待时间0s");
            gameMatchingPresenter.cancelMatchingTimer();
//            key = "1.12";
//            ip = "127.0.0.1";
//            changeViewState();
            Toast.makeText(App.getContext(), "等待时间超时，请重新匹配", Toast.LENGTH_SHORT);
            finish();
        }
    };


}
