package org.alexwayne.invaders.drops;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import org.alexwayne.invaders.Game;

public class ShootFast extends Drop {

    public ShootFast(float x, float y){
        position = new Vector2(x, y);
        size = new Vector2(8, 8);
        texture = new Texture("drop_orange.png");
    }

    public void activate(Game game) {
        game.getPlayer().shootFast();
    }
}
