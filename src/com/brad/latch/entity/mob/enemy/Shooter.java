package com.brad.latch.entity.mob.enemy;

import com.brad.latch.entity.Entity;
import com.brad.latch.entity.mob.Mob;
import com.brad.latch.entity.projectile.Projectile;
import com.brad.latch.entity.projectile.SpearProjectile;
import com.brad.latch.graphics.Sprite;
import com.brad.latch.util.Vector2i;

import java.util.List;

public abstract class Shooter extends Mob {

    protected Entity randomEntity = null;

    public Shooter(int x, int y) {
        super(x, y);
    }

    @Override
    public void update() {
        if (attackTimer > 0) attackTimer--;
        time++;
        shootRandom();
        updateHealth();
        updateDamagedTargets();
    }

    private void shootRandom() {
        if (time % (60 + random.nextInt(61)) == 0) {
            List<Entity> entities = level.getEntitiesInRange(this, aggroRadius);
            entities.addAll(level.getPlayersInRange(this, aggroRadius));
            if (entities.size() != 0) {
                int index = random.nextInt(entities.size());
                randomEntity = entities.get(index);
            } else {
                randomEntity = null;
            }
        }
        shootAt(randomEntity);
    }

    private void shootClosest() {
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
        shootAt(closestEntity);
    }

    protected void shootAt(Entity entity) {
        if (entity != null && attackTimer <= 0) {
            double dx = entity.getX() - x;
            double dy = entity.getY() - y;
            double dir = Math.atan2(dy, dx);
            shoot(x, y, dir);
            attackTimer = fireRate;
        }
    }

    @Override
    protected void shoot(double x, double y, double dir) {
        Projectile p = new SpearProjectile(x, y, dir, this);
        level.add(p);
    }
}
