package com.brad.latch.entity.mob.enemy;

import com.brad.latch.entity.Entity;
import com.brad.latch.entity.mob.player.Player;
import com.brad.latch.entity.projectile.Projectile;
import com.brad.latch.entity.projectile.SpearProjectile;
import com.brad.latch.level.tile.TileCoordinate;
import com.brad.latch.util.Vector2i;

import java.util.List;

public class Shooter extends Enemy {

    protected Entity randomPlayer = null;

    public Shooter(TileCoordinate tileCoordinate) {
        super(tileCoordinate);
    }

    @Override
    public void update() {
        super.update();

        if (projectileTimer > 0) projectileTimer--;
        time++;
        shootRandomPlayer();
    }

    private void shootRandomPlayer() {
        if (time % (60 + random.nextInt(61)) == 0) {
            List<Player> players = level.getPlayersInRange(this, aggroRadius);
            if (players.size() != 0) {
                int index = random.nextInt(players.size());
                randomPlayer = players.get(index);
            } else {
                randomPlayer = null;
            }
        }
        shootAt(randomPlayer);
    }

    private void shootClosestPlayer() {
        List<Player> players = level.getPlayersInRange(this, aggroRadius);
        double min = 0;
        Player closestPlayer = null;
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            double distance = new Vector2i((int) x, (int) y).distanceTo(new Vector2i((int) player.getX(), (int) player.getY()));
            if (distance < min || i == 0) {
                min = distance;
                closestPlayer = player;
            }
        }
        shootAt(closestPlayer);
    }

    protected void shootAt(Entity entity) {
        if (entity != null && projectileTimer <= 0) {
            double dx = entity.getX() - x;
            double dy = entity.getY() - y;
            double dir = Math.atan2(dy, dx);
            shoot(x, y, dir);
            projectileTimer = (int) (60 / projectileRate);
        }
    }

    @Override
    protected void shoot(double x, double y, double dir) {
        Projectile p = new SpearProjectile(x, y, dir, this);
        level.add(p);
    }

    public static Shooter halbird(TileCoordinate tileCoordinate) {
        Shooter mob = new Shooter(tileCoordinate);

        mob.health = 500;
        mob.maxHealth = mob.health;
        mob.melee = true;
        mob.meleeDamage = 20;
        mob.aggroRadius = 200;
        mob.projectileRate = SpearProjectile.projectileRate;
        mob.moveSpeed = 0.7;
        mob.meleeRate = 1.0;

        mob.name = "Hal Bird";
        mob.sprite = pokey_down.getSprite();
        size = 32;
        mob.animatedSprite = pokey_down;
        mob.animatedSpriteDown = pokey_down;
        mob.animatedSpriteUp = pokey_up;
        mob.animatedSpriteLeft = pokey_left;
        mob.animatedSpriteRight = pokey_right;

        return mob;
    }
}
