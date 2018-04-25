package com.weile.casualgames.mine;

import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.weile.casualgames.R;
import com.weile.casualgames.mine.adapter.BlackListAdapter;
import com.weile.casualgames.mine.model.BlackListVo;
import com.weile.casualgames.net.NetUtil;
import com.weile.casualgames.utils.LogUtils;
import com.weile.casualgames.view.base.BaseActivity;
import com.weile.casualgames.view.widget.pulltorefresh.PullToRefreshBase;
import com.weile.casualgames.view.widget.pulltorefresh.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

public class BlackListActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout llBack;
    private ImageView ivBack;
    private LinearLayout llSubmit;
    private TextView ivSubmit;
    private RelativeLayout rlEmptyList;
    private FrameLayout flBlacklist;
    private PullToRefreshListView pullRefreshList;
    private FrameLayout loadingView;
    private LinearLayout dialogView;
    private TextView tvLoading;


    private ListView lv;
    private ArrayList<BlackListVo> alist;
    private ArrayList<BlackListVo> alists;
    private BlackListAdapter adapter;




    @Override
    public void setRootView() {
        setContentView(R.layout.activity_blacklist);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initData() {
        super.initData();
        initFindId();
        initList();
        tvLoading.setText("加载中...");
        alist = new ArrayList<BlackListVo>();

    }

    public void initDataList(){
        alist = new ArrayList<BlackListVo>();
        BlackListVo blackListVo=new BlackListVo();
        blackListVo.setName("风中野百合");
        blackListVo.setSelect(true);
        alist.add(blackListVo);
        BlackListVo blackListVo2=new BlackListVo();
        blackListVo2.setName("风中野百合");
        blackListVo2.setSelect(true);
        alist.add(blackListVo2);
        BlackListVo blackListVo3=new BlackListVo();
        blackListVo3.setName("风中野百合");
        blackListVo3.setSelect(false);
        alist.add(blackListVo3);
        BlackListVo blackListVo4=new BlackListVo();
        blackListVo4.setName("风中野百合");
        blackListVo4.setSelect(true);
        alist.add(blackListVo4);
        BlackListVo blackListVo5=new BlackListVo();
        blackListVo5.setName("风中野百合");
        blackListVo5.setSelect(true);
        alist.add(blackListVo5);
        BlackListVo blackListVo6=new BlackListVo();
        blackListVo6.setName("风中野百合");
        blackListVo6.setSelect(true);
        alist.add(blackListVo6);
        BlackListVo blackListVo7=new BlackListVo();
        blackListVo7.setName("风中野百合");
        blackListVo7.setSelect(true);
        alist.add(blackListVo7);
        BlackListVo blackListVo8=new BlackListVo();
        blackListVo8.setName("风中野百合");
        blackListVo8.setSelect(false);
        alist.add(blackListVo8);
        BlackListVo blackListVo9=new BlackListVo();
        blackListVo9.setName("风中野百合");
        blackListVo9.setSelect(true);
        alist.add(blackListVo9);
        BlackListVo blackListVo10=new BlackListVo();
        blackListVo10.setName("风中野百合");
        blackListVo10.setSelect(true);
        alist.add(blackListVo10);
        BlackListVo blackListVo11=new BlackListVo();
        blackListVo11.setName("风中野百合");
        blackListVo11.setSelect(true);
        alist.add(blackListVo11);
        BlackListVo blackListVo12=new BlackListVo();
        blackListVo12.setName("风中野百合");
        blackListVo12.setSelect(true);
        alist.add(blackListVo12);
    }

    public void initFindId() {
        llBack = (LinearLayout) findViewById(R.id.ll_back);
        ivBack = (ImageView) findViewById(R.id.iv_back);
        llSubmit = (LinearLayout) findViewById(R.id.ll_submit);
        ivSubmit = (TextView) findViewById(R.id.iv_submit);
        rlEmptyList = (RelativeLayout) findViewById(R.id.rl_empty_list);
        flBlacklist = (FrameLayout) findViewById(R.id.fl_blacklist);
        pullRefreshList = (PullToRefreshListView) findViewById(R.id.pull_refresh_list);
        loadingView = (FrameLayout) findViewById(R.id.loadingView);
        dialogView = (LinearLayout) findViewById(R.id.dialog_view);
        tvLoading = (TextView) findViewById(R.id.tv_loading);

        llBack.setOnClickListener(this);
        ivBack.setOnClickListener(this);

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

    public void  initList()
    {
        pullRefreshList.setMode(PullToRefreshBase.Mode.BOTH);
        initDataList();
        pullRefreshList.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
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
        lv = pullRefreshList.getRefreshableView();

        adapter = new BlackListAdapter(this, alist);
        lv.setAdapter(adapter);
        adapter.initItem(alist);
        adapter.notifyDataSetChanged();
    }

    public void refreshMyNotices() {
        if (loadingView != null) {
            loadingView.setVisibility(View.GONE);
        }
    }
    void finishRefresh() {
        if ((pullRefreshList != null && pullRefreshList
                .isRefreshing())
                ) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    pullRefreshList.onRefreshComplete();
                }
            }, 1000);
        }
    }

    public void getMyNotices(final String lastTime) {

        if (loadingView != null) {
            loadingView.setVisibility(TextUtils.isEmpty(lastTime) ? View.VISIBLE : View.GONE);
        }

        if (!NetUtil.isNetworkConnected(this) && TextUtils.isEmpty(lastTime) && loadingView != null && rlEmptyList != null) {
            loadingView.setVisibility(View.GONE);
            rlEmptyList.setVisibility(View.VISIBLE);
        }

        //test
        alist.addAll(alists);
//        my_duang_fl.setVisibility(View.VISIBLE);
//                my_duang_loading_fl.setVisibility(View.GONE);


        adapter.initItem(alist);
        finishRefresh();

        loadingView.setVisibility(View.GONE);

//                }

        if (null == alist || alist.size() <= 0) {
            LogUtils.E("topic all getMyNotices======alists.length() < 0");
//            my_duang_fl.setVisibility(View.GONE);
//                    my_duang_loading_fl.setVisibility(View.GONE);
            rlEmptyList.setVisibility(View.VISIBLE);
        } else {
            rlEmptyList.setVisibility(View.GONE);
        }


    }

}
