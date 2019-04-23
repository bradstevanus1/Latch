package com.brad.latch.entity;

import com.brad.latch.CustomRenderingEngine;
import com.brad.latch.entity.mob.Mob;
import com.brad.latch.graphics.Screen;
import com.brad.latch.graphics.Sprite;
import com.brad.latch.graphics.Sprites;
import com.brad.latch.level.Level;

import java.util.ArrayList;
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

    protected ArrayList<Mob> damagedMobs = new ArrayList<>(); // Mobs that this entity has damaged
    protected double attackInvincTime;
    protected int invincTimer; // How long it takes before a mob is damageable again
    protected int attackTimer;

    public Entity() {

    }

    public Entity(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Entity(int x, int y, Sprite sprite) {
        this.x = x;
        this.y = y;
    }

    public Entity(int x, int y, Sprite sprite, double attackInvincTime) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
        this.attackInvincTime = attackInvincTime;
    }

    public void updateDamagedTargets() {
        invincTimer++;
        if (invincTimer == (int) (attackInvincTime * 60)) {
            damagedMobs.clear();
            invincTimer = 0;
        }
    }

    // Remove from level
    public void remove() {
        removed = true;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public ArrayList<Mob> getDamagedMobs() {
        return damagedMobs;
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
