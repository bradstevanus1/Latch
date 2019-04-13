package com.brad.latch.graphics;

import com.brad.latch.entity.mob.Mob;
import com.brad.latch.entity.mob.Player;
import com.brad.latch.entity.mob.enemy.Pokey;
import com.brad.latch.entity.mob.enemy.Straggler;
import com.brad.latch.entity.mob.friendly.Traveller;
import com.brad.latch.entity.projectile.Projectile;
import com.brad.latch.level.tile.Tile;

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

    @SuppressWarnings("Duplicates")
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
                int colour = sprite.pixels[x + y * sprite.getWidth()];
                if (colour != Sprite.ALPHA_COLOUR && colour != 0xFFA23FFF)
                    pixels[xRelToScreen + yRelToScreen * width] = colour;
            }
        }
    }

    @SuppressWarnings("Duplicates")
    public void renderCharacter(int xOrigin, int yOrigin, Sprite sprite, int colour, boolean fixed) {
        if (fixed) {
            xOrigin -= xOffset;
            yOrigin -= yOffset;
        }
        for (int y = 0; y < sprite.getHeight(); y++) {
            int yRelToScreen = y + yOrigin;
            for (int x = 0; x < sprite.getWidth(); x++) {
                int xRelToScreen = x + xOrigin;
                if (xRelToScreen < 0 || xRelToScreen >= width || yRelToScreen < 0 || yRelToScreen >= height) continue;
                int col = sprite.pixels[x + y * sprite.getWidth()];
                if (col != Sprite.ALPHA_COLOUR && col != 0xFFA23FFF) pixels[xRelToScreen + yRelToScreen * width] = colour;
            }
        }
    }

    public void renderTile(int xp, int yp, Tile tile) {
        xp -= xOffset;
        yp -= yOffset;
        for (int y = 0; y < tile.sprite.SIZE; y++) {
            int yDelta = y + yp;
            for (int x = 0; x < tile.sprite.SIZE; x++) {
                int xDelta = x + xp;
                if (xDelta < -tile.sprite.SIZE || xDelta >= width || yDelta < 0 || yDelta >= height) break;
                if (xDelta < 0) xDelta = 0;
                pixels[xDelta + yDelta * width] = tile.sprite.pixels[x + y * tile.sprite.SIZE];
            }
        }
    }

    public void renderProjectile(int xp, int yp, Projectile p) {
        xp -= xOffset;
        yp -= yOffset;
        for (int y = 0; y < p.getSprite().getSize(); y++) {
            int yDelta = y + yp;
            for (int x = 0; x < p.getSprite().getSize(); x++) {
                int xDelta = x + xp;
                if (xDelta < -p.getSprite().getSize() || xDelta >= width || yDelta < 0 || yDelta >= height) break;
                if (xDelta < 0) xDelta = 0;
                int colour = p.getSprite().pixels[x + y * p.getSprite().getSize()];
                if (colour != Sprite.ALPHA_COLOUR) pixels[xDelta + yDelta * width] = colour;
            }
        }
    }

    public void renderMob(int xOrigin, int yOrigin, Mob mob) {
        xOrigin -= xOffset;
        yOrigin -= yOffset;
        for (int y = 0; y < Player.getSize(); y++) {
            int yScreen = y + yOrigin;
            for (int x = 0; x < Player.getSize(); x++) {
                int xScreen = x + xOrigin;
                if (xScreen < -Player.getSize() || xScreen >= width || yScreen < 0 || yScreen >= height) break;
                if (xScreen < 0) xScreen = 0;
                int colour = mob.getSprite().pixels[x + y * Player.getSize()];
                if (mob instanceof Traveller && colour == 0xFF2084CC) colour = 0xFF36B72A;
                else if (mob instanceof Straggler && colour == 0xFF2084CC) colour = 0xFFF70E1A;
                else if (mob instanceof Pokey && colour == 0xFF2084CC) colour = 0xFFFF47A9;
                if (colour != Sprite.ALPHA_COLOUR) pixels[xScreen + yScreen * width] = colour;
            }
        }
    }

    public void drawRect(int x, int y, int width, int height, int colour, boolean fixed) {
        if (fixed) {
            x -= xOffset;
            y -= yOffset;
        }
        for (int xp = 0; xp < width; xp++) {
            if (xp + x < 0 || xp + x >= this.width || y >= this.height) continue;
            if (y > 0) pixels[(x + xp) + y * this.width] = colour;
            if (y + height >= this.height) continue;
            if (y + height > 0) pixels[(x + xp) + (y + height) * this.width] = colour;
        }
        for (int yp = 0; yp <= height; yp++) {
            if (x >= this.width || y + yp < 0 || y + yp >= this.height) continue;
            if (x > 0) pixels[x + (y + yp) * this.width] = colour;
            if (x + width >= this.width) continue;
            if (x + width > 0) pixels[(x + width) + (y + yp) * this.width] = colour;
        }
    }

    public void setOffset(int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }
}
