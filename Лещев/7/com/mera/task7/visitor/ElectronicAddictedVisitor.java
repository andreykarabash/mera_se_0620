package com.mera.task7.visitor;

import com.mera.task7.shop.ElectronicItem;
import com.mera.task7.shop.ShopItem;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

public class ElectronicAddictedVisitor implements ShopVisitor, ShopCustomer {

    @Override
    public void visitShop(Collection<? extends ShopItem> shop) {
        if(shop.isEmpty()) {
            System.out.println("There are no items in that shop.");
            return;
        }
        // Такой посетитель сначала просматривает все электронные товары в магазине
        Collection<? extends ShopItem> electronicGoods = getAllElectronicItems(shop);
        if(electronicGoods.isEmpty()) {
            System.out.println("There are no electronic items in that shop.");
            return;
        }
        for(ShopItem item : electronicGoods) {
            System.out.println(item);
        }
        //Затем покупает товар с максимальной потребляемой мощностью
        ShopItem itemToBuy = getMostPowerfulItem(electronicGoods);
        buyItemInShop(shop, itemToBuy);
    }

    private Collection<? extends ShopItem> getAllElectronicItems(Collection<? extends ShopItem> shop) {
        return shop
                   .stream()
                   .filter(shopItem -> (shopItem instanceof ElectronicItem))
                   .collect(Collectors.toList());
    }

    private ShopItem getMostPowerfulItem(Collection<? extends ShopItem> shop) {
        return shop.stream().max(Comparator.comparingInt(item -> ((ElectronicItem) item).getPower())).get();
    }
}
