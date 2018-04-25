package com.weile.casualgames.view.widget.dialog;


import android.content.Context;
import android.view.Gravity;

import com.weile.casualgames.R;

/**
 * 基础对话框, 用法与AlertDialog基本一致
 * 
 * @author zjj
 */
public class BaseDialog extends CustomDialog {

	public BaseDialog(Context context) {
		super(context, R.style.CustomDialog);
		getWindow().getAttributes().dimAmount = 0.4f;
	}

	@Override
	public void show() {
		getWindow().setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
		setLayout(-1, -2);
		super.show();

	}


}
