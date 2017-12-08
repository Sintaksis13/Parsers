package com.epam.lab23.parsers.flatwares;

public class Knife extends Flatware {

    private int bladeLength;
    private int bladeWidth;

    public int getBladeLength() {
        return bladeLength;
    }

    public void setBladeLength(int bladeLength) {
        this.bladeLength = bladeLength;
    }

    public int getBladeWidth() {
        return bladeWidth;
    }

    public void setBladeWidth(int bladeWidth) {
        this.bladeWidth = bladeWidth;
    }

    @Override
    public String toString() {
        return super.toString() + " " + bladeLength + " " + bladeWidth;
    }
}
