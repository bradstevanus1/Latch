package com.brad.rain.level.tile;

import com.brad.rain.graphics.Screen;
import com.brad.rain.graphics.Sprite;
import com.brad.rain.level.tile.spawn_tile.*;

public class Tile {

    public int x, y;
    public Sprite sprite;

    public static Tile voidTile = new VoidTile(Sprite.voidSprite);
    public static Tile grass = new GrassTile(Sprite.grass);
    public static Tile flower = new FlowerTile(Sprite.flower);
    public static Tile rock = new RockTile(Sprite.rock);

    public static Tile spawn_grass = new SpawnGrassTile(Sprite.spawn_grass);
    public static Tile spawn_leaves = new SpawnLeavesTile(Sprite.spawn_leaves);
    public static Tile spawn_water = new SpawnWaterTile(Sprite.spawn_water);
    public static Tile spawn_wall1 = new SpawnWallTile(Sprite.spawn_wall1);
    public static Tile spawn_wooden_floor = new SpawnWoodenFloorTile(Sprite.spawn_wooden_floor);
    public static Tile spawn_cobblestone = new SpawnCobblestoneTile(Sprite.spawn_cobblestone);
    public static Tile spawn_wall2 = new SpawnWallTile(Sprite.spawn_wall2);
    public static Tile spawn_log = new SpawnLogTile(Sprite.spawn_log);

    public static final int col_spawn_grass =   0xff00ff00;
    public static final int col_spawn_leaves = 0xff2D7A3D;
    public static final int col_spawn_water = 0xff0000ff;
    public static final int col_spawn_wall1 = 0xff808080;
    public static final int col_spawn_wooden_floor = 0xff602a00;
    public static final int col_spawn_cobblestone = 0xffC0C0C0;
    public static final int col_spawn_wall2 = 0xff303030;
    public static final int col_spawn_log = 0xff8E5122;

    public Tile(Sprite sprite) {
        this.sprite = sprite;
    }

    public void render(int x, int y, Screen screen) {

    }

    public boolean solid() {
        return false;
    }

}
