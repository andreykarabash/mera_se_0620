package com.hexcode.java;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class MainApp
{
    private static Integer MAX_STRING_LENGTH = 10;

    public static void main(String[] args)
    {
        Shop perekrestok = new Shop();

        perekrestok.addShopItemList(new ShopItem("Mild products", 130., "Cheese", 20));
        perekrestok.addShopItemList(new ShopItem("Bakery products", 34., "Bread", 35));
        perekrestok.addShopItemList(new ShopItem("Sausages", 142., "Vyazanka", 12));
        perekrestok.addShopItemList(new ShopItem("Other", 72., "Napkins", 20));
        perekrestok.addShopItemList(new ShopItem("Household chemicals", 28., "Soap", 20));
        perekrestok.addShopItemList(new ShopItem("Fish", 210., "Trout", 10));
        perekrestok.addShopItemList(new ShopItem("Cold drinks", 82., "RedBull", 30));
        perekrestok.addShopItemList(new ShopItem("Sweets", 400., "MilkyWay", 45));
        perekrestok.addShopItemList(new ShopItem("Frozen food", 54., "Ice cream", 15));
        perekrestok.addShopItemList(new ShopItem("Alcohol", 120., "Beer", 24));

        List<Comparator<ShopItem>> itemSorters = new ArrayList<>();

        itemSorters.add(ItemSorters::sortByCategory);
        itemSorters.add(ItemSorters::sortByTitle);
        itemSorters.add(ItemSorters::sortByPrice);
        itemSorters.add(ItemSorters::sortByQuantity);

        Integer randomComparatorIndex = ThreadLocalRandom.current().nextInt(itemSorters.size());
        Comparator<ShopItem> randomComparator = itemSorters.get(randomComparatorIndex);

        perekrestok.getShopItemList().stream().
                sorted(randomComparator).
                forEach((i) -> System.out.printf("%s\t\"%-10s\"\t\t%-10s\t\t%f\t%d\n",
                        i.getId(),
                        StringUtils.abbreviate(i.getCategory(), MAX_STRING_LENGTH),
                        StringUtils.abbreviate(i.getTitle(), MAX_STRING_LENGTH),
                        i.getPrice(),
                        i.getQuantity()));
    }
}
