package com.weile.casualgames.matching;

import android.content.Context;
import android.graphics.Paint;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.weile.casualgames.R;
import com.weile.casualgames.matching.model.Info;
import com.weile.casualgames.matching.view.WaveView;
import com.weile.casualgames.model.config.ActivityResultHelper;
import com.weile.casualgames.view.widget.RoundImageView;
import com.weile.casualgames.view.widget.dialog.GameMatchingDialog;
import com.weile.casualgames.view.widget.radarscan.RadarViewGroup;

import org.kymjs.kjframe.ui.KJFragment;

import java.util.Timer;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MatchingFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MatchingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MatchingFragment extends KJFragment implements RadarViewGroup.IRadarClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private Context mContext;

//    @Bind(R.id.radarViewGroup)
//    RadarViewGroup radarViewGroup;

    @Bind(R.id.wave_view)
    WaveView mWaveView;
    @Bind(R.id.iv_header)
    RoundImageView ivHeader;

    @Bind(R.id.iv_header1)
    RoundImageView iv_header1;
    @Bind(R.id.iv_header2)
    RoundImageView iv_header2;
    @Bind(R.id.iv_header3)
    RoundImageView iv_header3;
    @Bind(R.id.iv_header4)
    RoundImageView iv_header4;

    private int[] mImgs = {R.mipmap.len, R.mipmap.leo, R.mipmap.lep,
            R.mipmap.leq, R.mipmap.ler, R.mipmap.les, R.mipmap.mln, R.mipmap.mmz, R.mipmap.mna,
            R.mipmap.mnj, R.mipmap.leo, R.mipmap.leq, R.mipmap.les, R.mipmap.lep};
    private String[] mNames = {"Immortal", "唐马儒", "王尼玛", "张全蛋", "蛋花", "王大锤", "叫兽", "哆啦A梦"};

    private SparseArray<Info> mDatas = new SparseArray<>();


//    private WhewView wv;
    private RoundImageView my_photo;
    private static final int Nou = 1;

    // 声明一个SoundPool
    private SoundPool sp;
    // 定义一个整型用load（）；来设置suondIDf
    private int music;


    /*private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == Nou) {
                // 每隔10s响一次
                handler.sendEmptyMessageDelayed(Nou, 5000);
                sp.play(music, 1, 1, 0, 0, 1);
            }
        }
    };*/

    private void initView() {

        // 第一个参数为同时播放数据流的最大个数，第二数据流类型，第三为声音质量
//        sp = new SoundPool(10, AudioManager.STREAM_SYSTEM, 5);
        // 把你的声音素材放到res/raw里，第2个参数即为资源文件，第3个为音乐的优先级
//        music = sp.load(this, R.raw.hongbao_gq, 1);

//        wv = (WhewView) findViewById(R.id.wv);
//        my_photo = (RoundImageView) findViewById(R.id.my_photo);
//        my_photo.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                if(wv.isStarting()){
//                    //如果动画正在运行就停止，否则就继续执行
//                    wv.stop();
//                    //结束进程
//                    handler.removeMessages(Nou);
//                }else{
//                    // 执行动画
//                    wv.start();
//                    handler.sendEmptyMessage(Nou);
//                }
//            }
//        });

    }

    public MatchingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MatchingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MatchingFragment newInstance(String param1, String param2) {
        MatchingFragment fragment = new MatchingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    /*@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }*/


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        for (int i = 0; i < mImgs.length; i++) {
            Info info = new Info();
            info.setPortraitId(mImgs[i]);
            info.setAge(((int) Math.random() * 25 + 16) + "岁");
            info.setName(mNames[(int) (Math.random() * mNames.length)]);
            info.setSex(i % 3 == 0 ? false : true);
            info.setDistance(Math.round((Math.random() * 10) * 100) / 100);
            mDatas.put(i, info);
        }

//        new Handler().postDelayed(new Runnable() {
////            @Override
////            public void run() {
////                radarViewGroup.setDatas(mDatas);
////            }
////        }, 1500);
//        radarViewGroup.setiRadarClickListener(this);

        mWaveView.setDuration(12000);
        mWaveView.setMaxRadius(650);
        mWaveView.setStyle(Paint.Style.FILL);
        mWaveView.setColor(getResources().getColor(R.color.matching_waveview_bg));
        mWaveView.setInterpolator(new LinearOutSlowInInterpolator());
        mWaveView.start();

//        mWaveView.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                mWaveView.stop();
//            }
//        }, 10000);
        matchingTimer.schedule(new MatchingTimerTask(),2000, 18000);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        }, 2000);

    }

    @Override
    protected View inflaterView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        mContext = getActivity();
        return View.inflate(mContext, R.layout.fragment_matching, null);
    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);

    }

    @OnClick({R.id.iv_header})
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.iv_header:
                GameMatchingDialog gameMatchingDialog = new GameMatchingDialog(getActivity());
                gameMatchingDialog.show();
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();

