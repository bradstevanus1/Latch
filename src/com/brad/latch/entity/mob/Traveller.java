package com.brad.latch.entity.mob;

import com.brad.latch.graphics.Screen;
import com.brad.latch.graphics.SpriteCollection;

public class Traveller extends Mob {

    public Traveller(int x, int y) {
        super(x << 4, y << 4, SpriteCollection.traveller_down.getSprite(), 0.8);
        size = 32;
        animatedSprite = SpriteCollection.traveller_down;
        animatedSpriteDown = SpriteCollection.traveller_down;
        animatedSpriteUp = SpriteCollection.traveller_up;
        animatedSpriteLeft = SpriteCollection.traveller_left;
        animatedSpriteRight = SpriteCollection.traveller_right;
    }

    @Override
    public void update() {
        updateNPCMovement();
    }

    @Override
    public void render(Screen screen) {
        sprite = animatedSprite.getSprite();
        screen.renderMob((int) (x - size/2), (int) (y - size/2), this);
    }

}
