package org.alexwayne;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class PlayerProjectile extends Projectile{

    public PlayerProjectile(float x, float y){
        position = new Vector2(x, y);
        velocity = new Vector2(0, 400);
        size = new Vector2(10, 30);
        texture = new Texture("player_proj_single.png");
    }

    @Override
    void update(float dt) {
        position.x += velocity.x * dt;
        position.y += velocity.y * dt;
    }

    @Override
    void render(SpriteBatch batch) {
        batch.draw(texture, position.x, position.y, size.x, size.y);
    }

    @Override
    public void dispose(){
        texture.dispose();
    }

    @Override
    public Rectangle getRect(){
        return new Rectangle(position.x, position.y, size.x, size.y);
    }
}
