package com.brad.latch.entity.mob;

import com.brad.latch.graphics.Screen;
import com.brad.latch.graphics.SpriteCollection;
import com.brad.latch.level.tile.TileCollection;

public class Straggler extends Mob {

    public Straggler(int x, int y, int moveSpeed) {
        // Converts from pixel precision to tile precision
        super(x << 4, y << 4, SpriteCollection.player_up.getSprite(), moveSpeed);
    }

    @Override
    public void update() {
    }

    @Override
    public void render(Screen screen) {
        screen.renderMob(x, y, sprite, 0);
    }
}
