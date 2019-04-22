package com.brad.latch.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Creates spritesheet objects, which are large sheets consisting
 * of many sprites.
 */
public class SpriteSheet {

    private String path;
    public final int SIZE;
    public final int SPRITE_WIDTH, SPRITE_HEIGHT;
    private int width, height;
    public int[] pixels;

    private Sprite[] sprites;

    @SuppressWarnings("ManualArrayCopy")
    public SpriteSheet(SpriteSheet sheet, int x, int y, int width, int height, int spriteSize) {
        int xSprite = x * spriteSize;
        int ySprite = y * spriteSize;
        int spriteWidth = width * spriteSize;
        int spriteHeight = height * spriteSize;
        if (width == height) SIZE = width;
        else SIZE = -1;
        SPRITE_WIDTH = spriteWidth;
        SPRITE_HEIGHT = spriteHeight;
        pixels = new int[spriteWidth * spriteHeight];
        for (int vertical = 0; vertical < spriteHeight; vertical++) {
            int yRelToScreen = ySprite + vertical;
            for (int horizontal = 0; horizontal < spriteWidth; horizontal++) {
                int xRelToScreen = xSprite + horizontal;
                pixels[horizontal + vertical * spriteWidth] = sheet.pixels[xRelToScreen + yRelToScreen * sheet.SPRITE_WIDTH];
            }
        }
        int frame = 0;
        sprites = new Sprite[width * height];
        for (int yDelta = 0; yDelta < height; yDelta++) {
            for (int xDelta = 0; xDelta < width; xDelta++) {
                int[] spritePixels = new int[spriteSize * spriteSize];
                for (int y0 = 0; y0 < spriteSize; y0++) {
                    for (int x0 = 0; x0 < spriteSize; x0++) {
                        spritePixels[x0 + y0 * spriteSize] = pixels[(x0 + xDelta * spriteSize) + (y0 + yDelta *spriteSize) * SPRITE_WIDTH];
                    }
                }
                Sprite sprite = new Sprite(spritePixels, spriteSize, spriteSize);
                sprites[frame++] = sprite;
            }
        }
    }

    public SpriteSheet(String path, int size) {
        this.path = path;
        SIZE = size;
        SPRITE_WIDTH = size;
        SPRITE_HEIGHT = size;
        pixels = new int[SIZE * SIZE];
        load();
    }


    public SpriteSheet(String path, int width, int height) {
        this.path = path;
        SIZE = -1;
        SPRITE_WIDTH = width;
        SPRITE_HEIGHT = height;
        pixels = new int[SPRITE_WIDTH * SPRITE_HEIGHT];
        load();
    }

    public Sprite[] getSprites() {
        return sprites;
    }

    public void load() {
        try {
            System.out.print("Trying to load: " + path + " ... ");
            BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
            System.out.println("succeeded!");
            width = image.getWidth();
            height = image.getHeight();
            pixels = new int[width * height];
            image.getRGB(0, 0, width, height, pixels, 0, width);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("failed!");
        } catch (Exception e) {
            System.err.println("failed!");
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int[] getPixels() {
        return pixels;
    }
}
