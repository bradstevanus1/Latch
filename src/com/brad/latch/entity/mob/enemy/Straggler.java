package com.brad.latch.entity.mob.enemy;

public class Straggler extends Chaser {

    public Straggler(int x, int y) {
        // Converts from pixel precision to tile precision
        super(x << 4, y << 4, straggler_down.getSprite(), 0.7);
        name = "Straggler";
        aggroRadius = 100;  // Range at which straggler will follow the player
        size = 32;
        animatedSprite = straggler_down;
        animatedSpriteDown = straggler_down;
        animatedSpriteUp = straggler_up;
        animatedSpriteLeft = straggler_left;
        animatedSpriteRight = straggler_right;
    }

    @Override
    protected void shoot(double x, double y, double dir) {

    }
}
