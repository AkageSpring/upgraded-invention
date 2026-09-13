package com.akage;

public class ForzaHorizonColor {
    private final boolean TWO_COLORED;
    private final String HEX_1;
    private String hex2;

    public ForzaHorizonColor(String hex1) {
        this.TWO_COLORED = false;
        this.HEX_1 = hex1;
    }

    public ForzaHorizonColor(String hex1, String hex2) {
        this.TWO_COLORED = true;
        this.HEX_1 = hex1;
        this.hex2 = hex2;
    }

    public String getHex1() {
        return HEX_1;
    }

    public String getHex2() {
        if (isTwoColored()) {
            return hex2;
        } else {
            return null;
        }
    }

    public boolean isTwoColored() {
        return TWO_COLORED;
    }
}
