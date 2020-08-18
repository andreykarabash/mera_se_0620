package com.mera.lesson7;

public abstract class ShopItem {
    String titleItem;
    int priceItem;

    public ShopItem(String titleItem, int priceItem) {
        this.titleItem = titleItem;
        this.priceItem = priceItem;
    }

    public void setTitleItem(String titleItem) {
        this.titleItem = titleItem;
    }

    public void setPriceItem(int priceItem) {
        this.priceItem = priceItem;
    }

    public String getTitleItem() {
        return titleItem;
    }

    public int getPriceItem() {
        return priceItem;
    }

    public abstract String getTypeItem();

}
