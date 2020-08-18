package com.mera.task7.factory;

import com.mera.task7.shop.Apple;
import com.mera.task7.shop.AppleColor;

import java.util.Collection;
import java.util.HashSet;
import java.util.concurrent.ThreadLocalRandom;

public class AppleGarden {

    private static final int MIN_ITEM_COUNT = 3;
    private static final int MAX_ITEM_COUNT = 10;
    private static final int MIN_PRICE = 5;
    private static final int MAX_PRICE = 15;
    private static final int MIN_CALORIES = 20;
    private static final int MAX_CALORIES = 40;
    private static final int BEST_BEFORE_DAYS = 14;
    private static final String[] names = { "Granny Smith", "Golden", "Foodgie", "Red Delicious"};

    public static void fillShopWithApples(Collection<? super Apple> shop) {
        if(shop == null) {
            System.out.println("Can't fill invalid shop.");
        }

        HashSet<AppleColor> colors = new HashSet<>();
        int itemCount = ThreadLocalRandom.current().nextInt(MIN_ITEM_COUNT, MAX_ITEM_COUNT + 1);

        while(itemCount-- != 0) {
            String name = names[ThreadLocalRandom.current().nextInt(names.length)];
            int price = ThreadLocalRandom.current().nextInt(MIN_PRICE, MAX_PRICE + 1);
            int calories = ThreadLocalRandom.current().nextInt(MIN_CALORIES, MAX_CALORIES + 1);
            AppleColor color = AppleColor.values()[ThreadLocalRandom.current().nextInt(AppleColor.values().length)];
            colors.add(color);
            Apple freshApple = new Apple(name, price, calories, BEST_BEFORE_DAYS, color);

            shop.add(freshApple);
        }

        System.out.println("Apples have been added to the shop: " + colors);
    }
}
