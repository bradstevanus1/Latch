package com.brad.latch.net.player;

import com.brad.latch.entity.mob.player.Player;
import com.brad.latch.entity.projectile.SpearProjectile;
import com.brad.latch.graphics.Screen;

import java.awt.Color;

public class NetPlayer extends Player {

    public NetPlayer(int x, int y) {
        super(x, y);

        maxHealth = 100;
        moveSpeed = 1;
        melee = false;
        meleeDamage = 0;
        meleeRate = 60;
        projectileRate = SpearProjectile.projectileRate; // later
        aggroRadius = 0;

        health = maxHealth;
        size = 32;
        sprite = player_down;
        animatedSprite = player_down;
        animatedSpriteDown = player_down;
        animatedSpriteUp = player_up;
        animatedSpriteLeft = player_left;
        animatedSpriteRight = player_right;
    }

    public void update() {
        super.update();
    }

    //TODO this might not have to override the super
    public void render(Screen screen) {
        screen.fillRect((int) x, (int) y, size, size, Color.BLUE.getRGB(), true);
    }


    @Override
    protected void shoot(double x, double y, double dir) {
    }
}
