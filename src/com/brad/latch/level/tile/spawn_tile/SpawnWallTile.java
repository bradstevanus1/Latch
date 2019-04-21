package com.brad.latch.level.tile.spawn_tile;

import com.brad.latch.graphics.Screen;
import com.brad.latch.graphics.Sprite;
import com.brad.latch.level.tile.Tile;

public class SpawnWallTile extends Tile {
    public SpawnWallTile(Sprite sprite) {
        super(sprite);
    }

    public void render(int x, int y, Screen screen) {
        screen.renderTile(x << Tile.getTileSizeSqrt(), y << Tile.getTileSizeSqrt(), this);
    }

    public boolean solid() {
        return true;
    }
}
