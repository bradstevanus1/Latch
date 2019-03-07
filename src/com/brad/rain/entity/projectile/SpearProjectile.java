package com.brad.rain.entity.projectile;

import com.brad.rain.graphics.Screen;
import com.brad.rain.graphics.SpriteCollection;

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
        screen.renderProjectile((int) x - 8, (int) y - 8, this);
    }

}
