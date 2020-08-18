package com.mera.task7.shop;

public class Apple extends FoodItem {

    private AppleColor color;

    public Apple() {
        color = AppleColor.GREEN;
    }

    public Apple(String name, int price, int calories, int bestBefore, AppleColor color) {
        super(name, price, calories, bestBefore);
        setColor(color);
    }

    public AppleColor getColor() {
        return color;
    }

    public void setColor(AppleColor color) {
        this.color = color;
    }
}
