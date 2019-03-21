package com.brad.latch.entity.mob;

import com.brad.latch.graphics.AnimatedSprite;
import com.brad.latch.graphics.Screen;
import com.brad.latch.graphics.SpriteCollection;

public class Traveller extends Mob {

    private AnimatedSprite animatedSprite = SpriteCollection.straggler_down;
    private int time = 0;
    int xDelta = 0;
    int yDelta = 0;

    public Traveller(int x, int y, int moveSpeed) {
        super(x << 4, y << 4, SpriteCollection.straggler_down.getSprite(), moveSpeed);
    }

    @Override
    public void update() {
        // Basic NPC AI
        time++;
        if (time % (random.nextInt(50) + 30) == 0) {
            xDelta = random.nextInt(3) - 1;
            yDelta = random.nextInt(3) - 1;
            if (random.nextInt(3) == 0) {
                xDelta = 0;
                yDelta = 0;
            }
            if ((xDelta != 0) && (yDelta != 0)) {
                if (random.nextBoolean()) xDelta = 0;
                else yDelta = 0;
            }
        }
        else animatedSprite.setFrame(0);
        if (yDelta < 0) {
            dir = Direction.UP;
            animatedSprite = SpriteCollection.straggler_up;
        } else if (yDelta > 0) {
            dir = Direction.DOWN;
            animatedSprite = SpriteCollection.straggler_down;
        }
        if (xDelta < 0) {
            dir = Direction.LEFT;
            animatedSprite = SpriteCollection.straggler_left;
        } else if (xDelta > 0) {
            dir = Direction.RIGHT;
            animatedSprite = SpriteCollection.straggler_right;
        }
        if (xDelta != 0 || yDelta != 0) {
            move(xDelta, yDelta);
            moving = true;
        } else {
            moving = false;
        }
        if (moving) animatedSprite.update();
    }

    @Override
    public void render(Screen screen) {
        sprite = animatedSprite.getSprite();
        screen.renderMob(x, y, sprite, 0);
    }

}
