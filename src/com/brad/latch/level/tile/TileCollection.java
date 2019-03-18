package com.brad.latch.level.tile;

import com.brad.latch.graphics.SpriteCollection;
import com.brad.latch.level.tile.spawn_tile.*;

public final class TileCollection {

    // General tiles

    public static final Tile voidTile = new VoidTile(SpriteCollection.voidSprite);
    public static final Tile grass = new GrassTile(SpriteCollection.grass);
    public static final Tile flower = new FlowerTile(SpriteCollection.flower);
    public static final Tile rock = new RockTile(SpriteCollection.rock);

    // Spawn tiles

    public static final Tile spawn_grass = new SpawnGrassTile(SpriteCollection.spawn_grass);
    public static final Tile spawn_leaves = new SpawnLeavesTile(SpriteCollection.spawn_leaves);
    public static final Tile spawn_water = new SpawnWaterTile(SpriteCollection.spawn_water);
    public static final Tile spawn_wall1 = new SpawnWallTile(SpriteCollection.spawn_wall1);
    public static final Tile spawn_wooden_floor = new SpawnWoodenFloorTile(SpriteCollection.spawn_wooden_floor);
    public static final Tile spawn_cobblestone = new SpawnCobblestoneTile(SpriteCollection.spawn_cobblestone);
    public static final Tile spawn_wall2 = new SpawnWallTile(SpriteCollection.spawn_wall2);
    public static final Tile spawn_log = new SpawnLogTile(SpriteCollection.spawn_log);

    // Tile colour IDs for level-maker

    public static final int col_spawn_grass =   0xff00ff00;
    public static final int col_spawn_leaves = 0xff2D7A3D;
    public static final int col_spawn_water = 0xff0000ff;
    public static final int col_spawn_wall1 = 0xff808080;
    public static final int col_spawn_wooden_floor = 0xff602a00;
    public static final int col_spawn_cobblestone = 0xffC0C0C0;
    public static final int col_spawn_wall2 = 0xff303030;
    public static final int col_spawn_log = 0xff8E5122;

}
