package com.mera.lesson7;

import java.util.Collection;

public class ElectronicAddictedVisitor implements ShopVisitor {
    @Override
    public void visitShop(Collection<? extends ShopItem> shopItems) {
        int maxPowerConsumptionItem = getMaxPowerConsumptionItem(shopItems);
        for (ShopItem shopItem : shopItems) {
            if (shopItem instanceof ElectronicItem) {
                System.out.println("Товар: " + shopItem.getTitleItem() + ", цена: " + shopItem.getPriceItem());
                if (maxPowerConsumptionItem == ((ElectronicItem) shopItem).getPowerConsumptionItem()) {
                    System.out.println("Товар с максимальной мощностью: " + shopItem.getTitleItem()
                            + " куплен по цене: " + shopItem.getPriceItem());
                }
            }
        }
    }

    private int getMaxPowerConsumptionItem(Collection<? extends ShopItem> shopItems) {
        int maxPowerConsumptionItem = 0;
        for (ShopItem shopItem : shopItems) {
            if (shopItem instanceof  ElectronicItem) {
                if(maxPowerConsumptionItem < ((ElectronicItem) shopItem).getPowerConsumptionItem()) {
                    maxPowerConsumptionItem = ((ElectronicItem) shopItem).getPowerConsumptionItem();
                }
            }
        }
        return maxPowerConsumptionItem;
    }
}
