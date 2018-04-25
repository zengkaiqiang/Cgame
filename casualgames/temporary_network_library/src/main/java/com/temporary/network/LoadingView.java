package com.temporary.network;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

public class LoadingView extends Dialog {

    Context conText = null;
    ProgressBar loadImg;
    LinearLayout loadDialogBox;

    public LoadingView(Context context) {
        super(context, R.style.dialogs);
//		super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_loading);
        conText = context;
        // 锁定dialog不可点击取消
        setCanceledOnTouchOutside(false);
        loadImg = (ProgressBar) findViewById(R.id.Load_Img);
        loadDialogBox = (LinearLayout) findViewById(R.id.Load_DialogBox);

    }

    public void setBgColor(int bgColor)
    {
        loadDialogBox.setBackgroundResource(bgColor);
        this.show();
    }

    public void showView()
    {
        this.show();
    }
    public void dismissView()
    {

        if(this.isShowing())
            this.dismiss();
    }




}

