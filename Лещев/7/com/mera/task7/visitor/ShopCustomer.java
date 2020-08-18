package com.mera.task7.visitor;

import com.mera.task7.shop.ShopItem;

import java.util.Collection;

public interface ShopCustomer {

    default void buyItemInShop(Collection<? extends ShopItem> shop, ShopItem item) {
        if(shop.isEmpty()) {
            System.out.println("There is nothing to buy in that shop.");
            return;
        }

        if(shop.remove(item)) {
            System.out.printf("Shop item '%s' has been bought for %d price.%n", item.getName(), item.getPrice());
        }
        else {
            System.out.println("No such item in that shop:(");
        }
    }
}
