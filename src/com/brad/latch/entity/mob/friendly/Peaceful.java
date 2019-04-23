package com.brad.latch.entity.mob.friendly;

import com.brad.latch.entity.mob.Mob;
import com.brad.latch.graphics.Sprite;

public abstract class Peaceful extends Mob {

    public Peaceful(int x, int y) {
        super(x, y);
    }

    @Override
    public void update() {
        time++;
        randomDirection();
        moveToDirection();
        updateHealth();
    }


}
