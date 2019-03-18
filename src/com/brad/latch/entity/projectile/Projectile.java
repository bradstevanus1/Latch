package com.brad.latch.entity.projectile;

import com.brad.latch.entity.Entity;
import com.brad.latch.graphics.Sprite;

import java.util.Random;

public abstract class Projectile extends Entity {

    protected double x, y;
    protected final int xOrigin, yOrigin;
    protected double angle;
    protected Sprite sprite;
    protected double xDelta, yDelta;
    protected double distance;
    protected double speed, range, damage;

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
