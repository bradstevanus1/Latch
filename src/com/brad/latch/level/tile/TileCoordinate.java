package com.brad.latch.level.tile;

@SuppressWarnings("FieldCanBeLocal")
public class TileCoordinate {

    private int x, y;
    private static final int TILE_SIZE  = Tile.getTileSize();

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

    public static int getTileX(int x) {
        return x * TILE_SIZE;
    }

    public static int getTileY(int y) {
        return y * TILE_SIZE;
    }

}
