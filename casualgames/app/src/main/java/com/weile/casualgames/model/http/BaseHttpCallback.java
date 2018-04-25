package com.weile.casualgames.model.http;

import com.module.common.event.RxBus;
import com.module.common.util.MyViewUtil;
import com.module.common.util.ViewUtil;
import com.weile.casualgames.App;
import com.weile.casualgames.model.config.Const;
import com.weile.casualgames.model.event.LogoutEvent;


/**
 * Created by zjj
 */

public class BaseHttpCallback implements com.weile.casualgames.model.http.IHttpCallBackEx {
    boolean showSuccessMsg=false;
    boolean showFailMsg=true;
    boolean  showLoadView=false;
    String    loadMsg="";
    public BaseHttpCallback(boolean showSuccessMsg, boolean showFailMsg) {
        this.showSuccessMsg = showSuccessMsg;
        this.showFailMsg = showFailMsg;
    }
    public BaseHttpCallback(boolean showLoadView, boolean showFailMsg, boolean showSuccessMsg) {
        this.showLoadView = showLoadView;
        this.showFailMsg = showFailMsg;
        this.showSuccessMsg = showSuccessMsg;
    }
    public BaseHttpCallback(boolean showSuccessMsg, boolean showFailMsg, boolean showLoadView, String loadMsg) {
        this.showSuccessMsg = showSuccessMsg;
        this.showFailMsg = showFailMsg;
        this.showLoadView = showLoadView;
        this.loadMsg = loadMsg;
    }


    public BaseHttpCallback(){
         showSuccessMsg=false;
         showFailMsg=true;
          showLoadView=false;
    };



    @Override
    public void success(String data, String msg) {
        MyViewUtil.hideshowProgressDialog();
        if (showSuccessMsg){
            ViewUtil.showToast(App.getContext(), msg);
        }

    }

    @Override
    public void fail(String msg, String code) {
        MyViewUtil.hideshowProgressDialog();
        if (code.equals(Const.EVENT_LOOUT)){
            RxBus.getInstance().post(new LogoutEvent());

        }
        if (showFailMsg){
            ViewUtil.showToast(App.getContext(), msg);
        }

    }

    @Override
    public void beforeRequest() {
        if (showLoadView){
            MyViewUtil.showProgressDialog(App.getContext(),loadMsg);
        }
    }
}
