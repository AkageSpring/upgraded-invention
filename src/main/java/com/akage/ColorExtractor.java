package com.akage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ColorExtractor {
    public String getColor(String filePath, int colorNumber) {
        BufferedImage image;
        try {
            image = ImageIO.read(new File(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int x = 230 + 82 * (colorNumber - 1);
        int y = 846;

        int rgb = image.getRGB(x, y);
        Color color = new Color(rgb);

        return String.format("#%02X%02X%02X", color.getRed(), color.getGreen(), color.getBlue());
    }
}
