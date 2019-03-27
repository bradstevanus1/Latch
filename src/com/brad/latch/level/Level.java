package com.brad.latch.level;

import com.brad.latch.entity.Entity;
import com.brad.latch.entity.mob.Player;
import com.brad.latch.entity.particle.Particle;
import com.brad.latch.entity.projectile.Projectile;
import com.brad.latch.graphics.Screen;
import com.brad.latch.level.tile.Tile;
import com.brad.latch.level.tile.TileCollection;
import com.brad.latch.util.Vector2i;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Level {

    protected int width, height;
    protected int[] tilesInt;
    protected int[] tiles;
    public static Level spawn = new SpawnLevel("/levels/spawn.png");

    private List<Entity> entities = new ArrayList<>();
    private List<Projectile> projectiles = new ArrayList<>();
    private List<Particle> particles = new ArrayList<>();
    private List<Player> players = new ArrayList<>();

    //  Overrides "compare" in the functional interface "comparator"
    private Comparator<Node> nodeSorter = (n0, n1) -> {
        if (n1.fCost < n0.fCost) return 1;
        if (n1.fCost > n0.fCost) return -1;
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
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).update();
        }
        for (int i = 0; i < projectiles.size(); i++) {
            projectiles.get(i).update();
        }
        for (int i = 0; i < particles.size(); i++) {
            particles.get(i).update();
        }
        for (int i = 0; i < players.size(); i++) {
            players.get(i).update();
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
            int nextTileX = (x - c % 2 * size + xOffset) >> Tile.getTileSizeExp2();
            int nextTileY = (y - c / 2 * size + yOffset) >> Tile.getTileSizeExp2();
            if (getTile(nextTileX, nextTileY).solid()) solid = true;

        }
        return solid;
    }

    // Render the level
    // tileSizeDiv4 is 4 in the case of size 16 tiles.
    @SuppressWarnings("Duplicates")
    public void render(int xScroll, int yScroll, Screen screen) {
        screen.setOffset(xScroll, yScroll);
        int x0 = xScroll >> Tile.getTileSizeExp2();
        int x1 = (xScroll + screen.width + Tile.getTileSize()) >> Tile.getTileSizeExp2();
        int y0 = yScroll >> Tile.getTileSizeExp2();
        int y1 = (yScroll + screen.height + Tile.getTileSize()) >> Tile.getTileSizeExp2();

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
        for (int i = 0; i < particles.size(); i++) {
            particles.get(i).render(screen);
        }
        for (int i = 0; i < players.size(); i++) {
            players.get(i).render(screen);
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

    /**
     * The A* Search Algorithm.
     * @param start
     * @param end
     * @return
     */
    public List<Node> findPath(Vector2i start, Vector2i end) {
        List<Node> openList = new ArrayList<>();
        List<Node> closedList = new ArrayList<>();
        Node current = new Node(start, null, 0, start.distanceTo(end));
        openList.add(current);
        while (openList.size() > 0) {
            Collections.sort(openList, nodeSorter);  // Sorts the openList in order of fCost
            current = openList.get(0);

            // At the end of the linked list, loop through to the beginning to create the path
            if (current.tileVector.equals(end)) {
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
                int x = current.tileVector.getX();
                int y = current.tileVector.getY();
                int xi = (i % 3) - 1;
                int yi = (i / 3) - 1;
                Tile tileAt = getTile(x + xi, y + yi);
                //FIXME does solid create nullptrException if tileAt is null?
                //      if so, then separate the if statement onto two lines
                if (tileAt == null || tileAt.solid()) continue;
                Vector2i tileAtVector = new Vector2i(x + xi, y + yi);
                double gCost = current.gCost + current.tileVector.distanceTo(tileAtVector);
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
            if (node.tileVector.equals(vector)) return true;
        }
        return false;
    }

    @SuppressWarnings("Duplicates")
    public List<Entity> getEntitiesInRadius(Entity e, int radius) {
        int ex = (int) e.getX();
        int ey = (int) e.getY();
        List<Entity> result = new ArrayList<>();
        for (Entity entity : entities) {
            int x = (int) entity.getX();
            int y = (int) entity.getY();
            double distance = Math.sqrt((x - ex)*(x - ex) + (y - ey)*(y - ey));
            if (distance <= radius) result.add(entity);
        }
        return result;
    }

    @SuppressWarnings("Duplicates")
    public List<Player> getPlayersInRange(Entity e, int radius) {
        int ex = (int) e.getX();
        int ey = (int) e.getY();
        List<Player> result = new ArrayList<>();
        for (Player player : players) {
            int x = (int) player.getX();
            int y = (int) player.getY();
            double distance = Math.sqrt((x - ex)*(x - ex) + (y - ey)*(y - ey));
            if (distance <= radius) result.add(player);
        }
        return result;
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
