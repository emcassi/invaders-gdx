package org.alexwayne;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Game extends ApplicationAdapter {
	SpriteBatch batch;
	Player player;

	OrthographicCamera camera;

	@Override
	public void create () {
		batch = new SpriteBatch();
		player = new Player(300, 0);
		Gdx.graphics.setWindowedMode(1280, 720);
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 400);
	}

	void update(){
		player.update(Gdx.graphics.getDeltaTime());
	}

	@Override
	public void render () {
		update();

		ScreenUtils.clear(0, 0, 0, 1);
		batch.begin();
		player.render(batch);
		batch.end();
	}

	@Override
	public void dispose () {
		player.dispose();
		batch.dispose();
	}
}
