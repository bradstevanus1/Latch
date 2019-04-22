package com.brad.latch.graphics.ui;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 * Manager class for all UIPanels.
 * A panel must be added to this manager for
 * it to be updated and rendered. Also, any subclasses
 * of components must be added to panels to be updated
 * and rendered.
 */
public class UIManager {

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
