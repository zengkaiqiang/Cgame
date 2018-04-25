package com.weile.casualgames.game;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebSettings.ZoomDensity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.temporary.network.util.CMd;
import com.weile.casualgames.R;
import com.weile.casualgames.game.util.AndroidtoJs;
import com.weile.casualgames.view.base.BaseActivity;

/**
 * H5Game
 */
public class H5GameActivity extends BaseActivity {


    //    @Bind(R.id.wv_game)
    private WebView wvGame;
    private WebView wvGame2;

    private String url;


    private ImageView ivH5Friends;
    private ImageView ivH5Horn;
    private ImageView ivH5Microphone;
    private ImageView ivH5Music;
    private ImageView ivH5Exit;

    private int selectId=0;


    @Override
    public void setRootView() {

    }

    @Override
    public void initData() {
        super.initData();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_h5_game);
        initView();

    }

    @Override
    public void initWidget() {
        super.initWidget();

    }

    @SuppressLint("JavascriptInterface")
    private void initView() {
        wvGame = (WebView) findViewById(R.id.wv_game);
        wvGame2= (WebView) findViewById(R.id.wv_game2);
        ivH5Friends = (ImageView) findViewById(R.id.iv_h5_friends);
        ivH5Horn = (ImageView) findViewById(R.id.iv_h5_horn);
        ivH5Microphone = (ImageView) findViewById(R.id.iv_h5_microphone);
        ivH5Music = (ImageView) findViewById(R.id.iv_h5_music);
        ivH5Exit = (ImageView) findViewById(R.id.iv_h5_exit);
        ivH5Friends.setOnClickListener(this);
        ivH5Horn.setOnClickListener(this);
        ivH5Microphone.setOnClickListener(this);
        ivH5Music.setOnClickListener(this);
        ivH5Exit.setOnClickListener(this);
        Intent intent = getIntent();
        if (intent != null) {
            url = intent.getStringExtra("url");
        }

//        DialogUtil.showProgess(this, getResources().getString(R.string.pull_to_refresh_from_bottom_refreshing_label));
//
        // listview,webview中滚动拖动到顶部或者底部时的阴影
        wvGame.setOverScrollMode(View.OVER_SCROLL_NEVER);
        wvGame.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY); // 取消滚动条白边效果
        wvGame.getSettings().setRenderPriority(RenderPriority.HIGH);
        // 允许JS执行
        wvGame.getSettings().setJavaScriptEnabled(true);
//        wvGame.setJavaScriptCanOpenWindowsAutomatically(true);
        wvGame.addJavascriptInterface(new AndroidtoJs(), "cgames");
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int scale = dm.densityDpi;
        if (scale == 240) {
            wvGame.getSettings().setDefaultZoom(ZoomDensity.FAR);
        } else if (scale == 160) {
            wvGame.getSettings().setDefaultZoom(ZoomDensity.MEDIUM);
        } else {
            wvGame.getSettings().setDefaultZoom(ZoomDensity.CLOSE);
        }
//        Map<String, String> map = new HashMap<String, String>();
//        map.put("Authorization",
//                PreferencesUtils.getValueFromSPMap(this, AppKeys.APP_TOKEN));
        wvGame.setWebViewClient(new WebViewClient() {
            // 点击超链接的时候重新在原来进程上加载URL
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return true;
            }

            // webview加载完成
            @Override
            public void onPageFinished(WebView view, String url) {
//                DialogUtil.dismissProgessDirectly();
            }
        });

//        wvGame.setOnTouchListener(this);
        if (!TextUtils.isEmpty(url)) {
            wvGame.loadUrl(url);
//            wvGame.loadUrl("file:///android_asset/test.js");
        }

        wvGame2.setOverScrollMode(View.OVER_SCROLL_NEVER);
        wvGame2.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY); // 取消滚动条白边效果
        wvGame2.getSettings().setRenderPriority(RenderPriority.HIGH);
        // 允许JS执行
        wvGame2.getSettings().setJavaScriptEnabled(true);
