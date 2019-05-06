package com.brad.latch.entity.mob.enemy;

import com.brad.latch.entity.mob.Mob;
import com.brad.latch.level.tile.TileCoordinate;

public abstract class Enemy extends Mob {

    public Enemy(TileCoordinate tileCoordinate) {
        super(tileCoordinate);
    }

    @Override
    public void update() {
        super.update();
        if (health == 0) {
            death();
        }
    }

    private void death() {
        recall();
        health = maxHealth;
    }

    private void recall() {
        x = level.spawnPoint.getX();
        y = level.spawnPoint.getY();
    }

}
