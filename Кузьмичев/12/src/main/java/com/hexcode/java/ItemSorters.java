package com.hexcode.java;

public class ItemSorters{
    static int sortByCategory(ShopItem s1, ShopItem s2) {
        if (s1 == null || s2 == null) {
            throw new IllegalArgumentException("Objects are not comparable");
        }
        return s1.getCategory().compareTo(s2.getCategory());
    }

    static int sortByTitle(ShopItem s1, ShopItem s2) {
        if (s1 == null || s2 == null) {
            throw new IllegalArgumentException("Objects are not comparable");
        }
        return s1.getTitle().compareTo(s2.getTitle());
    }

    static int sortByPrice(ShopItem s1, ShopItem s2) {
        if (s1 == null || s2 == null) {
            throw new IllegalArgumentException("Objects are not comparable");
        }

        return (int)Math.round(s1.getPrice() - s2.getPrice());
    }

    static int sortByQuantity(ShopItem s1, ShopItem s2) {
        if (s1 == null || s2 == null) {
            throw new IllegalArgumentException("Objects are not comparable");
        }

        return (int)Math.round(s1.getPrice() - s2.getPrice());
    }
}
