package com.mera.task7.shop;

import java.util.Objects;

public abstract class ShopItem {

    private String name;
    private int price;

    protected ShopItem() {
        name = "item";
        price = 0;
    }

    protected ShopItem(String name, int price) {
        this.name = name;
        this.price = Math.max(0, price);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        if(price < 0) {
            throw new IllegalArgumentException("Price can't be negative!");
        }
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShopItem shopItem = (ShopItem) o;
        return price == shopItem.price &&
                Objects.equals(name, shopItem.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }

    @Override
    public String toString() {
        return String.format("Shop item '%s' with price %d.", name, price);
    }
}
