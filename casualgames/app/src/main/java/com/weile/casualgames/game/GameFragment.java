package com.weile.casualgames.game;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.module.common.log.LogUtil;
import com.squareup.picasso.Picasso;
import com.temporary.loadimage.ImageLoaderExample;
import com.temporary.network.util.CMd;
import com.weile.casualgames.App;
import com.weile.casualgames.R;
import com.weile.casualgames.game.model.DoubleGame;
import com.weile.casualgames.game.model.GameMenu;
import com.weile.casualgames.game.presenter.GameHomePresenterCompl;
import com.weile.casualgames.game.presenter.IGameHomePresenter;
import com.weile.casualgames.game.view.IGameHomeView;
import com.weile.casualgames.model.config.ActivityResultHelper;
import com.weile.casualgames.utils.LogUtils;
import com.weile.casualgames.view.widget.NoScrollGridView;
import com.weile.casualgames.view.widget.RoundImageView;
import com.weile.casualgames.view.widget.quickadapter.BaseAdapterHelper;
import com.weile.casualgames.view.widget.quickadapter.QuickAdapter;

import org.kymjs.kjframe.ui.KJFragment;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GameFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GameFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GameFragment extends KJFragment implements IGameHomeView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;


    private Context mContext;
    private QuickAdapter<GameMenu> gameMenuAdapter;
    private QuickAdapter<DoubleGame> doubleGameAdapter;
    private IGameHomePresenter gameHomePresenter;
    private ArrayList gameMenus = new ArrayList<GameMenu>();

    @Bind(R.id.gvTopGameMenus)
    NoScrollGridView gvTopGameMenus;
    @Bind(R.id.gvDoubleGames)
    NoScrollGridView gvDoubleGames;
    @Bind(R.id.iv_user_header)
    RoundImageView iv_user_header;


    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE };

    private App app;
