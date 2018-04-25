package com.weile.casualgames.find;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.weile.casualgames.R;
import com.weile.casualgames.find.model.MyPostMode;
import com.weile.casualgames.find.view.MainBodyBottomView;
import com.weile.casualgames.find.view.UserInfoItemView;
import com.weile.casualgames.utils.AppKeys;
import com.weile.casualgames.utils.LogUtils;
import com.weile.casualgames.utils.PreferencesUtils;
import com.weile.casualgames.utils.Utility;

import org.kymjs.kjframe.utils.StringUtils;

import java.util.ArrayList;

/**
 * Created by Administrator
 */
public class FriendsAdapter extends BaseAdapter {
    private Context context;
    ArrayList<MyPostMode> arrayList;
    private Activity activity;
    //    private String img = "http://img.duangapp.cn/@/2015/04/24/10/upload_59_37_1429844376004.jpeg";
//    MyImageProgressListener mipl;
//    MyImageLoadingLisenter mill;
    private String mImageUrl;
    private String mNickName;
    private boolean mIsUserHomePage = false;
    private boolean mIsHideUserInfoView = false;
    private boolean mIsShowHotOrderView = false;
    private boolean mIsMoreVisible = true;
    private String mMyUserId;

    public FriendsAdapter(Context context, ArrayList<MyPostMode> list, Activity act) {
        this.context = context;
        arrayList = list;
        activity = act;
        mMyUserId = PreferencesUtils.getValueFromSPMap(context, AppKeys.USERID, "");
    }

    public void updateList(ArrayList<MyPostMode> list) {
        arrayList = list;
        this.notifyDataSetChanged();
    }

    private final int TYPE_1 = 1; //含图片布局
    private final int TYPE_2 = 0; //纯文字布局


    private final int VIEW_TYPE = 2;

    @Override
    public int getItemViewType(int position) {
        if (null != arrayList.get(position).photos && arrayList.get(position).photos.size() > 0)
            return TYPE_1;
        else
            return TYPE_2;
    }


