package com.brad.latch.entity.projectile;

import com.brad.latch.entity.mob.Mob;
import com.brad.latch.entity.spawner.ParticleSpawner;
import com.brad.latch.graphics.Screen;
import com.brad.latch.graphics.Sprite;
import com.brad.latch.level.tile.Tile;

public class SpearProjectile extends Projectile {

    public static int fireRate = 15;

    public SpearProjectile(double xOrigin, double yOrigin, double angle, Mob shooter) {
        super(xOrigin, yOrigin, angle, shooter);
        range = 100 + random.nextInt(50);
        speed = 1.5;
        damage = 20;
        fireRate = 15;
        sprite = Sprite.rotate(projectile_spear, angle);
        xDelta = speed * Math.cos(angle);
        yDelta = speed * Math.sin(angle);
    }



}
