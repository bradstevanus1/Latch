package com.brad.latch.entity.mob.enemy;

public class Halbird extends Shooter {

    public Halbird(int x, int y) {
        super(x << 4, y << 4, pokey_down.getSprite(), 0.7);
        name = "Hal Bird";
        aggroRadius = 100;  // Range at which straggler will follow the player
        size = 32;
        animatedSprite = pokey_down;
        animatedSpriteDown = pokey_down;
        animatedSpriteUp = pokey_up;
        animatedSpriteLeft = pokey_left;
        animatedSpriteRight = pokey_right;
    }

}