//        wvGame.setJavaScriptCanOpenWindowsAutomatically(true);
//        wvGame2.addJavascriptInterface(new AndroidtoJs(), "cgames");

        wvGame2.setWebViewClient(new WebViewClient() {
            // 点击超链接的时候重新在原来进程上加载URL
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return true;
            }

            // webview加载完成
            @Override
            public void onPageFinished(WebView view, String url) {
//                DialogUtil.dismissProgessDirectly();
            }
        });

        wvGame2.loadUrl("http://192.168.67.71:7456/?uid=1&ip=127.0.0.1:9500&name=nimei&sex=1&gameid=1&key=1.12&head=ab");
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);

        switch (v.getId()) {
            case R.id.iv_h5_friends:
                wvGame.evaluateJavascript("test:clickExitBtn()", new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String value) {
                        //此处为 js 返回的结果
                        CMd.syo("点击声音");
                    }
                });
             CMd.syo("点击添加好友");
                break;
            case R.id.iv_h5_horn:
                wvGame.evaluateJavascript("test:clickMicrophoneBtn(0)", new ValueCallback<String>() {
                @Override
                public void onReceiveValue(String value) {
                    //此处为 js 返回的结果
                    CMd.syo("点击声音");
                }
            });

                break;
            case R.id.iv_h5_microphone:
                wvGame.evaluateJavascript("test:clickVoiceBtn(1)", new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String value) {
                        //此处为 js 返回的结果
                        CMd.syo("点击话筒");
                    }
                });

                break;
            case R.id.iv_h5_music:
                wvGame.evaluateJavascript("test:clickBackGroundMusicBtn(0)", new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String value) {
                        //此处为 js 返回的结果
                        CMd.syo("点击音乐");
                    }
                });
                break;
            case R.id.iv_h5_exit:
                wvGame.evaluateJavascript("test:clickExitBtn()", new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String value) {
                        //此处为 js 返回的结果
                        CMd.syo("点击退出");
                    }
                });
                break;
        }
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        //SDK已经禁用了基于Activity 的页面统计，所以需要再次重新统计页面
//        MobclickAgent.onPageEnd("H5Activity");
//        MobclickAgent.onPause(this);
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        //SDK已经禁用了基于Activity 的页面统计，所以需要再次重新统计页面
//        MobclickAgent.onPageStart("H5Activity");
//        MobclickAgent.onResume(this);
    }

//    /**
//     * html5页面js操作处理
//     *
//     *
//     */
//    private class JavaScriptInterface {
//        // 点赞处理
//        public void gameOver(String uid,String oid,String state) {
//            CMd.syo("看看能不能触发到="+uid+"   "+oid+"    "+state);
//        }
//
////        // 视频分享处理
////        public void shareVideoOnJavaScript(String title, String content,
////                                           String img, String url) {
////            shareControl.share(title, content, img, url);
////        }
//    }

    public void onClickEvent()
    {
        wvGame.post(new Runnable() {
            @Override
            public void run() {

                // 注意调用的JS方法名要对应上
                // 调用javascript的callJS()方法
                switch (selectId)
                {
                    case 0://加好友
                        wvGame.loadUrl("test:clickExitBtn()");
                        break;

                    case 1://声音
                        wvGame.loadUrl("test:clickMicrophoneBtn(0)");
                        break;
                    case 2://话筒
                        wvGame.loadUrl("test:clickVoiceBtn(1)");
                        break;
                    case 3://音乐
                        wvGame.loadUrl("test:clickBackGroundMusicBtn(0)");
                        break;
                    case 4://退出
                        wvGame.loadUrl("test:clickExitBtn()");
                        break;
                  default:
                      wvGame.loadUrl("test:clickExitBtn()");
                        break;

                }

            }
        });
    }
}
