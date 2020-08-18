package com.mera.task7.shop;

public class TV extends ElectronicItem {

    private int volume;

    public TV() {

    }

    public TV(String name, int price, int power, int volume) {
        super(name, price, power);
        this.volume = Math.max(0, volume);
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        if(volume < 0) {
            throw new IllegalArgumentException("Volume value can't be negative!");
        }
        this.volume = volume;
    }
}
