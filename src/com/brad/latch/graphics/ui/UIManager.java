package com.brad.latch.graphics.ui;

import com.brad.latch.events.Event;
import com.brad.latch.events.EventDispatcher;
import com.brad.latch.events.types.MousePressedEvent;
import com.brad.latch.events.types.MouseReleasedEvent;
import com.brad.latch.graphics.layers.Layer;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

/**
 * Manager class for all UIPanels.
 * A panel must be added to this manager for
 * it to be updated and rendered. Also, any subclasses
 * of components must be added to panels to be updated
 * and rendered.
 */
public class UIManager extends Layer implements EventListener {

    private List<UIPanel> panels = new ArrayList<>();

    public UIManager() {

    }

    /**
     * Adds a panel to the UIManager.
     * @param panel panel to add.
     */
    public void addPanel(UIPanel panel) {
        panels.add(panel);
    }

    public void update() {
        for (UIPanel panel : panels) {
            panel.update();
        }
    }

    public void render(Graphics g) {
        for (UIPanel panel : panels) {
            panel.render(g);
        }
    }

}
