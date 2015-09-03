package com.mygdx.entities;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.MyGdxGame;

public class LinePlayer extends SpaceObjects {

	private float[] flamex;
	private float[] flamey;

	private boolean left;
	private boolean right;

	private int LEFTDIR = 0;
	private int RIGHTDIR = 1;

	private int direction;

	private float maxSpeed;
	private float acceleration;
	private float deacceleration;
	private float acceleratingTime;
	private int maxbullet = 4;
	private ArrayList<Bullet> bullets;

	public LinePlayer() {
		x = MyGdxGame.height / 2;
		y = MyGdxGame.width / 4;
		dx = 0;
		maxSpeed = 300;
		acceleration = 200;
		deacceleration = 0.1f;

		shapex = new float[4];
		shapey = new float[4];
		flamex = new float[3];
		flamey = new float[3];

		radians = 3.1415f / 2;
		rotationalSpeed = 3;

	}

	public LinePlayer(ArrayList<Bullet> bulletList) {
		// TODO Auto-generated constructor stub
		x = MyGdxGame.height / 2;
		y = MyGdxGame.width / 4;

		maxSpeed = 300;
		acceleration = 200;
		deacceleration = 0.1f;

		shapex = new float[4];
		shapey = new float[4];
		flamex = new float[3];
		flamey = new float[3];

		direction = 2;

		radians = 3.1415f / 2;
		rotationalSpeed = 3;
		this.bullets = bulletList;
	}

	private void setShape() {

		shapex[0] = x + MathUtils.cos(radians) * 8;
		shapey[0] = y + MathUtils.sin(radians) * 8;

		shapex[1] = x + MathUtils.cos(radians - 4 * 3.1415f / 5) * 20;
		shapey[1] = y + MathUtils.sin(radians - 4 * 3.1415f / 5) * 20;

		shapex[2] = x + MathUtils.cos(radians + 3.1415f) * 5;
		shapey[2] = y + MathUtils.sin(radians + 3.1415f) * 5;

		shapex[3] = x + MathUtils.cos(radians + 4 * 3.1415f / 5) * 20;
		shapey[3] = y + MathUtils.sin(radians + 4 * 3.1415f / 5) * 20;

	}

	private void setFlame() {
		flamex[0] = x + MathUtils.cos(radians - 5 * 3.1415f / 6) * 10;
		flamey[0] = y + MathUtils.sin(radians - 5 * 3.1415f / 6) * 10;

		flamex[1] = x + MathUtils.cos(radians - 3.1415f)
				* (6 + acceleratingTime * 50);
		flamey[1] = y + MathUtils.sin(radians - 3.1415f)
				* (6 + acceleratingTime * 50);

		flamex[2] = x + MathUtils.cos(radians + 5 * 3.1415f / 6) * 10;
		flamey[2] = y + MathUtils.sin(radians + 5 * 3.1415f / 6) * 10;

	}

	public void setLeft(boolean b) {
		left = b;
	}

	public void setRight(boolean b) {
		right = b;
	}

	public void shoot() {

		if (bullets.size() == maxbullet)
			return;
		bullets.add(new Bullet(x, y, radians));
	}

	public void update(float dt) {

		// turning
		if (left) {
			dx -= dt;
			direction = LEFTDIR;

		} else if (right) {
			dx += dt;
			direction = RIGHTDIR;
		} else if (direction == LEFTDIR)
			dx += deacceleration;
		else if (direction == RIGHTDIR)
			dx -= deacceleration;

		// set position
		x += dx;

		// set Shape
		setShape();
		// setflame

		// screen wrap
		wrap();
	}

	public void draw(ShapeRenderer sr) {
		sr.setColor(1, 1, 1, 1);
		sr.begin(ShapeType.Line);

		for (int i = 0, j = shapex.length - 1; i < shapex.length; j = i++) {
			sr.line(shapex[i], shapey[i], shapex[j], shapey[j]);
		}

		sr.end();

	}
}
