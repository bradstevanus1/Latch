package com.brad.latch.graphics.ui;

import com.brad.latch.graphics.Screen;
import com.brad.latch.graphics.Sprite;
import com.brad.latch.util.Vector2i;

import java.util.ArrayList;
import java.util.List;

public class UIPanel {

    private List<UIComponent> components = new ArrayList<>();
    private Vector2i position;
    private Sprite sprite;

    public UIPanel(Vector2i position) {
        this.position = position;
        sprite = new Sprite(80, 168, 0xCACACA);
    }

    public void addComponent(UIComponent component) {
        components.add(component);
    }

    public void update() {
        for (UIComponent component : components) {
            component.setOffset(position);
            component.update();
        }
    }

    public void render(Screen screen) {
        screen.renderSprite(position.x, position.y, sprite, false);
        for (UIComponent component : components) {
            component.render(screen);
        }
    }

}