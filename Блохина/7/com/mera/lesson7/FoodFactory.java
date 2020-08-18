package com.mera.lesson7;

import java.util.Collection;
import java.util.Random;

public class FoodFactory extends FabricUtils{
    private static AppleColors[] colors = AppleColors.values();
    private static String[] breadItems = {"Булка", "Деревенский", "Бородинский", "Нарезной"};

    public static void fillShopWithFood(Collection<? super FoodItem> foodShop) {
        System.out.println("В магазин добавлены яблоки и хлеб: ");
        for (int i = 0; i < 6; i++) {
            FoodItem item;
            if (new Random().nextBoolean()) {
                item = new Apple("Яблоко",
                        getRandom(500, 1000),
                        getRandom(100, 500),
                        getRandom(50, 100),
                        colors[new Random().nextInt(colors.length)]);
            } else {
                item = new Bread(breadItems[new Random().nextInt(breadItems.length)],
                        getRandom(500, 1000),
                        getRandom(100, 500),
                        getRandom(50, 100),
                        getRandom(500, 1000));
            }
            foodShop.add(item);
            System.out.println("Товар: " + item.getTitleItem() + ", цена: " + item.getPriceItem());
        }
    }
}
