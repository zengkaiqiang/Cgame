package com.module.common.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 图片相关的工具类
 */
public class ImageUtil {

    public static Bitmap getBitmapFromUri(Uri uri, Context mContext) {
        try {
            // 读取uri所在的图片
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(
                    mContext.getContentResolver(), uri);
            return bitmap;
        } catch (Exception e) {
            Log.e("[Android]", e.getMessage());
            Log.e("[Android]", "目录为：" + uri);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获得小图片
     * @param filePath
     * @return
     */
    public static Bitmap getSmallBitmap(String filePath,int w,int h) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        options.inSampleSize = calculateInSampleSize(options, w, h);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(filePath, options);
    }
    public static Bitmap getSmallBitmap(String filePath) {
        return getSmallBitmap(filePath,400,800);
    }
    /**
     *  按比例缩小
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    private static int calculateInSampleSize(BitmapFactory.Options options,
                                      int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            if (width > height) {
                inSampleSize = Math.round((float) height / (float) reqHeight);
            } else {
                inSampleSize = Math.round((float) width / (float) reqWidth);
            }
        }
        return inSampleSize;
    }

    /**
     * 压缩图片质量
     * @param photo
     * @param myCaptureFile  保存图片的地址
     * @param quality
     * @return
     */
    public static Bitmap compress(Bitmap photo, File myCaptureFile,int quality) {
        try {
            BufferedOutputStream bos = new BufferedOutputStream(
                    new FileOutputStream(myCaptureFile));
            photo.compress(Bitmap.CompressFormat.JPEG, quality, bos);

            bos.flush();
            bos.close();
            Log.d("PersonalActivity", myCaptureFile.toString());
        } catch (FileNotFoundException e) {
            Log.e("PersonalActivity", e.getMessage());
        } catch (IOException e) {
            Log.e("PersonalActivity", e.getMessage());
        }
        return photo;
    }
}
