package com.brad.latch.graphics;

public final class SpriteSheetCollection {

    public static final SpriteSheet tiles = new SpriteSheet("/textures/sheets/tiles/spritesheet.png", 256);
    public static final SpriteSheet spawn_level = new SpriteSheet("/textures/sheets/tiles/spawn_level.png", 48);
    public static final SpriteSheet projectile_spear = new SpriteSheet("/textures/sheets/entities/projectiles/spear.png", 48);

    public static final SpriteSheet player = new SpriteSheet("/textures/sheets/entities/mobs/player.png", 96);
    public static final SpriteSheet player_down = new SpriteSheet(player, 2, 0, 1, 3, 32);
    public static final SpriteSheet player_up = new SpriteSheet(player, 0, 0, 1, 3, 32);
    public static final SpriteSheet player_side = new SpriteSheet(player, 1, 0, 1, 3, 32);

    public static final SpriteSheet straggler = new SpriteSheet("/textures/sheets/entities/mobs/traveller.png", 128, 96);
    public static final SpriteSheet straggler_down = new SpriteSheet(straggler, 0, 0, 1, 3, 32);
    public static final SpriteSheet straggler_up = new SpriteSheet(straggler, 1, 0, 1, 3, 32);
    public static final SpriteSheet straggler_left = new SpriteSheet(straggler, 2, 0, 1, 3, 32);
    public static final SpriteSheet straggler_right = new SpriteSheet(straggler, 3, 0, 1, 3, 32);
}
