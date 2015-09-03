package com.mygdx.manager;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;

public class GameInputProcessor extends InputAdapter {

	public boolean keyDown(int k) {
		if (k == Keys.UP)
			GameKeys.setKeys(GameKeys.UP, true);
		if (k == Keys.DOWN)
			GameKeys.setKeys(GameKeys.DOWN, true);
		if (k == Keys.LEFT)
			GameKeys.setKeys(GameKeys.LEFT, true);
		if (k == Keys.RIGHT)
			GameKeys.setKeys(GameKeys.RIGHT, true);
		if (k == Keys.ENTER)
			GameKeys.setKeys(GameKeys.ENTER, true);
		if (k == Keys.ESCAPE)
			GameKeys.setKeys(GameKeys.ESCAPE, true);
		if (k == Keys.SHIFT_RIGHT || k == Keys.SHIFT_LEFT)
			GameKeys.setKeys(GameKeys.SHIFT, true);
		if (k == Keys.SPACE)
			GameKeys.setKeys(GameKeys.SPACE, true);

		return true;
	}

	public boolean keyUp(int k) {
		if (k == Keys.UP)
			GameKeys.setKeys(GameKeys.UP, false);
		if (k == Keys.DOWN)
			GameKeys.setKeys(GameKeys.DOWN, false);
		if (k == Keys.LEFT)
			GameKeys.setKeys(GameKeys.LEFT, false);
		if (k == Keys.RIGHT)
			GameKeys.setKeys(GameKeys.RIGHT, false);
		if (k == Keys.ENTER)
			GameKeys.setKeys(GameKeys.ENTER, false);
		if (k == Keys.ESCAPE)
			GameKeys.setKeys(GameKeys.ESCAPE, false);
		if (k == Keys.SHIFT_RIGHT || k == Keys.SHIFT_LEFT)
			GameKeys.setKeys(GameKeys.SHIFT, false);
		if (k == Keys.SPACE)
			GameKeys.setKeys(GameKeys.SPACE, false);
		return true;
	}
}
