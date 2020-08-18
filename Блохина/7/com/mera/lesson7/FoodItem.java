package com.mera.lesson7;

public abstract class FoodItem extends ShopItem {
    int calorieContentItem;
    int expirationDateItem;

    public FoodItem(String titleItem, int priceItem, int calorieContentItem, int expirationDateItem) {
        super(titleItem, priceItem);
        this.calorieContentItem = calorieContentItem;
        this.expirationDateItem = expirationDateItem;
    }

    public void setCalorieContentItem(int calorieContentItem) {
        this.calorieContentItem = calorieContentItem;
    }

    public void setExpirationDateItem(int expirationDateItem) {
        this.expirationDateItem = expirationDateItem;
    }

    public int getCalorieContentItem() {
        return calorieContentItem;
    }

    public int getExpirationDateItem() {
        return expirationDateItem;
    }
}
