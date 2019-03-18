package com.brad.latch.level.tile.spawn_tile;

import com.brad.latch.graphics.Screen;
import com.brad.latch.graphics.Sprite;
import com.brad.latch.level.tile.Tile;

public class SpawnWaterTile extends Tile {
    public SpawnWaterTile(Sprite sprite) {
        super(sprite);
    }

    public void render(int x, int y, Screen screen) {
        screen.renderTile(x << Tile.getTileSizeExp2(), y << Tile.getTileSizeExp2(), this);
    }
}
