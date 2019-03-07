package com.brad.rain.graphics;

public final class SpriteCollection {

    // General sprites

    public static final Sprite voidSprite = new Sprite(16, 0, 0, SpriteSheet.tiles);
    public static final Sprite grass = new Sprite(16, 1, 0, SpriteSheet.tiles);
    public static final Sprite flower = new Sprite(16, 2, 0, SpriteSheet.tiles);
    public static final Sprite rock = new Sprite(16, 3, 0, SpriteSheet.tiles);

    // Spawn level sprites

    public static final Sprite spawn_grass = new Sprite(16, 0, 0, SpriteSheet.spawn_level);
    public static final Sprite spawn_leaves = new Sprite(16, 1, 0, SpriteSheet.spawn_level);
    public static final Sprite spawn_water = new Sprite(16, 2, 0, SpriteSheet.spawn_level);
    public static final Sprite spawn_wall1 = new Sprite(16, 0, 1, SpriteSheet.spawn_level);
    public static final Sprite spawn_log = new Sprite(16, 1, 1, SpriteSheet.spawn_level);
    public static final Sprite spawn_cobblestone = new Sprite(16, 2, 1, SpriteSheet.spawn_level);
    public static final Sprite spawn_wall2 = new Sprite(16, 0, 2, SpriteSheet.spawn_level);
    public static final Sprite spawn_wooden_floor = new Sprite(16, 1, 2, SpriteSheet.spawn_level);

    // Player sprites

    public static final Sprite player_forward = new Sprite(32, 0, 5, SpriteSheet.tiles);
    public static final Sprite player_back = new Sprite(32, 2, 5, SpriteSheet.tiles);
    public static final Sprite player_side = new Sprite(32, 1, 5, SpriteSheet.tiles);
    public static final Sprite player_forward_1 = new Sprite(32, 0, 6, SpriteSheet.tiles);
    public static final Sprite player_forward_2 = new Sprite(32, 0, 7, SpriteSheet.tiles);
    public static final Sprite player_side_1 = new Sprite(32, 1, 6, SpriteSheet.tiles);
    public static final Sprite player_side_2 = new Sprite(32, 1, 7, SpriteSheet.tiles);
    public static final Sprite player_back_1 = new Sprite(32, 2, 6, SpriteSheet.tiles);
    public static final Sprite player_back_2 = new Sprite(32, 2, 7, SpriteSheet.tiles);

}
