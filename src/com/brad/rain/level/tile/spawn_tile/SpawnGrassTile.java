package com.brad.rain.level.tile.spawn_tile;

import com.brad.rain.graphics.Screen;
import com.brad.rain.graphics.Sprite;
import com.brad.rain.level.tile.Tile;

public class SpawnGrassTile extends Tile {

    public SpawnGrassTile(Sprite sprite) {
        super(sprite);
    }

    public void render(int x, int y, Screen screen) {
        screen.renderTile(x << Tile.getTileSizeDiv4(), y << Tile.getTileSizeDiv4(), this);
    }
}
