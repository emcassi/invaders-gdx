package org.alexwayne.invaders;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Projectile {
    Vector2 position, velocity, size;
    Texture texture;

    abstract void update(float dt);
    abstract void render(SpriteBatch batch);
    abstract void dispose();
    abstract Rectangle getRect();
}
