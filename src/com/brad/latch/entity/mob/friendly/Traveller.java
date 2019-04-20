package com.brad.latch.entity.mob.friendly;


public class Traveller extends Peaceful {

    public Traveller(int x, int y) {
        super(x << 4, y << 4, traveller_down.getSprite(), 0.8);
        name = "Traveller";
        size = 32;
        animatedSprite = traveller_down;
        animatedSpriteDown = traveller_down;
        animatedSpriteUp = traveller_up;
        animatedSpriteLeft = traveller_left;
        animatedSpriteRight = traveller_right;
    }

    @Override
    protected void shoot(double x, double y, double dir) {

    }
}
