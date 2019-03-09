package com.brad.rain.level.tile;

import com.brad.rain.graphics.Screen;
import com.brad.rain.graphics.Sprite;

public class VoidTile extends Tile {

    public VoidTile(Sprite sprite) {
        super(sprite);
    }

    public void render(int x, int y, Screen screen) {
        screen.renderTile(x << Tile.getTileSizeDiv4(), y << Tile.getTileSizeDiv4(), this);
    }

}
