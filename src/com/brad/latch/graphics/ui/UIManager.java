package com.brad.latch.graphics.ui;

import com.brad.latch.graphics.Screen;

import java.util.ArrayList;
import java.util.List;

public class UIManager {

    private List<UIPanel> panels = new ArrayList<>();

    public UIManager() {

    }

    public void addPanel(UIPanel panel) {
        panels.add(panel);
    }

    public void update() {
        for (UIPanel panel : panels) {
            panel.update();
        }
    }

    public void render(Screen screen) {
        for (UIPanel panel : panels) {
            panel.render(screen);
        }
    }

}
