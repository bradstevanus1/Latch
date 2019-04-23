package com.brad.latch.entity.projectile;

import com.brad.latch.entity.Entity;
import com.brad.latch.entity.mob.Mob;
import com.brad.latch.graphics.Sprite;

import java.util.Random;

public abstract class Projectile extends Entity {

    private final Mob shooter;
    private final double xOrigin, yOrigin;
    private double angle;
    protected Sprite sprite;
    double xDelta, yDelta;
    protected double distance;
    protected double speed;
    protected double range;
    protected int damage;
    public static int size = 10;

    protected final Random random = new Random();

    public Projectile(double xOrigin, double yOrigin, double angle, Mob shooter) {
        this.xOrigin = xOrigin;
        this.yOrigin = yOrigin;
        this.angle = angle;
        this.x = xOrigin;
        this.y = yOrigin;
        this.shooter = shooter;
    }

    public abstract void update();

    public Entity getShooter() {
        return shooter;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
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
