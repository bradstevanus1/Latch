package com.brad.latch.graphics.ui;

import com.brad.latch.util.Vector2i;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 * Labels are components that have text, and as such,
 * can have certain fonts (defined by Java's font class)
 * and shadows.
 */
public class UILabel extends UIComponent {

    public String text;
    private Font font;
    public boolean dropShadow = false;
    public int dropShadowOffset = 2;

    /**
     * Creates a label at a vector position relative to
     * the panel, with the specified text.
     * @param position Vector position relative to the panel.
     * @param text Text to display.
     */
    public UILabel(Vector2i position, String text) {
        super(position);
        font = new Font("Verdana", Font.PLAIN, 32);
        this.text = text;
        color = new Color(0xFFFF00FF);
    }

    public UILabel setFont(Font font) {
        this.font = font;
        return this;
    }

    /**
     * Renders the font and any dropshadows.
     * @param g Graphics object.
     */
    public void render(Graphics g) {
        if (dropShadow) {
            g.setFont(font);
            g.setColor(Color.BLACK);
            g.drawString(text, position.x + offset.x + dropShadowOffset, position.y + offset.y + dropShadowOffset);
        }
        g.setFont(font);
        g.setColor(color);
        g.drawString(text, position.x + offset.x, position.y + offset.y);
    }


}
