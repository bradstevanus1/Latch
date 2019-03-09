package com.brad.rain.entity.mob;

import com.brad.rain.entity.Entity;
import com.brad.rain.entity.projectile.Projectile;
import com.brad.rain.entity.projectile.SpearProjectile;
import com.brad.rain.graphics.Sprite;
import com.brad.rain.helpers.debug;
import com.brad.rain.input.Mouse;
import com.brad.rain.level.tile.Tile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Mob extends Entity {

    protected Sprite sprite;
    protected int dir = -1;
    protected boolean moving = false;
    protected boolean walking = false;
    protected int moveSpeed = 1;

    public void move(int xa, int ya) {
        // Use this method, or separate the
        // collision statement below
        if (xa != 0 && ya != 0) {
            move(xa, 0);
            move(0, ya);
            return;
        }

        if (xa > 0) dir = 1;
        if (xa < 0) dir = 3;
        if (ya > 0) dir = 2;
        if (ya < 0) dir = 0;

        if (!collision(xa, ya)) {
            x += xa;
            y += ya;
        }
    }

    public void update() {

    }

    protected void shoot(int x, int y, double dir) {
        // dir = Math.toDegrees(dir);
        Projectile p = new SpearProjectile(x, y, dir);
        level.addProjectile(p);
    }

    private boolean collision(int xa, int ya) {
        boolean solid = false;
        for (byte c = 0; c < 4; c++) {
            int nextTileX = ((x + xa) + c % 2 * 14 - 8) >> Tile.getTileSizeDiv4();
            int nextTileY = ((y + ya) + c / 2 * 12 + 3) >> Tile.getTileSizeDiv4();
            if (level.getTile(nextTileX, nextTileY).solid()) solid = true;

        }
        return solid;
    }

    public void render() {

    }

}
