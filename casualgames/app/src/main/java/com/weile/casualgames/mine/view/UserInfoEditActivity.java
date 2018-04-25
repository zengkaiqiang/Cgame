package com.weile.casualgames.mine.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.weile.casualgames.R;
import com.weile.casualgames.login.sethead.ImitateIphoneDialog;
import com.weile.casualgames.view.base.BaseActivity;
import com.weile.casualgames.view.widget.RoundImageView;
import com.weile.casualgames.view.widget.rollpopup.CityPickerView;
import com.weile.casualgames.view.widget.rollpopup.OptionsPickerView;
import com.weile.casualgames.view.widget.rollpopup.TimePickerView;

import org.kymjs.kjframe.ui.BindView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class UserInfoEditActivity extends BaseActivity implements ImitateIphoneDialog.MenuItemClickListener {

    @BindView(id = R.id.iv_user_edit_header, click = true)
    private RoundImageView iv_user_edit_header;
    @BindView(id = R.id.iv_back, click = true)
    private ImageView iv_back;
    @BindView(id = R.id.ll_back, click = true)
    private LinearLayout ll_back;
    @BindView(id = R.id.rl_userinfo_edit_age, click = true)
    private RelativeLayout rl_userinfo_edit_age;
    @BindView(id = R.id.userinfo_edit_age)
    private TextView userinfo_edit_age;
    @BindView(id = R.id.rl_userinfo_edit_city, click = true)
    private RelativeLayout rl_userinfo_edit_city;
    @BindView(id = R.id.tv_userinfo_edit_city)
    private TextView tv_userinfo_edit_city;

    @BindView(id = R.id.scrollView)
    private ScrollView scrollView;


    private CityPickerView mCityPickerView;
    private int cityOption1 = -1, cityOption2 = -1, cityOption3 = -1;//城市选择器选中下标

    private TimePickerView mTimePickerView;
    private Date selectDate;

    private int selectSex = 1;//选中性别：默认1表示女

    private static final int PHOTO_REQUEST = 1;
    private static final int CAMERA_REQUEST = 2;
    private static final int PHOTO_CLIP = 3;
    private File file;
    private Bitmap photo;

    private int selectBirthYear = 0;
    private int selectBirthMonth = 0;
    private int selectBirthDay = 0;
    private int selectAge;
    private String selectProvince;
    private String selectCity;
    private String selectArea;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_user_info_edit);
    }


    @Override
    public void initData() {
        super.initData();
        scrollView.setOverScrollMode(ScrollView.OVER_SCROLL_ALWAYS);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_back:
            case R.id.iv_back:
                finish();
                break;

            case R.id.iv_user_edit_header:
                setTheme(R.style.ActionSheetStyleIOS7);
                showActionSheet();
                break;
            case R.id.rl_userinfo_edit_age:
                selectAge();
                break;
            case R.id.rl_userinfo_edit_city:
                selectCity();
                break;


        }
    }

    public void selectCity() {
        mCityPickerView = new CityPickerView(this);
        // 设置点击外部是否消失
//        mCityPickerView.setCancelable(true);
        // 设置滚轮字体大小
        mCityPickerView.setTextSize(18f);
        // 设置标题
//        mCityPickerView.setTitle("我是标题");
        // 设置取消文字
//        mCityPickerView.setCancelText("我是取消文字");
        // 设置取消文字颜色
//        mCityPickerView.setCancelTextColor(Color.GRAY);
        // 设置取消文字大小
//        mCityPickerView.setCancelTextSize(14f);
        // 设置确定文字
//        mCityPickerView.setSubmitText("我是确定文字");
        // 设置确定文字颜色
//        mCityPickerView.setSubmitTextColor(Color.BLACK);
        // 设置确定文字大小
//        mCityPickerView.setSubmitTextSize(14f);
        // 设置头部背景
//        mCityPickerView.setHeadBackgroundColor(Color.RED);
        //这两个监听会冲突
//        mCityPickerView.setOnCitySelectListener(new OnCitySelectListener() {
//            @Override
//            public void onCitySelect(String str) {
//                // 一起获取
//                tv_userinfo_city.setText(str);
//                System.out.println("没进入这里吗2");
//
//            }
//
//            @Override
//            public void onCitySelect(String prov, String city, String area) {
//                // 省、市、区 分开获取
////                Log.e(TAG, "省: " + prov + " 市: " + city + " 区: " + area);
//                System.out.println("没进入这里吗1");
//            }
//        });

        mCityPickerView.setOnOptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int option1, int option2, int option3) {
//                System.out.println("没进入这里吗3");
//                ViewInject.toast("选择了：" + option1+"   "+option2+"   "+option3);
//                ViewInject.toast("选择了：" + mCityPickerView.getAllStr(option1,option2,option3));
                cityOption1 = option1;
                cityOption2 = option2;
                cityOption3 = option3;
                selectProvince = mCityPickerView.getProvinceStr(option1);
                selectCity = mCityPickerView.getCityStr(option1, option2);
                selectArea = mCityPickerView.getAreaStr(option1, option2, option3);
                if(selectCity.equals(""))
                {
                    selectCity="-";
                }
                tv_userinfo_edit_city.setText(mCityPickerView.getAllStr(option1, option2, option3));
//                mCityPickerView.
            }

        });
        if (cityOption1 != -1 && cityOption2 != -1 && cityOption3 != -1) {
            mCityPickerView.setSelectOptions(cityOption1, cityOption2, cityOption3);
        }
        mCityPickerView.show();
    }

    public void selectAge() {
        //     TimePickerView 同样有上面设置样式的方法
        mTimePickerView = new TimePickerView(this, TimePickerView.Type.YEAR_MONTH_DAY);
        // 设置是否循环
//        mTimePickerView.setCyclic(true);
        // 设置滚轮文字大小
//        mTimePickerView.setTextSize(TimePickerView.TextSize.SMALL);
        // 设置时间可选范围(结合 setTime 方法使用,必须在)
        if (selectDate == null) {
            Calendar calendar = Calendar.getInstance();
            mTimePickerView.setRange(calendar.get(Calendar.YEAR) - 200, calendar.get(Calendar.YEAR));
            // 设置选中时间
            mTimePickerView.setTime(new Date());
        } else
            mTimePickerView.setTime(selectDate);


        mTimePickerView.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date) {
                selectDate = date;
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
//                Toast.makeText(this, format.format(date), Toast.LENGTH_SHORT).show();
//                tv_userinfo_age.setText(format.format(date));
                userinfo_edit_age.setText(yearToAge(format.format(new Date()), format.format(date)) + "");
            }
        });

        mTimePickerView.show();
    }

    /**
     * 通过出生日期计算年龄
     */
    public int yearToAge(String nowDate, String birthDate) {
        int nowYear = 0;
        int nowMonth = 0;
        int nowDay = 0;
        selectBirthYear = 0;
        selectBirthMonth = 0;
        selectBirthDay = 0;
        selectAge = 0;
        if (nowDate.length() == 10 && birthDate.length() == 10) {
            nowYear = Integer.parseInt(nowDate.substring(0, 4));
            nowMonth = Integer.parseInt(nowDate.substring(5, 7));
            nowDay = Integer.parseInt(nowDate.substring(8, 10));
            selectBirthYear = Integer.parseInt(birthDate.substring(0, 4));
            selectBirthMonth = Integer.parseInt(birthDate.substring(5, 7));
            selectBirthDay = Integer.parseInt(birthDate.substring(8, 10));
            selectAge = nowYear - selectBirthYear;
            if (nowYear == selectBirthYear)
                return 0;
            if (nowMonth > selectBirthMonth || (nowMonth == selectBirthMonth && nowDay >= selectBirthDay))
                ;
            else
                selectAge = selectAge - 1;
        }
        return selectAge;
    }



    /*************************选择头像图片相关*********************************/
    @Override
    public void onItemClick(int itemPosition) {
        // TODO Auto-generated method stub
        if (itemPosition == 0) {
            getPicFromCamera();
        } else if (itemPosition == 1) {
            getPicFromPhoto();
        } else {

        }
    }

    private void getPicFromPhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                "image/*");
        startActivityForResult(intent, PHOTO_REQUEST);
    }

    private void getPicFromCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // 下面这句指定调用相机拍照后的照片存储的路径
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(
                Environment.getExternalStorageDirectory(), "test.jpg")));
        startActivityForResult(intent, CAMERA_REQUEST);
    }

    private void photoClip(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, PHOTO_CLIP);
    }

    public void showActionSheet() {
        ImitateIphoneDialog menuView = new ImitateIphoneDialog(this);
        menuView.setCancelButtonTitle(R.string.title_other_btn);// before add items
        menuView.addItems(this.getString(R.string.take_pictures), this.getString(R.string.photo_check));
        menuView.setItemClickListener(this);
        menuView.setCancelableOnTouchMenuOutside(true);
        menuView.showMenu();
    }

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.arg1) {

                case 2:
//                    ThreadUtil.executeSingle(UpUserHeadRun);
                    break;
                default:

                    break;
            }
        }

    };

    Runnable UpUserHeadRun = new Runnable() {

        @Override
        public void run() {
            // TODO Auto-generated method stub
//            uploadUserHead(file);
        }
    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CAMERA_REQUEST:
                switch (resultCode) {
                    case -1://-1表示拍照成功
                        File file = new File(Environment.getExternalStorageDirectory()
                                + "/test.jpg");
                        if (file.exists()) {
                            photoClip(Uri.fromFile(file));
                        }
                        break;
                    default:
                        break;
                }
                break;
            case PHOTO_REQUEST:
                if (data != null) {
                    photoClip(data.getData());
                }
                break;
            case PHOTO_CLIP:
                if (data != null) {
                    Bundle extras = data.getExtras();
                    if (extras != null) {
                        photo = extras.getParcelable("data");
                        int width = photo.getWidth();
                        int height = photo.getHeight();
                        System.out.println("截图头像高宽=" + height + "    " + width);
                        // 取得想要缩放的matrix参数
//                        Matrix matrix = new Matrix();
//                        matrix.postScale(300, 300);
//                        // 得到新的图片   www.2cto.com
//                        Bitmap newbm = Bitmap.createBitmap(photo, 0, 0, width, height, matrix, true);
                        iv_user_edit_header.setImageBitmap(photo);
//                        String imagePath = Utils.savePhoto(photo, Environment
//                                .getExternalStorageDirectory().getAbsolutePath(), String
//                                .valueOf(System.currentTimeMillis()));
//                        file = new File("", imagePath);// 1.avi,jpg,png,rar
//                        Message msgMessage = new Message();
//                        msgMessage.arg1 = 2;
//                        handler.sendMessage(msgMessage);
                    }
                }
                break;
            default:
                break;
        }
    }

}
