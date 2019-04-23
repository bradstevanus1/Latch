package com.brad.latch.entity.projectile;

import com.brad.latch.Game;
import com.brad.latch.entity.Entity;
import com.brad.latch.entity.mob.Mob;
import com.brad.latch.entity.spawner.ParticleSpawner;
import com.brad.latch.graphics.Screen;
import com.brad.latch.graphics.Sprite;
import com.brad.latch.level.tile.Tile;

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

    public Mob getShooter() {
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

    protected int getDistance() {
        return (int) Math.sqrt( (x - xOrigin)*(x - xOrigin) + (y - yOrigin)*(y - yOrigin) );
    }

    public void update() {
        if (level.tileCollision((int) (x + xDelta), (int) (y + yDelta), size, 5, 2)) {
            // 420, 50
            ParticleSpawner projectileHit = new ParticleSpawner((int) x, (int) y);
            level.add(projectileHit);
            projectileHit.spawn(50, Game.getParticleLife());
            projectileHit.remove();
            remove();
        }
        move();
    }

    protected void move() {
        x += xDelta;
        y += yDelta;
        if (getDistance() > range) remove();
    }

    public void render(Screen screen) {
        screen.renderProjectile((int) x - Tile.getTileSize()/2, (int) y - Tile.getTileSize()/2, this);
    }
}
