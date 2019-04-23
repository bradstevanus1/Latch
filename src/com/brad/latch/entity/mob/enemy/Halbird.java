package com.brad.latch.entity.mob.enemy;

import com.brad.latch.entity.projectile.SpearProjectile;

public class Halbird extends Shooter {

    public Halbird(int x, int y) {
        super(x << 4, y << 4);

        health = 100;
        hasMelee = true;
        meleeDamage = 20;
        aggroRadius = 200;
        fireRate = SpearProjectile.fireRate;
        moveSpeed = 0.7;
        attackInvincTime = 1.0;

        name = "Hal Bird";
        sprite = pokey_down.getSprite();
        size = 32;
        animatedSprite = pokey_down;
        animatedSpriteDown = pokey_down;
        animatedSpriteUp = pokey_up;
        animatedSpriteLeft = pokey_left;
        animatedSpriteRight = pokey_right;
    }

}
