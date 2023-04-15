package org.alexwayne.invaders.enemies;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import org.alexwayne.invaders.PlayerProjectile;

import java.util.ArrayList;
import java.util.Iterator;

public class EnemyRow {
    ArrayList<Enemy> enemies;
    public int count = 10;

    Vector2 position, velocity, size;
    boolean movingDown = false;

    public EnemyRow(float x, float y, float vel) {
        position = new Vector2(x, y);
        velocity = new Vector2(vel, 0);
        enemies = new ArrayList<Enemy>();
        for (int i = 0; i < count; i++) {
            Enemy newEnemy = new GreenEnemy((i * 50) + 5, y, 40);
            enemies.add(newEnemy);
        }

        size = new Vector2(enemies.size() * 55, 60);

    }

    public void render(SpriteBatch batch) {
        for (Enemy enemy : enemies) {
            enemy.render(batch);
        }
    }

    public void update(float dt, ArrayList<PlayerProjectile> playerProjs) {
        Iterator<Enemy> enemyIterator = enemies.iterator();

        if(movingDown){
            while (enemyIterator.hasNext()) {
                Enemy enemy = enemyIterator.next();
                enemy.position.y -= size.y;
                enemy.velocity = velocity;
                checkProjCollision(playerProjs, enemyIterator, enemy);
                enemy.update(dt);
            }
            movingDown = false;
        } else {
            while (enemyIterator.hasNext()) {
                Enemy enemy = enemyIterator.next();
                enemy.velocity = velocity;
                checkProjCollision(playerProjs, enemyIterator, enemy);
                enemy.update(dt);
            }
        }


        if (enemies.size() < 1) {
            dispose();
        }
    }

    public void moveDown(){
        position.y -= size.y;
        velocity.x *= -1;
        movingDown = true;
    }

    void checkProjCollision(ArrayList<PlayerProjectile> playerProjs, Iterator<Enemy> iter, Enemy enemy) {
        Iterator<PlayerProjectile> projectileIterator = playerProjs.iterator();
        while (projectileIterator.hasNext()) {
            PlayerProjectile proj = projectileIterator.next();
            if (enemy.getRect().overlaps(proj.getRect())) {
                proj.dispose();
                projectileIterator.remove();
                enemy.dispose();
                iter.remove();
            }
        }
    }

    public void dispose() {
        Iterator<Enemy> enemyIterator = enemies.iterator();
        while (enemyIterator.hasNext()) {
            Enemy enemy = enemyIterator.next();
            enemy.dispose();
            enemyIterator.remove();
        }
    }

    public void setVelocity(float x){
        velocity.x = x;
    }
}
