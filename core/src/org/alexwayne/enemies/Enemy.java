package org.alexwayne.enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Enemy {

    Vector2 position, velocity, size;
    Texture texture;
    int currentAnimFrame = 0, numFrames;
    float animFrameTime, animTimer;

    float scale;

    public abstract void update(float dt);

    public abstract void render(SpriteBatch batch);
    abstract public void dispose();
    public abstract Rectangle getRect();
}
