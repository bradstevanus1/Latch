package com.brad.latch.events.types;

import com.brad.latch.events.Event;

public class MouseReleasedEvent extends MouseButtonEvent {

    protected MouseReleasedEvent(int button, int x, int y) {
        super(button, x, y, Type.MOUSE_RELEASED);
    }

}
