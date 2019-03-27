package com.brad.latch.util;

import java.util.Vector;

/**
 * 2D Vector class with integer components
 */
public class Vector2i {

    private int x, y;

    public Vector2i() {
        set(0, 0);
    }

    public Vector2i(int x, int y) {
        set(x, y);
    }

    public Vector2i(Vector2i vector) {
        set(vector.x, vector.y);
    }

    public Vector2i add(int x, int y) {
        this.x += x;
        this.y += y;
        return this;
    }

    public Vector2i add(Vector2i vector) {
        this.x += vector.x;
        this.y += vector.y;
        return this;
    }

    public Vector2i subtract(int x, int y) {
        this.x -= x;
        this.y -= y;
        return this;
    }

    public Vector2i subtract(Vector2i vector) {
        this.x -= vector.x;
        this.y -= vector.y;
        return this;
    }

    public double distanceTo(Vector2i vector) {
        int dx = vector.x - this.x;
        int dy = vector.y - this.y;
        return Math.sqrt(dx*dx + dy*dy);
    }

    public double dotProduct(Vector2i vector) {
        return this.x * vector.x + this.y * vector.y;
    }

    public double crossProduct(Vector2i vector) {
        return 0.0;
    }

    public void set(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public Vector2i setX(int x) {
        this.x = x;
        return this;
    }

    public int getY() {
        return y;
    }

    public Vector2i setY(int y) {
        this.y = y;
        return this;
    }

    public boolean equals(Object object) {
        if (!(object instanceof Vector2i)) return false;
        Vector2i vector = (Vector2i) object;
        return vector.getX() == this.getX() && vector.getY() == this.getY();
    }
}
