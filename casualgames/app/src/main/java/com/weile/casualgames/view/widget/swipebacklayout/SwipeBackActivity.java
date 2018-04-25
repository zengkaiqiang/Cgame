
package com.weile.casualgames.view.widget.swipebacklayout;

import android.os.Bundle;
import android.view.View;

import com.weile.casualgames.view.base.BaseActivity;


public class SwipeBackActivity extends BaseActivity implements com.weile.casualgames.view.widget.swipebacklayout.SwipeBackActivityBase {
    private com.weile.casualgames.view.widget.swipebacklayout.SwipeBackActivityHelper mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHelper = new com.weile.casualgames.view.widget.swipebacklayout.SwipeBackActivityHelper(this);
        mHelper.onActivityCreate();
    }

    @Override
    public void setRootView() {
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mHelper.onPostCreate();
    }

    @Override
    public View findViewById(int id) {
        View v = super.findViewById(id);
        if (v == null && mHelper != null)
            return mHelper.findViewById(id);
        return v;
    }

    @Override
    public com.weile.casualgames.view.widget.swipebacklayout.SwipeBackLayout getSwipeBackLayout() {
        return mHelper.getSwipeBackLayout();
    }

    @Override
    public void setSwipeBackEnable(boolean enable) {
        getSwipeBackLayout().setEnableGesture(enable);
    }

    @Override
    public void scrollToFinishActivity() {
        com.weile.casualgames.view.widget.swipebacklayout.Utils.convertActivityToTranslucent(this);
        getSwipeBackLayout().scrollToFinishActivity();
    }

}
