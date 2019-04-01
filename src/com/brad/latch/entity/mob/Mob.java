package com.brad.latch.entity.mob;

import com.brad.latch.entity.Entity;
import com.brad.latch.entity.projectile.Projectile;
import com.brad.latch.entity.projectile.SpearProjectile;
import com.brad.latch.graphics.AnimatedSprite;
import com.brad.latch.graphics.Screen;
import com.brad.latch.graphics.Sprite;

public abstract class Mob extends Entity {

    protected double xDelta = 0; // Speed in x
    protected double yDelta = 0; // Speed in y
    protected boolean moving = false;
    protected double moveSpeed;
    protected Direction dir;
    protected int time = 0;
    protected static int size;
    protected int aggroRadius;
    protected String name;

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

    public Mob(int x, int y, double moveSpeed) {
        super(x, y);
        this.moveSpeed = moveSpeed;
    }

    public Mob(int x, int y, Sprite sprite, double moveSpeed) {
        super(x, y, sprite);
        this.moveSpeed = moveSpeed;
    }

    public abstract void update();

    protected abstract void shoot(double x, double y, double dir);

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


    @SuppressWarnings("Duplicates")
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

}
