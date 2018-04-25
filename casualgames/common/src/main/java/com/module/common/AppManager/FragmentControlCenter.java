package com.module.common.AppManager;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.text.TextUtils;


import java.util.HashMap;
import java.util.Map;
/**
 * 存放不同的碎片动态类
 *
 * @author zjj
 */

public class FragmentControlCenter {
    public  static  String  FRAGMENT_KEY="fragment";
	private static FragmentControlCenter instance;

	private Map<String, FragmentModel> mFragmentModelMaps = new HashMap<String, FragmentModel>();



	public static synchronized FragmentControlCenter getInstance() {
		if (instance == null) {
			instance = new FragmentControlCenter();
		}
		return instance;
	}

	public FragmentModel getFragmentModel(Fragment fragment) {
		return getFragmentModel(fragment,false);
	}
	public FragmentModel getFragmentModel(Fragment fragment,boolean isNew) {
		String key = fragment.getClass().getName();
		if (isNew){
			mFragmentModelMaps.remove(key);
		}
		FragmentModel fragmentModel = mFragmentModelMaps.get(key);
		if (fragmentModel == null) {
			fragmentModel = FragmentBuilder.getFragmentModel(key, fragment);
			mFragmentModelMaps.put(key, fragmentModel);
		}
		return fragmentModel;
	}
	public FragmentModel getFragmentModel(String key) {
		if (key.equals("")) {
			return null;
		}
		return mFragmentModelMaps.get(key);
	}

	public void addFragmentModel(String name, FragmentModel fragment) {
		mFragmentModelMaps.put(name, fragment);
	}
	public void removeFragmentModel(String key) {
		if (TextUtils.isEmpty(key)) {
	     	return;
		}
		if (mFragmentModelMaps.containsKey(key)){
			mFragmentModelMaps.remove(key);
		}

	}
	public static class FragmentBuilder {

		public static FragmentModel getFragmentModel(String key,
				Fragment fragment) {
			FragmentModel fragmentModel = new FragmentModel(key, fragment);
			return fragmentModel;
		}
	}
	
	public void  StartFragmentActivity(Context context, Fragment fragment) {
		StartFragmentActivity(context,fragment,true);
	}

	/**
	 *
	 * @param context
	 * @param fragment
	 * @param isNew  是否新建fragment
     */
	public void  StartFragmentActivity(Context context, Fragment fragment,boolean isNew) {
		Intent  intent=new Intent();
		intent.setClass(context, TemplateFragmentActivity.class);
		String  fragKey= getFragmentModel(fragment,isNew).mTitle;
		intent.putExtra(FRAGMENT_KEY, fragKey);
		context.startActivity(intent);
	}
}
