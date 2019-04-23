package com.brad.latch.entity.mob;

import com.brad.latch.entity.Entity;
import com.brad.latch.entity.projectile.Projectile;
import com.brad.latch.graphics.AnimatedSprite;
import com.brad.latch.graphics.Screen;
import com.brad.latch.graphics.Sprite;

import static com.brad.latch.util.MathUtils.*;

/**
 * Mobs are entities that must have sprites, be renderable, and that
 * will appears as the actual characters in the game.
 */
public abstract class Mob extends Entity {

    // Variable mob attributes
    protected String name;
    protected int health;
    protected boolean hasMelee;
    protected int meleeDamage;
    protected int aggroRadius;
    protected int fireRate;
    protected double moveSpeed;

    // Technical fields
    protected double xDelta = 0; // Speed in x
    protected double yDelta = 0; // Speed in y
    protected boolean moving = false;
    protected Direction dir;
    protected static int size;

    protected AnimatedSprite animatedSprite;
    protected AnimatedSprite animatedSpriteDown;
    protected AnimatedSprite animatedSpriteUp;
    protected AnimatedSprite animatedSpriteLeft;
    protected AnimatedSprite animatedSpriteRight;

    protected enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    public Mob(double moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

    public Mob(int x, int y) {
        super(x, y);
    }

    public abstract void update();

    protected abstract void shoot(double x, double y, double dir);

    /**
     * Sets important mob attributes.
     * @param health            The mob's health.
     * @param hasMelee          If the mob can use melee attacks.
     * @param meleeDamage       Damage the mob inflicts with melee attacks.
     * @param aggroRadius       Radius at which the mob will attack an enemy.
     * @param fireRate          Rate at which the mob fires
     * @param moveSpeed         Speed at which the mob moves.
     * @param attackInvincTime  How long the mob leaves enemies invincible for.
     */
    public void setAttributes(int health, boolean hasMelee, int meleeDamage, int aggroRadius,
                              int fireRate, double moveSpeed, double attackInvincTime) {
        this.health = health;
        this.hasMelee = hasMelee;
        this.meleeDamage = meleeDamage;
        this.aggroRadius = aggroRadius;
        this.fireRate = fireRate;
        this.moveSpeed = moveSpeed;
        this.attackInvincTime = attackInvincTime;
    }

    /**
     * Checks to see if the mob should take damage this tick.
     * Damage could come from projectiles or melee.
     */
    protected void updateHealth() {
        // Check if the player is colliding with a projectile that can damage them.
        for (Projectile projectile : level.getProjectiles()) {
            if (projectile.getShooter().getDamagedMobs().contains(this) || projectile.getShooter().equals(this)) {
                continue;
            }
            if (inRange((int) x, (int) projectile.getX(), 8) &&
                    inRange((int) y, (int) projectile.getY(), 8)) {
                health -= projectile.getDamage();
                projectile.getShooter().getDamagedMobs().add(this);
            }
        }

        // Check if the player is colliding with another mob that can damage them.
        for (Entity entity : level.getEntitiesInRange(this, size)) {
            if (!(entity instanceof Mob)) continue;
            Mob mob = (Mob) entity;
            if (!mob.hasMelee || mob.getDamagedMobs().contains(this)) continue;
            if (inRange((int) x, (int) mob.getX(), size) &&
                    inRange((int) y, (int) mob.getY(), size)) {
                health -= mob.meleeDamage;
                mob.getDamagedMobs().add(this);
            }
        }

        if (health < 0) health = 0;
    }

    /**
     * Moves the mob in the amount specified in x and y.
     * Collision checking is done in micro-movements so that
     * collision detectionworks well with a small amount of
     * updates per second.
     * @param xDelta Amount to move in the x direction.
     * @param yDelta Amount to move in the y direction.
     */
    public void move(double xDelta, double yDelta) {
        // Use this method, or separate the
        // collision statement below.
        if (xDelta != 0 && yDelta != 0) {
            move(xDelta, 0);
            move(0, yDelta);
            return;
        }

        if (xDelta > 0) dir = Direction.RIGHT;
        else if (xDelta < 0) dir = Direction.LEFT;
        if (yDelta > 0) dir = Direction.DOWN;
        else if (yDelta < 0) dir = Direction.UP;

        while (xDelta != 0) {
            if (Math.abs(xDelta) > 1) {
                if (!collision(absNorm(xDelta), 0)) {
                    x += absNorm(xDelta);
                }
                xDelta -= absNorm(xDelta);
            } else {
                if (!collision(absNorm(xDelta), 0)) {
                    x += xDelta;
                }
                xDelta = 0;
            }
        }
        while (yDelta != 0) {
            if (Math.abs(yDelta) > 1) {
                if (!collision(0, absNorm(yDelta))) {
                    y += absNorm(yDelta);
                }
                yDelta -= absNorm(yDelta);
            } else {
                if (!collision(0, absNorm(yDelta))) {
                    y += yDelta;
                }
                yDelta = 0;
            }
        }
    }

    private int absNorm(double value) {
        if (value < 0) return -1;
        else if (value > 0) return 1;
        else return 0;
    }

    /**
     * Causes the entity to move around randomly,
     * frequently stopping.
     */
    protected void randomDirection() {
        if (time % (random.nextInt(50) + 30) == 0) {
            xDelta = (random.nextInt(3) - 1) * moveSpeed;
            yDelta = (random.nextInt(3) - 1) * moveSpeed;
            if (random.nextInt(3) == 0) {
                xDelta = 0;
                yDelta = 0;
            }
            //  Random movements cannot be diagonal
            if ((xDelta != 0) && (yDelta != 0)) {
                if (random.nextBoolean()) xDelta = 0;
                else yDelta = 0;
            }
        }
    }

    protected void moveToDirection() {
        if (yDelta < 0) {
            dir = Direction.UP;
            animatedSprite = animatedSpriteUp;
        } else if (yDelta > 0) {
            dir = Direction.DOWN;
            animatedSprite = animatedSpriteDown;
        }
        if (xDelta < 0) {
            dir = Direction.LEFT;
            animatedSprite = animatedSpriteLeft;
        } else if (xDelta > 0) {
            dir = Direction.RIGHT;
            animatedSprite = animatedSpriteRight;
        }
        if (xDelta != 0 || yDelta != 0) {
            move(xDelta, yDelta);
            moving = true;
        } else {
            moving = false;
        }
        if (moving) animatedSprite.update();
        else animatedSprite.setFrame(0);
    }


    @SuppressWarnings({"Duplicates", "BooleanMethodIsAlwaysInverted"})
    private boolean collision(double xDelta, double yDelta) {
        boolean solid = false;
        for (byte c = 0; c < 4; c++) {
            double nextTileX = ((x + xDelta) - c % 2 * 15) / 16;
            double nextTileY = ((y + yDelta) - c / 2.0 * 15) / 16;
            int intNextTileX = (c % 2 == 0) ? (int) Math.floor(nextTileX) : (int) Math.ceil(nextTileX);
            int intNextTileY = (c / 2 == 0) ? (int) Math.floor(nextTileY) : (int) Math.ceil(nextTileY);
            if (level.getTile(intNextTileX, intNextTileY).solid()) solid = true;
        }
        return solid;
    }

    @Override
    public void render(Screen screen) {
        sprite = animatedSprite.getSprite();
        screen.renderMob((int) (x - size/2), (int) (y - size/2), this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
