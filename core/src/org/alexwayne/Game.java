package org.alexwayne;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import org.alexwayne.enemies.Enemy;
import org.alexwayne.enemies.GreenEnemy;

public class Game extends ApplicationAdapter {
	SpriteBatch batch;
	Player player;

	OrthographicCamera camera;

	Enemy enemies[] = new Enemy[10];

	@Override
	public void create () {
		batch = new SpriteBatch();
		player = new Player(300, 0);
		Gdx.graphics.setWindowedMode(1280, 720);
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 400);

		for(int i = 0; i < enemies.length; i++){
			enemies[i] = new GreenEnemy(i * 50 + 5, 425);
		}
	}

	void update(){
		float dt = Gdx.graphics.getDeltaTime();
		player.update(dt);
		for(int i = 0; i < enemies.length; i++){
			enemies[i].update(dt);
		}
	}

	@Override
	public void render () {
		update();

		ScreenUtils.clear(0, 0, 0, 1);
		batch.begin();
		player.render(batch);
		for(int i = 0; i < enemies.length; i++){
			enemies[i].render(batch);
		}
		batch.end();
	}

	@Override
	public void dispose () {
		player.dispose();
		batch.dispose();
	}
}
