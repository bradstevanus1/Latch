package com.brad.rain.level.tile;

import com.brad.rain.graphics.Screen;
import com.brad.rain.graphics.Sprite;
import org.jetbrains.annotations.Contract;

public class Tile {

    public int x, y;
    public Sprite sprite;
    private static final int TILE_SIZE = 16;
    private static final int TILE_SIZE_DIV_2 = TILE_SIZE / 2;
    private static final int TILE_SIZE_EXP_2 = expOfBase2(TILE_SIZE);

    @Contract(pure = true)
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

    public static final int getTileSize() {
        return TILE_SIZE;
    }

    public static final int getTileSizeDiv2() {
        return TILE_SIZE_DIV_2;
    }

    public static final int getTileSizeExp2() {
        return TILE_SIZE_EXP_2;
    }

}
