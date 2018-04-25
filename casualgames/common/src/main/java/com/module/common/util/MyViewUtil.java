package com.module.common.util;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.ColorMatrixColorFilter;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.weile.casualgames.common.R;

import java.lang.reflect.Field;

/**
 * Created by zjj
 */
public class MyViewUtil {

    /**
     * 获取EditText文字
     *
     * @param editText
     */
    public static String getViewText(EditText editText) {
        if (editText == null) {
            return "";
        }
        return editText.getText().toString().trim();
    }


    /**
     * 显示软键盘
     *
     * @param view
     */
    public static void showKeyboard(View view) {
        if (null == view)
            return;
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, 0);
    }

    /**
     * 隐藏软键盘
     *
     * @param view
     */
    public static void hideKeyboard(View view) {
        if (null == view) {
            return;
        }
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    static Toast toast;
    public static void showToast(Context context, String str,boolean isShort) {
        View layout = LayoutInflater.from(context).inflate(R.layout.custom_toast, null);
        TextView tv = (TextView) layout.findViewById(R.id.TextViewInfo);
        if (toast == null) {
            toast = new Toast(context);
            toast.setDuration(Toast.LENGTH_LONG);
            if (isShort){
                toast.setDuration(Toast.LENGTH_SHORT);
            }
            toast.setGravity(Gravity.FILL_HORIZONTAL | Gravity.TOP, 0, (int) context.getResources().getDimension(R.dimen.tittle_bar_height));
            try {
                Object mTN = null;
                mTN = getField(toast, "mTN");
                if (mTN != null) {
                    Object mParams = getField(mTN, "mParams");
                    if (mParams != null
                            && mParams instanceof WindowManager.LayoutParams) {
                        WindowManager.LayoutParams params = (WindowManager.LayoutParams) mParams;
                        params.windowAnimations = R.style.Lite_Animation_Toast;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        tv.setText(str);
        toast.setView(layout);
        toast.show();
    }
    public static void showToast(Context context, String str) {
        showToast(context,str,false);
    }

    /**
     * 反射字段
     *
     * @param object    要反射的对象
     * @param fieldName 要反射的字段名称
     * @return
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    private static Object getField(Object object, String fieldName)
            throws NoSuchFieldException, IllegalAccessException {
        Field field = object.getClass().getDeclaredField(fieldName);
        if (field != null) {
            field.setAccessible(true);
            return field.get(object);
        }
        return null;
    }



    private static ProgressDialog m_pDialog = null;

    // 显示loading对话框
    public static void showProgressDialog(Context content, String mess) {
        try {
            if (m_pDialog == null) {
                // 创建ProgressDialog对象
                m_pDialog = new ProgressDialog(content);
                // 设置进度条风格，风格为圆形，旋转的
                m_pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                // 设置ProgressDialog 的进度条是否不明确
                m_pDialog.setIndeterminate(false);
                // 设置ProgressDialog 是否可以按退回按键取消
                m_pDialog.setCancelable(false);
                // 设置提示信息
                m_pDialog.setMessage(mess);
                // 让ProgressDialog显示
                m_pDialog.show();
            } else {
                m_pDialog.show();
            }
        } catch (Exception e) {

        }

    }

    // 关闭
    public static void hideshowProgressDialog() {
        if (m_pDialog != null)
            m_pDialog.dismiss();
        m_pDialog = null;
    }



    private static long lastClickTime;

    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 800) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    public static ValueAnimator createHeightAnimator(final View view, int start, int end) {
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int value = (Integer) valueAnimator.getAnimatedValue();

                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = value;
                view.setLayoutParams(layoutParams);
            }
        });
        animator.setDuration(500);
        return animator;
    }

    /*
    * 显示view
    * */
    public static void animateExpanding(final View view) {
        view.setVisibility(View.VISIBLE);

        final int widthSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        final int heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(widthSpec, heightSpec);

        ValueAnimator animator = createHeightAnimator(view, 0, view.getMeasuredHeight());
        animator.start();
    }

    /*
   * 隐藏view
   * */
    public static void animateCollapsing(final View view) {
        int origHeight = view.getHeight();

        ValueAnimator animator = createHeightAnimator(view, origHeight, 0);
        animator.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.GONE);
            };
        });
        animator.start();
    }

    /**
     * 让控件点击时，颜色变深
     * */
    public static final View.OnTouchListener  VIEW_TOUCH_DARK = new View.OnTouchListener() {

        public final float[] BT_SELECTED = new float[] { 1, 0, 0, 0, -50, 0, 1,
                0, 0, -50, 0, 0, 1, 0, -50, 0, 0, 0, 1, 0 };
        public final float[] BT_NOT_SELECTED = new float[] { 1, 0, 0, 0, 0, 0,
                1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0 };

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                if(v instanceof ImageView){
                    ImageView iv = (ImageView) v;
                    iv.setColorFilter( new ColorMatrixColorFilter(BT_SELECTED) ) ;
                }else{
                    v.getBackground().setColorFilter( new ColorMatrixColorFilter(BT_SELECTED) );
                    v.setBackgroundDrawable(v.getBackground());
                }
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                if(v instanceof ImageView){
                    ImageView iv = (ImageView) v;
                    iv.setColorFilter( new ColorMatrixColorFilter(BT_NOT_SELECTED) ) ;
                }else{
                    v.getBackground().setColorFilter(
                            new ColorMatrixColorFilter(BT_NOT_SELECTED));
                    v.setBackgroundDrawable(v.getBackground());
                }
            }
            return false;
        }
    };

    /**
     * 让控件点击时，颜色变暗
     * */
    public static final View.OnTouchListener VIEW_TOUCH_LIGHT = new View.OnTouchListener(){

        public final float[] BT_SELECTED = new float[] { 1, 0, 0, 0, 50, 0, 1,
                0, 0, 50, 0, 0, 1, 0, 50, 0, 0, 0, 1, 0 };
        public final float[] BT_NOT_SELECTED = new float[] { 1, 0, 0, 0, 0, 0,
                1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0 };

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                if(v instanceof ImageView){
                    ImageView iv = (ImageView) v;
                    iv.setDrawingCacheEnabled(true);

                    iv.setColorFilter( new ColorMatrixColorFilter(BT_SELECTED) ) ;
                }else{
                    v.getBackground().setColorFilter( new ColorMatrixColorFilter(BT_SELECTED) );
                    v.setBackgroundDrawable(v.getBackground());
                }
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                if(v instanceof ImageView){
                    ImageView iv = (ImageView) v;
                    iv.setColorFilter( new ColorMatrixColorFilter(BT_NOT_SELECTED) ) ;
                    System.out.println( "变回来" );
                }else{
                    v.getBackground().setColorFilter(
                            new ColorMatrixColorFilter(BT_NOT_SELECTED));
                    v.setBackgroundDrawable(v.getBackground());
                }
            }
            return false;
        }
    };


    /**
     * EditText获取焦点并显示软键盘
     */
    public static void showSoftInputFromWindow(Activity activity, EditText editText) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }

}
