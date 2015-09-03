package com.mygdx.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;

public class Bullet extends SpaceObjects {

	private float lifeTime;
	private float lifeTimer;

	private boolean remove;

	public Bullet(float x, float y, float radian) {

		this.x = x;
		this.y = y;
		this.radians = radian;

		float speed = 350;
		dx = MathUtils.cos(radian) * speed;
		dy = MathUtils.sin(radian) * speed;

		width = height = 4;

		lifeTime = 1;
		lifeTimer = 0;
	}

	public boolean shouldRemove() {
		return remove;
	}

	public void update(float dt) {
		x += dx * dt;
		y += dy * dt;

		lifeTimer += dt;

		if (lifeTimer > lifeTime)
			remove = true;

		wrap();

	}

	public void draw(ShapeRenderer sr) {
		sr.setColor(Color.RED);
		sr.begin(ShapeType.Filled);
		sr.circle(x - width / 2, y - width / 2, width / 2);
		sr.end();
	}

}
