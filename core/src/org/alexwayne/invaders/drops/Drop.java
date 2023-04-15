package org.alexwayne.invaders.drops;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import org.alexwayne.invaders.Game;

public abstract class Drop {

    Vector2 position, size;
    float scale = 5;
    Texture texture;
    float gravity = 200;
    float floorLevel = 0;

    public abstract void activate(Game game);
    public void render(SpriteBatch batch){
        batch.draw(texture, position.x, position.y, size.x * scale, size.y * scale);
    }

    public void update(float dt){
        position.y -= gravity * dt;

        if(position.y <= floorLevel){
            position.y = floorLevel;
        }
    }

    public void dispose(){
        texture.dispose();
    }

    public Rectangle getRect(){
        return new Rectangle(position.x, position.y, size.x * scale, size.y * scale);
    }
}
