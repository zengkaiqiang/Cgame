package com.module.common.log;
import com.weile.casualgames.common.BuildConfig;

public class LogDebug {
	public static final String LOG_TAG = "payment";
//	private  static boolean isDebug=BuildConfig.DEBUG;
	private  static boolean isDebug= BuildConfig.DEBUG;
	public LogDebug() {
	}

	public static final void analytics(boolean isUse,String tag,String log) {
		if (isUse&&isDebug)
			LogUtil.d(tag, log);
	}


	public static final void i(boolean isUse,String tag,String log) {
		if (isUse&&isDebug)
			LogUtil.i(tag, log);
	}

	public static final void v(boolean isUse,String tag,String log) {
		if (isUse&&isDebug)
			LogUtil.v(tag, log);
	}
	public static final void d(boolean isUse,String tag,String log) {
		if (isUse&&isDebug)
			LogUtil.d(tag, log);
	}

	public static final void w(boolean isUse,String tag,String log) {
		if (isUse&&isDebug)
			LogUtil.w(tag, log);
	}
	public static final void e(boolean isUse,String tag,String log) {
		if (isUse&&isDebug)
			LogUtil.e(tag, "" + log);
	}
	public static final void i(String tag,String log) {
		if (isDebug)
			LogUtil.i(tag, log);
	}

	public static final void v(String tag,String log) {
		if (isDebug)
			LogUtil.v(tag, log);
	}
	public static final void d(String tag,String log) {
		if (isDebug)
			LogUtil.d(tag, log);
	}

	public static final void w(String tag,String log) {
		if (isDebug)
			LogUtil.w(tag, log);
	}
	public static final void e(String tag,String log) {
		if (isDebug)
			LogUtil.e(tag, "" + log);
	}
	public static final void analytics(boolean isUse,String log) {
		if (isUse&&isDebug)
			LogUtil.d(LOG_TAG, log);
	}


	public static final void i(boolean isUse,String log) {
		if (isUse&&isDebug)
			LogUtil.i(LOG_TAG, log);
	}

	public static final void v(boolean isUse,String log) {
		if (isUse&&isDebug)
			LogUtil.v(LOG_TAG, log);
	}
	public static final void d(boolean isUse,String log) {
		if (isUse&&isDebug)
			LogUtil.d(LOG_TAG, log);
	}

	public static final void w(boolean isUse,String log) {
		if (isUse&&isDebug)
			LogUtil.w(LOG_TAG, log);
	}
	public static final void e(boolean isUse,String log) {
		if (isUse&&isDebug)
			LogUtil.e(LOG_TAG, "" + log);
	}

	public static final void i(String log) {
		if (isDebug)
			LogUtil.i(LOG_TAG, log);
	}

	public static final void v(String log) {
		if (isDebug)
			LogUtil.v(LOG_TAG, log);
	}
	public static final void d(String log) {
		if (isDebug)
			LogUtil.d(LOG_TAG, log);
	}

	public static final void w(String log) {
		if (isDebug)
			LogUtil.w(LOG_TAG, log);
	}
	public static final void e(String log) {
		if (isDebug)
			LogUtil.e(LOG_TAG, "" + log);
	}
}
