package com.brad.latch.graphics;

/**
 * Custom font class that displays text in our rendering engine.
 */
public class Font implements Sprites {

    private static final String characters_static =
            "ABCDEFGHIJKLM" + //
                    "NOPQRSTUVWXYZ" + //
                    "abcdefghijklm" + //
                    "nopqrstuvwxyz" + //
                    "0123456789.,'" + //
                    "'\"\";:!@$%()-+";

    public Font() {

    }

    public void render(int x, int y, String text, Screen screen) {
        render(x, y, 0, text, screen);
    }

    public void render(int x, int y, int colour, String text, Screen screen) {
        render(x, y, 0, colour, text, screen);
    }

    public void render(int x, int y, int spacing, int colour, String text, Screen screen) {
        int line = 0;
        int xOffset = 0;
        for (int i = 0; i < text.length(); i++) {
            xOffset += 16 + spacing;
            int yOffset = 0;
            int currentChar = text.charAt(i);
            if (currentChar == 'g' || currentChar == 'y' || currentChar == 'q' || currentChar == 'p' ||
                    currentChar == 'j' || currentChar == ',') {
                yOffset = 4;
            } else if (currentChar == '\n') {
                line++;
                xOffset = 0;
            }
            int charIndex = characters_static.indexOf(currentChar);

            if (charIndex == -1) continue;
            screen.renderTextCharacter(x + xOffset, y + line * 20 + yOffset,
                    characters[charIndex], colour, false);
        }
    }

}
