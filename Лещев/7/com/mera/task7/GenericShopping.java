package com.mera.task7;

import com.mera.task7.factory.AppleGarden;
import com.mera.task7.factory.ElectronicFabric;
import com.mera.task7.factory.FoodFactory;
import com.mera.task7.shop.Apple;
import com.mera.task7.shop.ElectronicItem;
import com.mera.task7.shop.FoodItem;
import com.mera.task7.shop.ShopItem;
import com.mera.task7.visitor.ElectronicAddictedVisitor;
import com.mera.task7.visitor.ImJustLookingVisitor;
import com.mera.task7.visitor.RichVisitor;
import com.mera.task7.visitor.ShopVisitor;

import java.util.ArrayList;
import java.util.Collection;

public class GenericShopping {

    public static void main(String[] args) {
        //Создайте 4 магазина:
        System.out.println("Creating shops...");
        Collection<ShopItem> auchan = new ArrayList<>();
        Collection<ElectronicItem> mvideo = new ArrayList<>();
        Collection<FoodItem> lukoshko = new ArrayList<>();
        Collection<Apple> larek = new ArrayList<>();
        //Заполнение магазинов товарами
        System.out.println("Filling shops with goods...");
        FoodFactory.fillShopWithFood(auchan);
        FoodFactory.fillShopWithFood(lukoshko);
        AppleGarden.fillShopWithApples(auchan);
        AppleGarden.fillShopWithApples(lukoshko);
        AppleGarden.fillShopWithApples(larek);
        ElectronicFabric.fillShopWithElectronicGoods(auchan);
        ElectronicFabric.fillShopWithElectronicGoods(mvideo);
        //Создайте трех посетителей разных классов и пусть они погуляют по всем магазинам:
        System.out.println("Preparing visitors...");
        ShopVisitor[] visitors = { new ImJustLookingVisitor(), new RichVisitor(), new ElectronicAddictedVisitor() };
        for (int i = 0; i < visitors.length; i++) {
            System.out.println("Visitor " + (i + 1) +  " is exploring shops: ");
            visitors[i].visitShop(auchan);
            visitors[i].visitShop(mvideo);
            visitors[i].visitShop(lukoshko);
            visitors[i].visitShop(larek);
            System.out.println();
        }
    }
}
