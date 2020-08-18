package com.mera.task7.visitor;

import com.mera.task7.shop.ShopItem;

import java.util.Collection;
import java.util.Iterator;

public class ImJustLookingVisitor implements ShopVisitor {

    @Override
    public void visitShop(Collection<? extends ShopItem> shop) {
        //Такой посетитель просматривает все товары в магазине и уходит
        final Iterator<? extends ShopItem> iterator = shop.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
