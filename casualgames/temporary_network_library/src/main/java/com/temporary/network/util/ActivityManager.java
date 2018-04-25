package com.temporary.network.util;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.app.Application;

/**
 * @author 作者 zkq:
 * @version 创建时间：2016年9月14日 下午3:26:01 Activity管理类
 */
public class ActivityManager extends Application {
	// 建立链表集合
	private List<Activity> activityList = new LinkedList<Activity>();

	// 用单例模式，保证，这个ActivityManager 在整个应用中只有一个
	private static ActivityManager instance;

	private ActivityManager() {

	}

	public static ActivityManager getInstance() {
		if (instance == null) {
			instance = new ActivityManager();
		}
		return instance;
	}

	// 向链表中，添加Activity
	public void addActivity(Activity activity) {
		activityList.add(activity);
	}

	// 结束整个应用程序
	public void exit() {

		// 遍历 链表，依次杀掉各个Activity
		System.out.println("当前activityList=" + activityList.size());
		for (Activity activity : activityList) {
			if (!activity.isFinishing()) {
				activity.finish();
			}
		}
		// 杀掉，这个应用程序的进程，释放 内存
		int id = android.os.Process.myPid();
		if (id != 0) {
			android.os.Process.killProcess(id);
		}
	}

}