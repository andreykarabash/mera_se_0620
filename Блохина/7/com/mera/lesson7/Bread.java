package com.mera.lesson7;

public class Bread extends FoodItem {
    int weightItem;

    public Bread(String titleItem, int priceItem, int calorieContentItem, int expirationDateItem, int weightItem) {
        super(titleItem, priceItem, calorieContentItem, expirationDateItem);
        this.weightItem = weightItem;
    }

    public void setWeightItem(int weightItem) {
        this.weightItem = weightItem;
    }

    public int getWeightItem() {
        return weightItem;
    }


    @Override
    public String getTypeItem() {
        return "Хлеб";
    }
}
