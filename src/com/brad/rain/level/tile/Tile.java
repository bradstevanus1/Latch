package com.brad.rain.level.tile;

import com.brad.rain.graphics.Screen;
import com.brad.rain.graphics.Sprite;

public class Tile {

    public int x, y;
    public Sprite sprite;
    private static final int TILE_SIZE = 16;
    private static final int TILE_SIZE_DIV_2 = TILE_SIZE / 2;
    private static final int TILE_SIZE_DIV_4 = TILE_SIZE / 4;

    public Tile(Sprite sprite) {
        this.sprite = sprite;
    }

    public void render(int x, int y, Screen screen) {

    }

    public boolean solid() {
        return false;
    }

    public static final int getTileSize() {
        return TILE_SIZE;
    }

    public static final int getTileSizeDiv2() {
        return TILE_SIZE_DIV_2;
    }

    public static final int getTileSizeDiv4() {
        return TILE_SIZE_DIV_4;
    }

}
