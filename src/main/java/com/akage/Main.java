package com.akage;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ColorExtractor colorExtractor = new ColorExtractor();

        System.out.println("Welcome!");
        System.out.println("1. Extract color");
        System.out.println("2. Get best color");
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
            filePath = filePath.substring(1, filePath.length() - 1);
            System.out.print("Color number: > ");
            String colorNumber = scanner.nextLine();
            System.out.println(colorExtractor.getColor(filePath, Integer.parseInt(colorNumber)));
        }

        if (choice.equals("2")) {
            System.out.print("Your color: > ");
            String userColor = scanner.nextLine();
            System.out.print("Color number: > ");
            String filePath = scanner.nextLine();
            filePath = filePath.substring(1, filePath.length() - 1);
            System.out.println(colorExtractor.getBestColor(userColor, filePath));
        }
    }
}