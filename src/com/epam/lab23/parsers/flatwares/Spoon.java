package com.epam.lab23.parsers.flatwares;

public class Spoon extends Flatware {

    private int volume;

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return super.toString() + " " + volume;
    }
}
