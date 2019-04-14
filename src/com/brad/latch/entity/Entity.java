package com.brad.latch.entity;

import com.brad.latch.CustomRenderingEngine;
import com.brad.latch.graphics.Screen;
import com.brad.latch.graphics.Sprite;
import com.brad.latch.level.Level;
import com.brad.latch.level.tile.Tile;

import java.util.Random;

public abstract class Entity implements CustomRenderingEngine {

    protected double x, y;
    protected double xRelativeToScreen, yRelativeToScreen;
    protected Sprite sprite;
    private boolean removed = false;
    protected Level level;
    protected final Random random = new Random();

    public Entity() {

    }

    public Entity(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Entity(int x, int y, Sprite sprite) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
    }

    public abstract void update();

    public void render(Screen screen) {
        if (sprite != null) screen.renderSprite((int) x, (int) y, sprite, true);
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

}
