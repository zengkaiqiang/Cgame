package com.weile.casualgames.find;

import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.weile.casualgames.R;
import com.weile.casualgames.find.model.MyPostMode;
import com.weile.casualgames.net.NetUtil;
import com.weile.casualgames.utils.AppKeys;
import com.weile.casualgames.utils.LogUtils;
import com.weile.casualgames.utils.PreferencesUtils;
import com.weile.casualgames.view.widget.pulltorefresh.PullToRefreshBase;
import com.weile.casualgames.view.widget.pulltorefresh.PullToRefreshListView;

import org.kymjs.kjframe.ui.KJFragment;

import java.util.ArrayList;

/**
 * 帖子详情
 * 获取获取帖子详情和回复详情
 */
public class FindFragment extends KJFragment implements AdapterView.OnItemClickListener{
    private View v;

    private Animation shows;

    private PullToRefreshListView mPullRefreshListView;
    private ArrayList<MyPostMode> alist;
    private FriendsAdapter adapter;
    private ListView lv;
    private FrameLayout my_duang_fl;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return null;
    }

    private FrameLayout loadingView;
    private FrameLayout empty_layout;
    ArrayList<MyPostMode> alists;
//    private TextView tvTitleCenter;
//    private TextView tvTitleBack;
//    private FrameLayout flNotice;
    //    private TextView my_duang_number_tv, my_duang_readcount, my_duang_scorecount;
    private boolean isNeedUpdate = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_find,
                container, false);


        shows = AnimationUtils.loadAnimation(getActivity(), R.anim.popshow_anim_topic_detail);
        LogUtils.E("frag baoliao oncreateView");
        initTitle();
        initView();

        return v;
    }

    private void initView() {
        alist = new ArrayList<MyPostMode>();
        my_duang_fl = (FrameLayout) v.findViewById(R.id.my_message_list_fl);
        loadingView = (FrameLayout) v.findViewById(R.id.loadingView);
        ((TextView)v.findViewById(R.id.tv_loading)).setText("加载中...");
//        my_duang_loading_fl = (FrameLayout) findViewById(R.id.my_duang_loading_fl);
        empty_layout = (FrameLayout) v.findViewById(R.id.empty_layout);
//        ((TextView) findViewById(R.id.my_mailbox_empty_tv)).setText("上滑的酸爽试过才知道");
//        ImageView my_mailbox_empty_img = (ImageView) findViewById(R.id.my_mailbox_empty_img);
//        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);  // , 1是可选写的
//        lp.setMargins(0, StringUtils.dip2px(this, 108), 0, 0);
//        my_mailbox_empty_img.setLayoutParams(lp);
        mPullRefreshListView = (PullToRefreshListView) v.findViewById(R.id.pull_refresh_list);

//        mPullRefreshListView.setShowIndicator(false);
        mPullRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);

//        mPullRefreshListView.setOnLastItemVisibleListener(new OnLastItemVisibleListener() {
//            @Override
//            public void onLastItemVisible() {
//            }
//        });
        /*mPullRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                LogUtils.E("topic all ***OnRefreshListener is running");
                if (alist != null && alist.size() > 0) {
                    getMyNotices(alist.get(alist.size() - 1).createdAt);
                }
            }

        });*/

        mPullRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                refreshMyNotices();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                LogUtils.E("topic all onPullUpToRefresh is running");
                if (alist != null && alist.size() > 0) {
//                    new Thread(new Runnable() {
//                        @Override
//                        public void run() {
                    getMyNotices(alist.get(alist.size() - 1).createdAt);
//                        }
//                    }).start();

                }
            }
        });

        lv = mPullRefreshListView.getRefreshableView();
//        loadingView.setVisibility(View.VISIBLE);
        initData();
        //暂时
        adapter = new FriendsAdapter(getActivity(), alist, getActivity());
        adapter.setIsUserHomePage(false);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LogUtils.E("lv onitemclcik position: " + position);
            }
        });