//        if (matchingTimer != null){
//            matchingTimer.schedule(new MatchingTimerTask(),3000, 18000);
//        }

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

    }

    @Override
    protected void initData() {
        super.initData();

    }

    @Override
    public void onRadarItemClick(int position) {

    }
    private Timer matchingTimer = new Timer();

    private void showMatchingHead(){

        Animation animation1 = AnimationUtils
                .loadAnimation(getActivity(), R.anim.topic_detail_delete_pop_visibale);
        Animation animation2 = AnimationUtils
                .loadAnimation(getActivity(), R.anim.topic_detail_delete_pop_invisibale);

        iv_header1.postDelayed(new Runnable() {
            @Override
            public void run() {

                        iv_header1.setAnimation(animation1);
                        iv_header1.startAnimation(animation1);
                        iv_header1.setVisibility(View.VISIBLE);

                iv_header1.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                iv_header1.setAnimation(animation2);
                                iv_header1.startAnimation(animation2);
                                iv_header1.setVisibility(View.GONE);


                                iv_header2.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        iv_header2.setAnimation(animation1);
                                        iv_header2.startAnimation(animation1);
                                        iv_header2.setVisibility(View.VISIBLE);

                                        iv_header2.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                iv_header2.setAnimation(animation2);
                                                iv_header2.startAnimation(animation2);
                                                iv_header2.setVisibility(View.GONE);


                                                iv_header3.postDelayed(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        iv_header3.setAnimation(animation1);
                                                        iv_header3.startAnimation(animation1);
                                                        iv_header3.setVisibility(View.VISIBLE);

                                                        iv_header3.postDelayed(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                iv_header3.setAnimation(animation2);
                                                                iv_header3.startAnimation(animation2);
                                                                iv_header3.setVisibility(View.GONE);

                                                                iv_header4.postDelayed(new Runnable() {
                                                                    @Override
                                                                    public void run() {
                                                                        iv_header4.setAnimation(animation1);
                                                                        iv_header4.startAnimation(animation1);
                                                                        iv_header4.setVisibility(View.VISIBLE);

                                                                        iv_header4.postDelayed(new Runnable() {
                                                                            @Override
                                                                            public void run() {
                                                                                iv_header4.setAnimation(animation2);
                                                                                iv_header4.startAnimation(animation2);
                                                                                iv_header4.setVisibility(View.GONE);
                                                                            }
                                                                        }, 2000);


                                                                    }
                                                                }, 2000);
                                                            }
                                                        }, 2000);

                                                    }
                                                }, 2000);

                                            }
                                        }, 2000);
                                    }
                                }, 2000);


                            }
                        }, 2000);


            }
        }, 2000);


    }

    /*private void changeHeadState(RoundImageView view, final int tag, int time){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                switch (tag){
                    case 0:
                        view.setAnimation(animation2);
                        view.setAnimation(animation2);
                        view.setVisibility(View.GONE);
                        break;
                    case 1:
                        view.setAnimation(animation1);
                        view.setAnimation(animation1);
                        view.setVisibility(View.VISIBLE);
                        break;
                }
            }
        }, time);
    }*/

    public class MatchingTimerTask extends java.util.TimerTask {

        @Override

        public void run() {
            showMatchingHead();
        }

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

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

  /*  @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_matching, container, false);
    }
    */
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

   /* @Override
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

    @Override
    public void onPause() {
        super.onPause();
//        matchingTimer.cancel();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        matchingTimer.cancel();
    }
}
