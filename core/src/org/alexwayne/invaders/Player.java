package org.alexwayne.invaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Iterator;

public class Player {

    private final Texture img;
    private Vector2 position, velocity, size;
    private float speed = 430;

    private float bulletCooldown = 0.75f, bulletTimer = 0;
    boolean canShoot = true;


    public ArrayList<PlayerProjectile> projs;

    public Player(float x, float y){
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        size = new Vector2(35, 40);
        img = new Texture("player.png");
        projs = new ArrayList<PlayerProjectile>();
    }

    public void render(SpriteBatch batch){
        for(PlayerProjectile proj : projs){
            proj.render(batch);
        }
        batch.draw(img, position.x, position.y, size.x, size.y);
    }

    public void update(float dt){
        velocity.x = 0;
        if(Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            velocity.x -= speed;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            velocity.x += speed;
        }

        position.x += velocity.x * dt;

        if(position.x < 0) {
            position.x = 0;
        }
        else if (position.x > 600) {
            position.x = 600;
        }

        if(!canShoot) {
            if (bulletTimer <= 0) {
                bulletTimer = bulletCooldown;
                canShoot = true;
            } else {
                bulletTimer -= dt;
            }
        } else {
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE) || Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                PlayerProjectile newProj = new PlayerProjectile(position.x + 13, position.y);
                projs.add(newProj);
                canShoot = false;
            }
        }

        Iterator<PlayerProjectile> iter = projs.iterator();

        while(iter.hasNext()){
            PlayerProjectile proj = iter.next();
            proj.update(dt);

            if(proj.position.y > 450){
                iter.remove();
            }
        }
    }

    public void dispose(){

        img.dispose();

        for( PlayerProjectile proj : projs) {
            proj.dispose();
        }
    }

    public void setSpeed(float newSpeed) {
        this.speed = speed;
    }

    public void setBulletCooldown(float bulletCooldown) {
        this.bulletCooldown = bulletCooldown;
    }
}
