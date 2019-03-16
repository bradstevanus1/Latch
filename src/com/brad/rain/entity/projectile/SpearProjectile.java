package com.brad.rain.entity.projectile;

import com.brad.rain.entity.particle.Particle;
import com.brad.rain.entity.spawner.ParticleSpawner;
import com.brad.rain.entity.spawner.Spawner;
import com.brad.rain.graphics.Screen;
import com.brad.rain.graphics.SpriteCollection;
import com.brad.rain.level.tile.Tile;

public class SpearProjectile extends Projectile {

    public static int rateOfFire = 15; // Higher is slower
    public static int size = 10;

    public SpearProjectile(int xOrigin, int yOrigin, double angle) {
        super(xOrigin, yOrigin, angle);
        range = 100 + random.nextInt(50);
        speed = 2;
        damage = 20;
        sprite = SpriteCollection.projectile_spear;
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
