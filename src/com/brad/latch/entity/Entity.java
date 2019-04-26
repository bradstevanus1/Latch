package com.brad.latch.entity;

import com.brad.latch.CustomRenderingEngine;
import com.brad.latch.Game;
import com.brad.latch.entity.mob.Mob;
import com.brad.latch.entity.projectile.Projectile;
import com.brad.latch.entity.spawner.ParticleSpawner;
import com.brad.latch.graphics.Screen;
import com.brad.latch.graphics.Sprite;
import com.brad.latch.graphics.Sprites;
import com.brad.latch.level.Level;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.brad.latch.util.MathUtils.*;

/**
 * Entities are objects that have a position on the level and can be updated.
 * Additionally, they may have a sprite and they may be renderable.
 */
public abstract class Entity implements CustomRenderingEngine, Sprites {

    // Attributes
    protected int maxHealth;
    protected boolean melee;
    protected int meleeDamage;
    protected double meleeRate; // Times per second

    // Technical fields
    protected int health;
    protected double x, y;
    protected double xRelativeToScreen, yRelativeToScreen;
    protected Sprite sprite;
    private boolean removed = false;
    protected Level level;
    protected final Random random = new Random();
    protected int time = 0;
    protected static int size;
    protected Map<Entity, Integer> damagedEntities = new HashMap<>(); // Entities that this entity has damaged


    public Entity() {

    }

    public Entity(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void update() {
        updateHealth();
        updateDamagedTargets();
    }

    public void render(Screen screen) {
        if (sprite != null) screen.renderSprite((int) x, (int) y, sprite, true);
    }


    /**
     * Checks to see if the mob should take damage this tick.
     * Damage could come from projectiles or melee.
     */
    protected void updateHealth() {
        // Check if the mob is colliding with a projectile that can damage them.
        for (Projectile projectile : level.getProjectilesInRange(this, Mob.size*2)) {
            if (projectile.getShooter().equals(this) || projectile.damagedEntities.containsKey(this)) continue;
            if (inRange(this, projectile, 8)) {
                takeDamage(projectile.getDamage());
                projectile.damagedEntities.put(this, -1);

            }
        }
        if (health < 0) health = 0;
    }

    protected void takeDamage(final int damage) {
        int initialHealth = health;
        health -= damage;
        int healthLost = Math.abs(health - initialHealth);
        int particleAmount = max(percentageOf(healthLost, maxHealth), 20);
        ParticleSpawner particleSpawner = new ParticleSpawner((int) x, (int) y);
        level.add(particleSpawner);
        if (this instanceof Mob) {
            particleSpawner.spawn(particleAmount, Game.getParticleLife(), particle_blood);
        } else {
            particleSpawner.spawn(particleAmount, Game.getParticleLife(), particle_blood);
        }
        particleSpawner.remove();
    }

    /**
     * Removes mobs from the list of damaged targets
     * if this mob's attacks' invicinbility timer has expired.
     */
    public void updateDamagedTargets() {
        // Remove all pairs in the hashMap with the value zero,
        // freeing up mobs to be attacked again.
        damagedEntities.values().removeAll(Collections.singleton(0));

        // Decrement every value (timer) in the array. This
        // creates individual timers for each mob based on when they were hit.
        for (Map.Entry<Entity, Integer> entity : damagedEntities.entrySet()) {
            entity.setValue(entity.getValue() - 1);
        }

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

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public boolean isMelee() {
        return melee;
    }

    public void setMelee(boolean melee) {
        this.melee = melee;
    }

    public int getMeleeDamage() {
        return meleeDamage;
    }

    public void setMeleeDamage(int meleeDamage) {
        this.meleeDamage = meleeDamage;
    }

    public double getMeleeRate() {
        return meleeRate;
    }

    public void setMeleeRate(double meleeRate) {
        this.meleeRate = meleeRate;
    }

    public Map<Entity, Integer> getDamagedEntities() {
        return damagedEntities;
    }

    public void setDamagedEntities(Map<Entity, Integer> damagedEntities) {
        this.damagedEntities = damagedEntities;
    }
}
