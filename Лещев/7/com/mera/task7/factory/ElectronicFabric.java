package com.mera.task7.factory;

import com.mera.task7.shop.ElectronicItem;
import com.mera.task7.shop.Fridge;
import com.mera.task7.shop.TV;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ElectronicFabric {

    private static final int MIN_ITEM_COUNT = 5;
    private static final int MAX_ITEM_COUNT = 10;
    private static final int MIN_PRICE = 1500;
    private static final int MAX_PRICE = 4000;
    private static final int MIN_POWER = 200;
    private static final int MAX_POWER = 1500;
    private static final int MIN_TV_VOLUME = 40;
    private static final int MAX_TV_VOLUME = 200;
    private static final int MIN_FREEZER_VOLUME = 1000;
    private static final int MAX_FREEZER_VOLUME = 2000;
    private static final String[] fridgeNames = { "Biryuza Fridge", "Electrolux Fridge", "LG Fridge", "Indesit Fridge"};
    private static final String[] tvNames = { "Samsung TV", "Apple TV", "Panasonic TV", "Rubin TV"};

    public static void fillShopWithElectronicGoods(Collection<? super ElectronicItem> shop) {
        if(shop == null) {
            System.out.println("Can't fill invalid shop.");
        }
        List<String> addedGoods = new ArrayList<>();
        int count = ThreadLocalRandom.current().nextInt(MIN_ITEM_COUNT, MAX_ITEM_COUNT + 1);
        while (count-- != 0) {
            int price = ThreadLocalRandom.current().nextInt(MIN_PRICE, MAX_PRICE + 1);
            int power = ThreadLocalRandom.current().nextInt(MIN_POWER, MAX_POWER + 1);
            int volume;
            String name;
            if(ThreadLocalRandom.current().nextBoolean()) {
                volume = ThreadLocalRandom.current().nextInt(MIN_TV_VOLUME, MAX_TV_VOLUME + 1);
                name = tvNames[ThreadLocalRandom.current().nextInt(tvNames.length)];
                TV brandNewTV = new TV(name, price, power, volume);
                shop.add(brandNewTV);
            }
            else {
                volume = ThreadLocalRandom.current().nextInt(MIN_FREEZER_VOLUME, MAX_FREEZER_VOLUME + 1);
                name = fridgeNames[ThreadLocalRandom.current().nextInt(fridgeNames.length)];
                Fridge brandNewFridge = new Fridge(name, price, power, volume);
                shop.add(brandNewFridge);
            }
            addedGoods.add(name);
        }
        System.out.println("Electronic goods have been added to the shop: " + addedGoods);
    }
}
