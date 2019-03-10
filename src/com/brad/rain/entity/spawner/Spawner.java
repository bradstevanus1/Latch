package com.brad.rain.entity.spawner;

import com.brad.rain.entity.Entity;
import com.brad.rain.entity.particle.Particle;
import com.brad.rain.level.Level;

import java.util.ArrayList;
import java.util.List;

public class Spawner extends Entity {

    private List<Entity> entities = new ArrayList<Entity>();

    public enum Type {
        MOB, PARTICLE
    }

    private Type type;

    public Spawner(int x, int y, Type type, int amount, Level level) {
        init(level);
        this.x = x;
        this.y = y;
        this.type = type;
    }

}
