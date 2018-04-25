package com.weile.casualgames.game.presenter;

import android.content.Context;
import android.widget.GridView;

import com.weile.casualgames.App;
import com.weile.casualgames.R;
import com.weile.casualgames.game.model.DoubleGame;
import com.weile.casualgames.game.view.IGameHomeView;
import com.weile.casualgames.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zjj
 */
public class GameHomePresenterCompl implements IGameHomePresenter {

private Context context;
private IGameHomeView gameHomeView;
private GridView gvGameMenu;

	public GameHomePresenterCompl(Context context, IGameHomeView gameHomeView) {
		this.context = context;
		this.gameHomeView = gameHomeView;
	}

//	public GameHomePresenterCompl(GridView gvGameMenu, IGameHomeView gameHomeView){
//
//	}

	@Override
	public void loadGameMenuDatas() {
		gameHomeView.onGetGameMenuDatas(com.weile.casualgames.game.presenter.GameMenuUtil.gameMenuList);
//		Handler handler = new Handler(Looper.getMainLooper());
//		handler.post(new Runnable() {
//			@Override
//			public void run() {
//				gameHomeView.onGetGameMenuDatas(GameMenuUtil.gameMenuList);
//			}
//		});
	}

	@Override
	public void loadDoubleGameDatas() {
		List<DoubleGame> doubleGames = new ArrayList<DoubleGame>();

		List<DoubleGame> games = App.getSharedPreferences().getGList();
		doubleGames.add(new DoubleGame(101, "fb7a4d", "ff4c65", R.mipmap.ic_game_start));
		for(DoubleGame game : games){
			doubleGames.add(game);
		}

		doubleGames.add(new DoubleGame(102, "7fa6f0", "6063e9", R.mipmap.ic_game_coming_soon));
		LogUtils.E("doubleGames size: " + doubleGames.size());
		gameHomeView.onGetDoubleGameDatas(doubleGames);
	}

	@Override
	public void onItemClick(int position) {
//		Class activity = activityHolder.getActivity(activityHolder.getNameList().get(position));
//		if (activity!=null){
//			context.startActivity(new Intent(context, activity));
//		}
	}
}
