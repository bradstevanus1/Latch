package com.brad.latch.graphics.ui;

import com.brad.latch.input.Mouse;
import com.brad.latch.util.Vector2i;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

/**
 * Creates a button in the UI.
 */
@SuppressWarnings("FieldCanBeLocal")
public class UIButton extends UIComponent implements UIButtonListener {

    private UILabel label;
    private UIButtonListener buttonListener;
    private UIActionListener actionListener;

    protected BufferedImage image;

    private boolean inside = false;
    private boolean pressed = false;
    private boolean ignorePressed = false;
    private boolean ignoreAction = false;

    private static final int defaultButtonColor = new Color(0XEBEBEB).getRGB();
    private static final int defaultLabelColor = new Color(0x969696).getRGB();

    /**
     * @param position       Position of the button.
     * @param size           Size of the button.
     * @param buttonListener New instance of the buttonListener interface that can
     *                       override some button methods with an inner class if necessary.
     * @param actionListener New instance of the actionListener interface that must
     *                       override the perform method.
     */
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
        init();
    }

    public UIButton(Vector2i position, BufferedImage image, UIButtonListener buttonListener,
                    UIActionListener actionListener) {
        super(position, new Vector2i(image.getWidth(), image.getHeight()));
        this.buttonListener = buttonListener;
        this.actionListener = actionListener;
        setImage(image);
        init();
    }

    private void init() {
        setColor(defaultLabelColor);
    }

    @Override
    void init(UIPanel panel) {
        super.init(panel);
        if (label != null)
            panel.addComponent(label);
    }

    /**
     * Sets the image for the button.
     * @param image The button to put in the button.
     */
    public void setImage(BufferedImage image) {
        this.image = image;
    }

    /**
     * Changes the functionality of what happens when buttons
     * are pressed, released, exited, and entered.
     *
     * @param buttonListener New instance of the buttonListener interface
     *                       that overrides some methods.
     */
    public void setButtonListener(UIButtonListener buttonListener) {
        this.buttonListener = buttonListener;
    }

    public void setText(String text) {
        if (text.equals(""))
            label.active = false;
        else
            label.setText(text);
    }

    public void performAction() {
        actionListener.perform();
    }

    public void ignoreNextPress() {
        ignoreAction = true;
    }

    /**
     * Dictates the syntax of what constitutes the semantics of
     * pressing, releasing, entering, or exiting a button.
     */
    //TODO fix how it doesnt fire if you go back into the button.
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
        int x = position.x + offset.x;
        int y = position.y + offset.y;
        if (image != null) {
            g.drawImage(image, x, y, null);
        } else {
            g.setColor(color);
            g.fillRect(x, y, size.x, size.y);
            label.update();
            if (label != null) {
                label.render(g);
            }
        }
    }
}