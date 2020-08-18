package com.mera.lesson7;

import java.util.Collection;

public class ImJustLookingVisitor implements ShopVisitor{
    @Override
    public void visitShop(Collection<? extends ShopItem> shopItems) {
        for (ShopItem shopItem : shopItems) {
            System.out.println("Товар: " + shopItem.titleItem + ", цена: " + shopItem.priceItem);
        }
    }
}
