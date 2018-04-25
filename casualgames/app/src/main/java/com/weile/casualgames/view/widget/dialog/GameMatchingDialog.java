package com.weile.casualgames.view.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.weile.casualgames.R;


public class GameMatchingDialog extends Dialog {

    private TextView tvClose;
    private TextView tvRandom;
    private TextView tvFilter;
    private String mText;


    public GameMatchingDialog(Context context) {
        super(context, R.style.loading_dialog);
        // TODO Auto-generated constructor stub
        init();
    }

    private void init() {
        setContentView(R.layout.dialog_game_matching);
        setCancelable(false);

        ((TextView) findViewById(R.id.tv_close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        ((TextView)findViewById(R.id.tv_random)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        ((TextView)findViewById(R.id.tv_filter)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void setText(String text) {
        mText = text;
    }

    @Override
    public void dismiss() {
        if (isShowing()) {
            super.dismiss();
        }
    }


}
