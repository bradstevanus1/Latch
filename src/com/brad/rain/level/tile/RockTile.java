package com.brad.rain.level.tile;

import com.brad.rain.graphics.Screen;
import com.brad.rain.graphics.Sprite;

public class RockTile extends Tile {

    public RockTile(Sprite sprite) {
        super(sprite);
    }

    public void render(int x, int y, Screen screen) {
        screen.renderTile(x << Tile.getTileSizeDiv4(), y << Tile.getTileSizeDiv4(), this);
    }

    public boolean solid() {
        return true;
    }
}
