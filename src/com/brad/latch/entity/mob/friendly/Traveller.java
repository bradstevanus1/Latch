package com.brad.latch.entity.mob.friendly;


public class Traveller extends Peaceful {

    public Traveller(int x, int y) {
        super(x << 4, y << 4);

        health = 100;
        maxHealth = health;
        hasMelee = false;
        meleeDamage = 0;
        aggroRadius = 0;
        fireRate = 0;
        moveSpeed = 0.7;
        attackInvincTime = 1.0;

        name = "Traveller";
        sprite = traveller_down.getSprite();
        size = 32;
        animatedSprite = traveller_down;
        animatedSpriteDown = traveller_down;
        animatedSpriteUp = traveller_up;
        animatedSpriteLeft = traveller_left;
        animatedSpriteRight = traveller_right;
    }

    @Override
    protected void shoot(double x, double y, double dir) {

    }
}
