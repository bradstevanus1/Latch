package com.brad.rain.graphics;

import com.brad.rain.entity.mob.Player;
import com.brad.rain.entity.projectile.Projectile;
import com.brad.rain.level.tile.Tile;

import java.util.Random;

/**
 * Screen is responsible for handling how each type of object is rendered in the game.
 * Its methods fill the pixels array with RGB colours. This data is then handed back to
 * the Game class to be rendered by rasterized buffered image \s.
 */
public class Screen {

    // TODO Refactor to make all objects be rendered by renderSprite

    public int width, height;
    public int[] pixels;
    public int xOffset, yOffset;
    private Random random = new Random();

    public Screen(int width, int height) {
        this.width = width;
        this.height = height;

        // The array to be cast to and rendered as a buffered image. If you want to render anything,
        // simply fill this array with the correct pixel colours.
        pixels = new int[width * height];
    }

    public void clear() {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = 0;
        }
    }

    public void renderSprite(int xOrigin, int yOrigin, Sprite sprite, boolean fixed) {
        // The origin variables represent the top left coordinate of the screen
        if (fixed) {
            xOrigin -= xOffset;
            yOrigin -= yOffset;
        }
        for (int y = 0; y < sprite.getHeight(); y++) {
            int yRelToScreen = y + yOrigin;
            for (int x = 0; x < sprite.getWidth(); x++) {
                int xRelToScreen = x + xOrigin;
                if (xRelToScreen < 0 || xRelToScreen >= width || yRelToScreen < 0 || yRelToScreen >= height) continue;
                pixels[xRelToScreen + yRelToScreen * width] = sprite.pixels[x + y * sprite.getWidth()];
            }
        }
    }

    public void renderTile(int xp, int yp, Tile tile) {
        xp -= xOffset;
        yp -= yOffset;
        for (int y = 0; y < tile.sprite.SIZE; y++) {
            int ya = y + yp;
            for (int x = 0; x < tile.sprite.SIZE; x++) {
                int xa = x + xp;
                if (xa < -tile.sprite.SIZE || xa >= width || ya < 0 || ya >= height) break;
                if (xa < 0) xa = 0;
                pixels[xa + ya * width] = tile.sprite.pixels[x + y * tile.sprite.SIZE];
            }
        }
    }


    public void renderProjectile(int xp, int yp, Projectile p) {
        xp -= xOffset;
        yp -= yOffset;
        for (int y = 0; y < p.getSpriteSize(); y++) {
            int ya = y + yp;
            for (int x = 0; x < p.getSpriteSize(); x++) {
                int xa = x + xp;
                if (xa < -p.getSpriteSize() || xa >= width || ya < 0 || ya >= height) break;
                if (xa < 0) xa = 0;
                int col = p.getSprite().pixels[x + y * p.getSpriteSize()];
                if (col != 0xffff00ff) pixels[xa + ya * width] = col;
            }
        }
    }

    /*
     * This method renders the player onto the screen.
     * xs is x but flipped depending on the direction to
     * show reversed sprite direction.
     */
    public void renderPlayer(int xp, int yp, Sprite sprite, int flip) {
        xp -= xOffset;
        yp -= yOffset;
        for (int y = 0; y < Player.getPlayerSize(); y++) {
            int ya = y + yp;
            int ys = y;
            if (flip == 2 || flip == 3) {
                ys = (Player.getPlayerSize() - 1) - y;
            }
            for (int x = 0; x < Player.getPlayerSize(); x++) {
                int xa = x + xp;
                int xs = x;
                if (flip == 1 || flip == 3) {
                    xs = (Player.getPlayerSize() - 1) - x;
                }
                if (xa < -Player.getPlayerSize() || xa >= width || ya < 0 || ya >= height) break;
                if (xa < 0) xa = 0;
                int col = sprite.pixels[xs + ys * Player.getPlayerSize()];
                if (col != 0xffff00ff) pixels[xa + ya * width] = col;
            }
        }
    }


    public void setOffset(int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }
}
