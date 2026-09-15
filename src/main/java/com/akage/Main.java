package com.akage;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ColorExtractor colorExtractor = new ColorExtractor();
        System.out.println("1. Extract color from screenshot");
        System.out.println("2. Get similar color");
        System.out.println("3. Extract color and get similar (in development)");
        System.out.println("0. Quit");
        System.out.print("> ");

        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();

        if (choice.equals("0")) {
            System.exit(0);
        }

        if (choice.equals("1")) {
            System.out.print("File path: > ");
            String filePath = scanner.nextLine();
            filePath = filePath.replace("\"", "");
            System.out.print("Color number: > ");
            String colorNumber = scanner.nextLine();
            System.out.println(colorExtractor.getColor(filePath, Integer.parseInt(colorNumber)));
        }

        if (choice.equals("2")) {
            System.out.print("Your Grand Theft Auto Color HEX: > ");
            String userColor = scanner.nextLine();
            System.out.print("Grand Theft Auto Colors File: > ");
            String filePath = scanner.nextLine();
            filePath = filePath.replace("\"", "");
            System.out.println(colorExtractor.getSimilarColor(userColor, filePath));
        }

        if (choice.equals("3")) {
            System.out.print("Screenshot absolute path: > ");
            String screenshotAbsolutePath = scanner.nextLine();
            screenshotAbsolutePath = screenshotAbsolutePath.replace("\"", "");
            System.out.print("Color number: > ");
            String colorNumber = scanner.nextLine();
            System.out.print("Grand Theft Auto Colors File: > ");
            String grandTheftAutoColorsFile = scanner.nextLine();
            grandTheftAutoColorsFile = grandTheftAutoColorsFile.replace("\"", "");

            ForzaHorizonColor forzaHorizonColor = colorExtractor.getForzaHorizonColor(
                    screenshotAbsolutePath,
                    Integer.parseInt(colorNumber)
            );
            System.out.println("Forza Horizon Color 1: " + forzaHorizonColor.getHex1());
            System.out.println("Forza Horizon Color 2: " + forzaHorizonColor.getHex2());

            try (Playwright playwright = Playwright.create()) {
                Browser browser = playwright.chromium().launch();
                Page page = browser.newPage();
                page.navigate("https://gtacolors.com/configurator");
                page.locator("input.pcr-result").clear();
                page.locator("input.pcr-result").pressSequentially("#" + forzaHorizonColor.getHex1());
                page.keyboard().press("Enter");
                page.waitForTimeout(1000);
                String grandTheftAutoColor = page.getByPlaceholder("GTA Computed").inputValue();
                System.out.println("Grand Theft Auto Color 1: " + grandTheftAutoColor);
                System.out.println(colorExtractor.getSimilarColor(grandTheftAutoColor, grandTheftAutoColorsFile));
                page.close();
                browser.close();
            }
        }
    }
}