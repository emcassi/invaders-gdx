package org.alexwayne.invaders;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.AudioDevice;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import org.alexwayne.invaders.audio.Boombox;
import org.alexwayne.invaders.drops.Drop;
import org.alexwayne.invaders.enemies.EnemyRow;

import java.util.ArrayList;
import java.util.Iterator;

public class Game extends ApplicationAdapter {
	SpriteBatch batch;
	Player player;

	OrthographicCamera camera;

	int enemiesInRow = 10;
	ArrayList<EnemyRow> rows;
	ArrayList<Drop> drops;
	float topRowY;

	float blockPosX = 5, blockSpeed = 30, blockVel, blockWidth;

	Boombox boombox;

	@Override
	public void create () {
		batch = new SpriteBatch();
		player = new Player(300, 0, this);
		Gdx.graphics.setWindowedMode(1280, 720);
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 400);
		rows = new ArrayList<EnemyRow>();
		drops = new ArrayList<Drop>();
		blockVel = blockSpeed;
		blockWidth = enemiesInRow * 55;

		for(int i = 0; i < 5; i++){
			topRowY = 430 + i * 60;
			EnemyRow newRow = new EnemyRow(5, topRowY, blockVel, this);
			rows.add(newRow);
		}

		boombox = new Boombox();
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

		Iterator<Drop> dropIterator = drops.iterator();
		while(dropIterator.hasNext()) {
			Drop drop = dropIterator.next();

			drop.update(dt);

			if(player.getRect().overlaps(drop.getRect())) {
				drop.activate(this);
				drop.dispose();;
				dropIterator.remove();
			}
		}
	}

	@Override
	public void render () {
		update();

		ScreenUtils.clear(0, 0, 0, 1);
		batch.begin();

		for (Drop drop : drops) {
			drop.render(batch);
		}

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

	public Player getPlayer(){
		return player;
	}
	public Boombox getBoombox() { return boombox; }

	public void addRow(){
		EnemyRow newRow = new EnemyRow(5, topRowY + 60, blockVel, this);
		rows.add(newRow);
	}

	public void addDrop(Drop drop){
		drops.add(drop);
	}

	public void destroyDrop(Drop drop) { drops.remove(drop); }
}
