package org.alexwayne.invaders.enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import org.alexwayne.invaders.Game;
import org.alexwayne.invaders.audio.SFX;
import org.alexwayne.invaders.drops.Drop;
import org.alexwayne.invaders.drops.DropChance;
import org.alexwayne.invaders.drops.ShootFast;
import org.alexwayne.invaders.drops.SpeedUp;

import java.util.Random;

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

    public void kill(Random random, Game game){

        float diceRoll = random.nextFloat() * 100;
        float total = 0;
        for(DropChance dc : dropTypes){
            if(diceRoll >= total && diceRoll < total + dc.getHitRate()){
                Drop newDrop;
                switch (dc.getType()){
                    case SPEEDUP:
                        newDrop = new SpeedUp(position.x, position.y);
                        game.addDrop(newDrop);
                        break;
                    case SHOOTFAST:
                        newDrop = new ShootFast(position.x, position.y);
                        game.addDrop(newDrop);
                    default:
                        break;
                }
            }
            total += dc.getHitRate();
        }

        int soundToPlay = random.nextInt(2);
        switch (soundToPlay){
            case 0:
                game.getBoombox().playSound(SFX.BOOM1);
                break;
            case 1:
                game.getBoombox().playSound(SFX.BOOM2);
                break;
            default:
                break;
        }

    }
}
