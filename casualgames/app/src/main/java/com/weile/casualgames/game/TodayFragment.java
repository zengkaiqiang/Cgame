package com.weile.casualgames.game;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;

import com.weile.casualgames.R;
import com.weile.casualgames.game.model.Player;
import com.weile.casualgames.game.presenter.IRankPresenter;
import com.weile.casualgames.game.presenter.RankPresenterCompl;
import com.weile.casualgames.game.view.ITodayRankView;
import com.weile.casualgames.model.config.ActivityResultHelper;
import com.weile.casualgames.view.widget.quickadapter.BaseAdapterHelper;
import com.weile.casualgames.view.widget.quickadapter.QuickAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TodayFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TodayFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TodayFragment extends Fragment implements ITodayRankView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private Context mContext;

//    @Bind(R.id.lvTodayRank)
//    ListView lvTodayRank;

    private ITodayRankView todayRankView;
    private IRankPresenter rankPresenter;
    private QuickAdapter<Player> todayRankAdapter;
    private View v;
    ListView lvTodayRank;

    public TodayFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChatFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TodayFragment newInstance(String param1, String param2) {
        TodayFragment fragment = new TodayFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

 /*   @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        initView();

    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_today, container, false);
        mContext = getActivity();
        initView();
        return v;
    }





    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

    }


    @Override
    public void onGetTodayRankDatas(ArrayList<Player> players) {
        if (players !=null && players.size()>0) {
            todayRankAdapter.clear();
            todayRankAdapter.addAll(players);
            todayRankAdapter.notifyDataSetChanged();
            ((ScrollView)v.findViewById(R.id.scrollView)).smoothScrollTo(0, 0);
        }
    }


    private void initView() {
        ((ScrollView)v.findViewById(R.id.scrollView)).smoothScrollTo(0, 0);
        lvTodayRank = v.findViewById(R.id.lvTodayRank);
        todayRankAdapter = new QuickAdapter<Player>(getActivity(), R.layout.adapter_item_rank_today) {
            @Override
            protected void convert(BaseAdapterHelper helper, Player item) {
                helper.setText(R.id.tv_ranking_num, String.valueOf(item.getId()));
                helper.setImageResource(R.id.iv_user_header, item.getHeader());
                helper.setText(R.id.tv_user_nickname, item.getName());
                helper.setText(R.id.tv_flowers_num, String.valueOf(item.getFlowers()));
                helper.setText(R.id.tv_player_grade, "Lv." + item.getGrade());
            }
        };
        lvTodayRank.setAdapter(todayRankAdapter);
        // 点击事件
        lvTodayRank.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

        rankPresenter = new RankPresenterCompl(getActivity(), this);
        rankPresenter.loadTodayRankDatas();
        setListViewHeightBasedOnChildren(lvTodayRank);
    }


    public void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }


    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ActivityResultHelper.SUCCESS:
//                    ContactsFragment.this.contactsLoadSuccess();
//                    mContactsAdapter.notifyDataSetChanged();
//                    ContactsIndexHelper.getInstance().praseContacts(ContactsHelper.getInstance().getBaseContacts());
//                    mSearchEt.setHint(String.format(mContext.getString(R.string.contacts_search), ContactsHelper.getInstance().getSearchContactsCount() + ""));
                    break;
                case ActivityResultHelper.START:
//                    ContactsFragment.this.contactsLoading();
                    break;
                case ActivityResultHelper.FAIL:
//                    ContactsFragment.this.contactsLoadFailed();
                    break;
                case ActivityResultHelper.UPDATE:
//                    String input=mSearchBox.getSearchEtInput();
//                    if(input==null||input.equals("")){
//                        ContactsHelper.getInstance().qwertySearch(null);
//                    }else{
//                        ContactsHelper.getInstance().qwertySearch(input);
//                    }
                    break;
            }
        }
    };

    public Handler getHandler() {
        return mHandler;
    }
    /*@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat, container, false);
    }*/

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

   /* @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }*/

  /*  @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }*/
/**
 * This interface must be implemented by activities that contain this
 * fragment to allow an interaction in this fragment to be communicated
 * to the activity and potentially other fragments contained in that
 * activity.
 * <p>
 * See the Android Training lesson <a href=
 * "http://developer.android.com/training/basics/fragments/communicating.html"
 * >Communicating with Other Fragments</a> for more information.
 */
public interface OnFragmentInteractionListener {
    // TODO: Update argument type and name
    void onFragmentInteraction(Uri uri);
}
}