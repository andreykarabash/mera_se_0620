package com.mera.lesson7;

public class Refrigerator extends ElectronicItem {
    int freezerVolumeItem;

    public Refrigerator(String titleItem, int priceItem, int powerConsumptionItem, int freezerVolumeItem) {
        super(titleItem, priceItem, powerConsumptionItem);
        this.freezerVolumeItem = freezerVolumeItem;
    }

    public void setFreezerVolumeItem(int freezerVolumeItem) {
        this.freezerVolumeItem = freezerVolumeItem;
    }

    public int getFreezerVolumeItem() {
        return freezerVolumeItem;
    }

    @Override
    public String getTypeItem() {
        return "Рефрижератор";
    }
}
