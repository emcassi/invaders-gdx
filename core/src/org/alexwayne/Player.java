package org.alexwayne;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player {

    Texture img;
    float posX, posY, velocity;
    float speed = 430;
    int width = 40, height = 35;
    public Player(float x, float y){
        this.posX = x;
        this.posY = y;

        img = new Texture("player.png");
    }

    public void render(SpriteBatch batch){
        batch.draw(img, posX, posY, width, height);
    }

    public void update(float dt){
        velocity = 0;
        if(Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            velocity -= speed;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            velocity += speed;
        }

        posX += velocity * dt;


        if(posX < 0) {
            posX = 0;
        }
        else if (posX > 600) {
            posX = 600;
        }
        System.out.println(posX);
    }

    public void dispose(){
        img.dispose();
    }
}
