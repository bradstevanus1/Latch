package com.brad.latch.graphics.ui;

import com.brad.latch.input.Mouse;
import com.brad.latch.util.Vector2i;

import java.awt.*;

public class UIButton extends UIComponent implements UIButtonListener {

    private UILabel label;
    private UIActionListener actionListener;

    private boolean insideButton = false;

    public UIButton(Vector2i position, Vector2i size, UIActionListener actionListener) {
        super(position, size);
        this.actionListener = actionListener;
        Vector2i labelPosition = new Vector2i(position);
        labelPosition.x += 4;
        labelPosition.y += size.y - 2;
        label = new UILabel(labelPosition, "");
        label.setColor(new Color(0XEBEBEB).getRGB());
        label.active = false;

        setColor(new Color(0x969696).getRGB());
    }

    @Override
    void init(UIPanel panel) {
        super.init(panel);
        panel.addComponent(label);
    }

    public void setText(String text) {
        if (text.equals(""))
            label.active = false;
        else
            label.text = text;
    }

    public void update() {
        Rectangle rect = new Rectangle(getAbsolutePosition().x, getAbsolutePosition().y, size.x, size.y);
        if (rect.contains(new Point(Mouse.getX(), Mouse.getY()))) {
            if (!insideButton)
                buttonEntered(this);
            insideButton = true;
        } else {
            if (insideButton)
                buttonExited(this);
            insideButton = false;
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect(position.x + offset.x, position.y + offset.y, size.x, size.y);
        label.update();
        if (label != null) {
            label.render(g);
        }
    }

}