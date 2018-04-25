package com.weile.casualgames.view.widget.dialog;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * 对话框View容器
 * 
 * @author zjj
 */
public class DialogViewContainer extends LinearLayout {

	private long mEnterAnimationStartTime;// 进入动画的启动时间
	private long mEnterAnimationDuration;// 进入动画的持续时间

	public DialogViewContainer(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public DialogViewContainer(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onAttachedToWindow() {
		// TODO Auto-generated method stub
		super.onAttachedToWindow();
		mEnterAnimationStartTime = System.currentTimeMillis();
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		// 确保在动画完成后才能点击界面
		if (System.currentTimeMillis() - mEnterAnimationStartTime <= mEnterAnimationDuration)
			return true;
		return super.dispatchTouchEvent(ev);
	}

	public void setEnterAnimationDuration(long duration) {
		mEnterAnimationDuration = duration;
	}
}
