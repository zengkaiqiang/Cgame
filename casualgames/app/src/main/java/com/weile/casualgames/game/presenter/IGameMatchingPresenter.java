package com.weile.casualgames.game.presenter;

/**
 * Created by zjj
 */
public interface IGameMatchingPresenter {
	public void loadRandomMatching();
	public void  cancelMatchingTimer();
	public void onItemClick(int position);
}
