package org.alexwayne.invaders.enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import org.alexwayne.invaders.Game;
import org.alexwayne.invaders.drops.Drop;
import org.alexwayne.invaders.drops.DropChance;
import org.alexwayne.invaders.drops.DropTypes;
import org.alexwayne.invaders.drops.SpeedUp;

public abstract class Enemy {

    Vector2 position, velocity, size;
    Texture texture;
    int currentAnimFrame = 0, numFrames;
    float animFrameTime, animTimer;

    DropChance dropTypes[];

    float scale;

    public abstract void update(float dt);

    public abstract void render(SpriteBatch batch);
    abstract public void dispose();
    public abstract Rectangle getRect();

    public void dropItem(float diceRoll, Game game){

        float total = 0;
        System.out.println(diceRoll);
        for(DropChance dc : dropTypes){
            if(diceRoll >= total && diceRoll < total + dc.getHitRate()){
                switch (dc.getType()){
                    case SPEEDUP:
                        Drop newDrop = new SpeedUp(position.x, position.y);
                        game.addDrop(newDrop);
                }
            }
            total += dc.getHitRate();
        }
    }
}
