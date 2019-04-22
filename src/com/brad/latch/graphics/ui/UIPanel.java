package com.brad.latch.graphics.ui;

import com.brad.latch.util.Vector2i;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 * A panel is a subclass of the UIComponent class, but
 * all components must be contained within a panel. All panels
 * must belong to a UIManager.
 */
public class UIPanel extends UIComponent {

    private List<UIComponent> components = new ArrayList<>();
    private Vector2i size;

    /**
     * Creates a panel at an absolute vector position in pixels,
     * with the specified vector size.
     * @param position Absolute vector position of the panel.
     * @param size Vector size of the panel.
     */
    public UIPanel(Vector2i position, Vector2i size) {
        super(position);
        this.position = position;
        this.size = size;
        color = new Color(0xCACACA);
    }

    /**
     * All components must be added to the panel.
     * @param component A component.
     */
    public void addComponent(UIComponent component) {
        component.init(this);
        components.add(component);
    }

    /**
     * Updates and sets the offset of the components that belong to
     * the panel, where the offset is the position of the panel.
     * Therefore, the coordinates of the components are relative to
     * the panel.
     */
    public void update() {
        for (UIComponent component : components) {
            component.setOffset(position);
            component.update();
        }
    }

    /**
     * Renders the panel and draws all the components
     * @param g Graphics Object.
     */
    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect(position.x, position.y, size.x, size.y);
        for (UIComponent component : components) {
            component.render(g);
        }
    }

}