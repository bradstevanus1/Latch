package com.brad.rain.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpriteSheet {

    private String path;
    public final int SIZE;
    public final int WIDTH, HEIGHT;
    public int[] pixels;

    private Sprite[] sprites;

    public SpriteSheet(SpriteSheet sheet, int x, int y, int width, int height, int spriteSize) {
        int xSprite = x * spriteSize;
        int ySprite = y * spriteSize;
        int spriteWidth = width * spriteSize;
        int spriteHeight = height * spriteSize;
        if (width == height) SIZE = width;
        else SIZE = -1;
        WIDTH = spriteWidth;
        HEIGHT = spriteHeight;
        pixels = new int[spriteWidth * spriteHeight];
        for (int vertical = 0; vertical < spriteHeight; vertical++) {
            int yRelToScreen = ySprite + vertical;
            for (int horizontal = 0; horizontal < spriteWidth; horizontal++) {
                int xRelToScreen = xSprite + horizontal;
                pixels[horizontal + vertical * spriteWidth] = sheet.pixels[xRelToScreen + yRelToScreen * sheet.WIDTH];
            }
        }
        int frame = 0;
        sprites = new Sprite[width * height];
        for (int ya = 0; ya < height; ya++) {
            for (int xa = 0; xa < width; xa++) {
                int[] spritePixels = new int[spriteSize * spriteSize];
                for (int y0 = 0; y0 < spriteSize; y0++) {
                    for (int x0 = 0; x0 < spriteSize; x0++) {
                        spritePixels[x0 + y0 * spriteSize] = pixels[(x0 + xa * spriteSize) + (y0 + ya *spriteSize) * WIDTH];
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
        WIDTH = size;
        HEIGHT = size;
        pixels = new int[SIZE * SIZE];
        load();
    }


    public SpriteSheet(String path, int width, int height) {
        this.path = path;
        SIZE = -1;
        WIDTH = width;
        HEIGHT = height;
        pixels = new int[WIDTH * HEIGHT];
        load();
    }

    public Sprite[] getSprites() {
        return sprites;
    }

    public void load() {
        try {
            BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
            int w = image.getWidth();
            int h = image.getHeight();
            image.getRGB(0, 0, w, h, pixels, 0, w);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
