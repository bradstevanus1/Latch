package com.brad.latch.entity.particle;

import com.brad.latch.entity.Entity;
import com.brad.latch.graphics.Screen;
import com.brad.latch.graphics.Sprite;
import com.brad.latch.graphics.SpriteCollection;
import com.brad.latch.level.tile.Tile;

public class Particle extends Entity {

    protected int life;
    private int time = 0;
    protected double xDouble, yDouble, zDouble;
    protected double xDelta, yDelta, zDelta;
    protected static final int SIZE = 3;

    /**
     * Constructor for a particle object that takes a coordinate
     * value and a life value, the latter being used to determine how long
     * the particle stays on the level for.
     * @param x the x coordinate of particle spawn location
     * @param y the y coordinate of particle spawn lcation
     * @param life the average life of the particle,
     *             in ticks (60 ticks per second)
     */
    public Particle(int x, int y, int life) {
        this.x = x;
        this.y = y;
        this.xDouble = x;
        this.yDouble = y;
        this.zDouble = 0.0;
        this.life = life + (random.nextInt(life) - life / 2);
        sprite = SpriteCollection.particle_normal;

        // Sets the distance delta for the particle to travel to a
        // random (bell-curved) value in the range -1 to 1
        this.xDelta = random.nextGaussian() / 2;
        this.yDelta = random.nextGaussian() / 2;
        this.zDelta = random.nextFloat() + 1.5;
    }

    @Override
    public void update() {
        time++;
        if (time >= Integer.MAX_VALUE) time = 0;
        if (time > life) remove();
        zDelta -= 0.1;
        if (zDouble < 0) {
            zDouble = 0;    // Particles should not be beneath the floor
            xDelta *= 0.4;
            yDelta *= 0.4;
            zDelta *= -0.5; // Reverse particle direction for bounce effect
        }
        // Does not include the height difference due to bouncing (zDouble + zDelta) to remove bug
        // where the particles clump player_up on the floor. However, this means that the particles do not have
        // accurate collision with tiles that are above the spawner vetically.
        move(xDouble + xDelta, (yDouble + yDelta) /*+ (zDouble + zDelta)*/);
    }

    @Override
    public void render(Screen screen) {
        screen.renderSprite((int) xDouble - 1, (int) yDouble - (int) zDouble - 2, sprite, true);
    }

    private void move(double x, double y) {
        if (collision(x, y)) {
            this.xDelta *= -0.4;
            this.yDelta *= -0.4;
            this.zDelta *= -0.4;
        }
        this.xDouble += xDelta;
        this.yDouble += yDelta;
        this.zDouble += zDelta;
    }

    public boolean collision(double x, double y) {
        boolean solid = false;
        for (byte c = 0; c < 4; c++) {
            double nextTileX = (x - c % 2 * Tile.getTileSize()) / Tile.getTileSize();
            double nextTileY = (y - c / 2.0 * Tile.getTileSize()) / Tile.getTileSize();
            int intNextTileX = (c % 2 == 0) ? (int) Math.floor(nextTileX) : (int) Math.ceil(nextTileX);
            int intNextTileY = (c / 2 == 0) ? (int) Math.floor(nextTileY) : (int) Math.ceil(nextTileY);
            if (level.getTile(intNextTileX, intNextTileY).solid()) solid = true;
        }
        return solid;
    }



}
