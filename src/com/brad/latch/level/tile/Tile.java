package com.brad.latch.level.tile;

import com.brad.latch.graphics.Screen;
import com.brad.latch.graphics.Sprite;

public class Tile {

    public Sprite sprite;
    private static final int TILE_SIZE = 16;
    private static final int TILE_SIZE_EXP_2 = expOfBase2(TILE_SIZE);

    @SuppressWarnings("SameParameterValue")
    private static int expOfBase2(int num) {
        int count = 1;
        while (num != 2) {
            num /= 2;
            count++;
        }
        return count;
    }

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
