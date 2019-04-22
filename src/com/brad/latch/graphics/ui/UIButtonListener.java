package com.brad.latch.graphics.ui;

import java.awt.Color;
import java.util.EventListener;

/**
 * The listener interface for receiving events on buttons.
 * It is responsible for changing button states but not
 * executing the actions associated with pressing the button.
 */
public interface UIButtonListener extends EventListener {

    int defaultRestColor = new Color(0x969696).getRGB();
    int defaultHoverColor = new Color(0xAAAAAA).getRGB();
    int defaultPressColor = new Color(0xC8C8C8).getRGB();

    default void buttonEntered(UIButton button) {
        button.setColor(defaultHoverColor);
    }

    default void buttonExited(UIButton button) {
        button.setColor(defaultRestColor);
    }

    default void buttonPressed(UIButton button) {
        button.setColor(defaultPressColor);
    }

    default void buttonReleased(UIButton button) {
        button.setColor(defaultRestColor);
    }

}
