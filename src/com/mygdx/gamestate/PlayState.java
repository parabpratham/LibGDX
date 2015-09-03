package com.mygdx.gamestate;

import java.awt.Font;
import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.entities.Asteroid;
import com.mygdx.entities.Bullet;
import com.mygdx.entities.Particle;
import com.mygdx.entities.Player;
import com.mygdx.game.MyGdxGame;
import com.mygdx.manager.GameKeys;
import com.mygdx.manager.GameStateManager;

public class PlayState extends GameState {

	private SpriteBatch sb;

	private ShapeRenderer sr;

	private BitmapFont bf;

	private BitmapFont font;

	private Player player;

	private ArrayList<Bullet> bullets;

	private ArrayList<Asteroid> asteroids;

	private int level;

	private int totalAsteroids;
	private int numAsteroidsLeft;

	private ArrayList<Particle> particles;

	public PlayState(GameStateManager gsm) {
		super(gsm);

		init();
	}

	public void init() {
		FreeTypeFontGenerator gn = new FreeTypeFontGenerator(
				Gdx.files.internal("fonts/Hyperspace Bold.ttf"));
		font = gn.generateFont(20);

		sr = new ShapeRenderer();
		sb = new SpriteBatch();
		bullets = new ArrayList<Bullet>();
		particles = new ArrayList<Particle>();
		player = new Player(bullets);
		asteroids = new ArrayList<Asteroid>();
		level = 1;

		spawnAsteroids();
	}

	private void createParticles(float x, float y) {
		for (int i = 0; i < 6; i++) {
			particles.add(new Particle(x, y));
		}
	}

	private void spawnAsteroids() {

		asteroids.clear();

		int numToSpawn = 4 + level - 1;
		totalAsteroids = numToSpawn * 7;
		numAsteroidsLeft = totalAsteroids;
		for (int i = 0; i < numToSpawn; i++) {
			float x = MathUtils.random(MyGdxGame.width);
			float y = MathUtils.random(MyGdxGame.height);

			float dx = x - player.getX();
			float dy = y - player.getY();

			float dist = (float) Math.sqrt(dx * dx + dy * dy);
			while (dist < 100) {
				x = MathUtils.random(MyGdxGame.width);
				y = MathUtils.random(MyGdxGame.height);

				dx = x - player.getX();
				dy = y - player.getY();

				dist = (float) Math.sqrt(dx * dx + dy * dy);
			}
			asteroids.add(new Asteroid(x, y, Asteroid.LARGE));
		}
	}

	public void update(float dt) {
		// handleInput
		handleInput();

		// next level
		if (asteroids.size() == 0) {
			level++;
			spawnAsteroids();
		}

		// check collosions
		checkCollosions();

		// Update Player
		player.update(dt);
		if (player.isDead()) {
			player.reset();
			player.lostLife();
			return;
		}

		// Update Bullets
		for (int i = 0; i < bullets.size(); i++) {
			if (bullets.get(i).shouldRemove()) {
				bullets.remove(i);
			} else {
				bullets.get(i).update(dt);
				bullets.get(i).draw(sr);
			}
		}

		// update asteroids
		for (int i = 0; i < asteroids.size(); i++) {
			if (asteroids.get(i).shouldRemove()) {
				asteroids.remove(i);
			} else {
				asteroids.get(i).update(dt);
				asteroids.get(i).draw(sr);
			}
		}

		// update particle
		for (int i = 0; i < particles.size(); i++) {
			particles.get(i).update(dt);
			if (particles.get(i).shouldRemove()) {
				particles.remove(i);
			}
		}

	}

	private void checkCollosions() {
		if (!player.isHit()) {
			// player - asteroid
			for (int j = 0; j < asteroids.size(); j++) {
				Asteroid a = asteroids.get(j);
				if (a.intersects(player)) {
					player.hit();
					asteroids.remove(j);
					j--;
					splitAsteroids(a);
					break;
				}
			}
		}

		// bullet - asteroid
		for (int i = 0; i < bullets.size(); i++) {
			Bullet bullet = bullets.get(i);
			System.out.println(asteroids.size());
			for (int j = 0; j < asteroids.size(); j++) {
				Asteroid a = asteroids.get(j);
				// a contains b
				if (a.contains(bullet.getX(), bullet.getX())) {
					System.out.println("i" + i);
					bullets.remove(i);
					i--;
					asteroids.remove(j);
					j--;
					splitAsteroids(a);
					break;
				}
			}
		}
	}

	private void splitAsteroids(Asteroid a) {

		createParticles(a.getX(), a.getY());

		numAsteroidsLeft--;
		if (a.getType() == Asteroid.LARGE) {
			asteroids.add(new Asteroid(a.getX(), a.getY(), Asteroid.MEDIUM));
			asteroids.add(new Asteroid(a.getX(), a.getY(), Asteroid.MEDIUM));
		} else if (a.getType() == Asteroid.MEDIUM) {
			asteroids.add(new Asteroid(a.getX(), a.getY(), Asteroid.SMALL));
			asteroids.add(new Asteroid(a.getX(), a.getY(), Asteroid.SMALL));
		}
	}

	public void draw() {
		player.draw(sr);

		// draw bullets
		for (int i = 0; i < bullets.size(); i++)
			bullets.get(i).draw(sr);

		// draw asteroids
		for (int i = 0; i < asteroids.size(); i++)
			asteroids.get(i).draw(sr);

		// draw particles
		for (int i = 0; i < particles.size(); i++)
			particles.get(i).draw(sr);

		sb.setColor(Color.WHITE);
		sb.begin();
		font.draw(sb, Long.toString(player.getScore()), 30, 490);
		sb.end();

	}

	public void handleInput() {
		player.setLeft(GameKeys.isDown(GameKeys.LEFT));
		player.setRight(GameKeys.isDown(GameKeys.RIGHT));
		player.setUp(GameKeys.isDown(GameKeys.UP));
		if (GameKeys.isPressed(GameKeys.SPACE)) {
			player.shoot();
		}
	}

	public void dispose() {
	}
}
