package com.brad.latch.entity.mob;

import com.brad.latch.entity.Entity;
import com.brad.latch.entity.projectile.Projectile;
import com.brad.latch.entity.projectile.SpearProjectile;
import com.brad.latch.graphics.Screen;
import com.brad.latch.graphics.Sprite;
import com.brad.latch.level.tile.Tile;

public abstract class Mob extends Entity {

    protected boolean moving = false;
    protected int moveSpeed;
    protected Direction dir;

    protected enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    public Mob(int moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

    public Mob(int x, int y, int moveSpeed) {
        super(x, y);
        this.moveSpeed = moveSpeed;
    }

    public Mob(int x, int y, Sprite sprite, int moveSpeed) {
        super(x, y, sprite);
        this.moveSpeed = moveSpeed;
    }

    public void move(int xa, int ya) {
        // Use this method, or separate the
        // collision statement below
        if (xa != 0 && ya != 0) {
            move(xa, 0);
            move(0, ya);
            return;
        }

        if (xa > 0) dir = Direction.RIGHT;
        if (xa < 0) dir = Direction.LEFT;
        if (ya > 0) dir = Direction.DOWN;
        if (ya < 0) dir = Direction.UP;

        if (!collision(xa, ya)) {
            x += xa;
            y += ya;
        }
    }

    PUBLIC THING DO MOVEMENT

    public abstract void update();

    public abstract void render(Screen screen);

    protected void shoot(int x, int y, double dir) {
        // dir = Math.toDegrees(dir);
        Projectile p = new SpearProjectile(x, y, dir);
        level.add(p);
    }

    private boolean collision(int xa, int ya) {
        boolean solid = false;
        for (byte c = 0; c < 4; c++) {
            int nextTileX = ((x + xa) + c % 2 * 14 - 8) >> Tile.getTileSizeExp2();
            int nextTileY = ((y + ya) + c / 2 * 12 + 3) >> Tile.getTileSizeExp2();
            if (level.getTile(nextTileX, nextTileY).solid()) solid = true;

        }
        return solid;
    }

}
