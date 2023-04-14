package org.alexwayne;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import org.alexwayne.enemies.Enemy;
import org.alexwayne.enemies.EnemyRow;
import org.alexwayne.enemies.GreenEnemy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Game extends ApplicationAdapter {
	SpriteBatch batch;
	Player player;

	OrthographicCamera camera;

	int enemiesInRow = 10;
	ArrayList<EnemyRow> rows;

	@Override
	public void create () {
		batch = new SpriteBatch();
		player = new Player(300, 0);
		Gdx.graphics.setWindowedMode(1280, 720);
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 400);
		rows = new ArrayList<>();

		EnemyRow newRow = new EnemyRow(5, 425);
		rows.add(newRow);
	}

	void update(){
		float dt = Gdx.graphics.getDeltaTime();
		player.update(dt);

		Iterator<EnemyRow> rowIterator = rows.iterator();
		while(rowIterator.hasNext()) {
			rowIterator.next().update(dt, player.projs);
		}
	}

	@Override
	public void render () {
		update();

		ScreenUtils.clear(0, 0, 0, 1);
		batch.begin();
		player.render(batch);

		Iterator<EnemyRow> rowIterator = rows.iterator();
		while(rowIterator.hasNext()) {
			EnemyRow row = rowIterator.next();
			row.render(batch);
		}
		batch.end();
	}

	@Override
	public void dispose () {
		player.dispose();
		batch.dispose();
	}
}
