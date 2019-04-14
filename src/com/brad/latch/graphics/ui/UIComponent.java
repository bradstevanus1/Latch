package com.brad.latch.graphics.ui;

import com.brad.latch.graphics.Screen;
import com.brad.latch.util.Vector2i;

public class UIComponent {

    public int backgroundColor;
    public Vector2i position, offset;

    public UIComponent(Vector2i position) {
        this.position = position;
        offset = new Vector2i();
    }

    public void update() {

    }

    public void render(Screen screen) {

    }

    void setOffset(Vector2i offset) {
        this.offset = offset;
    }

}
