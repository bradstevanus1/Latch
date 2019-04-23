package com.brad.latch.graphics;

import com.brad.latch.level.tile.Tile;

import java.awt.Color;

/**
 * Collection of commonly-used sprites.
 */
public interface Sprites {


    /** Sprite Sheets */

    // Tile sheets

    SpriteSheet tiles = new SpriteSheet("/textures/sheets/tiles/spritesheet.png", 256);
    SpriteSheet spawn_level = new SpriteSheet("/textures/sheets/tiles/spawn_level.png", 48);
    SpriteSheet projectile_spear_sheet = new SpriteSheet("/textures/sheets/entities/projectiles/spear.png", 48);

    // Entity sheets

    SpriteSheet player = new SpriteSheet("/textures/sheets/entities/mobs/player.png", 128, 96);
    SpriteSheet player_down_sheet = new SpriteSheet(player, 0, 0, 1, 3, 32);
    SpriteSheet player_up_sheet = new SpriteSheet(player, 1, 0, 1, 3, 32);
    SpriteSheet player_left_sheet = new SpriteSheet(player, 2, 0, 1, 3, 32);
    SpriteSheet player_right_sheet = new SpriteSheet(player, 3, 0, 1, 3, 32);

    SpriteSheet traveller = new SpriteSheet("/textures/sheets/entities/mobs/traveller.png", 128, 96);
    SpriteSheet traveller_down_sheet = new SpriteSheet(traveller, 0, 0, 1, 3, 32);
    SpriteSheet traveller_up_sheet = new SpriteSheet(traveller, 1, 0, 1, 3, 32);
    SpriteSheet traveller_left_sheet = new SpriteSheet(traveller, 2, 0, 1, 3, 32);
    SpriteSheet traveller_right_sheet = new SpriteSheet(traveller, 3, 0, 1, 3, 32);

    SpriteSheet straggler = new SpriteSheet("/textures/sheets/entities/mobs/straggler.png", 128, 96);
    SpriteSheet straggler_down_sheet = new SpriteSheet(straggler, 0, 0, 1, 3, 32);
    SpriteSheet straggler_up_sheet = new SpriteSheet(straggler, 1, 0, 1, 3, 32);
    SpriteSheet straggler_left_sheet = new SpriteSheet(straggler, 2, 0, 1, 3, 32);
    SpriteSheet straggler_right_sheet = new SpriteSheet(straggler, 3, 0, 1, 3, 32);

    SpriteSheet pokey = new SpriteSheet("/textures/sheets/entities/mobs/straggler.png", 128, 96);
    SpriteSheet pokey_down_sheet = new SpriteSheet(pokey, 0, 0, 1, 3, 32);
    SpriteSheet pokey_up_sheet = new SpriteSheet(pokey, 1, 0, 1, 3, 32);
    SpriteSheet pokey_left_sheet = new SpriteSheet(pokey, 2, 0, 1, 3, 32);
    SpriteSheet pokey_right_sheet = new SpriteSheet(pokey, 3, 0, 1, 3, 32);

    // Font sheets

    SpriteSheet font = new SpriteSheet("/textures/sheets/fonts/arial.png", 16);


    /** Sprites */

    // General sprites

    Sprite voidSprite = new Sprite(Tile.getTileSize(), 0, 0, tiles);
    Sprite grass = new Sprite(Tile.getTileSize(), 1, 0, tiles);
    Sprite rock = new Sprite(Tile.getTileSize(), 3, 0, tiles);
    Sprite flower = new Sprite(Tile.getTileSize(), 2, 0, tiles);

    // Spawn level sprites

    Sprite spawn_wooden_floor = new Sprite(Tile.getTileSize(), 1, 2, spawn_level);
    Sprite spawn_wall2 = new Sprite(Tile.getTileSize(), 0, 2, spawn_level);
    Sprite spawn_cobblestone = new Sprite(Tile.getTileSize(), 2, 1, spawn_level);
    Sprite spawn_log = new Sprite(Tile.getTileSize(), 1, 1, spawn_level);
    Sprite spawn_wall1 = new Sprite(Tile.getTileSize(), 0, 1, spawn_level);
    Sprite spawn_water = new Sprite(Tile.getTileSize(), 2, 0, spawn_level);
    Sprite spawn_leaves = new Sprite(Tile.getTileSize(), 1, 0, spawn_level);
    Sprite spawn_grass = new Sprite(Tile.getTileSize(), 0, 0, spawn_level);

    // Mob sprites

    AnimatedSprite player_down = new AnimatedSprite(player_down_sheet, 32, 32, 3);
    AnimatedSprite player_up = new AnimatedSprite(player_up_sheet, 32, 32, 3);
    AnimatedSprite player_left = new AnimatedSprite(player_left_sheet, 32, 32, 3);
    AnimatedSprite player_right = new AnimatedSprite(player_right_sheet, 32, 32, 3);

    AnimatedSprite traveller_down = new AnimatedSprite(traveller_down_sheet, 32, 32, 3);
    AnimatedSprite traveller_up = new AnimatedSprite(traveller_up_sheet, 32, 32, 3);
    AnimatedSprite traveller_left = new AnimatedSprite(traveller_left_sheet, 32, 32, 3);
    AnimatedSprite traveller_right = new AnimatedSprite(traveller_right_sheet, 32, 32, 3);

    AnimatedSprite straggler_down = new AnimatedSprite(straggler_down_sheet, 32, 32, 3);
    AnimatedSprite straggler_up = new AnimatedSprite(straggler_up_sheet, 32, 32, 3);
    AnimatedSprite straggler_left = new AnimatedSprite(straggler_left_sheet, 32, 32, 3);
    AnimatedSprite straggler_right = new AnimatedSprite(straggler_right_sheet, 32, 32, 3);

    AnimatedSprite pokey_down = new AnimatedSprite(pokey_down_sheet, 32, 32, 3);
    AnimatedSprite pokey_up = new AnimatedSprite(pokey_up_sheet, 32, 32, 3);
    AnimatedSprite pokey_left = new AnimatedSprite(pokey_left_sheet, 32, 32, 3);
    AnimatedSprite pokey_right = new AnimatedSprite(pokey_right_sheet, 32, 32, 3);

    // Projectile sprites

    Sprite projectile_spear = new Sprite(16, 0, 0, projectile_spear_sheet);

    // Particle sprites

    Sprite particle_normal = new Sprite(3, new Color(0xAAAAAA).getRGB());
    Sprite particle_blood = new Sprite(2, new Color(0x9B0000).getRGB());

    // Font sprites

    Sprite[] characters = Sprite.split(font);

}
