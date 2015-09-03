package com.mygdx.manager;

import com.mygdx.gamestate.GameState;
import com.mygdx.gamestate.MenuState;
import com.mygdx.gamestate.PlayState;

public class GameStateManager {

	private GameState gameState;

	public static int MENU = 0;
	public static int PLAY = 1;

	public GameStateManager() {
	}

	public void setState(int state) {
		System.out.println(state);
		if (gameState != null)
			gameState.dispose();

		if (state == MENU) {
			//gameState = new MenuState(this);
		}
		if (state == PLAY) {
			gameState = new PlayState(this);
		}

	}

	public void update(float dt) {
		gameState.update(dt);
	}

	public void draw() {

		gameState.draw();
	}

}
