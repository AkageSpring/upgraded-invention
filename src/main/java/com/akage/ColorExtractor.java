package com.akage;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

public class ColorExtractor {

    private record GameColor(int id, String name, String hex) {
    }

    private ArrayList<GameColor> loadColors(String filePath) {
        Reader in;
        try {
            in = new FileReader(filePath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        Iterable<CSVRecord> records;
        CSVFormat myFormat = CSVFormat.DEFAULT.builder()
                .setHeader()
                .setSkipHeaderRecord(true)
                .get();
        try {
            records = myFormat.parse(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ArrayList<GameColor> metallicColors = new ArrayList<>();
        for (CSVRecord record : records) {
            metallicColors.add(new GameColor(
                    Integer.parseInt(record.get("number")),
                    record.get("name"),
                    record.get("hex")
            ));
        }
        return metallicColors;
    }

    public ForzaHorizonColor getForzaHorizonColor(String filePath, int colorNumber) {
        BufferedImage image;
        try {
            image = ImageIO.read(new File(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int x = 230 + 82 * (colorNumber - 1);
        int y1 = 797;
        int y2 = 877;
        int argb1 = image.getRGB(x, y1);
        int argb2 = image.getRGB(x, y2);
        String hex1 = String.format("%06X", argb1 & 0x00ffffff);
        ForzaHorizonColor forzaHorizonColor;
        if (argb1 == argb2) {
            forzaHorizonColor = new ForzaHorizonColor(hex1);
        } else {
            String hex2 = String.format("%06X", argb2 & 0x00ffffff);
            forzaHorizonColor = new ForzaHorizonColor(hex1, hex2);
        }
        return forzaHorizonColor;
    }

    public String getColor(String filePath, int colorNumber) {
        ForzaHorizonColor forzaHorizonColor = getForzaHorizonColor(filePath, colorNumber);
        if (!forzaHorizonColor.isTwoColored()) {
            return "Color: #" + forzaHorizonColor.getHex1();
        } else {
            return "Color 1: #" + forzaHorizonColor.getHex1() + "\nColor 2: #" + forzaHorizonColor.getHex2();
        }
    }

    private static int[] hexToRgb(String hex) {
        hex = hex.replace("#", "");
        return new int[]{
                Integer.parseInt(hex.substring(0, 2), 16),
                Integer.parseInt(hex.substring(2, 4), 16),
                Integer.parseInt(hex.substring(4, 6), 16)
        };
    }

    private double getColorDifference(String hex1, String hex2) {
        int[] rgb1 = hexToRgb(hex1);
        int[] rgb2 = hexToRgb(hex2);
        int redDifference = rgb1[0] - rgb2[0];
        int greenDifference = rgb1[1] - rgb2[1];
        int blueDifference = rgb1[2] - rgb2[2];
        return Math.sqrt(Math.pow(redDifference, 2) + Math.pow(greenDifference, 2) + Math.pow(blueDifference, 2));
    }

    public String getBestColor(String userColor, String colorFile) {
        ArrayList<GameColor> gameColors = loadColors(colorFile);
        double difference = 999.0;
        GameColor bestColor = new GameColor(-1, "Unknown Color", "#000000");
        for (GameColor gameColor : gameColors) {
            double currentDifference = getColorDifference(userColor, gameColor.hex);
            if (currentDifference < difference) {
                difference = currentDifference;
                bestColor = gameColor;
            }
        }
        return bestColor.id + " / 75 " + bestColor.name;
    }
}
