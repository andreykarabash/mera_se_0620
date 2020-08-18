package com.mera.lesson7;

import java.util.Collection;

public interface ShopVisitor {
    public void visitShop(Collection<? extends ShopItem> shopItems);
}
