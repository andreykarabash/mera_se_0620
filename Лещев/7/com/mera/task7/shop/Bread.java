package com.mera.task7.shop;

public class Bread extends FoodItem {

    private int weight;

    public Bread() {

    }

    public Bread(String name, int price, int calories, int bestBefore, int weight) {
        super(name, price, calories, bestBefore);
        this.weight = Math.max(0, weight);
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        if(weight < 0) {
            throw new IllegalArgumentException("Weight value can't be negative!");
        }
        this.weight = weight;
    }

}
