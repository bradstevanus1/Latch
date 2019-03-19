package com.brad.latch.graphics;

import com.brad.latch.level.tile.Tile;

import java.util.Random;

public final class SpriteCollection {

    private static Random random = new Random();

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

    public static final AnimatedSprite player_down = new AnimatedSprite(SpriteSheetCollection.player_down, 32, 32, 3);
    public static final AnimatedSprite player_up = new AnimatedSprite(SpriteSheetCollection.player_up, 32, 32, 3);
    public static final AnimatedSprite player_side = new AnimatedSprite(SpriteSheetCollection.player_side, 32, 32, 3);



    // Projectile sprites

    public static final Sprite projectile_spear = new Sprite(16, 0, 0, SpriteSheetCollection.projectile_spear);

    // Particle sprites

    public static final Sprite particle_normal = new Sprite(3, 0xaaaaaa);
    public static final Sprite projectile_spear_explosion = new Sprite(16, 1, 0, SpriteSheetCollection.projectile_spear);


}
