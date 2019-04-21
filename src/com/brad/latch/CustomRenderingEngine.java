package com.brad.latch;

import com.brad.latch.graphics.Screen;

/**
 * Interface for rendering all assets that use the
 * custom Latch rendering engine.
 */
@FunctionalInterface
public interface CustomRenderingEngine {

    /**
     * Must call a rendering method within the screen class.
     * @param screen    instance of the rendering class
     */
    void render(Screen screen);

}