package com.weile.casualgames.find.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.weile.casualgames.R;
import com.weile.casualgames.utils.StringUtils;

import java.util.ArrayList;


/**
 * @author zjj
 */
public class MainBodyBottomView extends RelativeLayout {

//    private Activity mActivity;
//    private View mLocationView;
//    private TextView mBrowserCountTextView;
//    private TextView mSpreadTimeTextView;
//    private TextView mSpreadCountTextView;
//    private ImageView imgGood;
//    private ImageView imgBad;

//    private boolean mIsOutClick = false;
//    private boolean mIsUp = false;
    private Context mContext;
    private RelativeLayout commentLayout;
    private TextView tvCommentCount;
    private TextView tvLocation;
    private TextView tvCreatAtTime;
//    private OnClickListener mListener;


    public MainBodyBottomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        mContext = context;
        LayoutInflater mInflater = LayoutInflater.from(context);
        View view = mInflater.inflate(R.layout.main_body_bottom_view, null);
        addView(view, new LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        initView();

    }

    public MainBodyBottomView(Context context) {

        super(context);

        // TODO Auto-generated constructor stub

    }


    private void initView(){
        tvLocation = (TextView)findViewById(R.id.tvLocation);
        tvCreatAtTime = (TextView)findViewById(R.id.tvCreateAtTime);
        tvCommentCount = (TextView)findViewById(R.id.tvCommentCount);
        commentLayout = (RelativeLayout)findViewById(R.id.commentLayout);
//        mLocationView = findViewById(R.id.location_layout);
//        mBrowserCountTextView = (TextView)findViewById(R.id.browser_count_text_view);
//        mSpreadTimeTextView = (TextView)findViewById(R.id.spread_time_text_view);
//        mSpreadCountTextView = (TextView)findViewById(R.id.spread_count_text_view);

//        imgGood = (ImageView)findViewById(R.id.imgGood);
//        imgBad = (ImageView)findViewById(R.id.imgBad);


    }

    public void initData(final String topicId, String createAtTime, String locName, int commentCount, ArrayList<String> loc) {
        if (tvCreatAtTime != null) {
            tvCreatAtTime.setText(!TextUtils.isEmpty(createAtTime) ? StringUtils.getTimer(createAtTime) : "now");
        }

        if (tvLocation != null) {
            String distance = "100";
//            String distance = StringUtils
//                    .getSuitableDistance(Double.valueOf(loc.get(0)), Double.valueOf(loc.get(1)),
//                            AppContext.longitude, AppContext.latitude);
            if (TextUtils.isEmpty(locName)) {
//                tvLocation.setVisibility(View.GONE);
                tvLocation.setText(distance);
            } else {
                tvLocation.setVisibility(View.VISIBLE);
                tvLocation.setText(locName.concat("  ").concat(distance));
            }
        }
        if (tvCommentCount != null) {
            if (commentCount > 0) {
//              tvCommentCount.setVisibility(View.VISIBLE);
                tvCommentCount.setText(String.valueOf(commentCount).concat(" ").concat(mContext.getResources().getString(R.string.duang_comment_count_title)));
            } else {
                tvCommentCount.setText(mContext.getResources().getString(R.string.duang_comment_count_title));
//              tvCommentCount.setVisibility(View.GONE);
            }
        }
        commentLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

//            Intent intent = new Intent(mContext, TopicDetailActivity_.class);
//            intent.putExtra("isMy", false);
//            intent.putExtra("topic", topicId);
//            intent.putExtra("showKeyboard", true);
////            intent.putExtra("index", position - lv.getHeaderViewsCount());
//            mContext.startActivity(intent);
            }
        });
//             mLocationView.setOnClickListener(new OnClickListener() {
//                  @Override
//                  public void onClick(View v) {
//                      LogUtils.E("onclick lon:" + mode.loc.get(0) + "; lat:" + mode.loc.get(1));
//                      Intent intent = new Intent(mContext, MyNearbySpreadActivity.class);
//                      intent.putExtra("longitude", mode.loc.get(0));
//                      intent.putExtra("latitude", mode.loc.get(1));
//                      intent.putExtra("locationTitle", mode.locName);
//                      mContext.startActivity(intent);
//
//                  }
//              });
//          }
          //旧的方式
          /*if (mIsOutClick){

              mLocationView.setOnClickListener(mListener);
          }else {

              mLocationView.setOnClickListener(new OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Intent intent = new Intent(mContext, TopicDetailActivity_.class);
                      intent.putExtra("topic", mode._id);
                      intent.putExtra(AppKeys.MAX_DISTANCE, TextUtils.isEmpty(mode.maxDistance) ? "0km" : mode.maxDistance);
                      intent.putExtra(AppKeys.READ_COUNT, mode.readCount);
                      intent.putExtra(AppKeys.SPREAD_TIMES, mode.spreadTimes);
                      if (mode.user != null) {
                          intent.putExtra(AppKeys.TOPIC_USER_ID, mode.user._id);
                      }
                      intent.putExtra(AppKeys.MAP_VIEW_SHORT_CUT, true);
                      mContext.startActivity(intent);
                  }
              });

          }*/


//        mSpreadTimeTextView.setText(!TextUtils.isEmpty(mode.createdAt) ? StringUtils.getTimer(mode.createdAt) : "now");
//          mSpreadTimeTextView.setText(mode.spreadTimes >= 0 ? String.valueOf(mode.spreadTimes) : "0");

//        mSpreadCountTextView.append(" 人转发");
//          mSpreadCountTextView.setText(mode.readCount >= 0 ? String.valueOf(mode.readCount) : "0");
//         mCommentCountTextView.append(" 阅读");
//          mCommentCountTextView.setText(mode.commentCount >= 0 ? String.valueOf(mode.commentCount) : "0");
//         mCommentCountTextView.append(" 回帖");
//          setUpAnddownClick(mode._id, mode.spreadTimes);




    }

    /**
     *
     */
    public void hideCommentView(){
        commentLayout.setVisibility(View.GONE);
    }


  /* public void setCustomClickListener(boolean isOutClick, OnClickListener listener){
       mIsOutClick = isOutClick;
       mListener = listener;
   }*/

    /*public void setUpAnddownClick(final String id, final int upCount){
        imgGood.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mIsUp){
                    mSpreadTimeTextView.setText(String.valueOf(upCount + 1));
                    imgGood.setBackgroundResource(R.drawable.ic_good_new_select);
                    mIsUp = true;
                    upDownTopic(mContext, id, true);
                }
            }
        });
        imgBad.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mIsUp){
                    imgBad.setBackgroundResource(R.drawable.ic_bad_new_select);
                    mIsUp = true;
                    upDownTopic(mContext, id, false);
                }
            }
        });
    }*/

   /* private static void upDownTopic(Context context, String id, boolean isSpread) {

            SpreadUtils.SpreadHelp(context, id, isSpread, new SpreadResponseListener() {
                @Override
                public void onSuccess() {
                    LogUtils.E("SpreadHelp onSucess position: ");

                }

                @Override
                public void onError(String error) {
                    LogUtils.E("SpreadHelp onError position: ");
                }
            });
    }*/


//    private void initGoodData(int upCount){
//        mIsUp = true;
//        mSpreadTimeTextView.setText(String.valueOf(upCount + 1));
//        imgGood.setBackgroundResource(R.drawable.ic_good_new_select);
//
//        imgBad.setBackgroundResource(R.drawable.ic_bad_new_select);
//
//    }
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








//    public void setActivity(Activity activity){
//        mActivity = activity;
//    }





}

