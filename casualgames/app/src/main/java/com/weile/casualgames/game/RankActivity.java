package com.weile.casualgames.game;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.weile.casualgames.R;

import java.util.ArrayList;


public class RankActivity extends FragmentActivity {

    private ArrayList<Fragment> fragmentsList;

    private ViewPager vPager;
    private TextView tvTodayRank;
    private TextView tvYesterdayRank;
    private TextView tvCelebrityList;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);
        initViews();
        initViewPager();
    }


    private void initViews(){
        vPager = findViewById(R.id.vPager);
        tvTodayRank = findViewById(R.id.tvTodayRank);
        tvYesterdayRank = findViewById(R.id.tvYesterdayRank);
        tvCelebrityList = findViewById(R.id.tvCelebrityList);
        findViewById(R.id.rl_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    /**
     * 初始化ViewPager
     */
    private void initViewPager() {
        setTvTodayRankStyle();
        // 设置菜单栏的点击事件
        tvTodayRank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vPager.setCurrentItem(0);
                setTvTodayRankStyle();
            }
        });
        tvYesterdayRank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vPager.setCurrentItem(1);
                setTvYesterdayRankStyle();

            }
        });
        tvCelebrityList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vPager.setCurrentItem(2);
                setTvCelebrityListStyle();

            }
        });

        //Fragment容器
        fragmentsList = new ArrayList<Fragment>();
        //添加到Fragment容器�?
        fragmentsList.add(new TodayFragment());
        fragmentsList.add(new YesterdayFragment());
        fragmentsList.add(new CelebrityListFragment());

        vPager.setOffscreenPageLimit(0);
        //给ViewPager添加适配�?
        vPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentsList));
//        //设置默认的视图为�?�?
        vPager.setCurrentItem(0);
//        //给Viewpager添加监听事件
        vPager.setOnPageChangeListener(new MyOnPageChangeListener());
    }


    private void setTextViewStyle(TextView tv, int resId, int colorId){
        tv.setBackgroundResource(resId);
        tv.setTextColor(getResources().getColor(colorId));
    }

    private void setTvTodayRankStyle(){
        setTextViewStyle(tvTodayRank, R.drawable.shape_rank_navigation_left_select_bg,
                R.color.rank_navigation_text);
        setTextViewStyle(tvYesterdayRank, R.drawable.shape_rank_navigation_center_bg,
                R.color.game_user_vip_text);
        setTextViewStyle(tvCelebrityList, R.drawable.shape_rank_navigation_right_bg,
                R.color.game_user_vip_text);
    }

    private void setTvYesterdayRankStyle(){
        setTextViewStyle(tvTodayRank, R.drawable.shape_rank_navigation_left_bg,
                R.color.game_user_vip_text);
        setTextViewStyle(tvYesterdayRank, R.drawable.shape_rank_navigation_center_select_bg,
                R.color.rank_navigation_text);
        setTextViewStyle(tvCelebrityList, R.drawable.shape_rank_navigation_right_bg,
                R.color.game_user_vip_text);
    }

    private void setTvCelebrityListStyle(){
        setTextViewStyle(tvTodayRank, R.drawable.shape_rank_navigation_left_bg,
                R.color.game_user_vip_text);
        setTextViewStyle(tvYesterdayRank, R.drawable.shape_rank_navigation_center_bg,
                R.color.game_user_vip_text);
        setTextViewStyle(tvCelebrityList, R.drawable.shape_rank_navigation_right_select_bg,
                R.color.rank_navigation_text);
    }

    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int arg0) {
            switch (arg0) {
                case 0:
                    setTvTodayRankStyle();
                    vPager.setCurrentItem(0);
                    break;
                case 1:
                    setTvYesterdayRankStyle();
                    vPager.setCurrentItem(1);
                    break;
                case 2:
                    setTvCelebrityListStyle();
                    vPager.setCurrentItem(2);
                    break;
            }
        }
    }

}

