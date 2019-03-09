package com.brad.rain.entity.projectile;

import com.brad.rain.graphics.Screen;
import com.brad.rain.graphics.SpriteCollection;
import com.brad.rain.level.tile.Tile;

public class SpearProjectile extends Projectile {

    public SpearProjectile(int xOrigin, int yOrigin, double angle) {
        super(xOrigin, yOrigin, angle);
        range = 100 + random.nextInt(50);
        speed = 1;
        damage = 20;
        rateOfFire = 15;
        sprite = SpriteCollection.projectile_spear;
        xMove = speed * Math.cos(angle);
        yMove = speed * Math.sin(angle);
    }

    public void update() {
        move();
    }

    protected void move() {
        x += xMove;
        y += yMove;
        if (getDistance() > range) remove();
    }

    public void render(Screen screen) {
        screen.renderProjectile((int) x - Tile.getTileSizeDiv2(), (int) y - Tile.getTileSizeDiv2(), this);
    }

}
