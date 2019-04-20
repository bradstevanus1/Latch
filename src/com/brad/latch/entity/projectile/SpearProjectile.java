package com.brad.latch.entity.projectile;

import com.brad.latch.entity.spawner.ParticleSpawner;
import com.brad.latch.graphics.Screen;
import com.brad.latch.graphics.Sprite;
import com.brad.latch.level.tile.Tile;

public class SpearProjectile extends Projectile {

    public static int rateOfFire = 15; // Higher is slower
    public static int size = 10;

    public SpearProjectile(double xOrigin, double yOrigin, double angle) {
        super(xOrigin, yOrigin, angle);
        range = 100 + random.nextInt(50);
        speed = 1.5;
        damage = 20;
        sprite = Sprite.rotate(projectile_spear, angle);
        xDelta = speed * Math.cos(angle);
        yDelta = speed * Math.sin(angle);
    }

    public void update() {
        if (level.tileCollision((int) (x + xDelta), (int) (y + yDelta), size, 5, 2)) {
            level.add(new ParticleSpawner((int) x, (int) y, 420, 50, level));
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
        screen.renderProjectile((int) x - Tile.getTileSizeDiv2(), (int) y - Tile.getTileSizeDiv2(), this);
    }

}
