package com.brad.latch.entity.mob.enemy;

import com.brad.latch.entity.mob.Mob;
import com.brad.latch.entity.mob.Player;
import com.brad.latch.graphics.Sprite;

import java.util.List;

public abstract class Chaser extends Mob {

    public Chaser(int x, int y) {
        super(x, y);
    }

    @Override
    public void update() {
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
        updateHealth();
        updateDamagedTargets();

    }

}
