package com.weile.casualgames.mine.view;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.weile.casualgames.R;
import com.weile.casualgames.view.base.BaseActivity;
import com.weile.casualgames.view.widget.RoundImageView;

import org.kymjs.kjframe.ui.BindView;

public class UserInfoActivity extends BaseActivity {

    @BindView(id = R.id.iv_user_header, click = true)
    private RoundImageView iv_user_header;
    @BindView(id = R.id.iv_bg)
    private ImageView iv_bg;
    @BindView(id = R.id.scrollView)
    private ScrollView scrollView;
    @BindView(id = R.id.iv_more, click = true)
    private ImageView iv_more;

    @BindView(id = R.id.ll_userinfo_fans, click = true)
    private LinearLayout ll_userinfo_fans;
    @BindView(id = R.id.ll_userinfo_follow, click = true)
    private LinearLayout ll_userinfo_follow;




    @Override
    public void setRootView() {
        setContentView(R.layout.activity_user_info);
    }

    @Override
    public void initData() {
        super.initData();
//        iv_bg.setTouchEventListener(new DragScaleImageView.TouchEventListener() {
//            @Override
//            public void onTouchEvent(MotionEvent event) {
//                // TODO Auto-generated method stub
//            }
//        });    // 回弹事件监听
//        iv_bg.setBackScaleListener(new DragScaleImageView.BackScaleListener() {
//            @Override
//            public void onBackScale() {      // TODO Auto-generated method stub
//            }
//        });


        initWindow();

//    adjustmentPicture();

    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.iv_more:
                Intent intent = new Intent(this,
                        UserInfoEditActivity.class);
                startActivity(intent);
                break;

            case R.id.ll_userinfo_fans:
                Intent intent2 = new Intent(this,
                        FansTabActivity.class);
                intent2.putExtra("currentPage",0);
                startActivity(intent2);
                break;
            case R.id.ll_userinfo_follow:
                Intent intent3 = new Intent(this,
                        FansTabActivity.class);
                intent3.putExtra("currentPage",1);
                startActivity(intent3);
                break;


        }
    }

    private void initWindow() {//初始化，将状态栏和标题栏设为透明
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    public void adjustmentPicture() {
        WindowManager wm = (WindowManager) this
                .getSystemService(Context.WINDOW_SERVICE);

        int screenWidth = wm.getDefaultDisplay().getWidth();
        ViewGroup.LayoutParams lp = iv_user_header.getLayoutParams();
        lp.width = screenWidth;
        lp.height = RelativeLayout.LayoutParams.WRAP_CONTENT;
        iv_user_header.setLayoutParams(lp);
        iv_user_header.setMaxWidth(screenWidth);
        iv_user_header.setMaxHeight(screenWidth * 5);
    }
}
