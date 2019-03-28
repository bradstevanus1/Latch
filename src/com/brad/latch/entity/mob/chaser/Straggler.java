package com.brad.latch.entity.mob.chaser;

import com.brad.latch.graphics.SpriteCollection;

// TODO Make this extend an intermediary "chaser" class with static members like aggroRange

public class Straggler extends Chaser {

    public Straggler(int x, int y) {
        // Converts from pixel precision to tile precision
        super(x << 4, y << 4, SpriteCollection.straggler_down.getSprite(), 0.5);
        aggroRange = 70;  // Range at which straggler will follow the player
        size = 32;
        animatedSprite = SpriteCollection.straggler_down;
        animatedSpriteDown = SpriteCollection.straggler_down;
        animatedSpriteUp = SpriteCollection.straggler_up;
        animatedSpriteLeft = SpriteCollection.straggler_left;
        animatedSpriteRight = SpriteCollection.straggler_right;
    }

}
