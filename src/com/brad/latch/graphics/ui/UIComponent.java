package com.brad.latch.graphics.ui;

import com.brad.latch.graphics.Screen;
import com.brad.latch.util.Vector2i;

import java.awt.*;

public class UIComponent {

    public Vector2i position, size;
    Vector2i offset;
    public Color color;
    protected UIPanel panel;

    public boolean active = true;

    public UIComponent(Vector2i position) {
        this.position = position;
        offset = new Vector2i();
    }

    public UIComponent(Vector2i position, Vector2i size) {
        this.position = position;
        this.size = size;
        offset = new Vector2i();
    }

    void init(UIPanel panel) {
        this.panel = panel;
    }

    public UIComponent setColor(int color) {
        this.color = new Color(color);
        return this;
    }

    public Vector2i getAbsolutePosition() {
        return new Vector2i(position).add(offset);
    }

    void setOffset(Vector2i offset) {
        this.offset = offset;
    }

    public void update() {

    }

    public void render(Graphics g) {
        g.setColor(color);
    }

}
