package com.brad.latch.entity.mob.friendly;

import com.brad.latch.entity.mob.Mob;
import com.brad.latch.entity.mob.enemy.Enemy;
import com.brad.latch.graphics.Sprite;
import com.brad.latch.level.tile.TileCoordinate;

import static com.brad.latch.util.MathUtils.inRange;

public abstract class Friendly extends Mob {

    public Friendly(TileCoordinate tileCoordinate) {
        super(tileCoordinate);
    }

    @Override
    public void update() {
        super.update();

        time++;
        randomDirection();
        moveToDirection();

        if (health == 0) {
            death();
        }
    }

    @Override
    @SuppressWarnings("Duplicates")
    protected void updateHealth() {
        super.updateHealth();
        for (Enemy enemy : level.getEnemiesInRange(this, Mob.size)) {
            if (!enemy.isMelee() || enemy.getDamagedEntities().containsKey(this)) continue;
            if (inRange(this, enemy, size)) {
                takeDamage(enemy.getMeleeDamage());
                enemy.getDamagedEntities().put(
                        this, (int) (60 / enemy.getMeleeRate()));
            }
        }
        if (health < 0) health = 0;
    }

    private void death() {
        remove();
    }

}