//        lv.setOnItemClickListener(this);

        getMyNotices("");


//        ImageView my_mailbox_empty_img = (ImageView) findViewById(R.id.my_mailbox_empty_img);
//        my_mailbox_empty_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_myspread_none));
    }

    long my_menu_firstTime = 0l;
    private void initTitle() {
//        LinearLayout title_back_ll = (LinearLayout) findViewById(R.id.title_back_ll);
//        title_back_ll.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//        findViewById(R.id.title_more).setVisibility(View.GONE);
//        ((TextView) findViewById(R.id.title_back_tx))
//                .setText(R.string.menu_myspread_text);

        ImageView title_back_iv = (ImageView)v.findViewById(R.id.title_back_iv);
        title_back_iv.setVisibility(View.GONE);
        ((TextView)v.findViewById(R.id.tvTitleCenter)).setText("朋友圈");
//        title_back_iv.setImageResource(R.drawable.ic_my);
//        title_back_iv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                long secondTimes = System.currentTimeMillis();
//                if (secondTimes - my_menu_firstTime > 700) {// 如果两次按键时间间隔大于800毫秒，则不退出
//                    LogUtils.E("time ＞700");
////                    mIsLinkMyMenu = true;
//                    my_menu_firstTime = secondTimes;// 更新firstTime
//                    startActivity(new Intent(NewTopicMainActivity.this, MineActivity.class));
//                }
//            }
//        });

        //暂时先注释
//        ImageView title_more = (ImageView)findViewById(R.id.title_more);
//        title_more.setBackgroundDrawable(null);
//        title_more.setImageResource(R.drawable.ic_nav_rank);
//        title_more.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                long secondTimes = System.currentTimeMillis();
//                if (secondTimes - my_menu_firstTime > 700) {// 如果两次按键时间间隔大于800毫秒，则不退出
//                    my_menu_firstTime = secondTimes;// 更新firstTime
//                    startActivity(new Intent(NewTopicMainActivity.this, RankTopicActivity.class));
//                }
//
//            }
//        });


//        tvTitleBack = (TextView)v.findViewById(R.id.title_back_tx);
//        tvTitleBack.setVisibility(View.VISIBLE);
//        tvTitleBack.setText("  收益");
//        tvTitleBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), MyIncomeActivity.class);
//                startActivity(intent);
//            }
//        });


        LinearLayout llTitleCenter = (LinearLayout)v.findViewById(R.id.close_layout);
        llTitleCenter.setVisibility(View.VISIBLE);
//        tvTitleCenter = (TextView)v.findViewById(R.id.tvTitleCenter);
//        if (!TextUtils.isEmpty(AppContext.address)) {
//            tvTitleCenter.setText(AppContext.address.substring(0, AppContext.address.indexOf("市")));
//        }
//        if (tvTitleCenter != null) {
//            AppContext.sCityName = tvTitleCenter.getText().toString();
//        }


    }

    public void getMyNotices(final String lastTime) {

        if (loadingView != null) {
            loadingView.setVisibility(TextUtils.isEmpty(lastTime) ? View.VISIBLE : View.GONE);
        }

        if (!NetUtil.isNetworkConnected(getActivity()) && TextUtils.isEmpty(lastTime) && loadingView != null && empty_layout != null) {
            loadingView.setVisibility(View.GONE);
            empty_layout.setVisibility(View.VISIBLE);
        }

        //test
        alist.addAll(alists);
        my_duang_fl.setVisibility(View.VISIBLE);
//                my_duang_loading_fl.setVisibility(View.GONE);


        adapter.updateList(alist);
        finishRefresh();

        loadingView.setVisibility(View.GONE);

//                }

        if (null == alist || alist.size() <= 0) {
            LogUtils.E("topic all getMyNotices======alists.length() < 0");
            my_duang_fl.setVisibility(View.GONE);
//                    my_duang_loading_fl.setVisibility(View.GONE);
            empty_layout.setVisibility(View.VISIBLE);
        } else {
            empty_layout.setVisibility(View.GONE);
        }

       /* SpreadUtils.getNewTopics(getActivity(), lastTime, AppContext.sAreaType, AppContext.sCityName, "566ff624ca3b579d41941622", new MySpreadResponseListener() {
            @Override
            public void onSuccess(ArrayList<MyPostMode> alists) {

//                if (my_duang_loading_fl.getVisibility() == View.VISIBLE) {
//                    my_duang_loading_fl.setVisibility(View.GONE);
//                }
                LogUtils.E("topic all getMyNotices======alists.length() > 0");
//                showGuideTips();
                alist.addAll(alists);
                my_duang_fl.setVisibility(View.VISIBLE);
//                my_duang_loading_fl.setVisibility(View.GONE);

                //test
//                if (TextUtils.isEmpty(lastTime)){
//                    adapter = new MyDuangAdapter(NewTopicMainActivity.this, alist, NewTopicMainActivity.this);
//                    adapter.setIsUserHomePage(false);
//                    lv.setAdapter(adapter);
//                }else {

                adapter.updateList(alist);
                finishRefresh();

                loadingView.setVisibility(View.GONE);

//                }

                if (null == alist || alist.size() <= 0) {
                    LogUtils.E("topic all getMyNotices======alists.length() < 0");
                    my_duang_fl.setVisibility(View.GONE);
//                    my_duang_loading_fl.setVisibility(View.GONE);
                    empty_layout.setVisibility(View.VISIBLE);
                } else {
                    empty_layout.setVisibility(View.GONE);
                }
//                for (MyPostMode mode : alists) {
//                    LogUtils.E("loc size:" + mode.loc.size());
//                    LogUtils.E("lon:" + mode.loc.get(0));
//                    LogUtils.E("lat:" + mode.loc.get(1));
//                }
            }

            @Override
            public void onError(String error) {
                loadingView.setVisibility(View.GONE);
                if (null == alist || alist.size() <= 0) {
                    LogUtils.E("topic all onError======alists.length() < 0");
                    my_duang_fl.setVisibility(View.GONE);
                    empty_layout.setVisibility(View.VISIBLE);
                } else {
                    my_duang_fl.setVisibility(View.VISIBLE);
                    empty_layout.setVisibility(View.GONE);
                }
            }
        });*/
    }


    public void initData(){
        alists = new ArrayList<MyPostMode>();
        MyPostMode myPostMode = new MyPostMode();
        myPostMode._id = "1";
        myPostMode.createdAt = String.valueOf(System.currentTimeMillis());
        myPostMode.commentCount = 15;
        myPostMode.followStatus = 0;
        myPostMode.title = "这是测试的。。。。。。。。";
//        myPostMode.user.profile.nickname = "test1";
        ArrayList<String> photos = new ArrayList<String>();
        photos.add("/ic_rank_first_photo.png");
        photos.add("/ic_rank_first_photo.png");
        photos.add("/ic_rank_first_photo.png");
        myPostMode.photos = photos;
        alists.add(myPostMode);

        myPostMode = new MyPostMode();
        myPostMode._id = "1";
        myPostMode.createdAt = String.valueOf(System.currentTimeMillis());
        myPostMode.commentCount = 15;
        myPostMode.followStatus = 0;
        myPostMode.title = "这是测试的。。。。。。。。";
//        myPostMode.user.profile.nickname = "test1";
        photos = new ArrayList<String>();
        photos.add("/ic_rank_first_photo.png");
        photos.add("/ic_rank_first_photo.png");
        myPostMode.photos = photos;
        alists.add(myPostMode);

        myPostMode = new MyPostMode();
        myPostMode._id = "1";
        myPostMode.createdAt = String.valueOf(System.currentTimeMillis());
        myPostMode.commentCount = 15;
        myPostMode.followStatus = 0;
        myPostMode.title = "这是测试的。。。。。。。。";
//        myPostMode.user.profile.nickname = "test1";
         photos = new ArrayList<String>();
        photos.add("/ic_rank_first_photo.png");
        myPostMode.photos = photos;
        alists.add(myPostMode);

        myPostMode = new MyPostMode();
        myPostMode._id = "1";
        myPostMode.createdAt = String.valueOf(System.currentTimeMillis());
        myPostMode.commentCount = 15;
        myPostMode.followStatus = 0;
        myPostMode.title = "这是测试的。。。。。。。。";
//        myPostMode.user.profile.nickname = "test1";
         photos = new ArrayList<String>();
        photos.add("/ic_rank_first_photo.png");
        photos.add("/ic_rank_first_photo.png");
        photos.add("/ic_rank_first_photo.png");
        myPostMode.photos = photos;
        alists.add(myPostMode);


    }
    public void refreshMyNotices() {
        if (loadingView != null) {
            loadingView.setVisibility(View.GONE);
        }
//        AppContext.sAreaType = 0;

       /* SpreadUtils.getNewTopics(getActivity(), "", AppContext.sAreaType, AppContext.sCityName, "566ff624ca3b579d41941622", new MySpreadResponseListener() {
            @Override
            public void onSuccess(ArrayList<MyPostMode> alists) {
//                if (my_duang_loading_fl.getVisibility() == View.VISIBLE) {
//                    my_duang_loading_fl.setVisibility(View.GONE);
//                }
                LogUtils.E("getMyNotices======alists.length() > 0");
                alist.clear();
                alist.addAll(alists);
                my_duang_fl.setVisibility(View.VISIBLE);
//                my_duang_loading_fl.setVisibility(View.GONE);
                adapter.updateList(alist);
                finishRefresh();
                if (null == alist || alist.size() <= 0) {
                    LogUtils.E("getMyNotices======alists.length() < 0");
                    my_duang_fl.setVisibility(View.GONE);
//                    my_duang_loading_fl.setVisibility(View.GONE);
                    empty_layout.setVisibility(View.VISIBLE);
                } else {
                    empty_layout.setVisibility(View.GONE);
                }

//                for (MyPostMode mode : alists) {
//                    LogUtils.E("loc size:" + mode.loc.size());
//                    LogUtils.E("lon:" + mode.loc.get(0));
//                    LogUtils.E("lat:" + mode.loc.get(1));
//                }
            }

            @Override
            public void onError(String error) {
                loadingView.setVisibility(View.GONE);
                if (null == alist || alist.size() <= 0) {
                    LogUtils.E("onError======alists.length() < 0");
                    my_duang_fl.setVisibility(View.GONE);
                    empty_layout.setVisibility(View.VISIBLE);
                } else {
                    my_duang_fl.setVisibility(View.VISIBLE);
                    empty_layout.setVisibility(View.GONE);
                }
            }
        });*/
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        if (position - lv.getHeaderViewsCount() >= 0) {
//            LogUtils.E(position + "");
//            isNeedUpdate = true;
////            Intent intent = new Intent(this, NewTopicDetailActivity.class);
////            Intent intent = new Intent(this, _TopicDetailActivity.class);
//            //暂时屏蔽
//            Intent intent = new Intent(this, TopicDetailActivity_.class);
//            intent.putExtra("isMy", false);
//            intent.putExtra("topic", alist.get(position - lv.getHeaderViewsCount())._id);
//            intent.putExtra("index", position - lv.getHeaderViewsCount());
//            startActivity(intent);
//        }
        LogUtils.E(" onItem click" + position + "getHeaderViewsCount: " + lv.getHeaderViewsCount());
        if (position - lv.getHeaderViewsCount() >= 0) {
//            isNeedUpdate = true;
////            Intent intent = new Intent(this, NewTopicDetailActivity.class);
////            Intent intent = new Intent(this, _TopicDetailActivity.class);
//            //暂时屏蔽
//            Intent intent = new Intent(getActivity(), TopicDetailActivity_.class);
//            intent.putExtra("isMy", false);
//            intent.putExtra("topic", alist.get(position - lv.getHeaderViewsCount())._id);
//            intent.putExtra("index", position - lv.getHeaderViewsCount());
//            startActivity(intent);
        }
//        LogUtils.E("alist size: " + alist.size());
//        LogUtils.E("alist onItem click" + position);
//                Intent intent = new Intent(this, TopicDetailActivity_.class);
//        intent.putExtra("isMy", false);
//        intent.putExtra("topic", alist.get(position)._id);
//        intent.putExtra("showKeyboard", false);
//        startActivity(intent);
    }

    void finishRefresh() {
        if ((mPullRefreshListView != null && mPullRefreshListView
                .isRefreshing())
                ) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mPullRefreshListView.onRefreshComplete();
                }
            }, 1000);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        AppContext.myPostList = null;
    }

    @Override
    public void onPause() {
        super.onPause();
        if (isNeedUpdate) {
//            AppContext.myPostList = alist;
//            isNeedUpdate = true;
        }
        //SDK已经禁用了基于Activity 的页面统计，所以需要再次重新统计页面
//        MobclickAgent.onPageEnd("MyDuangActivity");
//        MobclickAgent.onPause(getActivity());
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtils.E("fragment new topic onresume");
//        if (!((NewTopicMainActivity)getActivity()).mCurrentCityName.equals(AppContext.sCityName)){
//            LogUtils.E("fragment new topic onReloading.");
//            if (alist != null && alist.size() > 0) {
//                alist.clear();
//                adapter.updateList(alist);
//                getMyNotices("");
//            }

            /*if (tvTitleCenter != null && tvTitleCenter.getText().toString().contains("厦门")){
                if (tvTitleBack != null){
                    tvTitleBack.setVisibility(View.VISIBLE);
                }
                if (flNotice != null){
                    flNotice.setVisibility(View.VISIBLE);
                }
            }else{

                if (tvTitleBack != null){
                    tvTitleBack.setVisibility(View.GONE);
                }
                if (flNotice != null){
                    flNotice.setVisibility(View.GONE);
                }
            }*/
//        }



//        if (isNeedUpdate) {
//            alist = AppContext.myPostList;
//            if (alist != null && alist.size() > 0) {
//                my_duang_fl.setVisibility(View.VISIBLE);
//                adapter.notifyDataSetChanged();
//            } else {
//                my_duang_fl.setVisibility(View.GONE);
//            }
//            isNeedUpdate = false;
//        }
//
//        if (AppContext.sIsPublisheSucess){
//            AppContext.sIsPublisheSucess = false;
//        }


        //SDK已经禁用了基于Activity 的页面统计，所以需要再次重新统计页面
//        MobclickAgent.onPageStart("MyDuangActivity");
//        MobclickAgent.onResume(getActivity());
    }

    //    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//
