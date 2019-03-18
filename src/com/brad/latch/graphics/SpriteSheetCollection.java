package com.brad.latch.graphics;

public final class SpriteSheetCollection {

    public static final SpriteSheet tiles = new SpriteSheet("/textures/sheets/spritesheet.png", 256);
    public static final SpriteSheet spawn_level = new SpriteSheet("/textures/sheets/spawn_level.png", 48);
    public static final SpriteSheet projectile_spear = new SpriteSheet("/textures/sheets/projectiles/spear.png", 48);

    public static final SpriteSheet player = new SpriteSheet("/textures/sheets/player_sheet.png", 96);
    public static final SpriteSheet player_down = new SpriteSheet(player, 2, 0, 1, 3, 32);
    public static final SpriteSheet player_up = new SpriteSheet(player, 0, 0, 1, 3, 32);
    public static final SpriteSheet player_side = new SpriteSheet(player, 1, 0, 1, 3, 32);

}
