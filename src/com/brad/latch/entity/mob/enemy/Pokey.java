package com.brad.latch.entity.mob.enemy;

public class Pokey extends AdvancedChaser {

    public Pokey(int x, int y) {
        super(x << 4, y << 4, pokey_down.getSprite(), 0.7);
        name = "Pokey";
        aggroRadius = 100;
        size = 32;
        animatedSprite = pokey_down;
        animatedSpriteDown = pokey_down;
        animatedSpriteUp = pokey_up;
        animatedSpriteLeft = pokey_left;
        animatedSpriteRight = pokey_right;
    }

    @Override
    protected void shoot(double x, double y, double dir) {

    }
}
