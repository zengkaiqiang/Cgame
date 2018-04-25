package com.weile.casualgames.game.view;

import com.weile.casualgames.game.model.MatchingModel;

/**
 * Created by zjj
 */
public interface IGameMatchingView {
	public void onGetRandomMatching();
	public void onGetRandomMatchingResult(MatchingModel matchingModel);
//	public void toast(String msg);

}
