package com.mera.task7.factory;

import com.mera.task7.shop.Bread;
import com.mera.task7.shop.FoodItem;

import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;

public class FoodFactory {

    private static final int MIN_ITEM_COUNT = 5;
    private static final int MAX_ITEM_COUNT = 10;
    private static final int MIN_PRICE = 15;
    private static final int MAX_PRICE = 30;
    private static final int MIN_CALORIES = 100;
    private static final int MAX_CALORIES = 200;
    private static final int MIN_WEIGHT = 150;
    private static final int MAX_WEIGHT = 400;
    private static final int BEST_BEFORE_DAYS = 7;
    private static final String[] names = { "White bread", "Dark bread", "Gluten-free bread", "Donut"};

    public static void fillShopWithFood(Collection<? super FoodItem> shop) {
        if(shop == null) {
            System.out.println("Can't fill invalid shop.");
        }

        AppleGarden.fillShopWithApples(shop);
        fillShopWithBread(shop);

    }

    private static void fillShopWithBread(Collection<? super Bread> shop) {
        int itemCount = ThreadLocalRandom.current().nextInt(MIN_ITEM_COUNT, MAX_ITEM_COUNT + 1);
        int totalWeight = 0;
        while(itemCount-- != 0) {
            String name = names[ThreadLocalRandom.current().nextInt(names.length)];
            int price = ThreadLocalRandom.current().nextInt(MIN_PRICE, MAX_PRICE + 1);
            int calories = ThreadLocalRandom.current().nextInt(MIN_CALORIES, MAX_CALORIES + 1);
            int weight = ThreadLocalRandom.current().nextInt(MIN_WEIGHT, MAX_WEIGHT + 1);
            totalWeight += weight;

            Bread freshBread = new Bread(name, price, calories, BEST_BEFORE_DAYS, weight);
            shop.add(freshBread);
        }

        System.out.println("Bread items have been added to the shop, total weight: " + totalWeight);
    }
}
