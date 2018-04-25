package com.module.common.AppManager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;

import com.weile.casualgames.common.R;

/**
 * 存放不同的碎片动态类
 *
 * @author zjj
 */

public class TemplateFragmentActivity extends FragmentActivity implements OnClickListener {

    private Fragment mContent;
    private String fragmentKey;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        initView(getIntent());

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        initView(intent);
    }

    private void initView(Intent intent) {
        fragmentKey = intent.getStringExtra(FragmentControlCenter.FRAGMENT_KEY);
        FragmentModel fragmentModel = FragmentControlCenter.getInstance().getFragmentModel(fragmentKey);
        if (fragmentModel==null){
               return;
        }
        mContent = fragmentModel.mFragment;
        switchFragment(mContent);
    }

    public void switchFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }


    @Override
    public void onClick(View v) {

    }



    /**
     * 监听Back键按下事件,方法2:
     * 注意:
     * 返回值表示:是否能完全处理该事件
     * 在此处返回false,所以会继续传播该事件.
     * 在具体项目中此处的返回值视情况而定.
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            if (getSupportFragmentManager().getBackStackEntryCount()>0){
                getSupportFragmentManager().popBackStack();
            }
            if(getSupportFragmentManager().getBackStackEntryCount()==1){
              finish();
            }
            return false;
        }else {
            return super.onKeyDown(keyCode, event);
        }

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (TextUtils.isEmpty(fragmentKey)) {
            return;
        }
        FragmentControlCenter.getInstance().removeFragmentModel(fragmentKey);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /** attention to this below ,must add this**/
        //for umeng  share callback
//        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

}