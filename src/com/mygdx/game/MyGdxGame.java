package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.mygdx.manager.GameInputProcessor;
import com.mygdx.manager.GameKeys;
import com.mygdx.manager.GameStateManager;

public class MyGdxGame extends ApplicationAdapter {

	public static int height;
	public static int width;

	public static OrthographicCamera cam;

	public GameStateManager gsm;

	@Override
	public void create() {
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();

		cam = new OrthographicCamera(width, height);

		cam.translate(width / 2, height / 2);
		cam.update();

		Gdx.input.setInputProcessor(new GameInputProcessor());
		gsm = new GameStateManager();
		gsm.setState(GameStateManager.PLAY);
	}

	@Override
	public void dispose() {
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.draw();
		
		

		GameKeys.update();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
