package com.brad.latch.entity.mob.enemy;

import com.brad.latch.entity.mob.player.Player;
import com.brad.latch.level.tile.TileCoordinate;

import java.util.List;

public class Chaser extends Enemy {

    public Chaser(final TileCoordinate tileCoordinate) {
        super(tileCoordinate);
    }

    @Override
    public void update() {
        super.update();

        time++;
        List<Player> playersInRange = level.getPlayersInRange(this, aggroRadius);
        if (playersInRange.size() > 0) {
            Player player = playersInRange.get(0);  // First player is client
            if ((int) x < (int) player.getX()) xDelta = moveSpeed;
            else if ((int) x > (int) player.getX()) xDelta = -moveSpeed;
            else xDelta = 0;
            if ((int) y < (int) player.getY()) yDelta = moveSpeed;
            else if ((int) y > (int) player.getY()) yDelta = -moveSpeed;
            else yDelta = 0;
        } else {  // Random movement if the player is not in range
            randomDirection();
        }
        moveToDirection();
    }

    @Override
    protected void shoot(double x, double y, double dir) {

    }

    public static Chaser straggler(TileCoordinate tileCoordinate) {
        Chaser mob = new Chaser(tileCoordinate);

        mob.health = 50;
        mob.maxHealth = mob.health;
        mob.melee = true;
        mob.meleeDamage = 5;
        mob.aggroRadius = 500;
        mob.projectileRate = 0;
        mob.moveSpeed = 0.5;
        mob.meleeRate = 1.0;

        mob.name = "Straggler";
        mob.sprite = straggler_down.getSprite();
        size = 32;
        mob.animatedSprite = straggler_down;
        mob.animatedSpriteDown = straggler_down;
        mob.animatedSpriteUp = straggler_up;
        mob.animatedSpriteLeft = straggler_left;
        mob.animatedSpriteRight = straggler_right;

        return mob;
    }

}
