package com.brad.rain.entity.projectile;

import com.brad.rain.entity.Entity;
import com.brad.rain.graphics.Sprite;

public abstract class Projectile extends Entity {

    protected final int xOrigin, yOrigin;
    protected double angle;
    protected Sprite sprite;
    protected double xMove, yMove;
    protected double speed, rateOfFire, range, damage;

    public Projectile(int xOrigin, int yOrigin, double angle) {
        this.xOrigin = xOrigin;
        this.yOrigin = yOrigin;
        this.angle = angle;
        this.x = xOrigin;
        this.y = yOrigin;
    }

    protected void move() {

    }

}
