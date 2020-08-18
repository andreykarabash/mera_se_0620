package com.mera.task7.shop;

public class ElectronicItem extends ShopItem {

    private int power;

    public ElectronicItem() {

    }

    public ElectronicItem(String name, int price, int power) {
        super(name, price);
        this.power = Math.max(0, power);
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        if(power < 0) {
            throw new IllegalArgumentException("Power value can't be negative!");
        }
        this.power = power;
    }
}
