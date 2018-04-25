package com.weile.casualgames.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class CustomScrollView extends ScrollView {
	private static final String TAG = "ScrollviewEdit";
	private ScrollView parent_scrollview;
	int currentY;

	public CustomScrollView(Context context) {
		super(context);
	}

	public CustomScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public CustomScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public CustomScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		if (parent_scrollview == null) {
			return super.onInterceptTouchEvent(ev);
		} else {
			if (ev.getAction() == MotionEvent.ACTION_DOWN) {
				// 将父scrollview的滚动事件拦截
				currentY = (int) ev.getY();
				setParentScrollAble(false);
				return super.onInterceptTouchEvent(ev);
			} else if (ev.getAction() == MotionEvent.ACTION_UP) {
				// 把滚动事件恢复给父Scrollview
				setParentScrollAble(true);
			} else if (ev.getAction() == MotionEvent.ACTION_MOVE) {
			}
		}
		return super.onInterceptTouchEvent(ev);
	}

	/**
	 * 是否把滚动事件交给父scrollview
	 *
	 * @param flag
	 */
	private void setParentScrollAble(boolean flag) {
		parent_scrollview.requestDisallowInterceptTouchEvent(!flag);
	}

}
