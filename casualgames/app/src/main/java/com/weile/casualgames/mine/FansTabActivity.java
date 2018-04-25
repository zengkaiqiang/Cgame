package com.weile.casualgames.mine;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.temporary.tabs.PagerSlidingTabStrip;
import com.weile.casualgames.R;
import com.weile.casualgames.mine.view.FansViewPager;
import com.weile.casualgames.view.base.BaseActivity;

import org.kymjs.kjframe.ui.BindView;


public class FansTabActivity extends BaseActivity {

    @BindView(id = R.id.vp_content)
    private ViewPager pager;
    @BindView(id = R.id.tabs)
    private PagerSlidingTabStrip tabs;
    @BindView(id = R.id.iv_back, click = true)
    private ImageView iv_back;
    @BindView(id = R.id.ll_back, click = true)
    private LinearLayout ll_back;

    private FansViewPager adapter = null;
    private int currentPage = 0;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_fanstab);
    }

    @Override
    public void initData() {
        super.initData();
        currentPage = getIntent().getIntExtra("currentPage", 0);
        adapter = new FansViewPager(getSupportFragmentManager());
        pager.setAdapter(adapter);
        setCurrentPage(currentPage);
        tabs.setStyleType(2);
        tabs.setViewPager(pager);

        tabs.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        // 监听页面变化
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                // TODO Auto-generated method stub
                setCurrentPage(arg0);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub
//                Log.d("arg0+++", "" + arg0);
//                Log.d("arg1+++", "" + arg1);
//                Log.d("arg2+++", "" + arg2);


            }


            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }
        });

    }

    /**
     * 页面与head标签一致（可以设置head的按钮样式）
     *
     * @param arg0
     */
    private void setCurrentPage(int arg0) {
        switch (arg0) {
            case 0:

                pager.setCurrentItem(0);// 默认选中
                break;
            case 1:

                pager.setCurrentItem(1);
                break;

            default:
                break;
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
        }
    }
}
