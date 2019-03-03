package com.brad.rain.level.tile.spawn_tile;

import com.brad.rain.graphics.Screen;
import com.brad.rain.graphics.Sprite;
import com.brad.rain.level.tile.Tile;

public class SpawnWoodenFloorTile extends Tile {
    public SpawnWoodenFloorTile(Sprite sprite) {
        super(sprite);
    }

    public void render(int x, int y, Screen screen) {
        screen.renderTile(x << 4, y << 4, this);
    }
}
