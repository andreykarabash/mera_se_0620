package com.mera.lesson7;

import java.util.Collection;
import java.util.Random;

public class AppleGarden extends FabricUtils {
    private static AppleColors[] colorItems = AppleColors.values();

    public static void fillShopWithApples(Collection<? super Apple> fruitShop) {
        System.out.println("В магазин добавлены яблоки: ");
        for (int i = 0; i < 2; i++) {
            Apple item = new Apple("Яблоко",
                    getRandom(10, 50),
                    getRandom(100, 500),
                    getRandom(1, 10),
                    colorItems[new Random().nextInt(colorItems.length)]);
            fruitShop.add(item);
            System.out.println("Товар: " + item.getTitleItem() + " " + item.getColorItem().getColor()
                    + ", цена: " + item.getPriceItem());
        }
    }
}
