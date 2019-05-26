package com.brad.latch.level.tile;

import com.brad.latch.graphics.Sprites;
import com.brad.latch.level.tile.spawn_tile.SpawnCobblestoneTile;
import com.brad.latch.level.tile.spawn_tile.SpawnGrassTile;
import com.brad.latch.level.tile.spawn_tile.SpawnLeavesTile;
import com.brad.latch.level.tile.spawn_tile.SpawnLogTile;
import com.brad.latch.level.tile.spawn_tile.SpawnWallTile;
import com.brad.latch.level.tile.spawn_tile.SpawnWaterTile;
import com.brad.latch.level.tile.spawn_tile.SpawnWoodenFloorTile;

public interface Tiles {

    // General tiles

    Tile voidTile = new VoidTile(Sprites.voidSprite);
    Tile grass = new GrassTile(Sprites.grass);
    Tile flower = new FlowerTile(Sprites.flower);
    Tile rock = new RockTile(Sprites.rock);

    // Spawn tiles

    Tile spawn_grass = new SpawnGrassTile(Sprites.spawn_grass);
    Tile spawn_leaves = new SpawnLeavesTile(Sprites.spawn_leaves);
    Tile spawn_water = new SpawnWaterTile(Sprites.spawn_water);
    Tile spawn_wall1 = new SpawnWallTile(Sprites.spawn_wall1);
    Tile spawn_wooden_floor = new SpawnWoodenFloorTile(Sprites.spawn_wooden_floor);
    Tile spawn_cobblestone = new SpawnCobblestoneTile(Sprites.spawn_cobblestone);
    Tile spawn_wall2 = new SpawnWallTile(Sprites.spawn_wall2);
    Tile spawn_log = new SpawnLogTile(Sprites.spawn_log);

    // Tile colour IDs for level-maker

    int col_spawn_grass = 0xff00ff00;
    int col_spawn_leaves = 0xff2D7A3D;
    int col_spawn_water = 0xff0000ff;
    int col_spawn_wall1 = 0xff808080;
    int col_spawn_wooden_floor = 0xff602a00;
    int col_spawn_cobblestone = 0xffC0C0C0;
    int col_spawn_wall2 = 0xff303030;
    int col_spawn_log = 0xff8E5122;

}
