package com.weile.casualgames.view.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.weile.casualgames.R;

/**
 * 界面的头部栏
 * @author: zjj
 */
public class HomeHeaderLayout extends RelativeLayout  implements OnClickListener {
	private static final String TAG = "HeaderLayout";

	public void setHeaderListener(OnHeaderListener mHeaderListener) {
		this.mHeaderListener = mHeaderListener;
	}

	private OnHeaderListener mHeaderListener;
	FragmentManager mFragmentManager;
	public HomeHeaderLayout(Context context) {
		super(context);
		init(null);
	}

	public HomeHeaderLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(attrs);
	}


	public HomeHeaderLayout(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(attrs);

	}

	public interface OnHeaderListener {
		public void onLeftClick();
		public void onRightClick();
	}

	public void setActivity(Activity activity) {
		this.mActivity = activity;
	}
	public void setFragmentManager(FragmentManager fragmentManager) {
		this.mFragmentManager = fragmentManager;
	}
	private Activity mActivity;
	private Context mContext;
	private TextView titleTv;
	private ImageView rightIv;
	private ImageView leftIv;
	private TextView rightTv;
	private String title="";
	private String mRightName="";
	private int mRightImg=0;
	private int mLeftImg=0;
	private ViewGroup mLeftLl;
//
    private void init(AttributeSet attrs) {
	     this.mContext = getContext();
		 final TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.header_layout);
		 String titleName = typedArray.getString(R.styleable.header_layout_title_name);
		 String rightName = typedArray.getString(R.styleable.header_layout_right_name);
		 int right_img = typedArray.getResourceId(R.styleable.header_layout_right_img,0);
		 int left_img = typedArray.getResourceId(R.styleable.header_layout_left_img,0);
		 if (titleName!=null){
			 title=titleName;
		 }
		if (rightName!=null){
			mRightName=rightName;
		}
		if (right_img!=0){
			mRightImg=right_img;
		}
		if (left_img!=0){
			mLeftImg=left_img;
		}
		 typedArray.recycle();
		 initView();

	}
	public void initView() {
		View parent = View.inflate(mContext, R.layout.home_header_layout, null);
		leftIv=  (ImageView) parent.findViewById(R.id.leftImg);
		titleTv = (TextView) parent.findViewById(R.id.titleTv);
		rightTv = (TextView) parent.findViewById(R.id.rightTv);
		rightIv = (ImageView) parent.findViewById(R.id.rightImg);
		mLeftLl=(ViewGroup) parent.findViewById(R.id.leftLl);
		mLeftLl.setOnClickListener(this);
		rightIv.setOnClickListener(this);
		rightTv.setOnClickListener(this);
		 if (!title.equals("")){
			 titleTv.setText(title);
		 }
		if (!mRightName.equals("")){
			rightTv.setText(mRightName);
		}
		if (mRightImg!=0){
			rightIv.setVisibility(View.VISIBLE);
			rightIv.setImageResource(mRightImg);
		}
		if (mLeftImg!=0){
			leftIv.setVisibility(View.VISIBLE);
			leftIv.setImageResource(mLeftImg);
		}

		addView(parent,new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
	}


	@Override
	public void onClick(View v) {

		switch (v.getId()){
			case R.id.leftLl:

				if (mHeaderListener==null){
					return;
				}
				mHeaderListener.onLeftClick();
				break;
			case R.id.rightImg:

				if (mHeaderListener==null){
					return;
				}
				mHeaderListener.onRightClick();
				break;
			case R.id.rightTv:

				if (mHeaderListener==null){
					return;
				}
				mHeaderListener.onRightClick();
				break;
			default:

				break;
		}


	}

}
