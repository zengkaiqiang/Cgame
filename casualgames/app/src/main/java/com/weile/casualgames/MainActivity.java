package com.weile.casualgames;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.module.common.util.SystemUtil;
import com.temporary.network.util.CMd;
import com.weile.casualgames.chat.ChatFragment;
import com.weile.casualgames.find.FindFragment;
import com.weile.casualgames.game.GameFragment;
import com.weile.casualgames.matching.MatchingFragment;
import com.weile.casualgames.mine.MineFragment;
import com.weile.casualgames.model.config.ActivityResultHelper;
import com.weile.casualgames.view.base.BaseActivity;

import org.kymjs.kjframe.ui.ViewInject;

import rx.internal.operators.CompletableOnSubscribeMergeDelayErrorArray;

public class MainActivity extends BaseActivity {

    public static Context mContext;

    private RelativeLayout[] mTabs;
    private Fragment[] fragments;
    private FragmentTransaction fagmentTransaction;
    private int index;//tab索引
    private int currentTabIndex;//当前tab索引


    private long mExitTime;

    //第一次启动时为空，Activity被系统销毁后再启动不为空
    private Bundle savedInstanceState;

    private GameFragment gameFragment;
    private ChatFragment chatFragment;
    private MatchingFragment matchingFragment;
    private FindFragment findFragment;
    private MineFragment mineFragment;

    private App app;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
        super.onCreate(savedInstanceState);

        gameFragment = new GameFragment();
        chatFragment = new ChatFragment();
        matchingFragment = new MatchingFragment();
        findFragment = new FindFragment();
        mineFragment = new MineFragment();

        app = (App) getApplication(); // 获得Application对象
//
        CMd.syo("存在本地的数据Account="+app.getMySharedPreferences().getAccount());
        CMd.syo("存在本地的数据Age="+app.getMySharedPreferences().getAge());
        CMd.syo("存在本地的数据GList="+app.getMySharedPreferences().getGList());

//        FragmentManager fm = getFragmentManager();
//        gameFragment = (GameFragment) fm.findFragmentByTag(GameFragment.class.getSimpleName());
//        chatFragment = (ChatFragment) fm.findFragmentByTag(ChatFragment.class.getSimpleName());
//        matchingFragment = (MatchingFragment) fm.findFragmentByTag(MatchingFragment.class.getSimpleName());
//        findFragment = (FindFragment) fm.findFragmentByTag(FindFragment.class.getSimpleName());
//        mineFragment = (MineFragment) fm.findFragmentByTag(MineFragment.class.getSimpleName());

        mTabs = new RelativeLayout[5];

        mTabs[0] = (RelativeLayout) findViewById(R.id.rl_bottom_game);
        mTabs[1] = (RelativeLayout) findViewById(R.id.rl_bottom_chat);
        mTabs[2] = (RelativeLayout) findViewById(R.id.rl_bottom_matching);
        mTabs[3] = (RelativeLayout) findViewById(R.id.rl_bottom_find);
        mTabs[4] = (RelativeLayout) findViewById(R.id.rl_bottom_mine);
        for (int i = 0; i < mTabs.length; i++) {
            mTabs[i].setOnClickListener(this);
            mTabs[i].setSelected(false);
        }
        // 把第一个tab设为选中状态
        mTabs[0].setSelected(true);
        fragments = new Fragment[]{gameFragment, chatFragment, matchingFragment, findFragment, mineFragment};
        // 添加显示第一个fragment
        fagmentTransaction = getFragmentManager().beginTransaction();
        if (!gameFragment.isAdded()) {
            fagmentTransaction.add(R.id.tab_container, gameFragment, GameFragment.class.getSimpleName());
        }
        if (!chatFragment.isAdded()) {
            fagmentTransaction.add(R.id.tab_container, chatFragment, ChatFragment.class.getSimpleName());
        }
        if (!matchingFragment.isAdded()) {
            fagmentTransaction.add(R.id.tab_container, matchingFragment, MatchingFragment.class.getSimpleName());
        }
        if (!findFragment.isAdded()) {
            fagmentTransaction.add(R.id.tab_container, findFragment, FindFragment.class.getSimpleName());
        }
        if (!mineFragment.isAdded()) {
            fagmentTransaction.add(R.id.tab_container, mineFragment, MineFragment.class.getSimpleName());
        }

        fagmentTransaction.hide(chatFragment);
        fagmentTransaction.hide(matchingFragment);
        fagmentTransaction.hide(findFragment);
        fagmentTransaction.hide(mineFragment);
        fagmentTransaction.commit();

    }



    @Override
    public void setRootView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initData() {
        super.initData();
//        CrashHandler.getInstance().init(this);
//        App.setDbOptions(DBHandler.getInstance(this));
//        if (savedInstanceState == null) {//第一次创建Activity没有保存状态
//            gameFragment = new GameFragment();
//            chatFragment = new ChatFragment();
//            matchingFragment = new MatchingFragment();
//            findFragment = new FindFragment();
//            mineFragment = new MineFragment();
//        } else {//Activity被销毁后保存了状态
//            FragmentManager fm = getFragmentManager();
//            gameFragment = (GameFragment) fm.findFragmentByTag(GameFragment.class.getSimpleName());
//            chatFragment = (ChatFragment) fm.findFragmentByTag(ChatFragment.class.getSimpleName());
//            matchingFragment = (MatchingFragment) fm.findFragmentByTag(MatchingFragment.class.getSimpleName());
//            findFragment = (FindFragment) fm.findFragmentByTag(FindFragment.class.getSimpleName());
//            mineFragment = (MineFragment) fm.findFragmentByTag(MineFragment.class.getSimpleName());
//        }
//
//        mTabs = new RelativeLayout[5];

//        Observable<LogoutEvent> observable = RxBus.getInstance().register(new LogoutEvent());

    }

    @Override
    public void initWidget() {
        super.initWidget();

    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.rl_bottom_game:
                index = 0;
                gameFragment.getHandler().sendEmptyMessage(ActivityResultHelper.UPDATE);
                break;
            case R.id.rl_bottom_chat:
                index = 1;
                gameFragment.getHandler().sendEmptyMessage(ActivityResultHelper.UPDATE);
                break;
            case R.id.rl_bottom_matching:
                index = 2;
                matchingFragment.getHandler().sendEmptyMessage(ActivityResultHelper.UPDATE);
                break;
            case R.id.rl_bottom_find:
                index = 3;
//                findFragment.getHandler().sendEmptyMessage(ActivityResultHelper.UPDATE);
                break;
            case R.id.rl_bottom_mine:
                index = 4;
                break;
        }
        switchTab(index);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        RxBus.getInstance().unregister(new LogoutEvent());
    }


    public static Context getContext() {
        return mContext;
    }

    public static void intentTo(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        SystemUtil.startActivity(context, intent);
    }

    /**
     * 切换tab导航
     *
     * @param newTabIndex 新tab索引
     */
    private void switchTab(int newTabIndex) {
        if (currentTabIndex != newTabIndex) {
            FragmentTransaction trx = getFragmentManager().beginTransaction();
            trx.hide(fragments[currentTabIndex]);
            if (!fragments[newTabIndex].isAdded()) {
                trx.add(R.id.fragment_container, fragments[newTabIndex]);
            }
            trx.show(fragments[newTabIndex]).commit();
        }
        mTabs[currentTabIndex].setSelected(false);
        // 把当前tab设为选中状态
        mTabs[newTabIndex].setSelected(true);
        currentTabIndex = newTabIndex;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                ViewInject.toast("再按一次退出程序!");

                mExitTime = System.currentTimeMillis();

            } else {
                this.appExit();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
