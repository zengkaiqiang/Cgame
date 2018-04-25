package com.weile.casualgames.network;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.ImageView;

import com.temporary.network.util.CMd;
import com.weile.casualgames.App;
import com.weile.casualgames.R;
import com.weile.casualgames.login.sethead.ImitateIphoneDialog;
import com.weile.casualgames.login.sethead.Utils;

import java.io.File;

/**
 * 在BaseMiddleOrdinaryActivity的基础上加入头像选择功能
 */
public class BaseHeadActivity extends BaseMiddleOrdinaryActivity implements ImitateIphoneDialog.MenuItemClickListener {

    private static final int PHOTO_REQUEST = 1;
    private static final int CAMERA_REQUEST = 2;
    private static final int PHOTO_CLIP = 3;
    public File file;
    private Bitmap photo;

    public String imagePath = "";

    private ImageView imageView;
    public String imageStr = "";

    private App app;

    @Override
    public void setRootView() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (App) getApplication(); // 获得Application对象
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
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

    /**
     * 裁剪图片
     */
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
                        this.imageView.setImageBitmap(photo);
                        if (app != null && app.getMySharedPreferences().getAccount() != null && !app.getMySharedPreferences().getAccount().equals(""))
                            imageStr = app.getMySharedPreferences().getAccount();
                        else {
                            imageStr = String
                                    .valueOf(System.currentTimeMillis());
                        }
                        imagePath = Utils.savePhoto(photo, Environment
                                .getExternalStorageDirectory().getAbsolutePath(), imageStr);
//                        CMd.syo("获取到的图片地址imagePath="+imagePath+"    "+imageStr);

                        file = new File("", imagePath);// 1.avi,jpg,png,rar
                        selectHeadClick();
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

    public void selectHeadClick() {

    }


}
