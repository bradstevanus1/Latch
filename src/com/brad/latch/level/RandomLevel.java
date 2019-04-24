package com.brad.latch.level;

import com.brad.latch.level.tile.TileCoordinate;

import java.util.Random;

public class RandomLevel extends Level {

    private static final Random random = new Random();

    public RandomLevel(int width, int height) {
        super(width, height);
        spawnPoint = new TileCoordinate(0, 0);
    }

    protected void generateLevel() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tilesInt[x + y * width] = random.nextInt(4);
            }
        }
    }
}
