package com.weile.casualgames.mine;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.weile.casualgames.R;
import com.weile.casualgames.mine.adapter.FansAdapter;
import com.weile.casualgames.mine.model.FansVo;

import java.util.ArrayList;


public class FansFragment extends Fragment {

    private Activity myActivity;
    private Context myContext;
    private FansAdapter mAdapter;
    private ArrayList<FansVo> countlist = new ArrayList<FansVo>();
    public ListView mListView;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myActivity = this.getActivity();
        myContext = this.getContext();

        mAdapter = new FansAdapter(myActivity, countlist);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = null;
        if (mView == null && inflater != null) {
            mView = inflater.inflate(R.layout.fragment_fans, null);
            mListView = (ListView) mView.findViewById(R.id.common_list);
            mListView.setDividerHeight(0);// 隐藏分割线

            mListView.setCacheColorHint(Color.TRANSPARENT);// 设置缓存背景色为透明（不然，默认滑动时候会变黑掉）
            mListView.setAdapter(mAdapter);

            initValue();
        }

        return mView;
    }


    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onDestroy() {

        super.onDestroy();

    }

    public void initValue() {

        FansVo fansVo1 = new FansVo("风中的也百合", "个性签名1", false);
        countlist.add(fansVo1);
        FansVo fansVo2 = new FansVo("风中的也百合", "个性签名2", false);
        countlist.add(fansVo2);
        FansVo fansVo3 = new FansVo("风中的也百合", "个性签名3", false);
        countlist.add(fansVo3);
        FansVo fansVo4 = new FansVo("风中的也百合", "个性签名4", true);
        countlist.add(fansVo4);
        FansVo fansVo5 = new FansVo("风中的也百合", "个性签名5", false);
        countlist.add(fansVo5);

        setList();
    }

    public void setList() {
        // CMd.syo("刷新没用吗");
        mAdapter.initItem(countlist);
        mAdapter.notifyDataSetChanged();
    }


}
