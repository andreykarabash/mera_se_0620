package com.mera.lesson7;

import java.util.Collection;

public class RichVisitor implements ShopVisitor {
    @Override
    public void visitShop(Collection<? extends ShopItem> shopItems) {
        int i = 1;
        for (ShopItem shopItem : shopItems) {
            if (i % 2 == 0) {
                System.out.println(i + "-й товар " + shopItem.getTitleItem()
                        + " куплен по цене: " + shopItem.getPriceItem());
            }
            i++;
        }
    }
}
