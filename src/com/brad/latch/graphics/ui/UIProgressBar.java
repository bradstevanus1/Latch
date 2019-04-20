package com.brad.latch.graphics.ui;

import com.brad.latch.util.Vector2i;
import org.w3c.dom.ranges.RangeException;

import java.awt.*;

public class UIProgressBar extends UIComponent {

    private int progress; // 0 - 100
    private Color foregroundColor;

    public UIProgressBar(Vector2i position, Vector2i size) {
        super(position);
        this.size = size;
        setForegroundColor(0xFF00FF);
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect(position.x + offset.x, position.y + offset.y, size.x, size.y);
        g.setColor(foregroundColor);
        g.fillRect(position.x + offset.x, position.y + offset.y, (int) (progress/100.0 * size.x), size.y);
    }

    public void setForegroundColor(int color) {
        this.foregroundColor = new Color(color);
    }

    public void setProgress(int progress) {
        if (progress < 0 || progress > 100) {
            throw new RangeException(RangeException.BAD_BOUNDARYPOINTS_ERR, "Progress must be between 0.0 and 1.0");
        }
        this.progress = progress;
    }

    public int getProgress() {
        return progress;
    }
}
