package org.alexwayne.enemies;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import org.alexwayne.PlayerProjectile;

import java.util.ArrayList;
import java.util.Iterator;

public class EnemyRow {
    ArrayList<Enemy> enemies;
    public int count = 10;

    Vector2 position, size;
    boolean movingDown = false;

    public EnemyRow(float x, float y) {
        position = new Vector2(x, y);

        enemies = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Enemy newEnemy = new GreenEnemy((i * 50) + 5, y, 40);
            enemies.add(newEnemy);
        }

        size = new Vector2(enemies.size() * 55, 50);

    }

    public void render(SpriteBatch batch) {
        Iterator<Enemy> enemyIterator = enemies.iterator();
        while (enemyIterator.hasNext()) {
            Enemy enemy = enemyIterator.next();
            enemy.render(batch);
        }
    }

    public void update(float dt, ArrayList<PlayerProjectile> playerProjs) {
        Iterator<Enemy> enemyIterator = enemies.iterator();

        if (movingDown) {
            while (enemyIterator.hasNext()) {
                Enemy enemy = enemyIterator.next();
                enemy.velocity.x *= -1;
                enemy.position.y -= size.y;
                checkProjCollision(playerProjs, enemyIterator, enemy);
                enemy.update(dt);
            }
            movingDown = false;
        } else {
            while (enemyIterator.hasNext()) {
                Enemy enemy = enemyIterator.next();
                checkProjCollision(playerProjs, enemyIterator, enemy);
                enemy.update(dt);
                if(enemy.position.x < 0){
                    movingDown = true;
                } else if(enemy.position.x > 600 - enemy.size.x){
                    movingDown = true;
                }
            }
        }
        if (enemies.size() < 1) {
            dispose();
        }
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
}
