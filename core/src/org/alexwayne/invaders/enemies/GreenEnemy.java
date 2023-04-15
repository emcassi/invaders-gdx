package org.alexwayne.invaders.enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class GreenEnemy extends Enemy {

    public GreenEnemy(float x, float y){
        this.position = new Vector2(x, y);
        this.velocity = Vector2.Zero;
        this.size = new Vector2(8, 8);
        this.scale = 5.0f;
        this.texture = new Texture("enemy_green.png");

        numFrames = 2;
        animFrameTime = 0.5f;
        animTimer = animFrameTime;
    }

    public GreenEnemy(float x, float y, float velX){
        this.position = new Vector2(x, y);
        this.velocity = new Vector2(velX, 0);
        this.size = new Vector2(8, 8);
        this.scale = 5.0f;
        this.texture = new Texture("enemy_green.png");

        numFrames = 2;
        animFrameTime = 1.5f;
        animTimer = animFrameTime;

    }



    @Override
    public void update(float dt) {
        position.x += velocity.x * dt;
        position.y += velocity.y * dt;

        if(animTimer <= 0) {
            animTimer = animFrameTime;
            currentAnimFrame++;
            if(currentAnimFrame >= numFrames)
                currentAnimFrame = 0;
        } else {
            animTimer -= dt;
        }
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

    @Override
    public void dispose() {
        texture.dispose();
    }

    @Override
    public Rectangle getRect() {
        return new Rectangle(position.x, position.y, size.x * scale, size.y * scale);
    }

}
