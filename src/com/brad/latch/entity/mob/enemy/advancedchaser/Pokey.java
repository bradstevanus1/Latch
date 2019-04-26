package com.brad.latch.entity.mob.enemy.advancedchaser;

import com.brad.latch.entity.Entity;
import com.brad.latch.graphics.Sprites;

public class Pokey extends AdvancedChaser {

    public Pokey(int x, int y) {
        super(x << 4, y << 4);

        health = 8;
        maxHealth = health;
        melee = true;
        meleeDamage = 8;
        aggroRadius = 500;
        projectileRate = 0;
        moveSpeed = 0.5;
        meleeRate = 4.0;

        name = "Pokey";
        sprite = Sprites.pokey_down.getSprite();
        size = 32;
        animatedSprite = Sprites.pokey_down;
        animatedSpriteDown = Sprites.pokey_down;
        animatedSpriteUp = Sprites.pokey_up;
        animatedSpriteLeft = Sprites.pokey_left;
        animatedSpriteRight = Sprites.pokey_right;
    }

    @Override
    protected void shoot(double x, double y, double dir) {

    }
}
