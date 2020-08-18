package com.mera.lesson7;

public class Apple extends FoodItem {
    AppleColors colorItem;

    public Apple(String titleItem, int priceItem, int calorieContentItem, int expirationDateItem, AppleColors colorItem) {
        super(titleItem, priceItem, calorieContentItem, expirationDateItem);
        this.colorItem = colorItem;
    }

    public void setColorItem(AppleColors colorItem) {
        this.colorItem = colorItem;
    }

    public AppleColors getColorItem() {
        return colorItem;
    }

    @Override
    public String getTypeItem() {
        return "Яблоко";
    }
}
