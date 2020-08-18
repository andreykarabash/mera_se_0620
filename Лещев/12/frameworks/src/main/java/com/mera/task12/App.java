package com.mera.task12;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Hello world!
 *
 */
public class App 
{
    private static String[] categories = {"Food", "Drinks", "Alcohol", "Dishes", "Household goods"};
    private static String[] food = {"Apple", "Bread", "Beef", "Salmon"};
    private static String[] drinks = {"Coca Cola", "Milk", "Orange juice", "Ice tea"};
    private static String[] dishes = {"Plate", "Glass", "Knife", "Fork"};
    private static String[] alcohol = {"Beer", "Wine", "Whisky"};
    private static String[] householdGoods = {"Soap", "Cleanser", "Broom"};

    private static ShopItem generateRandomItem() {
        String category = categories[ThreadLocalRandom.current().nextInt(categories.length)];
        String title;
        switch (category) {
            case "Food":
                title = food[ThreadLocalRandom.current().nextInt(food.length)];
                break;
            case "Drinks":
                title = drinks[ThreadLocalRandom.current().nextInt(drinks.length)];
                break;
            case "Dishes":
                title = dishes[ThreadLocalRandom.current().nextInt(dishes.length)];
                break;
            case "Alcohol":
                title = alcohol[ThreadLocalRandom.current().nextInt(alcohol.length)];
                break;
            case "Household goods":
                title = householdGoods[ThreadLocalRandom.current().nextInt(householdGoods.length)];
                break;
            default:
                title = "";
        }
        double price = ThreadLocalRandom.current().nextDouble(0.99, 25.99);
        int count = ThreadLocalRandom.current().nextInt(1, 1000);
        return new ShopItem(category, price, title, count, ShopItem.generateItemID());
    }

    public static void main( String[] args )
    {
        Shop myShop = new Shop();
        for (int i = 0; i < 10; i++) {
            myShop.addItem(generateRandomItem());
        }

        myShop.addComparator(ItemSorters::sortByCategory);
        myShop.addComparator(ItemSorters::sortByPrice);
        myShop.addComparator(ItemSorters::sortByQuantity);
        myShop.addComparator(ItemSorters::sortByTitle);

        myShop.printItems();
    }
}
