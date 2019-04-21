package com.brad.latch.entity.projectile;

import com.brad.latch.entity.Entity;
import com.brad.latch.graphics.Sprite;

import java.util.Random;

public abstract class Projectile extends Entity {

    private final double xOrigin, yOrigin;
    protected double x, y;
    private double angle;
    protected Sprite sprite;
    double xDelta, yDelta;
    protected double distance;
    double speed, range, damage;

    protected final Random random = new Random();

    public Projectile(double xOrigin, double yOrigin, double angle) {
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
