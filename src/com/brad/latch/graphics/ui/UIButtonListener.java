package com.brad.latch.graphics.ui;

import java.awt.*;
import java.util.EventListener;

public interface UIButtonListener extends EventListener {

    default void buttonEntered(UIButton button) {
        button.setColor(new Color(0xCDCDCD).getRGB());
    }

    default void buttonExited(UIButton button) {
        button.setColor(new Color(0x969696).getRGB());
    }

    default void buttonPressed(UIButton button) {

    }

    default void buttonReleased(UIButton button) {

    }


}
