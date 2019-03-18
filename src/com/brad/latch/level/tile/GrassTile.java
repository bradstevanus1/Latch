package com.brad.latch.level.tile;

import com.brad.latch.graphics.Screen;
import com.brad.latch.graphics.Sprite;

// No collision
public class GrassTile extends Tile {

    public GrassTile(Sprite sprite) {
        super(sprite);
    }

    public void render(int x, int y, Screen screen) {
        screen.renderTile(x << Tile.getTileSizeExp2(), y << Tile.getTileSizeExp2(), this);
    }

}
