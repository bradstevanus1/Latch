package com.brad.latch.entity.mob.enemy;

import com.brad.latch.graphics.SpriteCollection;

public class Pokey extends AdvancedChaser {

    public Pokey(int x, int y) {
        super(x << 4, y << 4, SpriteCollection.pokey_down.getSprite(), 0.7);
        name = "Pokey";
        aggroRadius = 100;
        size = 32;
        animatedSprite = SpriteCollection.pokey_down;
        animatedSpriteDown = SpriteCollection.pokey_down;
        animatedSpriteUp = SpriteCollection.pokey_up;
        animatedSpriteLeft = SpriteCollection.pokey_left;
        animatedSpriteRight = SpriteCollection.pokey_right;
    }

    @Override
    protected void shoot(double x, double y, double dir) {

    }
}