//        // TODO Auto-generated method stub
//
//        if (event.getAction() == MotionEvent.ACTION_DOWN) {
//            downX = event.getX();
//            downY = event.getY();
//            if (getCurrentFocus() != null && getCurrentFocus().getWindowToken() != null) {
//                manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
//                        InputMethodManager.HIDE_NOT_ALWAYS);
//            }
//        }
//        if (event.getAction() == MotionEvent.ACTION_MOVE) {
//            moveY = event.getY();
//            moveX = event.getX();
////            if (Math.abs(moveX-moveX)>150&&)
//        }
//        LogUtils.E("moveY==" + moveY + "///downY=" + downY + "////p=" + (moveY - downY));
//
//        return super.onTouchEvent(event);
//    }
//    float downX = 0, downY = 0, moveX = 0, moveY = 0;
//    SwipeBackLayout mSwipeBackLayout;

/*    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        if (event != null) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                downX = event.getX();
                downY = event.getY();
//                if (getCurrentFocus() != null && getCurrentFocus().getWindowToken() != null) {
//                    manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
//                            InputMethodManager.HIDE_NOT_ALWAYS);
//                }
            }
            if (event.getAction() == MotionEvent.ACTION_MOVE) {
                moveY = event.getY();
                moveX = event.getX();
                if (Math.abs(moveY - downY) > 150) {

                    mSwipeBackLayout = getSwipeBackLayout();
                    mSwipeBackLayout.setEnableGesture(false);
                } else {
                    mSwipeBackLayout = getSwipeBackLayout();
//                    mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
//                    mSwipeBackLayout.setSensitivity(AppContext.mContext, 0.18f);
//                    mSwipeBackLayout.setEdgeSize(AppContext.screenWidth);
                    mSwipeBackLayout.setEnableGesture(true);
                }
            }
            if (event.getAction() == MotionEvent.ACTION_UP) {
                mSwipeBackLayout = getSwipeBackLayout();
                mSwipeBackLayout.setEnableGesture(true);
            }
        }
        return super.dispatchTouchEvent(event);
    }*/


