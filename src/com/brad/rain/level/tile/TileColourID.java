package com.brad.rain.level.tile;

public enum TileColourID {
    GRASS       (0xFF00FF00),
    FLOWER      (0xFFFFFF00),
    ROCK        (0xFF7F7F00);

    private final int tileColourID;

    TileColourID(int tileColourID) {
        this.tileColourID = tileColourID;
    }

    public int getTileColourID() {
        return this.tileColourID;
    }

}
