package com.module.share;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.ShareContent;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng_social_sdk_res_lib.R;


/**
 * Created by zjj
 * 分享
 */
public class ShareUtil {

    private Context mContext;
    public  static String shareContent="分享语";
    public  static  String shareTitle="程序名";
    private  static  String shareUrl="https://www.baidu.com/";

    public static void setShareUrl(String shareUrl) {
        ShareUtil.shareUrl = shareUrl;
    }



    public  static  String shareTitleRegiste="注册分享";
    public  static  String baseUrl="web/register/";
    private final SHARE_MEDIA[] displaylist = new SHARE_MEDIA[]
            {
                    SHARE_MEDIA.QZONE,SHARE_MEDIA.QQ,
                    SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE
            };
    private UMImage umImage;
    public ShareUtil(Context context) {
        mContext=context;
        umImage = new UMImage(mContext, R.drawable.ic_launcher);
    }
    public ShareUtil(Context context,int img) {
        mContext=context;
        umImage = new UMImage(mContext, img);
    }
    public  static  long mTaskId=-1;
    public void setTaskId(long taskId){
        mTaskId=taskId;
    }
    public void openShareAll(Share share){
        new ShareAction((Activity)mContext).setDisplayList(displaylist)
                .withText(share.getText())
                .withTitle(share.getTitle())
                .withTargetUrl(share.getTargetUrl())
                .withMedia(umImage)
                .open();
    }
    public void openQzoneShare(){
        openShare(SHARE_MEDIA.QZONE);
    }
    public void openQQShare(){
        openShare(SHARE_MEDIA.QQ);
    }
    public void openWinxinShare(){
        openShare(SHARE_MEDIA.WEIXIN);
    }
    public void openWinxinCircleShare(){
        openShare(SHARE_MEDIA.WEIXIN_CIRCLE);
    }

    private void openShare(SHARE_MEDIA type){
        ShareContent data=new ShareContent();
        data.mText=shareContent;
        data.mTargetUrl=shareUrl;
        data.mTitle=shareTitle;
        data.mMedia=umImage;
        ShareAction action= new ShareAction((Activity)mContext).setPlatform(type).setShareContent(data);
        UMShareAPI.get(mContext).doShare((Activity)mContext,action,umShareListener);

    }
    UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA share_media) {
            Toast.makeText(mContext,mContext.getString(R.string.share_success),Toast.LENGTH_SHORT).show();
            if (mMyShareListener==null){
                return;
            }
            mMyShareListener.onSuccess(mTaskId);

        }

        @Override
        public void onError(SHARE_MEDIA share_media, Throwable throwable) {
            Toast.makeText(mContext,mContext.getString(R.string.share_error),Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media) {
            Toast.makeText(mContext,mContext.getString(R.string.share_cancel),Toast.LENGTH_SHORT).show();
        }
    };

    public void setMyShareListener(MyShareListener myShareListener) {
        this.mMyShareListener = myShareListener;
    }

    MyShareListener mMyShareListener;
    public interface MyShareListener {
        void onSuccess(long type);
    }

}
