package com.brad.latch.graphics;

import com.brad.latch.Game;

import java.awt.*;
import java.awt.image.*;

public class SpriteRotation extends Canvas {

    // TODO will probably delete this later if it cannot rotate a sprite

    Screen screen;
    private BufferedImage image = new BufferedImage(Game.getWindowWidth(), Game.getWindowHeight(), BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();


    public SpriteRotation(Sprite sprite) {
        screen = new Screen(Game.getWindowWidth(), Game.getWindowHeight());
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        screen.clear();
        screen.renderSprite(150, 450, sprite, true);
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = screen.pixels[i];
        }
        Graphics2D g = (Graphics2D) bs.getDrawGraphics();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        g.dispose();
        bs.show();
    }


}


