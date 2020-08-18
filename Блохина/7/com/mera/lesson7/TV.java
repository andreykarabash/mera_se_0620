package com.mera.lesson7;

public class TV extends ElectronicItem {
    int volumeItem;

    public TV(String titleItem, int priceItem, int powerConsumptionItem, int volumeItem) {
        super(titleItem, priceItem, powerConsumptionItem);
        this.volumeItem = volumeItem;
    }

    public void setVolumeItem(int volumeItem) {
        this.volumeItem = volumeItem;
    }

    public int getVolumeItem() {
        return volumeItem;
    }

    @Override
    public String getTypeItem() {
        return "Телевизор";
    }
}
