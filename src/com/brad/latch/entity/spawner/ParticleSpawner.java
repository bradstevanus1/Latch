package com.brad.latch.entity.spawner;

import com.brad.latch.entity.particle.Particle;
import com.brad.latch.graphics.Screen;
import com.brad.latch.graphics.Sprite;

@SuppressWarnings("FieldCanBeLocal")
public class ParticleSpawner extends Spawner {

    public ParticleSpawner(int x, int y) {
        super(x, y, Type.PARTICLE);
    }


    @Override
    public void spawn(int amount) {
        for (int i = 0; i < amount; i++) {
            level.add(new Particle((int) x, (int) y, Integer.MAX_VALUE));
        }
    }

    public void spawn(int amount, int life) {
        for (int i = 0; i < amount; i++) {
            level.add(new Particle((int) x, (int) y, life));
        }
    }

    public void spawn(int amount, int life, Sprite sprite) {
        for (int i = 0; i < amount; i++) {
            level.add(new Particle((int) x, (int) y, life, sprite));
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Screen screen) {

    }
}
