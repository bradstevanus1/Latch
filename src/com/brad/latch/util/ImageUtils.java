package com.brad.latch.util;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;

import static com.brad.latch.util.MathUtils.*;

public class ImageUtils {

    private ImageUtils() {}

    /**
     * Creates a brighter version of the image specified.
     * @param image     BufferedImage to be made brighter.
     * @param amount    Amount to raise the brightness by.
     * @return          Brighter version of the original image.
     */
    public static BufferedImage getDifferentBrightnessImage(BufferedImage image, int amount) {

        // Creates a new buffered image from the old image.
        BufferedImage resultImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        byte[] imagePixels = ((DataBufferByte)image.getRaster().getDataBuffer()).getData();
        int[] resultImagePixels = ((DataBufferInt)resultImage.getRaster().getDataBuffer()).getData();

        // Modifies each pixel in the hover image to have slightly higher brightness.
        int offset = 0;
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                System.out.println(offset);
                int a = Byte.toUnsignedInt(imagePixels[offset++]);
                int r = Byte.toUnsignedInt(imagePixels[offset++]);
                int g = Byte.toUnsignedInt(imagePixels[offset++]);
                int b = Byte.toUnsignedInt(imagePixels[offset++]);


                r = clamp(r + amount, 0, 255);
                g = clamp(g + amount, 0, 255);
                b = clamp(b + amount, 0, 255);

                resultImagePixels[x + y * resultImage.getWidth()] = a << 24 | r << 16 | g << 8 | b;
            }
        }
        return resultImage;
    }


}
