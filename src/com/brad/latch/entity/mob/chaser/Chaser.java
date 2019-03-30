package com.brad.latch.entity.mob.chaser;

import com.brad.latch.entity.mob.Mob;
import com.brad.latch.entity.mob.Player;
import com.brad.latch.graphics.Sprite;

import java.util.List;

public abstract class Chaser extends Mob {

    public Chaser(int x, int y, Sprite sprite, double moveSpeed) {
        super(x, y, sprite, moveSpeed);
    }

    @SuppressWarnings("Duplicates")
    @Override
    public void update() {
        time++;
        xDelta = 0;
        yDelta = 0;
        List<Player> playersInRange = level.getPlayersInRange(this, aggroRadius);
        if (playersInRange.size() > 0) {
            Player player = playersInRange.get(0);  // First player is client
            if (x < player.getX()) xDelta += moveSpeed;
            else if (x > player.getX()) xDelta -= moveSpeed;
            if (y < player.getY()) yDelta += moveSpeed;
            else if (y > player.getY()) yDelta -= moveSpeed;
        }

        if (yDelta < 0) {
            dir = Direction.UP;
            animatedSprite = animatedSpriteUp;
        } else if (yDelta > 0) {
            dir = Direction.DOWN;
            animatedSprite = animatedSpriteDown;
        }
        if (xDelta < 0) {
            dir = Direction.LEFT;
            animatedSprite = animatedSpriteLeft;
        } else if (xDelta > 0) {
            dir = Direction.RIGHT;
            animatedSprite = animatedSpriteRight;
        }
        if (xDelta != 0 || yDelta != 0) {
            move(xDelta, yDelta);
            moving = true;
        } else {
            moving = false;
        }
        if (moving) animatedSprite.update();
        else animatedSprite.setFrame(0);
    }

}
