package org.alexwayne;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import org.alexwayne.enemies.Enemy;
import org.alexwayne.enemies.GreenEnemy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Game extends ApplicationAdapter {
	SpriteBatch batch;
	Player player;

	OrthographicCamera camera;

	int enemiesInRow = 10;
	ArrayList<Enemy> enemies;

	@Override
	public void create () {
		batch = new SpriteBatch();
		player = new Player(300, 0);
		Gdx.graphics.setWindowedMode(1280, 720);
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 400);
		enemies = new ArrayList<>();

		for(int i = 0; i < enemiesInRow; i++){
			Enemy newEnemy = new GreenEnemy(i * 50 + 5, 425);
			enemies.add(newEnemy);
		}
	}

	void update(){
		float dt = Gdx.graphics.getDeltaTime();
		player.update(dt);

		Iterator<Enemy> enemyIterator = enemies.iterator();
		while(enemyIterator.hasNext()) {
			Enemy enemy = enemyIterator.next();
				enemy.update(dt);
				Iterator<PlayerProjectile> projectileIterator = player.projs.iterator();
				while(projectileIterator.hasNext()) {
					PlayerProjectile proj = projectileIterator.next();
					if (enemy.getRect().overlaps(proj.getRect())) {
						proj.dispose();
						projectileIterator.remove();
						enemy.dispose();
						enemyIterator.remove();
					}
				}
		}
	}

	@Override
	public void render () {
		update();

		ScreenUtils.clear(0, 0, 0, 1);
		batch.begin();
		player.render(batch);

		Iterator<Enemy> enemyIterator = enemies.iterator();
		while(enemyIterator.hasNext()) {
			enemyIterator.next().render(batch);
		}
		batch.end();
	}

	@Override
	public void dispose () {
		player.dispose();
		batch.dispose();
	}
}
