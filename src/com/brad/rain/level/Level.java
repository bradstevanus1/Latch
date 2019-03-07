package com.brad.rain.level;

import com.brad.rain.entity.Entity;
import com.brad.rain.entity.projectile.Projectile;
import com.brad.rain.graphics.Screen;
import com.brad.rain.level.tile.Tile;
import com.brad.rain.level.tile.TileCollection;

import java.util.ArrayList;
import java.util.List;

public class Level {

    // TODO: create enum for spriteColNumber
    protected int width, height;
    protected int[] tilesInt;
    protected int[] tiles;
    public static Level spawn = new SpawnLevel("/levels/spawn.png");

    private List<Entity> entities = new ArrayList<Entity>();
    private List<Projectile> projectiles = new ArrayList<Projectile>();


    public Level(int width, int height) {
        this.width = width;
        this.height = height;
        tilesInt = new int[width * height];
        generateLevel();
    }

    public Level(String path) {
        loadLevel(path);
        generateLevel();
    }

    protected void generateLevel() {

    }

    protected void loadLevel(String path) {

    }

    public void update() {
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).update();
        }
        for (int i = 0; i < projectiles.size(); i++) {
            projectiles.get(i).update();
        }
    }

    public List<Projectile> getProjectiles() {
        return projectiles;
    }

    private void time() {

    }

    public void render(int xScroll, int yScroll, Screen screen) {
        // Render level
        screen.setOffset(xScroll, yScroll);
        int x0 = xScroll >> 4;
        int x1 = (xScroll + screen.width + 16) >> 4;
        int y0 = yScroll >> 4;
        int y1 = (yScroll + screen.height + 16) >> 4;

        for (int y = y0; y < y1; y++) {
            for (int x = x0; x < x1; x++) {
                getTile(x, y).render(x, y, screen);
            }
        }
        // Render entities on level
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).render(screen);
        }
        for (int i = 0; i < projectiles.size(); i++) {
            projectiles.get(i).render(screen);
        }
    }

    public void add(Entity e) {
        entities.add(e);
    }

    public void addProjectile(Projectile p) {
        projectiles.add(p);
    }

    /*
     * Grass = 0xFF00FF00
     * Flower = 0xFFFFFF00
     * Rock = 0xFF7F7F00
     */
    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) return TileCollection.voidTile;
        int spriteColNumber = tiles[x + y * width];
        switch (spriteColNumber) {

            case TileCollection.col_spawn_grass:          return TileCollection.spawn_grass;
            case TileCollection.col_spawn_leaves:         return TileCollection.spawn_leaves;
            case TileCollection.col_spawn_water:          return TileCollection.spawn_water;
            case TileCollection.col_spawn_wall1:          return TileCollection.spawn_wall1;
            case TileCollection.col_spawn_wooden_floor:   return TileCollection.spawn_wooden_floor;
            case TileCollection.col_spawn_cobblestone:    return TileCollection.spawn_cobblestone;
            case TileCollection.col_spawn_wall2:          return TileCollection.spawn_wall2;
            case TileCollection.col_spawn_log:            return TileCollection.spawn_log;

            default:                                      return TileCollection.voidTile;
        }
    }

}
