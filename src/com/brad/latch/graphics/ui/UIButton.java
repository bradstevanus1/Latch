package com.brad.latch.graphics.ui;

import com.brad.latch.input.Mouse;
import com.brad.latch.util.Vector2i;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

@SuppressWarnings("FieldCanBeLocal")
public class UIButton extends UIComponent implements UIButtonListener {

    private UILabel label;
    private UIButtonListener buttonListener;
    private UIActionListener actionListener;

    private boolean inside = false;
    private boolean pressed = false;
    private boolean ignorePressed = false;
    private boolean ignoreAction = false;

    private static final int defaultButtonColor = new Color(0XEBEBEB).getRGB();
    private static final int defaultLabelColor = new Color(0x969696).getRGB();

    public UIButton(Vector2i position, Vector2i size, UIButtonListener buttonListener,
                    UIActionListener actionListener) {
        super(position, size);
        this.buttonListener = buttonListener;
        this.actionListener = actionListener;
        Vector2i labelPosition = new Vector2i(position);
        labelPosition.x += 4;
        labelPosition.y += size.y - 2;
        label = new UILabel(labelPosition, "");
        label.setColor(defaultButtonColor);
        label.active = false;

        setColor(defaultLabelColor);
    }

    @Override
    void init(UIPanel panel) {
        super.init(panel);
        panel.addComponent(label);
    }

    public void setButtonListener(UIButtonListener buttonListener) {
        this.buttonListener = buttonListener;
    }

    public void setText(String text) {
        if (text.equals(""))
            label.active = false;
        else
            label.text = text;
    }

    public void performAction() {
        actionListener.perform();
    }

    public void ignoreNextPress() {
        ignoreAction = true;
    }

    public void update() {
        Rectangle rect = new Rectangle(getAbsolutePosition().x, getAbsolutePosition().y, size.x, size.y);
        boolean leftMouseButtonPressed = Mouse.getButton() == MouseEvent.BUTTON1;
        if (rect.contains(new Point(Mouse.getX(), Mouse.getY()))) {
            if (!inside) {
                if (leftMouseButtonPressed)
                    ignorePressed = true;
                else
                    ignorePressed = false;
                buttonListener.buttonEntered(this);
            }
            inside = true;

            if (!pressed && !ignorePressed && leftMouseButtonPressed) {
                buttonListener.buttonPressed(this);
                pressed = true;
            } else if (Mouse.getButton() == MouseEvent.NOBUTTON) {
                if (pressed) {
                    buttonListener.buttonReleased(this);
                    if (!ignoreAction)
                        actionListener.perform();
                    else
                        ignoreAction = false;
                    pressed = false;
                }
                ignorePressed = false;
            }
        } else {
            if (inside) {
                buttonListener.buttonExited(this);
                pressed = false;
            }
            inside = false;
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