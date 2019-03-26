package com.brad.latch.entity.mob;

import com.brad.latch.graphics.Screen;
import com.brad.latch.graphics.SpriteCollection;
import com.brad.latch.level.tile.TileCollection;

public class Straggler extends Mob {

    public Straggler(int x, int y, double moveSpeed) {
        // Converts from pixel precision to tile precision
        super(x << 4, y << 4, SpriteCollection.straggler_down.getSprite(), moveSpeed);
        animatedSprite = SpriteCollection.straggler_down;
        aggroRange = 100;  // Range at which straggler will follow the player
        size = 32;
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
