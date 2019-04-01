package com.brad.latch.entity.mob.enemy;

import com.brad.latch.graphics.SpriteCollection;

public class Straggler extends Chaser {

    public Straggler(int x, int y) {
        // Converts from pixel precision to tile precision
        super(x << 4, y << 4, SpriteCollection.straggler_down.getSprite(), 0.7);
        name = "Straggler";
        aggroRadius = 100;  // Range at which straggler will follow the player
        size = 32;
        animatedSprite = SpriteCollection.straggler_down;
        animatedSpriteDown = SpriteCollection.straggler_down;
        animatedSpriteUp = SpriteCollection.straggler_up;
        animatedSpriteLeft = SpriteCollection.straggler_left;
        animatedSpriteRight = SpriteCollection.straggler_right;
    }

    @Override
    protected void shoot(double x, double y, double dir) {

    }
}
