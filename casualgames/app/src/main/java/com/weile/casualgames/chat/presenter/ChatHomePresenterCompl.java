package com.weile.casualgames.chat.presenter;

import android.content.Context;

import com.weile.casualgames.chat.view.IChatHomeView;
import com.weile.casualgames.game.presenter.GameMenuUtil;

/**
 * Created by zjj
 */
public class ChatHomePresenterCompl implements IChatHomePresenter {

private Context context;
private IChatHomeView chatHomeView;
//private GridView gvGameMenu;

	public ChatHomePresenterCompl(Context context, IChatHomeView chatHomeView) {
		this.context = context;
		this.chatHomeView = chatHomeView;
	}


	@Override
	public void loadInteractiveMessageDatas() {
		chatHomeView.onGetInteractiveMessageDatas(GameMenuUtil.chatInteractiveMessageList);
	}

	@Override
	public void onItemClick(int position) {
//		Class activity = activityHolder.getActivity(activityHolder.getNameList().get(position));
//		if (activity!=null){
//			context.startActivity(new Intent(context, activity));
//		}
	}
}
