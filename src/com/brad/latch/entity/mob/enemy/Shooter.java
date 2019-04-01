package com.brad.latch.entity.mob.enemy;

import com.brad.latch.entity.Entity;
import com.brad.latch.entity.mob.Mob;
import com.brad.latch.entity.mob.Player;
import com.brad.latch.entity.projectile.Projectile;
import com.brad.latch.entity.projectile.SpearProjectile;
import com.brad.latch.graphics.Sprite;
import com.brad.latch.util.Vector2i;

import java.util.List;

public class Shooter extends Mob {

    public Shooter(int x, int y, Sprite sprite, double moveSpeed) {
        super(x, y, sprite, moveSpeed);
    }

    @Override
    public void update() {
        time++;
        //randomDirection();
        //moveToDirection();

        List<Entity> entities = level.getEntitiesInRange(this, aggroRadius);
        entities.addAll(level.getPlayersInRange(this, aggroRadius));

        double min = 0;
        Entity closestEntity = null;

        for (int i = 0; i < entities.size(); i++) {
            Entity entity = entities.get(i);
            double distance = new Vector2i((int) x, (int) y).distanceTo(new Vector2i((int) entity.getX(), (int) entity.getY()));
            if (distance < min || i == 0) {
                min = distance;
                closestEntity = entity;
            }
        }
        if (closestEntity != null) {
            double dx = closestEntity.getX() - x;
            double dy = closestEntity.getY() - y;
            double dir = Math.atan2(dy, dx);
            shoot(x, y, dir);
        }
    }

    @Override
    protected void shoot(double x, double y, double dir) {
        Projectile p = new SpearProjectile(x, y, dir);
        level.add(p);
    }
}
