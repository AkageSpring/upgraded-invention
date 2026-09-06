package com.akage;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ColorExtractorTest {
    @CsvSource({
            "1, '#3B67CA'",
            "2, '#121214'",
            "3, '#C1BFC2'",
            "4, '#BEC1D0'",
            "5, '#EDEDED'",
            "6, '#E2E8E6'",
            "7, '#9E9381'"
    })
    @ParameterizedTest
    void getColor(int colorNumber, String expectedColor) {
        ColorExtractor colorExtractor = new ColorExtractor();
        String actualColor = colorExtractor.getColor("C:\\Users\\Danila\\Desktop\\Images\\Forza Horizon 4\\20260905172716_1.jpg", colorNumber);
        assertEquals(expectedColor, actualColor);
    }
}
