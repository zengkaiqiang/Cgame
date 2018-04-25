package com.weile.casualgames.game.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.weile.casualgames.App;
import com.weile.casualgames.game.GameActivity;
import com.weile.casualgames.game.model.MatchingModel;
import com.weile.casualgames.net.IRequestListener;
import com.weile.casualgames.net.NetApi;
import com.weile.casualgames.net.NetworkService;
import com.weile.casualgames.utils.LogUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;

/**
 * Created by zjj
 */
public class GameMatchingPresenterCompl implements IGameMatchingPresenter {

private Context context;
private IGameMatchingView gameMatchingView;
private GameActivity gameActivity;

	public GameMatchingPresenterCompl(Context context, IGameMatchingView gameMatchingView) {
		this.context = context;
		this.gameMatchingView = gameMatchingView;
	}

	public GameMatchingPresenterCompl(Context context, GameActivity gameActivity) {
		this.context = context;
		this.gameActivity = gameActivity;
	}




	@Override
	public void cancelMatchingTimer() {

		if (matchingTimer != null){
			matchingTimer.cancel();
		}
		matchingTimer = null;
//		Handler handler = new Handler(Looper.getMainLooper());
//		handler.post(new Runnable() {
//			@Override
//			public void run() {
//				gameHomeView.onGetGameMenuDatas(GameMenuUtil.gameMenuList);
//			}
//		});
	}

	@Override
	public void loadRandomMatching() {
		startRandomMatching();
//		getDoubleGameList();
//		gameHomeView.onGetDoubleGameDatas(GameMenuUtil.doubleGameList);
	}

	@Override
	public void onItemClick(int position) {
//		Class activity = activityHolder.getActivity(activityHolder.getNameList().get(position));
//		if (activity!=null){
//			context.startActivity(new Intent(context, activity));
//		}
	}



	private Timer matchingTimer = new Timer();
	private String result;

	/**
	 * 从服务器端获取消息
	 *
	 */
	/*class MessageThread extends Thread{
		//运行状态，下一步骤有大用
		public boolean isRunning = true;
		public void run() {
			while(isRunning){
				try {

					new Handler().postDelayed(new Runnable() {
						@Override
						public void run() {

						}
					}, 1000);
					//获取服务器消息


				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}


	}*/

	public class MatchingTimerTask extends java.util.TimerTask {

		@Override

		public void run() {
			getMatchingResult();
		}

	}

	private void startRandomMatching(){

//		JSONObject js = new JSONObject();
//		try {
//			js.put("Id", App.getSharedPreferences().getUid());
//			js.put("Uid", App.getSharedPreferences().getUid());
//			js.put("Sex", "0");
//			js.put("Name", App.getSharedPreferences().getName());
//			js.put("Head", App.getSharedPreferences().getHead());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		 WebService.request(js,"/pipei_rand");

		JSONObject json = new JSONObject();
		try {

			json.put("Id", App.getSharedPreferences().getUid());
			json.put("Uid", App.getSharedPreferences().getUid());
			json.put("Sex", "0");
			json.put("Name", App.getSharedPreferences().getName());
			json.put("Head", App.getSharedPreferences().getHead());
		} catch (Exception e) {
			e.printStackTrace();
		}
		NetworkService.postWithLoading(App.getContext(), NetApi.RANDOM_MATCHING, json,
				new IRequestListener() {
					@Override
					public void onSuccess(JSONObject response) {
						LogUtils.E("onSuccess=====onSuccess=1" + response);
						Gson gson = new Gson();
						try {

							int message = response.getInt("ec");
							if (message == 0){
								matchingTimer.schedule(new MatchingTimerTask(),0, 1000);
							}

						} catch(JSONException e){
							e.printStackTrace();
						}


					}

					@Override
					public boolean onError(String status) {
						return false;
					}

				});
	}


	private void  getMatchingResult(){
		JSONObject json = new JSONObject();
		try {
			json.put("Uid", App.getSharedPreferences().getUid());
		} catch (Exception e) {
			e.printStackTrace();
		}
		NetworkService.postWithLoading(App.getContext(), NetApi.RANDOM_MATCHING_RESULT, json,
				new IRequestListener() {
					@Override
					public void onSuccess(JSONObject response) {
						LogUtils.E("onSuccess=====onSuccess=1" + response);
						Gson gson = new Gson();
						try {

							int message = response.getInt("ec");
							if (message == 0){
								matchingTimer.cancel();
//								String jsonDemo = "{\"ec\":0,\"em\":\"ok\",\"Key\":\"1.155\",\"Ip\":\"192.168.67.71:9500\",\"Users\":[{\"Uid\":1,\"Name\":\"nimei\",\"Sex\":0,\"Head\":\"1\"},{\"Uid\":18,\"Name\":\"5555\",\"Sex\":0,\"Head\":\"1\"}],\"Id\":1}";
								MatchingModel matchingModel = gson.fromJson(response.toString(), MatchingModel.class);
								gameActivity.onGetRandomMatchingResult(matchingModel);
							}

						} catch(JSONException e){
							e.printStackTrace();
						}


					}

					@Override
					public boolean onError(String status) {
						return false;
					}

				});

	}
}
