package com.brad.latch.entity.mob;

import com.brad.latch.graphics.Screen;
import com.brad.latch.graphics.SpriteCollection;

public class Traveller extends Mob {

    public Traveller(int x, int y, int moveSpeed) {
        super(x << 4, y << 4, SpriteCollection.straggler_down.getSprite(), moveSpeed);
        animatedSprite = SpriteCollection.player_down;
    }

    @Override
    public void update() {
        updateNPCMovement(
                SpriteCollection.straggler_up,
                SpriteCollection.straggler_down,
                SpriteCollection.straggler_left,
                SpriteCollection.straggler_right
        );
    }

    @Override
    public void render(Screen screen) {
        sprite = animatedSprite.getSprite();
        screen.renderMob(x, y, sprite, 0);
    }

}
