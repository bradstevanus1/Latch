package com.brad.latch.level;

import com.brad.latch.entity.Entity;
import com.brad.latch.entity.mob.Mob;
import com.brad.latch.entity.mob.Player;
import com.brad.latch.entity.particle.Particle;
import com.brad.latch.entity.projectile.Projectile;
import com.brad.latch.graphics.Screen;
import com.brad.latch.level.tile.Tile;
import com.brad.latch.level.tile.Tiles;
import com.brad.latch.util.Vector2i;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Level implements Tiles {

    protected int width, height;
    protected int[] tilesInt;
    protected int[] tiles;

    private final List<Entity> entities = new ArrayList<>();
    private final List<Projectile> projectiles = new ArrayList<>();
    private final List<Particle> particles = new ArrayList<>();
    private final List<Mob> mobs = new ArrayList<>();
    private final List<Player> players = new ArrayList<>();

    //  Overrides "compare" in the functional interface "comparator"
    private final Comparator<Node> nodeSorter = (n0, n1) -> {
        if (n1.fCost < n0.fCost) return 1;
        else if (n1.fCost > n0.fCost) return -1;
        return 0;
    };

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

    @SuppressWarnings("Duplicates")
    public void update() {
        for (Entity entity : entities) {
            entity.update();
        }
        for (Projectile projectile : projectiles) {
            projectile.update();
        }
        for (Particle particle : particles) {
            particle.update();
        }
        for (Mob mob : mobs) {
            mob.update();
        }
        for (Player player : players) {
            player.update();
        }
        remove();
    }

    private void remove() {
        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i).isRemoved()) {
                entities.remove(i);
                i--;
            }
        }
        for (int i = 0; i < projectiles.size(); i++) {
            if (projectiles.get(i).isRemoved()) {
                projectiles.remove(i);
                i--;
            }
        }
        for (int i = 0; i < particles.size(); i++) {
            if (particles.get(i).isRemoved()) {
                particles.remove(i);
                i--;
            }
        }
        for (Mob mob : mobs) {
            if (mob.isRemoved()) {
                mobs.remove(mob);
            }
        }
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).isRemoved()) {
                i--;
            }
        }
    }

    public List<Projectile> getProjectiles() {
        return projectiles;
    }

    public boolean tileCollision(int x, int y, int size, int xOffset, int yOffset) {
        boolean solid = false;
        for (byte c = 0; c < 4; c++) {
            int nextTileX = (x - c % 2 * size + xOffset) >> Tile.getTileSizeSqrt();
            int nextTileY = (y - c / 2 * size + yOffset) >> Tile.getTileSizeSqrt();
            if (getTile(nextTileX, nextTileY).solid()) solid = true;
        }
        return solid;
    }

    // Render the level
    // tileSizeSqrt is 4 in the case of size 16 tiles.
    @SuppressWarnings("Duplicates")
    public void render(int xScroll, int yScroll, Screen screen) {
        screen.setOffset(xScroll, yScroll);
        int x0 = xScroll >> Tile.getTileSizeSqrt();
        int x1 = (xScroll + screen.width + Tile.getTileSize()) >> Tile.getTileSizeSqrt();
        int y0 = yScroll >> Tile.getTileSizeSqrt();
        int y1 = (yScroll + screen.height + Tile.getTileSize()) >> Tile.getTileSizeSqrt();

        for (int y = y0; y < y1; y++) {
            for (int x = x0; x < x1; x++) {
                getTile(x, y).render(x, y, screen);
            }
        }
        // Render entities on level
        for (Entity entity : entities) {
            entity.render(screen);
        }
        for (Projectile projectile : projectiles) {
            projectile.render(screen);
        }
        for (Particle particle : particles) {
            particle.render(screen);
        }
        for (Mob mob : mobs) {
            mob.render(screen);
        }
        for (Player player : players) {
            player.render(screen);
        }
    }

    public void add(Entity e) {
        e.init(this);
        if (e instanceof Particle) {
            particles.add((Particle) e);
        } else if (e instanceof Projectile) {
            projectiles.add((Projectile) e);
        } else if (e instanceof Player) {
            players.add((Player) e);
        } else if (e instanceof  Mob) {
            mobs.add((Mob) e);
        } else {
            entities.add(e);
        }
    }

    // This can be return an ArrayList of players when multiplayer is implemented.
    public List<Player> getPlayers() {
        return players;
    }

    public Player getPlayerAt(int index) {
        return players.get(index);
    }

    public Player getClientPlayer() {
        return players.get(0);
    }

    public List<Mob> getMobs() {
        return mobs;
    }

    /**
     * The A* Search Algorithm.
     * @param start The vector position of the mob's current tile.
     * @param end   The vector position of the destination tile.
     * @return      A list of nodes that connect the start and end
     *              destination.
     */
    public List<Node> findPath(Vector2i start, Vector2i end) {
        List<Node> openList = new ArrayList<>();
        List<Node> closedList = new ArrayList<>();
        Node current = new Node(start, null, 0, start.distanceTo(end));
        openList.add(current);
        while (openList.size() > 0) {
            openList.sort(nodeSorter);  // Sorts the openList in order of fCost
            current = openList.get(0);

            // At the end of the linked list, loop through to the beginning to create the path
            if (current.tile.equals(end)) {
                List<Node> path = new ArrayList<>();
                while (current.parent != null) {
                    path.add(current);
                    current = current.parent;
                }
                openList.clear();
                closedList.clear();
                return path;
            }
            openList.remove(current);
            closedList.add(current);
            for (int i = 0; i < 9; i++) {
                if (i == 4) continue;
                int x = current.tile.getX();
                int y = current.tile.getY();
                int xi = (i % 3) - 1;
                int yi = (i / 3) - 1;
                Tile tileAt = getTile(x + xi, y + yi);
                if (tileAt == null || tileAt.solid()) continue;
                Vector2i tileAtVector = new Vector2i(x + xi, y + yi);
                double gCost = current.gCost + (current.tile.distanceTo(tileAtVector) == 1 ? 1 : 0.95);
                double hCost = tileAtVector.distanceTo(end);
                Node node = new Node(tileAtVector, current, gCost, hCost);
                //FIXME >= node.gCost? Are you sure? Might be the same value
                if (vectorInList(closedList, tileAtVector) && gCost >= node.gCost) continue;
                if (!vectorInList(openList, tileAtVector) || gCost < node.gCost) openList.add(node);
            }
        }
        closedList.clear();
        return null;
    }

    private boolean vectorInList(List<Node> nodes, Vector2i vector) {
        for (Node node : nodes) {
            if (node.tile.equals(vector)) return true;
        }
        return false;
    }

    /**
     * Gets the entities (in the level entities list, not all
     * entities!) that are in range of the entity specified.
     * @param e         Given entity
     * @param radius    Radius of search
     * @return          List of entities in the level list
     *                  that are in range
     */
    @SuppressWarnings("Duplicates")
    public List<Entity> getEntitiesInRange(Entity e, int radius) {
        int ex = (int) e.getX();
        int ey = (int) e.getY();
        List<Entity> entities = new ArrayList<>();
        for (Entity entity : this.entities) {
            if (entity.equals(e)) continue;
            int x = (int) entity.getX();
            int y = (int) entity.getY();
            double distance = Math.sqrt((x - ex)*(x - ex) + (y - ey)*(y - ey));
            if (distance <= radius) entities.add(entity);
        }
        return entities;
    }

    @SuppressWarnings("Duplicates")
    public List<Mob> getMobsInRange(Entity e, int radius) {
        int ex = (int) e.getX();
        int ey = (int) e.getY();
        List<Mob> mobs = new ArrayList<>();
        for (Mob mob : this.mobs) {
            int x = (int) mob.getX();
            int y = (int) mob.getY();
            double distance = Math.sqrt((x - ex)*(x - ex) + (y - ey)*(y - ey));
            if (distance <= radius) mobs.add(mob);
        }
        return mobs;
    }

    @SuppressWarnings("Duplicates")
    public List<Player> getPlayersInRange(Entity e, int radius) {
        int ex = (int) e.getX();
        int ey = (int) e.getY();
        List<Player> players = new ArrayList<>();
        for (Player player : this.players) {
            int x = (int) player.getX();
            int y = (int) player.getY();
            double distance = Math.sqrt((x - ex)*(x - ex) + (y - ey)*(y - ey));
            if (distance <= radius) players.add(player);
        }
        return players;
    }

    /*
     * Grass = 0xFF00FF00
     * Flower = 0xFFFFFF00
     * Rock = 0xFF7F7F00
     */
    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) return voidTile;
        int spriteColNumber = tiles[x + y * width];
        switch (spriteColNumber) {

            case col_spawn_grass:          return spawn_grass;
            case col_spawn_leaves:         return spawn_leaves;
            case col_spawn_water:          return spawn_water;
            case col_spawn_wall1:          return spawn_wall1;
            case col_spawn_wooden_floor:   return spawn_wooden_floor;
            case col_spawn_cobblestone:    return spawn_cobblestone;
            case col_spawn_wall2:          return spawn_wall2;
            case col_spawn_log:            return spawn_log;

            default:                       return voidTile;
        }
    }

}
