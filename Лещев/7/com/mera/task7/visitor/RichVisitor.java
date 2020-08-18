package com.mera.task7.visitor;

import com.mera.task7.shop.ShopItem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class RichVisitor implements ShopVisitor, ShopCustomer {

    @Override
    public void visitShop(Collection<? extends ShopItem> shop) {
        //Такой посетитель покупает каждый второй товар
        ArrayList<ShopItem> customerCart = new ArrayList<>();
        int counter = 0;
        final Iterator<? extends ShopItem> iterator = shop.iterator();
        while (iterator.hasNext()) {
            ShopItem item = iterator.next();
            if(counter % 2 != 0) {
                customerCart.add(item);
            }
            counter++;
        }
        for(ShopItem item : customerCart) {
            buyItemInShop(shop, item);
        }
    }
}
