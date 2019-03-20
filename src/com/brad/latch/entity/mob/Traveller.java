package com.brad.latch.entity.mob;

import com.brad.latch.graphics.AnimatedSprite;
import com.brad.latch.graphics.Screen;
import com.brad.latch.graphics.Sprite;
import com.brad.latch.graphics.SpriteCollection;

public class Traveller extends Mob {

    private AnimatedSprite animatedSprite = SpriteCollection.straggler_down;
    private int time = 0;
    int xa = 0;
    int ya = 0;

    public Traveller(int x, int y, int moveSpeed) {
        super(x << 4, y << 4, SpriteCollection.straggler_down.getSprite(), moveSpeed);
    }

    @Override
    public void update() {
        time++;
        if (time % 60 == 0) {

        }
        if (moving) animatedSprite.update();
        else animatedSprite.setFrame(0);
        if (ya < 0) {
            dir = Direction.UP;
            animatedSprite = SpriteCollection.straggler_up;
        } else if (ya > 0) {
            dir = Direction.DOWN;
            animatedSprite = SpriteCollection.straggler_down;
            ya = ya + moveSpeed;
        }
        if (xa < 0) {
            dir = Direction.LEFT;
            animatedSprite = SpriteCollection.straggler_left;
        } else if (xa > 0) {
            dir = Direction.RIGHT;
            animatedSprite = SpriteCollection.straggler_right;
        }
        if (xa != 0 || ya != 0) {
            move(xa, ya);
            moving = true;
        } else {
            moving = false;
        }
    }

    @Override
    public void render(Screen screen) {
        sprite = animatedSprite.getSprite();
        screen.renderMob(x, y, sprite, 0);
    }

}
