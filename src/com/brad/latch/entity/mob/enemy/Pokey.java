package com.brad.latch.entity.mob.enemy;

public class Pokey extends AdvancedChaser {

    public Pokey(int x, int y) {
        super(x << 4, y << 4);

        health = 8;
        maxHealth = health;
        hasMelee = true;
        meleeDamage = 8;
        aggroRadius = 500;
        projectileRate = 0;
        moveSpeed = 0.5;
        meleeRate = 4.0;

        name = "Pokey";
        sprite = pokey_down.getSprite();
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
