package com.brad.latch.graphics;

public class Font {

    public Font() {

    }

    public void render(Screen screen) {
        screen.renderSprite(50, 50, SpriteCollection.characters[1], false);
    }

}
