package com.brad.latch.entity.mob.enemy;

public class Straggler extends Chaser {

    public Straggler(int x, int y) {
        super(x << 4, y << 4);

        health = 50;
        hasMelee = true;
        meleeDamage = 5;
        aggroRadius = 500;
        fireRate = 0;
        moveSpeed = 0.7;
        attackInvincTime = 0.7;

        name = "Straggler";
        sprite = straggler_down.getSprite();
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
