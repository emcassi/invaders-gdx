package org.alexwayne.enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class GreenEnemy extends Enemy {

    public GreenEnemy(float x, float y){
        this.position = new Vector2(x, y);
        this.velocity = Vector2.Zero;
        this.size = new Vector2(8, 8);
        this.scale = 5.0f;
        this.texture = new Texture("enemy_green.png");
    }

    @Override
    public void update(float dt) {
        position.x += velocity.x * dt;
        position.y += velocity.y * dt;
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(texture,
                position.x,
                position.y,
                size.x * scale,
                size.y * scale,
                0,
                (int)(currentAnimFrame * size.y),
                (int)(size.x),
                (int)(size.y), false, false);
    }
}
