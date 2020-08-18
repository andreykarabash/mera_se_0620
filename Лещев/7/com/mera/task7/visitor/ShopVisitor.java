package com.mera.task7.visitor;

import com.mera.task7.shop.ShopItem;

import java.util.Collection;

public interface ShopVisitor {

    void visitShop(Collection<? extends ShopItem> shop);
}
