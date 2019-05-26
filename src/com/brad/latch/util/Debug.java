package com.brad.latch.util;


import com.brad.latch.graphics.Screen;

/**
 * Static class for displaying debug information
 * on screen.
 */
public class Debug {

    /**
     * Don't let anyone instantiate this class.
     */
    private Debug() {}

    public static void drawRect(Screen screen, int x, int y, int width, int height, boolean fixed) {
        screen.drawRect(x, y, width, height, 0xff0000, fixed);
    }

    public static void drawRect(Screen screen, int x, int y, int width, int height, int colour, boolean fixed) {
        screen.drawRect(x, y, width, height, colour, fixed);
    }

    public static <T> void print(T message) {
        System.out.println(message);
    }

}
