package com.weile.casualgames.mine.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.weile.casualgames.mine.FansFragment;
import com.weile.casualgames.mine.FollowFragment;

import java.util.ArrayList;
import java.util.List;

public class FansViewPager extends FragmentPagerAdapter {
    String[] title = { "粉丝", "关注" };

    private List<Fragment> list_fragments;

    public FansViewPager(FragmentManager fm) {
        super(fm);
        list_fragments = new ArrayList<Fragment>();

//        fragmentSpotList=FragmentSpotList.getInstance();

        list_fragments.add(new FansFragment());
        list_fragments.add(new FollowFragment());
    }

    @Override
    public Fragment getItem(int position) {
        return   list_fragments.get(position);
//        switch (position) {
//            case 0:
//                fragmentSpotList = new FragmentSpotList();
//                return fragmentSpotList;
//            case 1:
//                fragmentRouteList = new FragmentRouteList();
//                return fragmentRouteList;
//            case 2:
//                fragmentTracksList = new FragmentTracksList();
//                return fragmentTracksList;
//
//            default:
//                return null;
//       }
    }

    @Override
    public int getCount() {

        return title.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }

}



