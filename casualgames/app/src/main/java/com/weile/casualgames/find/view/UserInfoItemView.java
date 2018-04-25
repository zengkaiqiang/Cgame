package com.weile.casualgames.find.view;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.weile.casualgames.R;
import com.weile.casualgames.user.model.UserRegisterMode;
import com.weile.casualgames.utils.LogUtils;
import com.weile.casualgames.utils.StringUtils;
import com.weile.casualgames.view.widget.RoundImageView;

/**
 * @author zjj
 */
public class UserInfoItemView extends RelativeLayout {

//    private Activity mActivity;
    private RoundImageView mUserHeaderImageView;
    private TextView mNickNameTextView;
    private TextView mUserSignatureTextView;
    private TextView mAttentTextView;
    private TextView mRankTextView;
    private ImageView mGenderImageView;
    private ImageView mAuthenticationImageView;
    private ImageView imgMore;
    private String mImageUrl;
    private String mNickName;
    private boolean mIsClick = true;
    private Context mContext;
    private int mCurrentAttentStatus; //1已关注; 2互相关注
//    private boolean mIsAttentShow = true;
    private boolean mIsHotOrder = false;
    private int mPosition = 0;
    private String mCreateAt;

    public UserInfoItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        mContext = context;
        LayoutInflater mInflater = LayoutInflater.from(context);
        View view = mInflater.inflate(R.layout.user_info_item_view, null);
        addView(view, new LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

        initViews();
    }

