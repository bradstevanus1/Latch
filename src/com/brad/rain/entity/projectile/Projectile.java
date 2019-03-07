package com.brad.rain.entity.projectile;

import com.brad.rain.entity.Entity;
import com.brad.rain.graphics.Sprite;

import java.util.Random;

public abstract class Projectile extends Entity {

    protected double x, y;
    protected final int xOrigin, yOrigin;
    protected double angle;
    protected Sprite sprite;
    protected double xMove, yMove;
    protected double distance;
    protected double speed, rateOfFire, range, damage;

    protected final Random random = new Random();

    public Projectile(int xOrigin, int yOrigin, double angle) {
        this.xOrigin = xOrigin;
        this.yOrigin = yOrigin;
        this.angle = angle;
        this.x = xOrigin;
        this.y = yOrigin;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public int getSpriteSize() {
        return sprite.SIZE;
    }

    protected void move() {

    }

    protected int getDistance() {
        return (int) Math.sqrt( (x - xOrigin)*(x - xOrigin) + (y - yOrigin)*(y - yOrigin) );
    }

}
