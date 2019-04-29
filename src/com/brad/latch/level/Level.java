package com.brad.latch.level;

import com.brad.latch.entity.Entity;
import com.brad.latch.entity.mob.enemy.Enemy;
import com.brad.latch.entity.mob.friendly.Friendly;
import com.brad.latch.entity.mob.player.ClientPlayer;
import com.brad.latch.entity.mob.player.Player;
import com.brad.latch.entity.particle.Particle;
import com.brad.latch.entity.projectile.Projectile;
import com.brad.latch.events.Event;
import com.brad.latch.events.EventDispatcher;
import com.brad.latch.events.EventListener;
import com.brad.latch.events.types.MousePressedEvent;
import com.brad.latch.events.types.MouseReleasedEvent;
import com.brad.latch.graphics.Screen;
import com.brad.latch.graphics.layers.Layer;
import com.brad.latch.level.tile.Tile;
import com.brad.latch.level.tile.TileCoordinate;
import com.brad.latch.level.tile.Tiles;
import com.brad.latch.util.Vector2i;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public abstract class Level extends Layer implements Tiles, EventListener {

    public TileCoordinate spawnPoint;
    protected int width, height;
    protected int[] tilesInt;
    protected int[] tiles;

    private int xScroll, yScroll;

    private final List<Entity> entities = new ArrayList<>();
    private final List<Projectile> projectiles = new ArrayList<>();
    private final List<Particle> particles = new ArrayList<>();
    private final List<Enemy> enemies = new ArrayList<>();
    private final List<Friendly> friendlies = new ArrayList<>();
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
        for (Enemy enemy : enemies) {
            enemy.update();
        }
        for (Friendly friendly : friendlies) {
            friendly.update();
        }
        for (Player player : players) {
            player.update();
        }
        remove();
    }

    /**
     * Handles dispatching mouse pressed and released events for
     * the player. If the mouse pressed OR released methods return
     * false, that means that the mouse has not been pressed or released
     * in the LEVEL layer of the layerStack, and the event will be dispatched
     * to the next layer. If either method returns true, then the event has
     * been handled, the method returns true, and it is not dispatched
     * to the next layer in the layerStack.
     * @param event
     */
    @Override
    @SuppressWarnings("Duplicates")
    public void onEvent(Event event) {
        EventDispatcher dispatcher = new EventDispatcher(event);
        dispatcher.dispatch(Event.Type.MOUSE_PRESSED, event1 -> onMousePressed((MousePressedEvent)event1));
        dispatcher.dispatch(Event.Type.MOUSE_RELEASED, event1 -> onMouseReleased((MouseReleasedEvent)event1));
    }

    //FIXME Player's projectile gets stuck in wall if firing from too close to a top surface
    public boolean onMousePressed(MousePressedEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            getClientPlayer().setShooting(true);
            return true;
        }
        return false;
    }

    public boolean onMouseReleased(MouseReleasedEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            getClientPlayer().setShooting(false);
            return true;
        }
        return false;
    }

    // Removes objects from the list their field isRemoved equals true.
    private void remove() {
        entities.removeIf(Entity::isRemoved);
        projectiles.removeIf(Entity::isRemoved);
        particles.removeIf(Entity::isRemoved);
        enemies.removeIf(Entity::isRemoved);
        friendlies.removeIf(Entity::isRemoved);
        players.removeIf(Entity::isRemoved);
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

    public void setScroll(int xScroll, int yScroll) {
        this.xScroll = xScroll;
        this.yScroll = yScroll;
    }

    // Render the level
    // tileSizeSqrt is 4 in the case of size 16 tiles.
    @SuppressWarnings("Duplicates")
    public void render(Screen screen) {
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
        for (Enemy enemy : enemies) {
            enemy.render(screen);
        }
        for (Friendly friendly : friendlies) {
            friendly.render(screen);
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
        } else if (e instanceof Enemy) {
            enemies.add((Enemy) e);
        } else if (e instanceof Friendly) {
            friendlies.add((Friendly) e);
        } else {
            entities.add(e);
        }
    }

    // This can be return an ArrayList of players when multi-player is implemented.
    public List<Player> getPlayers() {
        return players;
    }

    public Player getPlayerAt(int index) {
        return players.get(index);
    }

    public ClientPlayer getClientPlayer() {
        return (ClientPlayer) players.get(0);
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public List<Friendly> getFriendlies() {
        return friendlies;
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
     * Gets all objects that are entities (not just those in the entities
     * level ArrayList) that are in range of the entity specified.
     * @param e         Given entity
     * @param radius    Radius of search
     * @return          List of all entities that are in range
     */
    @SuppressWarnings("Duplicates")
    public List<Entity> getAllEntitiesInRange(Entity e, int radius) {
        int ex = (int) e.getX();
        int ey = (int) e.getY();
        List<Entity> result = new ArrayList<>();
        List<Entity> allEntities = entities;
        allEntities.addAll(projectiles);
        allEntities.addAll(particles);
        allEntities.addAll(enemies);
        allEntities.addAll(friendlies);
        allEntities.addAll(players);
        for (Entity entity : allEntities) {
            int x = (int) entity.getX();
            int y = (int) entity.getY();
            double distance = Math.sqrt((x - ex)*(x - ex) + (y - ey)*(y - ey));
            if (distance <= radius) result.add(entity);
        }
        return result;
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
    public List<Enemy> getEnemiesInRange(Entity e, int radius) {
        int ex = (int) e.getX();
        int ey = (int) e.getY();
        List<Enemy> enemies = new ArrayList<>();
        for (Enemy enemy : this.enemies) {
            int x = (int) enemy.getX();
            int y = (int) enemy.getY();
            double distance = Math.sqrt((x - ex)*(x - ex) + (y - ey)*(y - ey));
            if (distance <= radius) enemies.add(enemy);
        }
        return enemies;
    }

    @SuppressWarnings("Duplicates")
    public List<Friendly> getFrendliesInRange(Entity e, int radius) {
        int ex = (int) e.getX();
        int ey = (int) e.getY();
        List<Friendly> friendlies = new ArrayList<>();
        for (Friendly friendly : this.friendlies) {
            int x = (int) friendly.getX();
            int y = (int) friendly.getY();
            double distance = Math.sqrt((x - ex)*(x - ex) + (y - ey)*(y - ey));
            if (distance <= radius) friendlies.add(friendly);
        }
        return friendlies;
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

    @SuppressWarnings("Duplicates")
    public List<Projectile> getProjectilesInRange(Entity e, int radius) {
        int ex = (int) e.getX();
        int ey = (int) e.getY();
        List<Projectile> projectiles = new ArrayList<>();
        for (Projectile projectile : this.projectiles) {
            int x = (int) projectile.getX();
            int y = (int) projectile.getY();
            double distance = Math.sqrt((x - ex)*(x - ex) + (y - ey)*(y - ey));
            if (distance <= radius) projectiles.add(projectile);
        }
        return projectiles;
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