    public UserInfoItemView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }



    private void initUserHeaderBackground(String url) {
        if (!TextUtils.isEmpty(url)) {

//            String imageUrl = CDNSManager.getHeaderIconUrl(mContext, url);
//            if (url.startsWith("http")) {
//                AppContext.resetConfigImageLoader(mContext);
//                imageUrl = url;
//            }else{
//                //新增动态IP解析 new test
//                AppContext.initCustomConfigImageLoader(mContext);
//                imageUrl = String.format(NetApi.QINIU_ICON_2, url);
//            }
            // 开启异步线程获取图片
//            ImageLoader.getInstance().displayImage(imageUrl, mUserHeaderImageView, new SimpleImageLoadingListener() {
//                @Override
//                public void onLoadingComplete(String imageUri, View view, final Bitmap loadedImage) {
//                    if (loadedImage == null) {
//                        return;
//                    }
//
//                }
//
////                @Override
////                public void onLoadingStarted(String imageUri, View view) {
////                    super.onLoadingStarted(imageUri, view);
////                }
//            });
        }


    }

    private void initViews(){
        mUserHeaderImageView = (RoundImageView) findViewById(R.id.user_header_imageView);
        mNickNameTextView = (TextView) findViewById(R.id.nick_name_textView);
        mUserSignatureTextView = (TextView) findViewById(R.id.user_signature_textView);
        mAttentTextView = (TextView) findViewById(R.id.attention_textView);
        mRankTextView = (TextView) findViewById(R.id.rank_textView);
        mGenderImageView = (ImageView)findViewById(R.id.gender_imageView);
        mAuthenticationImageView = (ImageView)findViewById(R.id.authentication_imageView);
        imgMore = (ImageView)findViewById(R.id.imgMore);
    }

    public void initData(UserRegisterMode.UserBean mode, int followStatus, Activity activity) {

        try {
            if (mode != null) {

                    if (mode.profile != null) {
                        LogUtils.E("nickname:" + mode.profile.nickname);
                        LogUtils.E("icon:" + mode.profile.icon);
                        //用户的昵称，性别， 地理位置，发布时间
                        if (mNickNameTextView != null) {
                            mNickNameTextView.setText(mode.profile.nickname);
                        }
                        if (mGenderImageView != null){
                            mGenderImageView.setImageResource(mode.profile.sex == 1 ? R.mipmap.ic_user_man : R.mipmap.ic_user_women);
                        }
                        if (mUserHeaderImageView != null) {
                            initUserHeaderBackground(mode.profile.icon);
                        }
                        //设置认证标志
//                        setAuthenticationTag(mode.profile.identity);

                        if (mUserSignatureTextView != null) {
                            mUserSignatureTextView.setText(!TextUtils.isEmpty(mCreateAt) ? StringUtils.getTimer(mCreateAt) : "now");
//                            String sign = mode.profile.sign;
//                            if(!TextUtils.isEmpty(sign)) {
//                                mUserSignatureTextView.setText(sign.length() > 20 ? (sign.subSequence(0, 17) + "...") : sign);
//                            }else{
//                                mUserSignatureTextView.setText("");
//                            }

                        }

                    } else {
                        if (mNickNameTextView != null) {
                            mNickNameTextView.setText(mNickName);
                        }
                        if (mUserHeaderImageView != null) {
                            initUserHeaderBackground(mImageUrl);
                        }
                    }

                    linkUserInfo(mode._id, activity);

                    if (mIsClick) {
                        LogUtils.E("mIsClick true");
                        if (!mIsHotOrder){
//                        }else {
                            LogUtils.E("mIsHotOrder false");
                            //初始化关注状态
                            mCurrentAttentStatus = followStatus;
//                            initAttentStatus(mode._id, activity);
                        }else{
                            LogUtils.E("mIsClick true, mAttentTextView visible");
                            changeAttentStyleAndContent(mPosition);
                        }
                    }else{
                        LogUtils.E("mIsClick false");
                        mAttentTextView.setVisibility(View.GONE);
                    }

                }
//                LogUtils.E("location:" + mode.locName);
//                LogUtils.E("time:" + StringUtils.getTimer(mode.createdAt));
//                if (mLocationInfoTextView != null) {
//                    LogUtils.E("mLocationInfoTextView setText");
//                    if (!TextUtils.isEmpty(mode.locName)) {
//                        mLocationInfoTextView.setText(mode.locName);
//                    } else {
//                        setLocationVisible(false);
//                    }
//                }
//
//                if (mCreateAtTimeTextView != null) {
//                    LogUtils.E("mCreateAtTimeTextView setText");
//                    mCreateAtTimeTextView.setText(!TextUtils.isEmpty(mode.createdAt) ? StringUtils.getTimer(mode.createdAt) : "刚刚");
//                }

//            }
        } catch (Exception ex) {
            LogUtils.E("userInfoView Exception:" + ex);
        }

    }


   /* public void setLocationVisible(boolean flag) {
        if (mLocationTagImageView != null && mLocationInfoTextView != null) {
            if (flag) {
                mLocationTagImageView.setVisibility(View.VISIBLE);
                mLocationInfoTextView.setVisibility(View.VISIBLE);
            } else {
                mLocationTagImageView.setVisibility(View.INVISIBLE);
                mLocationInfoTextView.setVisibility(View.INVISIBLE);
            }
        }
    }*/


    private void linkUserInfo(final String userId, final Activity context) {
        LogUtils.E("userInfoItemView userId:" + userId);
        if (mUserHeaderImageView != null && !TextUtils.isEmpty(userId) && context != null) {
            mUserHeaderImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent intent = new Intent(context, UserPostActivity.class);
//                    Intent intent;
//                    if (userId.equals(PreferencesUtils.getValueFromSPMap(context, AppKeys.USERID)))
//                        intent = new Intent(context, MyPostActivity.class);
//                    else
//                        intent = new Intent(context, .class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    intent.putExtra(AppKeys.MY_USER_INFO_TAG, userId);
//                    context.startActivity(intent);

                }
            });
        }
    }


    public void setUserInfo(String imageUrl, String nickName) {
        mImageUrl = imageUrl;
        mNickName = nickName;
    }

    public void setUserHeaderImageIsClick(boolean flag) {
        mIsClick = flag;
    }

    /**
     * 关注状态的初始化和触发的事件
     * @param userId
     */
    /*private void initAttentStatus(final String userId, final Activity activity){
        mAttentTextView.setVisibility(View.VISIBLE);
        setAttentTextAndBackground(mCurrentAttentStatus);
        mAttentTextView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
//
                switch (mCurrentAttentStatus) {
                    case 1:
                    case 2:
//                        if (activity != null) {

                                    PopWindowUtils.showCancelAttentPopwindow(mAttentTextView, new SharePopListener() {
                                        @Override
                                        public void sharePhoto() {
                                            setAttentStatus(0, userId);
                                        }

                                        @Override
                                        public void shareText() {

                                        }
                                    });

//                        }
                         break;
                    case 0:
                    setAttentStatus(1, userId);
                        break;
                }
            }
        });



    }*/

    /**
     * 设置关注状态
     */
   /* private void setAttentStatus(final int type, String userId){
        UserLogiHelp.setUserAttentStatus(mContext, type, userId, new IRequestListener() {
            @Override
            public void onSuccess(JSONObject response) {
//                int i = 0;
                int status = 0;
                try {
//                    i = response.getInt("result");
                    LogUtils.E("type: " + type);
                    if (type == 1) {
                        LogUtils.E("start parse..");
                        if (response != null){
                            JSONObject obj = (JSONObject)response.get("message");
                            status = obj.getInt("followStatus");
                        }

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

//                    mCurrentAttentStatus = status;
                    if (mAttentTextView != null) {
                        setAttentTextAndBackground(status);
                        LogUtils.E("attent text: " + mAttentTextView.getText() + "status: " + status);
                    }else{
                        LogUtils.E("mAttentTextView is null 没关注成功");
                    }

            }

            @Override
            public boolean onError(String error) {
                Toast.makeText(mContext, type == 1 ? R.string.toast_user_add_attent_error_text : R.string.toast_user_cancel_attent_error_text, Toast.LENGTH_SHORT);
                return false;
            }
        });
    }*/


   /* private void setAttentTextAndBackground(int status){
        int resId = 0;
        int drawableId = 0;
        switch (status) {
            case 0:
                resId = R.string.user_cancel_attent_text;
                drawableId = R.drawable.btn_attent_selector;
                break;
            case 1:
                resId = R.string.user_add_attent_text;
                drawableId = R.drawable.btn_canel_attent_selector;
                break;
            case 2:
                resId = R.string.user_mutual_attent_text;
                drawableId = R.drawable.btn_canel_attent_selector;
//                final ViewGroup.LayoutParams lp = mAttentTextView.getLayoutParams();
//                lp.width = StringUtils.dip2px(mContext, 65f);
//                mAttentTextView.setLayoutParams(lp);
                break;
        }
        if (resId > 0){
            mAttentTextView.setText(resId);
        }

        if (drawableId > 0){
            mAttentTextView.setBackgroundResource(drawableId);
        }

    }
*/

   /* private void setAuthenticationTag(int tag){
        if (mAuthenticationImageView != null){
            int resId = 0;
            switch (tag){
                case 0:
                    mAuthenticationImageView.setVisibility(View.GONE);
                    break;
                case 1:
                    resId = R.drawable.ic_approve_personal;
                    break;
                case 2:
                    resId = R.drawable.ic_approve_bussiness;
                    break;
            }
            if (resId > 0) {
                mAuthenticationImageView.setVisibility(View.VISIBLE);
                mAuthenticationImageView.setImageResource(resId);
            }
        }
    }*/

    /*public void setUserAttentVisible(boolean isShow){
        mIsAttentShow = isShow;
        mAttentTextView.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }*/

    /*public void setActivity(Activity activity){
        mActivity = activity;
    }*/


    public void changeAttentStyleAndContent(int position){
        if (mAttentTextView != null) {
            LogUtils.E("mRankTextView visible");
            mAttentTextView.setVisibility(View.GONE);
            mRankTextView.setVisibility(View.VISIBLE);
            if (position < 3){
                mRankTextView.setTextColor(mContext.getResources().getColor(R.color.rank_order_text_color));
            }else{
                mRankTextView.setTextColor(mContext.getResources().getColor(R.color.user_nickname_text_color));
            }
            mRankTextView.setText("第".concat(String.valueOf(position+1)).concat("名"));

            LogUtils.E("mRankTextView getText:" + mRankTextView.getText());
        }else{
            LogUtils.E("mAttentTextView gone");
        }
    }


    public void setHotOrderStatus(boolean flag, int position){
        mIsHotOrder = flag;
        mPosition = position;
    }

    public void setMoreView(){
        if (mAttentTextView != null) {
            mAttentTextView.setVisibility(View.GONE);
        }
        if (imgMore != null) {
            imgMore.setVisibility(View.VISIBLE);
            imgMore.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
//                    MoreControlDialog.showDialog(mContext);
                }
            });
        }
    }

    public void setCreateAt(String createAt){
        mCreateAt = createAt;
    }

}

