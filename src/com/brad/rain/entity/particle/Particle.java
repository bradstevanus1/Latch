package com.brad.rain.entity.particle;

import com.brad.rain.entity.Entity;
import com.brad.rain.graphics.Screen;
import com.brad.rain.graphics.Sprite;
import com.brad.rain.graphics.SpriteCollection;

public class Particle extends Entity {

    // TODO create subclasses of particle that use a different sprite

    private Sprite sprite;
    protected int life;
    protected double xDouble, yDouble;
    protected double xDelta, yDelta;

    // Constructor for a particle
    public Particle(int x, int y, int life) {
        this.x = x;
        this.y = y;
        this.xDouble = x;
        this.yDouble = y;
        this.life = life;
        sprite = SpriteCollection.particle_normal;

        // Sets the distance delta for the particle to travel to a
        // random (bell-curved) value in the range -1 to 1
        this.xDelta = random.nextGaussian();
        this.yDelta = random.nextGaussian();
    }

    @Override
    public void update() {
        this.xDouble += xDelta;
        this.yDouble += yDelta;
    }

    @Override
    public void render(Screen screen) {
        screen.renderSprite((int) xDouble, (int) yDouble, sprite, true);
    }

}