    public int getViewTypeCount() {
        return VIEW_TYPE;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageViewHolder imageHolder = null;
        TextViewHolder textHolder = null;
        GridAdapter gridAdapter = null;
        final MyPostMode mode = arrayList.get(position);
        int type = getItemViewType(position);
        boolean isHideAttent = false;
        if (convertView == null) {
            switch (type) { // 各个布局中使用的资源进行初始化
                case TYPE_1:
                    imageHolder = new ImageViewHolder();
                    convertView = View.inflate(context, R.layout.mypost_listview_item_new, null);
                    convertView.setClickable(true);
//                    imageHolder.tvNickName = (TextView) convertView.findViewById(R.id.tvNickName);
//                    imageHolder.imgGender = (ImageView) convertView.findViewById(R.id.imgGender);
                    imageHolder.mp_lv_item_content_tv = (TextView) convertView.findViewById(R.id.mp_lv_item_content_tv);
                    imageHolder.gvPhotos = (GridView)convertView.findViewById(R.id.gvPhotos);
//                    imageHolder.praiseView = (PraiseView)convertView.findViewById(R.id.praiseView);
//                    imageHolder.mp_lv_item_topic_photo_img = (DrawProgressView) convertView.findViewById(R.id.mp_lv_item_topic_photo_img);
//                    imageHolder.mp_lv_item_topic_photo_img.getImageView().setScaleType(ImageView.ScaleType.CENTER);
                    //用户的昵称，地理位置
                    imageHolder.mUserInfoItemView = (UserInfoItemView) convertView.findViewById(R.id.user_info_item);
                    imageHolder.mMainBodyBottomView = (MainBodyBottomView) convertView.findViewById(R.id.main_body_bottom_view);
                    imageHolder.cardView = (RelativeLayout)convertView.findViewById(R.id.cardView);
                    imageHolder.photosLayout = (RelativeLayout)convertView.findViewById(R.id.photosLayout);
                    imageHolder.layoutContent = convertView.findViewById(R.id.layout_content);
                    convertView.setTag(imageHolder);
                    break;
                case TYPE_2:
                    textHolder = new TextViewHolder();
                    convertView = View.inflate(context, R.layout.mypost_listview_item_text_new, null);
                    convertView.setClickable(true);
//                    textHolder.tvNickName = (TextView) convertView.findViewById(R.id.tvNickName);
//                    textHolder.imgGender = (ImageView) convertView.findViewById(R.id.imgGender);
                    textHolder.mp_lv_item_content_tv = (TextView) convertView.findViewById(R.id.mp_lv_item_content_tv);
//                    textHolder.praiseView = (PraiseView)convertView.findViewById(R.id.praiseView);

                    //用户的昵称，地理位置
                    textHolder.mUserInfoItemView = (UserInfoItemView) convertView.findViewById(R.id.user_info_item);
                    textHolder.mMainBodyBottomView = (MainBodyBottomView) convertView.findViewById(R.id.main_body_bottom_view);
                    textHolder.cardView = (RelativeLayout)convertView.findViewById(R.id.cardView);
                    convertView.findViewById(R.id.my_duang_header_line_t).setVisibility(View.VISIBLE);
                    textHolder.layoutContent = convertView.findViewById(R.id.layout_content);
                    convertView.setTag(textHolder);
                    break;
            }
        } else { //缓存复用
            switch (type) {
                case TYPE_1:
                    imageHolder = (ImageViewHolder) convertView.getTag();
//                    imageHolder.mp_lv_item_topic_photo_img.getProgress().setVisibility(View.VISIBLE);
                    break;
                case TYPE_2:
                    textHolder = (TextViewHolder) convertView.getTag();
                    break;
            }
        }

        //用户ID是否与我的用户ID相同
        if (mode.user != null) {
            if(mMyUserId.equals(mode.user._id)){
                isHideAttent = true;
            }
        }

        switch (type) {
            case TYPE_1:
//                imageHolder.mp_lv_item_topic_photo_img.setColor("#ff6e49");
//                imageHolder.mp_lv_item_topic_photo_img.setOpacity(false);



//                if (mode != null && mode.user != null && mode.user.profile != null) {
//                    LogUtils.E("adapter nickname:" + mode.user.profile.nickname);
//                    LogUtils.E("adapter icon:" + mode.user.profile.icon);
//                }
//                if (mode != null) {
//                    LogUtils.E("adapter locname:" + mode.locName);
//                    LogUtils.E("adapter time:" + mode.createdAt);
//                }
                //用户的昵称， 地理位置，发布时间
                if (mIsHideUserInfoView){
                    imageHolder.mUserInfoItemView.setVisibility(View.GONE);
                }else {
                    imageHolder.mUserInfoItemView.setUserInfo(mImageUrl, mNickName);

                    if (isHideAttent){
                        imageHolder.mUserInfoItemView.setUserHeaderImageIsClick(false);
                    }else {
                        imageHolder.mUserInfoItemView.setUserHeaderImageIsClick(!mIsUserHomePage);
                    }
//
//                        //显示热榜
//                    imageHolder.mUserInfoItemView.setHotOrderStatus(mIsShowHotOrderView, position);
//
//                    imageHolder.mUserInfoItemView.setCreateAt(mode.createdAt);
                    imageHolder.mUserInfoItemView.initData(mode.user, mode.followStatus, activity);
//
//                    if (mIsMoreVisible){
//                        imageHolder.mUserInfoItemView.setMoreView();
//                    }
                }

//                if (mode.shareLocation) {
//                    imageHolder.mUserInfoItemView.setLocationVisible(true);
//                } else {
//                    imageHolder.mUserInfoItemView.setLocationVisible(false);
//                }


                imageHolder.mp_lv_item_content_tv.setMaxLines(3);
                imageHolder.mp_lv_item_content_tv.setEllipsize(TextUtils.TruncateAt.END);

                if (mode != null) {

                    imageHolder.mp_lv_item_content_tv.setText(StringUtils.isEmpty(mode.titleWithGroupName) ? context.getResources().getString(R.string.my_send_content_hint) : mode.titleWithGroupName);

                    //是否匿名用户
//                    if (!mode.isAnonymous) {
//                        imageHolder.tvNickName.setText((mode.user != null && mode.user.profile != null) ? mode.user.profile.nickname.trim() : "我");
//                        imageHolder.tvNickName.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                if (mode.user != null && !TextUtils.isEmpty(mode.user._id)) {
//                                    Intent intent = new Intent(activity, UserPostActivity.class);
//                                    intent.putExtra(AppKeys.MY_USER_INFO_TAG, mode.user._id);
//                                    activity.startActivity(intent);
//                                }
//                            }
//                        });
//
//                        if (imageHolder.imgGender != null  && mode.user != null && mode.user.profile != null){
//                            imageHolder.imgGender.setImageResource(mode.user.profile.sex == 1 ? R.drawable.ic_user_man : R.drawable.ic_user_women);
//                        }

//                    }else{
//                        imageHolder.tvNickName.setTextColor(context.getResources().getColor(R.color.main_title_location_text));
//                        imageHolder.tvNickName.setText("匿名用户");
//                    }



                    imageHolder.mMainBodyBottomView.initData(mode._id, mode.createdAt, mode.locName, mode.commentCount, mode.loc);

                    if (null != mode.photos && mode.photos.size() > 0) {
//                        ArrayList<String> tempList = new ArrayList<String>();
//                        tempList.add(mode.photos.get(0));
//                        tempList.add(mode.photos.get(0));
//                        LogUtils.E("mode.photos size: " + mode.photos.size());
//                        for (String photo : mode.photos){
//                            LogUtils.E("photo: " + photo);
//                        }


//                        switch (mode.photos.size()){
//                            case 1:
//
//                                break;
//
//                            case 2:
//                                break;
//                        }

//                        if (mode.photos.size() < 4){
//                            imageHolder.gvPhotos.setNumColumns(mode.photos.size());
//                        }else{
//                            imageHolder.gvPhotos.setNumColumns(3);
//                        }
//                        if (mode.photos.size() == 1){
//                            imageHolder.gvPhotos.setNumColumns(1);
//                        }else{
//                            imageHolder.gvPhotos.setNumColumns(2);
//                        }

                        switch(mode.photos.size()){
                            case 1:
                                imageHolder.gvPhotos.setNumColumns(1);
                                break;
                            case 2:
                                imageHolder.gvPhotos.setNumColumns(3);
                                break;

                            case 3:
                                imageHolder.gvPhotos.setNumColumns(4);
                                break;
                            default:
                                imageHolder.gvPhotos.setNumColumns(4);
                                break;
                        }
                        gridAdapter = new GridAdapter(context, mode.photos);
                        imageHolder.gvPhotos.setAdapter(gridAdapter);
                    }else{
                        LogUtils.E("mode.photos is null or size = 0");
                    }

//                    imageHolder.praiseView.setAdapter(this);
//                    imageHolder.praiseView.setUpCount(mode.score);
//                    imageHolder.praiseView.initData(mode._id, mode.score, mode.haveUp, mode.haveDown);
//                    if (mIsRankMode) {
//                        imageHolder.praiseView.setRankMode(position + 1);
//                    }

//                    if (convertView != null){
//                        convertView.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                Intent intent = new Intent(activity, TopicDetailActivity_.class);
//                                intent.putExtra("isMy", false);
//                                intent.putExtra("topic", mode._id);
//                                intent.putExtra("showKeyboard", false);
////            intent.putExtra("index", position - lv.getHeaderViewsCount());
//                                activity.startActivity(intent);
//                            }
//                        });
//                    }

                    if (imageHolder.cardView != null) {
                        imageHolder.cardView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
//                                Intent intent = new Intent(activity, TopicDetailActivity_.class);
//                                intent.putExtra("isMy", false);
//                                intent.putExtra("topic", mode._id);
//                                intent.putExtra("showKeyboard", false);
////            intent.putExtra("index", position - lv.getHeaderViewsCount());
//                                activity.startActivity(intent);
                            }
                        });
                    }
                    if (imageHolder.gvPhotos != null){
                        imageHolder.gvPhotos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                                Intent intent = new Intent(activity, TopicDetailActivity_.class);
//                                intent.putExtra("isMy", false);
//                                intent.putExtra("topic", mode._id);
//                                intent.putExtra("showKeyboard", false);
////            intent.putExtra("index", position - lv.getHeaderViewsCount());
//                                activity.startActivity(intent);
                            }
                        });
                    }

                    if (convertView != null){
                        convertView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
//                                Intent intent = new Intent(activity, TopicDetailActivity_.class);
//                                intent.putExtra("isMy", false);
//                                intent.putExtra("topic", mode._id);
//                                intent.putExtra("showKeyboard", false);
////            intent.putExtra("index", position - lv.getHeaderViewsCount());
//                                activity.startActivity(intent);
                            }
                        });
                    }

