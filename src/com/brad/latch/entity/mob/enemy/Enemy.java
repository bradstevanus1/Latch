package com.brad.latch.entity.mob.enemy;

import com.brad.latch.entity.mob.Mob;

public abstract class Enemy extends Mob {

    public Enemy(int x, int y) {
        super(x, y);
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
