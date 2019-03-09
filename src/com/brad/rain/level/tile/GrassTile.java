package com.brad.rain.level.tile;

import com.brad.rain.graphics.Screen;
import com.brad.rain.graphics.Sprite;

// No collision
public class GrassTile extends Tile {

    public GrassTile(Sprite sprite) {
        super(sprite);
    }

    public void render(int x, int y, Screen screen) {
        screen.renderTile(x << Tile.getTileSizeDiv4(), y << Tile.getTileSizeDiv4(), this);
    }

}