//                    if (imageHolder.layoutContent != null) {
//                        imageHolder.layoutContent.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                Intent intent = new Intent(activity, TopicDetailActivity_.class);
//                                intent.putExtra("isMy", false);
//                                intent.putExtra("topic", mode._id);
//                                intent.putExtra("showKeyboard", false);
////            intent.putExtra("index", position - lv.getHeaderViewsCount());
//                                activity.startActivity(intent);
//                            }
//                        });
//                    }
//                    if (imageHolder.photosLayout != null) {
//                        imageHolder.photosLayout.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                Intent intent = new Intent(activity, TopicDetailActivity_.class);
//                                intent.putExtra("isMy", false);
//                                intent.putExtra("topic", mode._id);
//                                intent.putExtra("showKeyboard", false);
////            intent.putExtra("index", position - lv.getHeaderViewsCount());
//                                activity.startActivity(intent);
//                            }
//                        });
//                    }


            /*        if (null != mode.photos && mode.photos.size() > 0) {
                        mill = new MyImageLoadingLisenter(imageHolder.mp_lv_item_topic_photo_img);
                        mipl = new MyImageProgressListener(imageHolder.mp_lv_item_topic_photo_img);
//                    imgl.displayImage(String.format(NetApi.QINIU_IMAGEURL, mode.photos.get(0), AppContext.screenWidth), imageHolder.mp_lv_item_topic_photo_img.getImageView(), options, mill, mipl);
                        imageHolder.mp_lv_item_topic_photo_img.getImageView().setVisibility(View.INVISIBLE);
                        imageHolder.mp_lv_item_topic_photo_img.getProgress().setVisibility(View.INVISIBLE);
//                    imageHolder.mp_lv_item_topic_photo_img.setProgress(22);

                        //新增动态IP解析 new test
                        AppContext.initCustomConfigImageLoader(context);
                        imgl.displayImage(String.format(NetApi.QINIU_IMAGEURL_2, mode.photos.get(0), AppContext.screenWidth), imageHolder.mp_lv_item_topic_photo_img.getImageView(), options, mill);
                        imageHolder.mp_lv_item_topic_photo_img.setTag(String.format(NetApi.QINIU_IMAGEURL, mode.photos.get(0), AppContext.screenWidth));
//                    imageHolder.mp_lv_item_topic_photo_img.setProgress(46);
//                    imageHolder.my_post_img.setTag(R.id.tag_image_url, String.format(NetApi.QINIU_IMAGEURL, mode.photos.get(0), AppContext.screenWidth));
//                    imageHolder.my_post_img.setTag(R.id.tag_image_view, imageHolder.mp_lv_item_topic_photo_img);
//                    imageHolder.my_post_img.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            String imge = (String) v.getTag(R.id.tag_image_url);
//                            SquareProgressBar imageView = (SquareProgressBar) v.getTag(R.id.tag_image_view);
//                            imgl.displayImage(imge, imageView.getImageView(), options, mill, mipl);
//                        }
//                    });
                    }*/

                }
