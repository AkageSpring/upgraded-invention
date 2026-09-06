package com.akage;

public class Main {
    public static void main(String[] args) {
        ColorExtractor colorExtractor = new ColorExtractor();
        System.out.print(colorExtractor.getColor(args[0], Integer.parseInt(args[1])));
    }
}