//    private ImageLoaderExample imageLoaderExample;

    public GameFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GameFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GameFragment newInstance(String param1, String param2) {
        GameFragment fragment = new GameFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        verifyStoragePermissions(getActivity());
        initView();

    }

    @Override
    protected View inflaterView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        mContext = getActivity();
        return View.inflate(mContext, R.layout.fragment_game, null);

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
    public void onGetGameMenuDatas(ArrayList<GameMenu> menus) {
        if (menus!=null && menus.size()>0) {
            gameMenuAdapter.clear();
            gameMenuAdapter.addAll(menus);
            gameMenuAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onGetDoubleGameDatas(List<DoubleGame> games) {
        if (games!=null && games.size()>0) {
            doubleGameAdapter.clear();
            doubleGameAdapter.addAll(games);
            doubleGameAdapter.notifyDataSetChanged();
            LogUtils.E("game adapter count: " + doubleGameAdapter.getCount());
        }
    }

    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE);
        }
    }

    private void initView() {
        gameMenuAdapter = new QuickAdapter<GameMenu>(getActivity(), R.layout.adapter_item_game_menu) {
            @Override
            protected void convert(BaseAdapterHelper helper, GameMenu item) {
                helper.setImageResource(R.id.iv_game_menu, item.getResId());
                helper.setText(R.id.tv_game_menu, getResources().getString(item.getName()));

            }
        };
        gvTopGameMenus.setAdapter(gameMenuAdapter);
        // 点击事件
        gvTopGameMenus.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        startActivity(new Intent(getActivity(), RankActivity.class));
                        break;
                    case 1:

//                        Map<String, Object> map = new HashMap<String, Object>();
//                        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/test.jpg");
//
//                        DataService.uploadFile(file,"http://192.168.67.71:9001", "/headimg/", getActivity());
//                            map.put("filedata", file);
//                        uploadImgAndParameter(map, "http://192.168.67.71:9001/headImg/");

//                        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/test.jpg");// 1.avi,jpg,png,rar
//                        LogUtils.E("weile photo url:" + Environment.getExternalStorageDirectory().getAbsolutePath() + "/test.jpg");
//
//                        Map<String, File> files = new HashMap<String, File>();
//                        files.put("user_header", file);
//                        Map<String, String> params = new HashMap<String, String>();
//                        params.put("Uid", String.valueOf(App.getSharedPreferences().getUid()));
//
//
//
//                        NetworkService.addPutUploadFileRequest("http://192.168.67.71:9001/headimg/", files, params, new Response.Listener<String>() {
//                            @Override
//                            public void onResponse(String s) {
//                                LogUtils.E("upload message: " + s);
//
//                            }
//                        }, new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError volleyError) {
//                                LogUtils.E("upload error: " + volleyError.getLocalizedMessage());
//                            }
//                        });


                        break;
                }
            }
        });


        doubleGameAdapter = new QuickAdapter<DoubleGame>(getActivity(), R.layout.adapter_item_game_double) {
            @Override
            protected void convert(BaseAdapterHelper helper, DoubleGame item) {

                int c1 = Color.parseColor(item.getC1());
                int c2 = Color.parseColor(item.getC2());
                int colors[] = {c1, c2};
                GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, colors);
                LogUtils.E("item id: " + item.getId() + " C1:" + item.getC1() + ";C2:" + item.getC2() + "adapter count:" + this.getCount());
//                gradientDrawable.setCornerRadius(5);
                switch (item.getId()){
                    case 101:
                        helper.setVisible(R.id.rl_game_title_bg, true);
                        helper.setAlpha(R.id.rl_game_title_bg, 0);
                        helper.setBackgroundColor(R.id.rl_game_title_bg, R.color.transparent);
                        helper.setImageResource(R.id.iv_game_start, item.getResId());
                        helper.setVisible(R.id.ll_game, false);
                        helper.setVisible(R.id.iv_game_start, true);
                        helper.setVisible(R.id.iv_game_end, false);
                        break;
                    case 102:
                        helper.setVisible(R.id.rl_game_title_bg, true);
                        helper.setAlpha(R.id.rl_game_title_bg, 0);
                        helper.setBackgroundColor(R.id.rl_game_title_bg, R.color.transparent);
                        helper.setImageResource(R.id.iv_game_end, item.getResId());
                        helper.setVisible(R.id.ll_game, false);
                        helper.setVisible(R.id.iv_game_end, true);
                        helper.setVisible(R.id.iv_game_start, false);
                        break;

                    default:
                        helper.setVisible(R.id.rl_game_title_bg, true);
                        helper.setVisible(R.id.iv_game_start, false);
                        helper.setVisible(R.id.iv_game_end, false);
                        helper.setImageDrawable(R.id.iv_game_title_bg, gradientDrawable);
                        helper.setAlpha(R.id.ll_game, 10);
                        if (item.getImg().startsWith("http")){
                            helper.setImageUrl(R.id.iv_game_background, item.getImg());
                        }else{
                            helper.setImageResource(R.id.iv_game_background, item.getResId());
                        }
                        helper.setText(R.id.tv_game_title, item.getName());
                        helper.setText(R.id.tv_game_play_number, item.getNum() + "对在玩");

                }


            }

            @Override
            public int getCount() {
                return super.getCount();
            }


        };







        gvDoubleGames.setAdapter(doubleGameAdapter);
        // 点击事件
        gvDoubleGames.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DoubleGame doubleGame = doubleGameAdapter.getItem(i);
                if (doubleGame.getId() <= 100) {
                    Intent intent = new Intent(getActivity(), GameActivity.class);
                    intent.putExtra("gameId", doubleGame.getId());
                    startActivity(intent);
                }
                if (doubleGame.getId() == 101) {
                   CMd.syo("点击了101" );
                    Intent intent = new Intent(getActivity(), H5GameActivity.class);
                   intent.putExtra("url", "file:///android_asset/test.html");
//                   intent.putExtra("url", "http://192.168.67.71:7456/?uid=1&ip=127.0.0.1:9500&name=nimei&sex=1&gameid=1&key=1.12&head=ab");
                    startActivity(intent);
                }
            }
        });


        gameHomePresenter = new GameHomePresenterCompl(getActivity(), this);
        gameHomePresenter.loadGameMenuDatas();
        gameHomePresenter.loadDoubleGameDatas();


        app = (App) getActivity().getApplication(); // 获得Application对象
