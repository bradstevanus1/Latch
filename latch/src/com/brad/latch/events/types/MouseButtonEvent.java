package com.brad.latch.events.types;

import com.brad.latch.events.Event;

public class MouseButtonEvent extends Event {

    protected int button;
    protected int x, y;

    protected MouseButtonEvent(int button, int x, int y, Type type) {
        super(type);
        this.button = button;
        this.x = x;
        this.y = y;
    }

    public int getButton() {
        return button;
    }

    public void setButton(int button) {
        this.button = button;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
