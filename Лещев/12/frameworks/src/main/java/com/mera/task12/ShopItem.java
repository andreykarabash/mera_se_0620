package com.mera.task12;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class ShopItem {

    private static final int MAX_PRINT_LENGTH = 10;
    private static final int ITEM_ID_LENGTH = 25;

    private String category;
    private double price;
    private String name;
    private int count;
    private String id;

    public static String generateItemID() {
        return RandomStringUtils.randomAlphanumeric(ITEM_ID_LENGTH);
    }

    public ShopItem() {

    }

    public ShopItem(String category, double price, String name, int count, String id) {
        this.category = category;
        this.price = price;
        this.name = name;
        this.count = count;
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ShopItem shopItem = (ShopItem) o;

        return new EqualsBuilder()
                .append(price, shopItem.price)
                .append(count, shopItem.count)
                .append(category, shopItem.category)
                .append(name, shopItem.name)
                .append(id, shopItem.id)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(category)
                .append(price)
                .append(name)
                .append(count)
                .append(id)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append('[')
                .append("ID=\"" + StringUtils.abbreviate(id, MAX_PRINT_LENGTH) + "\"")
                .append(", Category=\"" + StringUtils.abbreviate(category, MAX_PRINT_LENGTH) + "\"")
                .append(", Name=\"" + StringUtils.abbreviate(name, MAX_PRINT_LENGTH) + "\"")
                .append(", Price=" + String.format("%.2f", price))
                .append(", Count=" + count)
                .append(']')
                .toString();
    }
}