//                else {
//                    imgl.displayImage(img, imageHolder.mp_lv_item_topic_photo_img, options, mill, mipl);
//                }
                //用户的昵称， 地理位置，发布时间
//                imageHolder.mUserInfoItemView.initData(mode, activity);
                break;

            case TYPE_2:

//                if (mode != null && mode.user != null && mode.user.profile != null) {
//                    LogUtils.E("adapter nickname:" + mode.user.profile.nickname);
//                    LogUtils.E("adapter icon:" + mode.user.profile.icon);
//
//                }
//                if (mode != null) {
//                    LogUtils.E("adapter locname:" + mode.locName);
//                    LogUtils.E("adapter time:" + mode.createdAt);
//                }
                //用户的昵称， 地理位置，发布时间
                if (mIsHideUserInfoView){
                    textHolder.mUserInfoItemView.setVisibility(View.GONE);
                }else {
                    textHolder.mUserInfoItemView.setUserInfo(mImageUrl, mNickName);
//                    textHolder.mUserInfoItemView.setUserHeaderImageIsClick(!mIsUserHomePage);
                    if (isHideAttent){
                        textHolder.mUserInfoItemView.setUserHeaderImageIsClick(false);
                    }else {
                        textHolder.mUserInfoItemView.setUserHeaderImageIsClick(!mIsUserHomePage);
                    }
//
//                        //显示热榜
//                    textHolder.mUserInfoItemView.setHotOrderStatus(mIsShowHotOrderView, position);
//
//                    textHolder.mUserInfoItemView.setCreateAt(mode.createdAt);
//
                    textHolder.mUserInfoItemView.initData(mode.user, mode.followStatus, activity);
//
//                    if (mIsMoreVisible){
//                        textHolder.mUserInfoItemView.setMoreView();
//                    }
                }

