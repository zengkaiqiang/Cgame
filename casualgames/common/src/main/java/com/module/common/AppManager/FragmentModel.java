package com.module.common.AppManager;

import android.support.v4.app.Fragment;
/**
 *
 *
 * @author zjj
 */

public class FragmentModel {

	public String mTitle = "";
	public Fragment mFragment;
	public boolean mIsSave=false;
	public FragmentModel(String title, Fragment fg){
		mTitle = title;
		mFragment = fg;
	}
	public FragmentModel(String title, Fragment fg, boolean isSave){
		mTitle = title;
		mFragment = fg;
		mIsSave=isSave;
	}
}
