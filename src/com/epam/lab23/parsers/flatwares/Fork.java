package com.epam.lab23.parsers.flatwares;

public class Fork extends Flatware{

    private int toothLength;

    public int getToothLength() {
        return toothLength;
    }

    public void setToothLength(int toothLength) {
        this.toothLength = toothLength;
    }

    @Override
    public String toString() {
        return super.toString() + " " + toothLength;
    }
}
