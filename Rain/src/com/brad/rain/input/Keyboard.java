package com.brad.rain.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

    private boolean[] keys = new boolean[120]; // or 65536
    public boolean up, down, left, right;

    public void update() {
        up =
    }

    @Override
    public void keyTyped(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
