package com.akage;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ColorExtractorTest {
    @CsvSource(
            textBlock = """
                    C:\\Users\\Danila\\Desktop\\Images\\Forza Horizon 4\\Nissan GT-R '02 Colors.jpg, 1, 3B67CA
                    C:\\Users\\Danila\\Desktop\\Images\\Forza Horizon 4\\Nissan GT-R '02 Colors.jpg, 2, 121214
                    C:\\Users\\Danila\\Desktop\\Images\\Forza Horizon 4\\Nissan GT-R '02 Colors.jpg, 3, C1BFC2
                    C:\\Users\\Danila\\Desktop\\Images\\Forza Horizon 4\\Nissan GT-R '02 Colors.jpg, 4, BEC1D0
                    C:\\Users\\Danila\\Desktop\\Images\\Forza Horizon 4\\Nissan GT-R '02 Colors.jpg, 5, EDEDED
                    C:\\Users\\Danila\\Desktop\\Images\\Forza Horizon 4\\Nissan GT-R '02 Colors.jpg, 6, E2E8E6
                    C:\\Users\\Danila\\Desktop\\Images\\Forza Horizon 4\\Nissan GT-R '02 Colors.jpg, 7, 9E9381
                    """
    )
    @ParameterizedTest
    void getForzaHorizonOneColorTest(String filePath, int colorNumber, String expectedHex1) {
        ColorExtractor colorExtractor = new ColorExtractor();
        ForzaHorizonColor forzaHorizonColor = colorExtractor.getForzaHorizonColor(filePath, colorNumber);
        String actualHex1 = forzaHorizonColor.getHex1();
        assertEquals(expectedHex1, actualHex1);
    }

    @CsvSource(
            textBlock = """
                    C:\\Users\\Danila\\Desktop\\Images\\Forza Horizon 4\\Dodge Charger '69 Colors.jpg, 1, F95E02, 070707
                    C:\\Users\\Danila\\Desktop\\Images\\Forza Horizon 4\\Dodge Charger '69 Colors.jpg, 2, C8661F, D0D0D0
                    C:\\Users\\Danila\\Desktop\\Images\\Forza Horizon 4\\Dodge Charger '69 Colors.jpg, 3, D18A54, 070707
                    C:\\Users\\Danila\\Desktop\\Images\\Forza Horizon 4\\Dodge Charger '69 Colors.jpg, 4, BB9333, 070707
                    C:\\Users\\Danila\\Desktop\\Images\\Forza Horizon 4\\Dodge Charger '69 Colors.jpg, 5, 070707, B40709
                    C:\\Users\\Danila\\Desktop\\Images\\Forza Horizon 4\\Dodge Charger '69 Colors.jpg, 6, B30608, D0D0D0
                    C:\\Users\\Danila\\Desktop\\Images\\Forza Horizon 4\\Dodge Charger '69 Colors.jpg, 7, E71C16, D0D0D0
                    C:\\Users\\Danila\\Desktop\\Images\\Forza Horizon 4\\Dodge Charger '69 Colors.jpg, 8, E9E1B3, 070707
                    C:\\Users\\Danila\\Desktop\\Images\\Forza Horizon 4\\Dodge Charger '69 Colors.jpg, 9, F0D56C, 070707
                    C:\\Users\\Danila\\Desktop\\Images\\Forza Horizon 4\\Dodge Charger '69 Colors.jpg, 10, 8DA67F, 070707
                    C:\\Users\\Danila\\Desktop\\Images\\Forza Horizon 4\\Dodge Charger '69 Colors.jpg, 11, 0E4120, D0D0D0
                    C:\\Users\\Danila\\Desktop\\Images\\Forza Horizon 4\\Dodge Charger '69 Colors.jpg, 12, 0A2A12, D0D0D0
                    C:\\Users\\Danila\\Desktop\\Images\\Forza Horizon 4\\Dodge Charger '69 Colors.jpg, 13, 2D7B87, D0D0D0
                    C:\\Users\\Danila\\Desktop\\Images\\Forza Horizon 4\\Dodge Charger '69 Colors.jpg, 14, A0B9CF, 070707
                    C:\\Users\\Danila\\Desktop\\Images\\Forza Horizon 4\\Dodge Charger '69 Colors.jpg, 15, 2D77C0, D0D0D0
                    C:\\Users\\Danila\\Desktop\\Images\\Forza Horizon 4\\Dodge Charger '69 Colors.jpg, 16, 0B3C81, D0D0D0
                    C:\\Users\\Danila\\Desktop\\Images\\Forza Horizon 4\\Dodge Charger '69 Colors.jpg, 17, 8C8E8D, 070707
                    C:\\Users\\Danila\\Desktop\\Images\\Forza Horizon 4\\Dodge Charger '69 Colors.jpg, 18, D0D0D0, 070707
                    """
    )
    @ParameterizedTest
    void getForzaHorizonTwoColorTest(String filePath, int colorNumber, String expectedHex1, String expectedHex2) {
        ColorExtractor colorExtractor = new ColorExtractor();
        ForzaHorizonColor forzaHorizonColor = colorExtractor.getForzaHorizonColor(filePath, colorNumber);
        String actualHex1 = forzaHorizonColor.getHex1();
        String actualHex2 = forzaHorizonColor.getHex2();
        assertEquals(expectedHex1, actualHex1);
        assertEquals(expectedHex2, actualHex2);
    }
}