//    private void startPublishActivity(boolean flag) {
//        Intent intent;
//        if (flag) {
//            intent = new Intent(this, ImageGridActivity.class);
//            intent.putExtra("fromPublish", flag);
//        } else {
//            intent = new Intent(this, PublishedActivity.class);
//        }
//        startActivity(intent);
//    }

   /* private void startPublish(){
        if (!PreferencesUtils.getBooleanFromSPMap(this,
                AppKeys.IS_FINISH_CARD_GUIDE, false)) {
            PreferencesUtils.putBooleanToSPMap(this, AppKeys.IS_FINISH_CARD_GUIDE, true);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startPublishActivity(false);
                }
            }, 1500);
        }

    }*/


    /**
     * 菜单、返回键响应
     */
   /* @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if(keyCode == KeyEvent.KEYCODE_BACK) {



//            exitBy2Click(); //调用双击退出函数
        }
        return false;
    }*/
    /**
     * 双击退出函数
     */
   /* private static Boolean isExit = false;

    private void exitBy2Click() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            Toast.makeText(getActivity(), R.string.app_exit_text, Toast.LENGTH_SHORT).show();
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
    }*/


    private void showGuideTips(){
        boolean isFirst = PreferencesUtils.getBooleanFromSPMap(getActivity(),
                AppKeys.IS_FINISH_CARD_GUIDE);
        LogUtils.E("isFirst:" + isFirst);
        if (!isFirst){
//            PopWindowUtils.showUpDownTipsDialog(my_duang_fl, getActivity());
        }
    }

/*    private void showNotice(){
        flNotice = (FrameLayout)v.findViewById(R.id.flNotice);
        TextView tvLinkPrize = (TextView)v.findViewById(R.id.tvLinkPrize);
        ImageView ivCloseNotice = (ImageView)v.findViewById(R.id.ivCloseNotice);
        ivCloseNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flNotice.setVisibility(View.GONE);
//                PopWindowUtils.showAddReBiTipsDialog(my_duang_fl,"+1热币");
            }
        });
        SpannableString spanableInfo = new SpannableString("奖劢计划");
        spanableInfo.setSpan(new Clickable(), 0, 4,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvLinkPrize.setText(spanableInfo);
        //setMovementMethod()该方法必须调用，否则点击事件不响应
        tvLinkPrize.setMovementMethod(LinkMovementMethod.getInstance());
    }

    class Clickable extends ClickableSpan {
        @Override
        public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
            ds.setColor(getResources().getColor(R.color.my_income_link_text_color));
            ds.setUnderlineText(true);
        }
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), H5Activity.class);
            intent.putExtra("title", "奖励计划");
            intent.putExtra("url", NetApi.MY_PRIZE_PLAN);
            startActivity(intent);
        }
    }*/


}

