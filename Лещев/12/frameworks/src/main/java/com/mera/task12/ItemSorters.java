package com.mera.task12;

public class ItemSorters {

    public static int sortByCategory(ShopItem one, ShopItem another) {
        String oneCategory = one.getCategory();
        String anotherCategory = another.getCategory();
        if(oneCategory == null || anotherCategory == null) {
            throw new IllegalStateException("Can't sort item with null category.");
        }
        return oneCategory.compareTo(anotherCategory);
    }

    public static int sortByTitle(ShopItem one, ShopItem another) {
        String oneTitle = one.getName();
        String anotherTitle = another.getName();
        if(oneTitle == null || anotherTitle == null) {
            throw new IllegalStateException("Can't sort item with null name.");
        }
        return one.getCategory().compareTo(another.getCategory());
    }

    public static int sortByPrice(ShopItem one, ShopItem another) {
        return Double.compare(one.getPrice(), another.getPrice());
    }

    public static int sortByQuantity(ShopItem one, ShopItem another) {
        return (one.getCount() - another.getCount());
    }
}