//        imageLoaderExample=new ImageLoaderExample(getActivity());
        String headstr=app.getMySharedPreferences().getHead();
        if(headstr!=null&& !headstr.equals("")) {

            Picasso.with(getActivity()).load(headstr)
                    .placeholder(R.mipmap.default_image)
                    .error(R.mipmap.default_image)
                    .tag(getActivity())
                    .into(iv_user_header);
//            imageLoaderExample.loadImage(headstr, iv_user_header);
        }
    }


/* @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }*/

   /* @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false);
    }*/

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        RxBus.getInstance().unregister(BalanceEvent.class.getName());
//        RxBus.getInstance().unregister(MyFYClient.EVENT_ONLINE);
//        RxBus.getInstance().unregister(UserInfo.class.getName());
//        ButterKnife.unbind(this);
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



    private final static String TAG = "weile";
    /*****
     *
     * okHttp上传
     *
     *
     *
     */

    private static final Handler handler = new Handler(Looper.getMainLooper());

    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/*");

    private static final OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    okhttp3.Request request = chain.request().newBuilder()
                            .build();
                    return chain.proceed(request);
                }
            }).readTimeout(15, TimeUnit.SECONDS)// 设置读取超时时间
            .writeTimeout(15, TimeUnit.SECONDS)// 设置写的超时时间
            .connectTimeout(15, TimeUnit.SECONDS)// 设置连接超时时间
            .build();

    // 上传图片公有方法
    private final static void uploadImgAndParameter(Map<String, Object> map,
                                                    String url) {

        // mImgUrls为存放图片的url集合
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);

        if (null != map) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                if (entry.getValue() != null) {
                    if (entry.getValue() instanceof File) {
                        File f = (File) entry.getValue();
                        builder.addFormDataPart(entry.getKey(), f.getName(),
                                RequestBody.create(MEDIA_TYPE_PNG, f));
                    } else {
                        builder.addFormDataPart(entry.getKey(), entry
                                .getValue().toString());
                    }
                }

            }
        }
        // 创建RequestBody
        RequestBody body = builder.build();
        final okhttp3.Request request = new okhttp3.Request.Builder().url(url)// 地址
                .post(body)// 添加请求体
                .build();
        client.newCall(request).enqueue(new okhttp3.Callback() {

            @Override
            public void onResponse(Call call, final Response response)
                    throws IOException {
                final String data = response.body().string();
                LogUtil.e(TAG, "上传照片成功-->" + data);
                call.cancel();// 上传成功取消请求释放内存
            }

            @Override
            public void onFailure(Call call, final IOException e) {
                LogUtil.e(TAG, "上传失败-->" + e.getMessage());
                call.cancel();// 上传失败取消请求释放内存
            }

        });

    }


    /**
     * 上传文件
     * @param view
     */
    public void uploadFile(View view)
    {

        File file = new File(Environment.getExternalStorageDirectory(), "1.jpg");
        if (!file.exists())
        {
            Toast.makeText(getActivity(), "文件不存在，请修改文件路径", Toast.LENGTH_SHORT).show();
            return;
        }
        Map<String, String> params = new HashMap<>();
        //        params.put("username", "杨光福");
        //        params.put("password", "123");
        Map<String, String> headers = new HashMap<>();
        headers.put("APP-Key", "APP-Secret222");
        headers.put("APP-Secret", "APP-Secret111");

        String url = "http://192.168.10.168:8080/FileUpload/FileUploadServlet";

//        OkHttpUtils.post()//
//                .addFile("mFile", "agguigu-afu.jpe", file)//
//                .url(url)//
//                .params(params)//
//                .headers(headers)//
//                .build()//
//                .execute(new MyStringCallback());
    }

}
