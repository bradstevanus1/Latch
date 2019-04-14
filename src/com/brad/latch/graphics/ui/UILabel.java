package com.brad.latch.graphics.ui;

import com.brad.latch.graphics.Font;
import com.brad.latch.graphics.Screen;
import com.brad.latch.util.Vector2i;

public class UILabel extends UIComponent {

    public String text;
    private Font font;

    public UILabel(Vector2i position, String text) {
        super(position);
        font = new Font();
        this.text = text;
    }

    public void render(Screen screen) {
        font.render(position.x + offset.x, position.y + offset.y, -6, 0xFFFFFFFF, text, screen);
    }


}
