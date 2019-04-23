package com.brad.latch.entity.spawner;

import com.brad.latch.entity.Entity;

import java.util.ArrayList;
import java.util.List;

public abstract class Spawner extends Entity {

    private List<Entity> entities = new ArrayList<>();

    public enum Type {
        MOB, PARTICLE
    }

    protected Type type;

    public Spawner(int x, int y, Type type) {
        super(x, y);
        this.type = type;
    }

    public abstract void spawn(int amount);

}
