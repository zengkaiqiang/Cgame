package com.weile.casualgames.chat.view;

import com.weile.casualgames.chat.model.InteractiveMessage;

import java.util.ArrayList;

/**
 * Created by zjj
 */
public interface IChatHomeView {
	public void onGetInteractiveMessageDatas(ArrayList<InteractiveMessage> messages);
//	public void toast(String msg);

}
