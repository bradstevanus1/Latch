package com.brad.latch.entity;

import com.brad.latch.CustomRenderingEngine;
import com.brad.latch.graphics.Screen;
import com.brad.latch.graphics.Sprite;
import com.brad.latch.graphics.Sprites;
import com.brad.latch.level.Level;

import java.util.Random;

/**
 * Entities are objects that have a position on the level and can be updated.
 * Additionally, they may have a sprite and they may be renderable.
 */
public abstract class Entity implements CustomRenderingEngine, Sprites {

    protected double x, y;
    protected double xRelativeToScreen, yRelativeToScreen;
    protected Sprite sprite;
    private boolean removed = false;
    protected Level level;
    protected final Random random = new Random();
    protected int time = 0;

    public Entity() {

    }

    public Entity(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Remove from level
    public void remove() {
        removed = true;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public boolean isRemoved() {
        return removed;
    }

    public void init(Level level) {
        this.level = level;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public abstract void update();

    public void render(Screen screen) {
        if (sprite != null) screen.renderSprite((int) x, (int) y, sprite, true);
    }

}
