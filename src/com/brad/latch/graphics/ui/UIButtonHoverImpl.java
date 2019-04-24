package com.brad.latch.graphics.ui;

import com.brad.latch.util.ImageUtils;

import java.awt.image.BufferedImage;

public class UIButtonHoverImpl implements UIButtonListener {

    private BufferedImage image;

    public UIButtonHoverImpl(BufferedImage image) {
        this.image = image;
    }

    public void buttonEntered(UIButton button) {
        button.setImage(ImageUtils.getDifferentBrightnessImage(image, 25));
    }

    public void buttonExited(UIButton button) {
        button.setImage(image);
    }

    public void buttonPressed(UIButton button) {
        button.setImage(ImageUtils.getDifferentBrightnessImage(image, 50));
    }

    public void buttonReleased(UIButton button) {
        button.setImage(image);
    }

}
