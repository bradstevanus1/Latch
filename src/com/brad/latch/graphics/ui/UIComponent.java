package com.brad.latch.graphics.ui;

import com.brad.latch.util.Vector2i;

import java.awt.Color;
import java.awt.Graphics;

public class UIComponent {

    public int backgroundColor;
    public Vector2i position, offset;
    protected Color color;

    public UIComponent(Vector2i position) {
        this.position = position;
        offset = new Vector2i();
    }

    public UIComponent setColor(int color) {
        this.color = new Color(color);
        return this;
    }

    public void update() {

    }

    public void render(Graphics g) {

    }

    void setOffset(Vector2i offset) {
        this.offset = offset;
    }

}
