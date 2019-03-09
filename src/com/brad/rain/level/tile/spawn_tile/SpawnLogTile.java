package com.brad.rain.level.tile.spawn_tile;

import com.brad.rain.graphics.Screen;
import com.brad.rain.graphics.Sprite;
import com.brad.rain.level.tile.Tile;

public class SpawnLogTile extends Tile {
    public SpawnLogTile(Sprite sprite) {
        super(sprite);
    }

    public void render(int x, int y, Screen screen) {
        screen.renderTile(x << Tile.getTileSizeExp2(), y << Tile.getTileSizeExp2(), this);
    }
}
