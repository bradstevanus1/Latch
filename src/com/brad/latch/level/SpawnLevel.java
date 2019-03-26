package com.brad.latch.level;

import com.brad.latch.entity.mob.Straggler;
import com.brad.latch.entity.mob.Traveller;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpawnLevel extends Level {

    public SpawnLevel(String path) {
        super(path);
    }

    protected void loadLevel(String path) {
        try {
            BufferedImage image = ImageIO.read(SpawnLevel.class.getResource(path));
            int w = width = image.getWidth();
            int h = height = image.getHeight();
            tiles = new int[w * h];
            image.getRGB(0, 0, w, h, tiles, 0, w);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Exception! Could not load level file!");
        }
        for (int i = 0; i < 5; i++) {
            add(new Straggler(20, 55, 0.5));
            add(new Traveller(20, 56, 0.8));
        }
    }


    protected void generateLevel() {
    }

}
