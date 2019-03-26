package com.brad.latch.entity.mob;

import com.brad.latch.graphics.Screen;
import com.brad.latch.graphics.SpriteCollection;
import com.brad.latch.level.tile.TileCollection;

// TODO Make this extend an intermediary "chaser" class with static members like aggroRange

public class Straggler extends Mob {

    public Straggler(int x, int y) {
        // Converts from pixel precision to tile precision
        super(x << 4, y << 4, SpriteCollection.straggler_down.getSprite(), 0.5);
        aggroRange = 70;  // Range at which straggler will follow the player
        size = 32;
        animatedSprite = SpriteCollection.straggler_down;
    }

    @Override
    public void update() {
        updateChaserMovement(
                SpriteCollection.straggler_up,
                SpriteCollection.straggler_down,
                SpriteCollection.straggler_left,
                SpriteCollection.straggler_right
        );
    }

    @Override
    public void render(Screen screen) {
        sprite = animatedSprite.getSprite();
        screen.renderMob((int) (x - size/2), (int) (y - size/2), this);
    }
}
