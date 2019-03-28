package com.brad.latch.entity.mob.peaceful;

import com.brad.latch.entity.mob.Mob;
import com.brad.latch.graphics.Sprite;

public abstract class Peaceful extends Mob {

    public Peaceful(int x, int y, Sprite sprite, double moveSpeed) {
        super(x, y, sprite, moveSpeed);
    }

    @SuppressWarnings("Duplicates")
    @Override
    public void update() {
        time++;
        if (time % (random.nextInt(50) + 30) == 0) {
            xDelta = (random.nextInt(3) - 1) * moveSpeed;
            yDelta = (random.nextInt(3) - 1) * moveSpeed;
            if (random.nextInt(3) == 0) {
                xDelta = 0;
                yDelta = 0;
            }
            //  To ensure NPCs cannot move diagonally
            if ((xDelta != 0) && (yDelta != 0)) {
                if (random.nextBoolean()) xDelta = 0;
                else yDelta = 0;
            }
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
