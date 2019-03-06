package com.brad.rain.entity.projectile;

import com.brad.rain.graphics.Screen;
import com.brad.rain.graphics.Sprite;
import com.brad.rain.level.tile.Tile;

public class SpearProjectile extends Projectile {

    public SpearProjectile(int xOrigin, int yOrigin, double angle) {
        super(xOrigin, yOrigin, angle);
        range = 200;
        speed = 4;
        damage = 20;
        rateOfFire = 15;
        sprite = Sprite.grass;
        xMove = speed * Math.cos(angle);
        yMove = speed * Math.sin(angle);
    }

    public void update() {
        move();
    }

    protected void move() {
        x += xMove;
        y += yMove;
    }

    public void render(Screen screen) {
        screen.renderTile(x, y, sprite);
    }

}
