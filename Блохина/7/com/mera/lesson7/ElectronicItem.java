package com.mera.lesson7;

public abstract class ElectronicItem extends ShopItem {
    int powerConsumptionItem;

    public ElectronicItem(String titleItem, int priceItem, int powerConsumptionItem) {
        super(titleItem, priceItem);
        this.powerConsumptionItem = powerConsumptionItem;
    }

    public void setPowerConsumptionItem(int powerConsumptionItem) {
        this.powerConsumptionItem = powerConsumptionItem;
    }

    public int getPowerConsumptionItem() {
        return powerConsumptionItem;
    }
}