//                if (mode.shareLocation) {
//                    textHolder.mUserInfoItemView.setLocationVisible(true);
//                } else {
//                    textHolder.mUserInfoItemView.setLocationVisible(false);
//                }


                if (mode != null) {

                    //是否匿名用户
//                    if (!mode.isAnonymous) {
//                        textHolder.tvNickName.setText((mode.user != null && mode.user.profile != null) ? mode.user.profile.nickname.trim() : "我");
//                        textHolder.tvNickName.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                if (mode.user != null && !TextUtils.isEmpty(mode.user._id)) {
//                                    Intent intent = new Intent(context, UserPostActivity.class);
//                                    intent.putExtra(AppKeys.MY_USER_INFO_TAG, mode.user._id);
//                                    context.startActivity(intent);
//                                }
//                            }
//                        });
//
//                        if (textHolder.imgGender != null && mode.user != null && mode.user.profile != null) {
//                            textHolder.imgGender.setImageResource(mode.user.profile.sex == 1 ? R.drawable.ic_user_man : R.drawable.ic_user_women);
//                        }
//
//                    }else{
//                        textHolder.tvNickName.setTextColor(context.getResources().getColor(R.color.main_title_location_text));
//                        textHolder.tvNickName.setText("匿名用户");
//                    }

                    textHolder.mp_lv_item_content_tv.setText(StringUtils.isEmpty(mode.titleWithGroupName) ? context.getResources().getString(R.string.my_send_content_hint) : mode.titleWithGroupName);
                    textHolder.mMainBodyBottomView.initData(mode._id, mode.createdAt, mode.locName, mode.commentCount, mode.loc);

                    LogUtils.E("topicId:" + mode._id + "; title:" + mode.titleWithGroupName + ";up:" + mode.haveUp + ";down:" + mode.haveDown);

//                    textHolder.praiseView.setAdapter(this);
//                    textHolder.praiseView.setUpCount(mode.score);
//                    textHolder.praiseView.initData(mode._id, mode.score, mode.haveUp, mode.haveDown);
//                    if (mIsRankMode){
//                        textHolder.praiseView.setRankMode(position + 1);
//                    }

                    if (textHolder.cardView != null) {
                        textHolder.cardView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
//                                Intent intent = new Intent(context, TopicDetailActivity_.class);
//                                intent.putExtra("isMy", false);
//                                intent.putExtra("topic", mode._id);
//                                intent.putExtra("showKeyboard", false);
////            intent.putExtra("index", position - lv.getHeaderViewsCount());
//                                context.startActivity(intent);
                            }
                        });
                    }


                    if (convertView != null){
                        convertView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
//                                Intent intent = new Intent(activity, TopicDetailActivity_.class);
//                                intent.putExtra("isMy", false);
//                                intent.putExtra("topic", mode._id);
//                                intent.putExtra("showKeyboard", false);
////            intent.putExtra("index", position - lv.getHeaderViewsCount());
//                                activity.startActivity(intent);
                            }
                        });
                    }
                }


//                textHolder.mp_lv_item_viewnum_tv.setText(mode.readCount + " 浏览");
//                textHolder.mp_lv_item_spreadnum_tv.setText(mode.spreadTimes + "人传播");
//                textHolder.mp_lv_item_commentnum_tv.setText(mode.commentCount + " 评论");

                //用户的昵称， 地理位置，发布时间
