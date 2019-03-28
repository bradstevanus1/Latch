package com.brad.latch.entity.mob.peaceful;

import com.brad.latch.graphics.SpriteCollection;

public class Traveller extends Peaceful {

    public Traveller(int x, int y) {
        super(x << 4, y << 4, SpriteCollection.traveller_down.getSprite(), 0.8);
        size = 32;
        animatedSprite = SpriteCollection.traveller_down;
        animatedSpriteDown = SpriteCollection.traveller_down;
        animatedSpriteUp = SpriteCollection.traveller_up;
        animatedSpriteLeft = SpriteCollection.traveller_left;
        animatedSpriteRight = SpriteCollection.traveller_right;
    }

}
