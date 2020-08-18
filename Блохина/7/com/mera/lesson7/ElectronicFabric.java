package com.mera.lesson7;

import java.util.Collection;
import java.util.Random;

public class ElectronicFabric extends FabricUtils {
    private static String[] refrigeratorItems = {"Холодильник1", "Холодильник2", "Холодильник3"};
    private static String[] tvItems = {"Телевизор1", "Телевизор2", "Телевизор3"};

    public static void fillShopWithElectronicGoods(Collection<? super ElectronicItem> electronicShop) {
        System.out.println("В магазин добавлена электроника: ");
        for (int i = 0; i < 6; i++) {
            ElectronicItem item;
            if (new Random().nextBoolean()) {
                item = new Refrigerator(refrigeratorItems[new Random().nextInt(refrigeratorItems.length)],
                        getRandom(500, 1000),
                        getRandom(100, 500),
                        getRandom(50, 100));
            } else {
                item = new TV(tvItems[new Random().nextInt(tvItems.length)],
                        getRandom(500, 1000),
                        getRandom(100, 500),
                        getRandom(50, 100));
            }
            electronicShop.add(item);
            System.out.println("Товар: " + item.getTitleItem() + ", цена: " + item.getPriceItem());
        }
    }
}
