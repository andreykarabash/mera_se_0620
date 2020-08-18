package com.mera.task12;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Shop {

    private List<ShopItem> shopItems;
    private List<Comparator<ShopItem>> comparators;

    public Shop() {
        shopItems = new ArrayList<>();
        comparators = new ArrayList<>();
    }

    public void addItem(ShopItem item) {
        if(item == null) {
            throw new IllegalArgumentException("Shop item must be not null");
        }
        shopItems.add(item);
    }

    public void addComparator(Comparator<ShopItem> comparator) {
        if(comparator == null) {
            throw new IllegalArgumentException("Comparator must be not null");
        }
        comparators.add(comparator);
    }

    public void printItems() {
        System.out.println("Sort items in the shop using random comparator and then print results...");
        Comparator<ShopItem> randomComparator = comparators.get(ThreadLocalRandom.current().nextInt(comparators.size()));
        shopItems.sort(randomComparator);
        for(ShopItem item : shopItems) {
            System.out.println(item.toString());
        }
    }
}
