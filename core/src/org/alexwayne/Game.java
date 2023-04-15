package org.alexwayne;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import org.alexwayne.enemies.EnemyRow;

import java.util.ArrayList;

public class Game extends ApplicationAdapter {
	SpriteBatch batch;
	Player player;

	OrthographicCamera camera;

	int enemiesInRow = 10;
	ArrayList<EnemyRow> rows;

	float blockPosX = 5, blockSpeed = 30, blockVel, blockWidth;

	@Override
	public void create () {
		batch = new SpriteBatch();
		player = new Player(300, 0);
		Gdx.graphics.setWindowedMode(1280, 720);
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 400);
		rows = new ArrayList<>();
		blockVel = blockSpeed;
		blockWidth = enemiesInRow * 55;

		for(int i = 0; i < 5; i++){
			EnemyRow newRow = new EnemyRow(5, 430 + i * 60, blockVel);
			rows.add(newRow);
		}
	}

	void update(){
		float dt = Gdx.graphics.getDeltaTime();
		player.update(dt);

		boolean moveDown = false;
		blockPosX += blockVel * dt;
		if(blockPosX < 0) {
			moveDown = true;
			blockVel *= -1;
			blockPosX = 0;
		} else if(blockPosX > 700 - blockWidth) {
			moveDown = true;
			blockVel *= -1;
			blockPosX = 700 - blockWidth;
		}


		for (EnemyRow row : rows) {
			row.setVelocity(blockVel);
			row.update(dt, player.projs);
			if (moveDown) {
				row.moveDown();
			}
		}
	}

	@Override
	public void render () {
		update();

		ScreenUtils.clear(0, 0, 0, 1);
		batch.begin();
		player.render(batch);

		for (EnemyRow row : rows) {
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
