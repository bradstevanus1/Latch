package com.brad.rain.entity.mob;

import com.brad.rain.entity.Entity;
import com.brad.rain.graphics.Sprite;
import com.brad.rain.input.Mouse;

public abstract class Mob extends Entity {

    protected Sprite sprite;
    protected int dir = -1;
    protected boolean moving = false;
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
        System.out.println("MouseX: " + Mouse.getX());
        System.out.println("Angle: " + dir);
    }

    private boolean collision(int xa, int ya) {
        boolean solid = false;
        for (byte c = 0; c < 4; c++) {
            int nextTileX = ((x + xa) + c % 2 * 14 - 8) / 16;
            int nextTileY = ((y + ya) + c / 2 * 12 + 3) / 16;
            if (level.getTile(nextTileX, nextTileY).solid()) solid = true;

        }
        return solid;
    }

    public void render() {

    }

}
