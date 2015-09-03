package com.mygdx.entities;

import com.mygdx.game.MyGdxGame;

public class SpaceObjects {

	public float[] getShapex() {
		return shapex;
	}

	public void setShapex(float[] shapex) {
		this.shapex = shapex;
	}

	public float[] getShapey() {
		return shapey;
	}

	public void setShapey(float[] shapey) {
		this.shapey = shapey;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	protected float x;
	protected float y;

	protected float dx;
	protected float dy;

	protected float radians;
	protected float speed;
	protected float rotationalSpeed;

	protected int width;
	protected int height;

	protected float[] shapex;
	protected float[] shapey;

	public boolean contains(float x, float y) {
		boolean b = false;
		// even-odd winding rule
		for (int i = 0, j = shapex.length - 1; i < shapex.length; j = i++) {
			if ((shapey[i] > y) != (shapey[j] > y)
					&& (x < (shapex[j] - shapex[i]) * (y - shapey[i])
							/ (shapey[j] - shapey[i]) + shapex[i])) {
				b = !b;
			}
		}
		return b;
	}

	public boolean intersects(SpaceObjects other) {
		float[] sx = other.getShapex();
		float[] sy = other.getShapey();
		for (int i = 0; i < sx.length; i++) {
			if (contains(sx[i], sy[i])) {
				return true;
			}

		}
		return false;
	}

	protected void wrap() {

		if (x < 0)
			x = MyGdxGame.width;
		if (x > MyGdxGame.width)
			x = 0;

		if (y < 0)
			y = MyGdxGame.height;
		if (y > MyGdxGame.height)
			y = 0;

	}

}
