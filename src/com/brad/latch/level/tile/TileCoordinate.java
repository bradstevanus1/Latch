package com.brad.latch.level.tile;

public class TileCoordinate {

    private int x, y;
    private final int TILE_SIZE  = Tile.getTileSize();

    public TileCoordinate(int x, int y) {
        this.x = x * TILE_SIZE;
        this.y = y * TILE_SIZE;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int[] xy() {
        return new int[]{x, y};
    }
}