//                textHolder.mUserInfoItemView.initData(mode);
                break;
        }
        return convertView;
    }


     class ImageViewHolder {
//        TextView tvNickName;
//        ImageView imgGender;
        TextView mp_lv_item_content_tv;
        GridView gvPhotos;
//        PraiseView praiseView;
//        DrawProgressView mp_lv_item_topic_photo_img;
        //用户的昵称，地理位置
        UserInfoItemView mUserInfoItemView;
        MainBodyBottomView mMainBodyBottomView;
        RelativeLayout cardView;
        RelativeLayout photosLayout;
        View layoutContent;
    }

     class TextViewHolder {
//        TextView tvNickName;
//        ImageView imgGender;
        TextView mp_lv_item_content_tv;
        View my_duang_header_line_t;
//        PraiseView praiseView;
        //用户的昵称，地理位置
        UserInfoItemView mUserInfoItemView;
        MainBodyBottomView mMainBodyBottomView;
        RelativeLayout cardView;
        RelativeLayout photosLayout;
        View layoutContent;
    }

    /*public class MyImageLoadingLisenter extends SimpleImageLoadingListener {

        DrawProgressView dpb;

        public MyImageLoadingLisenter(DrawProgressView bar) {
            dpb = bar;
        }

        @Override
        public void onLoadingCancelled(String imageUri, View view) {
            super.onLoadingCancelled(imageUri, view);
        }

        @Override
        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
            super.onLoadingComplete(imageUri, view, loadedImage);
            final List<String> displayedImages = Collections.synchronizedList(new LinkedList<String>());
            if (loadedImage != null) {
                ImageView imageView = (ImageView) view;
                boolean firstDisplay = !displayedImages.contains(imageUri);
                if (firstDisplay) {
                    dpb.getProgress().setVisibility(View.INVISIBLE);
                    FadeInBitmapDisplayer.animate(imageView, 150); // 设置image隐藏动画500ms
                    displayedImages.add(imageUri); // 将图片uri添加到集合中
                }
            }

        }

        @Override
        public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
            super.onLoadingFailed(imageUri, view, failReason);
        }

        @Override
        public void onLoadingStarted(String imageUri, View view) {
            super.onLoadingStarted(imageUri, view);
        }
    }

    public class MyImageProgressListener implements ImageLoadingProgressListener {
        private DrawProgressView sqb;
        int sec = 80;

        public MyImageProgressListener(DrawProgressView sqb) {
            this.sqb = sqb;
        }

        @Override
        public void onProgressUpdate(String s, View view, int i, int total) {
            LogUtils.E("onProgressUpdate   s=" + s + "///== i==" + i / 1024 + "total==" + total / 1024);
            sqb.setProgress(((double) i / (double) total) * 100);
        }

    }*/


    public void setUserInfo(String imageUrl, String nickName) {
        mImageUrl = imageUrl;
        mNickName = nickName;
    }

    public void setIsUserHomePage(boolean flag) {
        mIsUserHomePage = flag;
    }

    public void setHideUserInfoView(boolean isHide){
        mIsHideUserInfoView = isHide;
    }

    private void changeAttentStyleAndContent(UserInfoItemView userInfoItemView, boolean flag, int position){
        userInfoItemView.setHotOrderStatus(flag, position);
    }

    public void setIsShowHotOrderView(boolean isShow){
        mIsShowHotOrderView = isShow;
    }

    public void setMoreVisible(boolean isShow){
        mIsMoreVisible = isShow;
    }





    /****************************多图的适配器********************************/
    @SuppressLint("HandlerLeak")
    public class GridAdapter extends BaseAdapter {
        private LayoutInflater inflater; // 视图容器
        private int selectedPosition = -1;// 选中的位置
        private ArrayList<String> mPhotos;
//        private boolean shape;

//        public boolean isShape() {
//            return shape;
//        }
//
//        public void setShape(boolean shape) {
//            this.shape = shape;
//        }

        public GridAdapter(Context context, ArrayList<String> photos) {
            inflater = LayoutInflater.from(context);
            mPhotos = photos;
        }

        public void update() {
//            loading();
        }

        public int getCount() {
            return mPhotos.size();
//            return (Bimp.bmp.size() + 1);
        }

        public Object getItem(int arg0) {
            return null;
        }

        public long getItemId(int arg0) {
            return 0;
        }

        public void setSelectedPosition(int position) {
            selectedPosition = position;
        }

        public int getSelectedPosition() {
            return selectedPosition;
        }

        /**
         * ListView Item设置
         */
        public View getView(int position, View convertView, ViewGroup parent) {
            final ViewHolder holder;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.photos_item_view,
                        parent, false);
                holder = new ViewHolder();
                holder.dpvPhotoItem = (ImageView) convertView
                        .findViewById(R.id.imageView1);
                holder.dpvPhotoItem.setScaleType(ImageView.ScaleType.CENTER_CROP);
//                holder.dpvPhotoItem.getImageView().setLayoutParams(new RelativeLayout.LayoutParams(250, 250));
//                holder.dpvPhotoItem.setColor("#ff6e49");
//                holder.dpvPhotoItem.getImageView().setBackgroundColor(context.getResources().getColor(R.color.transparent));
//                holder.dpvPhotoItem.setOpacity(true);
                convertView.setTag(holder);

            } else {
                holder = (ViewHolder) convertView.getTag();
            }

