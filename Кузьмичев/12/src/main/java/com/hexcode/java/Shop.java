package com.hexcode.java;

import java.util.ArrayList;
import java.util.List;

public class Shop {
    private List<ShopItem> shopItemList;

    public Shop() {
        shopItemList = new ArrayList<>();
    }

    public Shop(List<ShopItem> shopItemList) {
        this.shopItemList = shopItemList;
    }

    public List<ShopItem> getShopItemList() {
        return shopItemList;
    }

    public void addShopItemList(ShopItem shopItem) {
        shopItemList.add(shopItem);
    }
}
