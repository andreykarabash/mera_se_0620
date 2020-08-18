package com.hexcode.java;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class ShopItem {
    private static Integer ID_LENGTH = 25;

    private String id;
    private String category;
    private Double price;
    private String title;
    private Integer quantity;

    public ShopItem(String category, Double price, String name, Integer quantity) {
        this.id = RandomStringUtils.random(ID_LENGTH, true, true).toUpperCase();
        this.category = category;
        this.price = price;
        this.title = name;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public Double getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

    public Integer getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ShopItem shopItem = (ShopItem) o;

        return new EqualsBuilder()
                .append(id, shopItem.id)
                .append(category, shopItem.category)
                .append(price, shopItem.price)
                .append(title, shopItem.title)
                .append(quantity, shopItem.quantity)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(category)
                .append(price)
                .append(title)
                .append(quantity)
                .toHashCode();
    }
}
