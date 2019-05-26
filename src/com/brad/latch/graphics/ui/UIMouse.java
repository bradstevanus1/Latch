package com.brad.latch.graphics.ui;

import com.brad.latch.events.Event;
import com.brad.latch.events.EventDispatcher;
import com.brad.latch.events.types.MouseMovedEvent;
import com.brad.latch.events.types.MousePressedEvent;
import com.brad.latch.events.types.MouseReleasedEvent;
import com.brad.latch.input.Mouse;
import com.brad.latch.util.Vector2i;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class UIMouse extends UIComponent {

    private BufferedImage image;

    public UIMouse(Vector2i position, Vector2i size, BufferedImage image) {
        super(position, size);
        this.image = image;

    }

    @Override
    public void update() {

    }

    public void render(Graphics g) {
        int x = position.x;
        int y = position.y;
        g.drawImage(image, x, y, null);
    }

}
