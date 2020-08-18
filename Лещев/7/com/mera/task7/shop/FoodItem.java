package com.mera.task7.shop;

public class FoodItem extends ShopItem {

    private int calories;
    private int bestBeforeDays;

    public FoodItem() {

    }

    public FoodItem(String name, int price) {
        super(name, price);
    }

    public FoodItem(String name, int price, int calories, int bestBeforeDays) {
        super(name, price);
        this.calories = Math.max(0, calories);
        this.bestBeforeDays = Math.max(0, bestBeforeDays);
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        if(calories < 0) {
            throw new IllegalArgumentException("Calories value can't be negative!");
        }
        this.calories = calories;
    }

    public int getBestBeforeDays() {
        return bestBeforeDays;
    }

    public void setBestBeforeDays(int bestBeforeDays) {
        if(bestBeforeDays < 0) {
            throw new IllegalArgumentException("Best before value can't be negative!");
        }
        this.bestBeforeDays = bestBeforeDays;
    }
}
