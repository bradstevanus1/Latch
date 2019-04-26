package com.brad.latch.entity.mob.player;

import com.brad.latch.entity.mob.Mob;
import com.brad.latch.entity.mob.enemy.Enemy;

import static com.brad.latch.util.MathUtils.inRange;

public abstract class Player extends Mob {

    public Player(int x, int y) {
        super(x, y);
    }

    @Override
    public void update() {
        super.update();
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

}

