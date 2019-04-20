//package com.brad.latch.util;
//
///**
// * 2D Vector class with generic number components
// */
//public class Vector2g<T extends Number> {
//
//    public T x;
//    public T y;
//
//    public Vector2g() {
//        set(0, 0);
//    }
//
//    public Vector2g(T x, T y) {
//        set(x, y);
//    }
//
//    public Vector2g(Vector2g<T> vector) {
//        set(vector.x, vector.y);
//    }
//
//    public Vector2g add(T x, T y) {
//        this.x += x;
//        this.y += y;
//        return this;
//    }
//
//    public Vector2g add(Vector2g vector) {
//        this.x += vector.x;
//        this.y += vector.y;
//        return this;
//    }
//
//    public Vector2g subtract(T x, T y) {
//        this.x -= x;
//        this.y -= y;
//        return this;
//    }
//
//    public Vector2g subtract(Vector2g vector) {
//        this.x -= vector.x;
//        this.y -= vector.y;
//        return this;
//    }
//
//    public double distanceTo(Vector2g vector) {
//        double dx = vector.x.doubleValue() - this.x.doubleValue();
//        double dy = vector.y.doubleValue() - this.y.doubleValue();
//        return Math.sqrt(dx*dx + dy*dy);
//    }
//
//    public double dotProduct(Vector2g vector) {
//        return this.x.doubleValue() * vector.x.doubleValue() +
//                this.y.doubleValue() * vector.y.doubleValue();
//    }
//
//    public double crossProduct(Vector2g vector) {
//        return 0.0;
//    }
//
//    public void set(T x, T y) {
//        this.x = x;
//        this.y = y;
//    }
//
//    public T getX() {
//        return x;
//    }
//
//    public Vector2g setX(T x) {
//        this.x = x;
//        return this;
//    }
//
//    public T getY() {
//        return y;
//    }
//
//    public Vector2g setY(T y) {
//        this.y = y;
//        return this;
//    }
//
//    public boolean equals(Object object) {
//        if (!(object instanceof Vector2g)) return false;
//        Vector2g vector = (Vector2g) object;
//        return vector.getX().equals(this.getX()) && vector.getY().equals(this.getY());
//    }
//}