//            holder.dpvPhotoItem.getProgress().setVisibility(View.VISIBLE);

            if (mPhotos.size() > 0) {
                int width = 0;
                switch (mPhotos.size()){
                    case 1:
                        width = 150;
                        break;
                    case 2:
                        width = 100;
                        break;
                    case 3:
                        width = 80;
                        break;
                    default:
                        width = 80;
                        break;
                }



                if (mPhotos.size() == 1){
                    holder.dpvPhotoItem.setMaxWidth(Utility.dip2px(context, 150));
                    holder.dpvPhotoItem.setMaxHeight(Utility.dip2px(context, 200));
                    holder.dpvPhotoItem.setAdjustViewBounds(true);
                }else{
                    holder.dpvPhotoItem.setLayoutParams(new RelativeLayout.LayoutParams(Utility.dip2px(context, width), Utility.dip2px(context, width)));
                }

//                  else{
//                    holder.dpvPhotoItem.setLayoutParams(new RelativeLayout.LayoutParams(Utility.dip2px(context, 120), Utility.dip2px(context, 120)));
//                }

//                mill = new MyImageLoadingLisenter(holder.dpvPhotoItem);
//                mipl = new MyImageProgressListener(holder.dpvPhotoItem);
//                    imgl.displayImage(String.format(NetApi.QINIU_IMAGEURL, mode.photos.get(0), AppContext.screenWidth), imageHolder.mp_lv_item_topic_photo_img.getImageView(), options, mill, mipl);
//                holder.dpvPhotoItem.getImageView().setVisibility(View.INVISIBLE);
//                holder.dpvPhotoItem.getProgress().setVisibility(View.INVISIBLE);
//                    imageHolder.mp_lv_item_topic_photo_img.setProgress(22);

                //新增动态IP解析 new test
//                AppContext.initCustomConfigImageLoader(context);
//                LogUtils.E("photo url: " + String.format(NetApi.QINIU_IMAGEURL_2, mPhotos.get(position), AppContext.screenWidth));
//                imgl.displayImage(String.format(NetApi.QINIU_IMAGEURL_2, mPhotos.get(position), AppContext.screenWidth), holder.dpvPhotoItem.getImageView(), options, mill);
//                holder.dpvPhotoItem.setTag(String.format(NetApi.QINIU_IMAGEURL_2, mPhotos.get(position), AppContext.screenWidth));


                //new test
//                holder.dpvPhotoItem.getProgress().setVisibility(View.VISIBLE);

//            if (mPhotos.size() > 0) {
                if (mPhotos != null && mPhotos.size() > 0) {
//                    holder.dpvPhotoItem.setProgress(22);
                    holder.dpvPhotoItem.setVisibility(View.VISIBLE);
                    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                    params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                    holder.dpvPhotoItem.setLayoutParams(params);
                    holder.dpvPhotoItem.setImageResource(R.mipmap.ic_rank_first_photo);
                }
            }

            return convertView;
        }

        public class ViewHolder {
            public ImageView dpvPhotoItem;
        }

    }

    private boolean mIsRankMode = false;
    public void setRankMode(boolean isRankMode){
        mIsRankMode = isRankMode;
    }

}
