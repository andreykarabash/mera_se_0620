package com.mera.task7.shop;

public class Fridge extends ElectronicItem {

    private int freezerVolume;

    public Fridge() {

    }

    public Fridge(String name, int price, int power, int freezerVolume) {
        super(name, price, power);
        this.freezerVolume = Math.max(0, freezerVolume);
    }

    public int getFreezerVolume() {
        return freezerVolume;
    }

    public void setFreezerVolume(int freezerVolume) {
        if(freezerVolume < 0) {
            throw new IllegalArgumentException("Freezer volume value can't be negative!");
        }
        this.freezerVolume = freezerVolume;
    }
}
