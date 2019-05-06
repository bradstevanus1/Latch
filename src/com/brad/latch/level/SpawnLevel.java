package com.brad.latch.level;

import com.brad.latch.entity.mob.enemy.AdvancedChaser;
import com.brad.latch.entity.mob.enemy.Chaser;
import com.brad.latch.entity.mob.enemy.Shooter;
import com.brad.latch.entity.mob.friendly.Traveller;
import com.brad.latch.level.tile.TileCoordinate;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpawnLevel extends Level {

    public SpawnLevel(String path) {
        super(path);
        spawnPoint = new TileCoordinate(19, 62);
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


        add(Chaser.straggler(new TileCoordinate(25, 55)));
        add(Shooter.halbird(new TileCoordinate(20, 55)));
        add(AdvancedChaser.Pokey(new TileCoordinate(15, 55)));
        for (int i = 0; i < 10; i++) {
            add(new Traveller(new TileCoordinate(15, 12)));
        }
    }


    protected void generateLevel() {
    }

}
