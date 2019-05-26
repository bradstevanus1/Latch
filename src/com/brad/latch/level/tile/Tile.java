package com.brad.latch.level.tile;

import com.brad.latch.graphics.Screen;
import com.brad.latch.graphics.Sprite;

import static com.brad.latch.util.MathUtils.getExponent;

public abstract class Tile {

    public Sprite sprite;
    private static final int TILE_SIZE = 16;
    private static final int TILE_SIZE_EXP_2 = getExponent(2, TILE_SIZE);

    public Tile(Sprite sprite) {
        this.sprite = sprite;
    }

    public void render(int x, int y, Screen screen) {

    }

    public boolean solid() {
        return false;
    }

    public static int getTileSize() {
        return TILE_SIZE;
    }

    public static int getTileSizeSqrt() {
        return TILE_SIZE_EXP_2;
    }

}
