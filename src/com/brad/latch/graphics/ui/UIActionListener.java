package com.brad.latch.graphics.ui;


/**
 * The listener interface for performing actions based
 * on when a button is defined to be activated, or
 * pressed and released within its bounds.
 */
@FunctionalInterface
public interface UIActionListener {

    void perform();

}
