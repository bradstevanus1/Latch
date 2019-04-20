package com.brad.latch.entity.spawner;

import com.brad.latch.entity.Entity;
import com.brad.latch.level.Level;

import java.util.ArrayList;
import java.util.List;

public abstract class Spawner extends Entity {

    private List<Entity> entities = new ArrayList<Entity>();

    public enum Type {
        MOB, PARTICLE
    }

    protected Type type;

    public Spawner(int x, int y, Type type, int amount, Level level) {
        super(x, y);
        init(level);
        this.type = type;
    }

}
