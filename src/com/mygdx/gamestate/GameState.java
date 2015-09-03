package com.mygdx.gamestate;

import com.mygdx.manager.GameStateManager;

public abstract class GameState {

	protected GameStateManager gsm;

	protected GameState(GameStateManager gameStateManager) {
		gsm = gameStateManager;
	}

	public abstract void init();

	public abstract void update(float dt);

	public abstract void draw();

	public abstract void handleInput();

	public abstract void dispose();

}
