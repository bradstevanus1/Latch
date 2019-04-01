package com.brad.latch.entity.mob.enemy;

import com.brad.latch.entity.mob.Mob;
import com.brad.latch.entity.mob.Player;
import com.brad.latch.graphics.SpriteCollection;

public class Halbird extends Shooter {

    public Halbird(int x, int y) {
        super(x << 4, y << 4, SpriteCollection.pokey_down.getSprite(), 0.7);
        name = "Hal Bird";
        aggroRadius = 100;  // Range at which straggler will follow the player
        size = 32;
        animatedSprite = SpriteCollection.pokey_down;
        animatedSpriteDown = SpriteCollection.pokey_down;
        animatedSpriteUp = SpriteCollection.pokey_up;
        animatedSpriteLeft = SpriteCollection.pokey_left;
        animatedSpriteRight = SpriteCollection.pokey_right;
    }

}
