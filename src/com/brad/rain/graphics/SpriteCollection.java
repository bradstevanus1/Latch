package com.brad.rain.graphics;

import com.brad.rain.entity.mob.Player;
import com.brad.rain.level.tile.Tile;

public final class SpriteCollection {

    // General sprites

    public static final Sprite voidSprite = new Sprite(Tile.getTileSize(), 0, 0, SpriteSheetCollection.tiles);
    public static final Sprite grass = new Sprite(Tile.getTileSize(), 1, 0, SpriteSheetCollection.tiles);
    public static final Sprite flower = new Sprite(Tile.getTileSize(), 2, 0, SpriteSheetCollection.tiles);
    public static final Sprite rock = new Sprite(Tile.getTileSize(), 3, 0, SpriteSheetCollection.tiles);

    // Spawn level sprites

    public static final Sprite spawn_grass = new Sprite(Tile.getTileSize(), 0, 0, SpriteSheetCollection.spawn_level);
    public static final Sprite spawn_leaves = new Sprite(Tile.getTileSize(), 1, 0, SpriteSheetCollection.spawn_level);
    public static final Sprite spawn_water = new Sprite(Tile.getTileSize(), 2, 0, SpriteSheetCollection.spawn_level);
    public static final Sprite spawn_wall1 = new Sprite(Tile.getTileSize(), 0, 1, SpriteSheetCollection.spawn_level);
    public static final Sprite spawn_log = new Sprite(Tile.getTileSize(), 1, 1, SpriteSheetCollection.spawn_level);
    public static final Sprite spawn_cobblestone = new Sprite(Tile.getTileSize(), 2, 1, SpriteSheetCollection.spawn_level);
    public static final Sprite spawn_wall2 = new Sprite(Tile.getTileSize(), 0, 2, SpriteSheetCollection.spawn_level);
    public static final Sprite spawn_wooden_floor = new Sprite(Tile.getTileSize(), 1, 2, SpriteSheetCollection.spawn_level);

    // Player sprites

    public static final Sprite player_forward = new Sprite(Player.getPlayerSize(), 0, 5, SpriteSheetCollection.tiles);
    public static final Sprite player_back = new Sprite(Player.getPlayerSize(), 2, 5, SpriteSheetCollection.tiles);
    public static final Sprite player_side = new Sprite(Player.getPlayerSize(), 1, 5, SpriteSheetCollection.tiles);
    public static final Sprite player_forward_1 = new Sprite(Player.getPlayerSize(), 0, 6, SpriteSheetCollection.tiles);
    public static final Sprite player_forward_2 = new Sprite(Player.getPlayerSize(), 0, 7, SpriteSheetCollection.tiles);
    public static final Sprite player_side_1 = new Sprite(Player.getPlayerSize(), 1, 6, SpriteSheetCollection.tiles);
    public static final Sprite player_side_2 = new Sprite(Player.getPlayerSize(), 1, 7, SpriteSheetCollection.tiles);
    public static final Sprite player_back_1 = new Sprite(Player.getPlayerSize(), 2, 6, SpriteSheetCollection.tiles);
    public static final Sprite player_back_2 = new Sprite(Player.getPlayerSize(), 2, 7, SpriteSheetCollection.tiles);

    // Projectile sprites

    public static final Sprite projectile_spear = new Sprite(16, 0, 0, SpriteSheetCollection.projectile_spear);
    public static final Sprite projectile_spear_explosion = new Sprite(16, 1, 0, SpriteSheetCollection.projectile_spear);

}
