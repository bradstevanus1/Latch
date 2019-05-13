package com.brad.latch.entity.mob.friendly;

import com.brad.latch.entity.mob.Mob;
import com.brad.latch.entity.mob.enemy.Enemy;
import com.brad.latch.graphics.Sprite;
import com.brad.latch.level.tile.TileCoordinate;

import static com.brad.latch.util.MathUtils.inRange;

public class Friendly extends Mob {

    public Friendly(TileCoordinate tileCoordinate) {
        super(tileCoordinate);
    }

    @Override
    public void update() {
        super.update();

        time++;
        randomDirection();
        moveToDirection();

        if (health == 0) {
            death();
        }
    }

    @Override
    protected void shoot(double x, double y, double dir) {

    }

    @Override
    @SuppressWarnings("Duplicates")
    protected void updateHealth() {
        super.updateHealth();
        for (Enemy enemy : level.getEnemiesInRange(this, Mob.size)) {
            if (!enemy.isMelee() || enemy.getDamagedEntities().containsKey(this)) continue;
            if (inRange(this, enemy, size)) {
                takeDamage(enemy.getMeleeDamage());
                enemy.getDamagedEntities().put(
                        this, (int) (60 / enemy.getMeleeRate()));
            }
        }
        if (health < 0) health = 0;
    }

    private void death() {
        remove();
    }

    public static Friendly Traveller(TileCoordinate tileCoordinate) {
        Friendly mob = new Friendly(tileCoordinate);

        mob.sprite = traveller_down.getSprite();
        mob.animatedSprite = traveller_down;
        mob.animatedSpriteDown = traveller_down;
        mob.animatedSpriteUp = traveller_up;
        mob.animatedSpriteLeft = traveller_left;
        mob.animatedSpriteRight = traveller_right;

        mob.health = 100;
        mob.maxHealth = mob.health;
        mob.setMoveSpeed(0.7);
        mob.melee = false;
        mob.meleeDamage = 0;
        mob.meleeRate = 1.0;
        mob.projectileRate = 0;
        mob.aggroRadius = 0;

        mob.name = "Traveller";
        size = 32;


        return mob;
    }

}
