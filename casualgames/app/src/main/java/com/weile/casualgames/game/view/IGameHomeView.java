package com.weile.casualgames.game.view;

import com.weile.casualgames.game.model.DoubleGame;
import com.weile.casualgames.game.model.GameMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zjj
 */
public interface IGameHomeView {
	public void onGetGameMenuDatas(ArrayList<GameMenu> menus);
	public void onGetDoubleGameDatas(List<DoubleGame> games);
//	public void toast(String msg);

}
