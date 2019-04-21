package com.brad.latch.entity.spawner;

import com.brad.latch.entity.particle.Particle;
import com.brad.latch.graphics.Screen;
import com.brad.latch.level.Level;

@SuppressWarnings("FieldCanBeLocal")
public class ParticleSpawner extends Spawner {

    private int life;

    public ParticleSpawner(int x, int y, int life, int amount, Level level) {
        super(x, y, Type.PARTICLE, amount, level);
        this.life = life;
        remove();
        for (int i = 0; i < amount; i++) {
            level.add(new Particle(x, y, this.life));
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Screen screen) {

    }
}
