package com.temporary.loadimage;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;


import java.io.File;

/**
 * 图片异步加载实例
 */
public class ImageLoaderExample {

//    public static String IMAGE_CACHE_PATH = "imageloader/Cache"; // 图片缓存路径
//    // 异步加载图片
//    private ImageLoader mImageLoader;
//    private DisplayImageOptions options;
//    private DisplayImageOptions options_head;
//
//    private Context mContext;
//
//    public ImageLoaderExample(Context mContext)
//    {
//        this.mContext=mContext;
//        initImageLoader();
//
//        // 获取图片加载实例
//        mImageLoader = ImageLoader.getInstance();
//        options = new DisplayImageOptions.Builder()
//                .showStubImage(R.color.light_gray)
//                .showImageForEmptyUri(R.color.light_gray)
//                .showImageOnFail(R.color.light_gray).cacheInMemory(true)
//                .cacheOnDisc(true).bitmapConfig(Bitmap.Config.RGB_565)
//                .imageScaleType(ImageScaleType.EXACTLY).build();
//
//        options_head= new DisplayImageOptions.Builder()
//                .showStubImage(R.color.light_gray)//此三处原先为图片R.drawable.item_head
//                .showImageForEmptyUri(R.color.light_gray)
//                .showImageOnFail(R.color.light_gray).cacheInMemory(true)
//                .cacheOnDisc(true).bitmapConfig(Bitmap.Config.RGB_565)
//                .imageScaleType(ImageScaleType.EXACTLY).build();
//    }
//
//
//    @SuppressWarnings("deprecation")
//    private void initImageLoader() {
//        File cacheDir = com.nostra13.universalimageloader.utils.StorageUtils
//                .getOwnCacheDirectory(mContext, IMAGE_CACHE_PATH);
//
//        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
//                .cacheInMemory(true).cacheOnDisc(true).build();
//
//        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
//                mContext).defaultDisplayImageOptions(defaultOptions)
//                .memoryCacheExtraOptions(120, 200)// max width, max height，即保存的每个缓存文件的最大长宽
//                .memoryCache(new LruMemoryCache(12 * 32 * 32))
//                .memoryCacheSize(12 * 32 * 32).discCacheSize(32 * 32 * 32)
//                .discCacheFileCount(100)
//                .discCache(new UnlimitedDiscCache(cacheDir))
//                .threadPriority(Thread.NORM_PRIORITY - 2)
//                .tasksProcessingOrder(QueueProcessingType.LIFO).build();
//
//        ImageLoader.getInstance().init(config);
//    }
//
//    //异步加载图片
//    public void loadImage(String  url,ImageView imageView)
//    {
//
//        mImageLoader.displayImage(url, imageView,
//                options);
//
//        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//    }
//
//    //异步加载图片
//    public void loadImage(String  url,ImageView imageView,int flag)
//    {
//        // 异步加载图片
//        if(flag==-1)
//        {
//            mImageLoader.displayImage(url, imageView,
//                    options);
//        }
//        else
//
//        if(flag==1)
//        {
//            mImageLoader.displayImage(url, imageView,
//                    options_head);
//        }
//        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//    }


}

