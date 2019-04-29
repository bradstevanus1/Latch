package com.brad.latch.graphics.ui;

import com.brad.latch.graphics.layers.Layer;
import com.brad.latch.util.Vector2i;

import java.awt.Color;
import java.awt.Graphics;

/**
 * A component of the Graphics UI layer of the game.
 * It is the superclass for classes such as the UIPanel and
 * UILabel. However, all components must exist within
 * a UIPanel. Therefore, their position is relative to
 * where the panel exists on the screen. To get the
 * absolute position of the component on the screen,
 * use the getAbsolutePosition() method.
 */
public class UIComponent {

    public Vector2i position, size;
    Vector2i offset;
    public Color color;
    protected UIPanel panel;

    public boolean active = true;

    public UIComponent(Vector2i position) {
        this.position = position;
    }

    public UIComponent(Vector2i position, Vector2i size) {
        this.position = position;
        this.size = size;
    }

    /**
     * When called as part of addComponent, sets the
     * component's panel field to be the panel that is
     * adding that component.
     * @param panel The panel that the component belongs to.
     */
    void init(UIPanel panel) {
        this.panel = panel;
        offset = new Vector2i(panel.position);
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
