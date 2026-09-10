package com.akage;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


public class Main {
    public static void main(String[] args) {
//        ColorExtractor colorExtractor = new ColorExtractor();
//        System.out.print(colorExtractor.getColor(args[0], Integer.parseInt(args[1])));
        Reader in;
        try {
            in = new FileReader("C:\\Users\\Danila\\Desktop\\Java\\upgraded-invention\\metallic_colors_copy.csv");
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
        for (CSVRecord record : records) {
            String name = record.get("name");
            System.out.println(name);
        }

        List<String> lines = null;
        try {
            lines = Files.readAllLines(Path.of("C:\\Users\\Danila\\Desktop\\Java\\upgraded-invention\\metallic_colors_copy.csv"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int lineNumber = 0;
        for (String line : lines) {
            line = line.replace("#", String.valueOf(lineNumber));
            lineNumber++;
            System.out.println(line);
        }
    }
}