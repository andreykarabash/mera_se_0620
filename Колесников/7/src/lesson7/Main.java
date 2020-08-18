package src.lesson7;

import java.util.ArrayList;
import java.util.Collection;

public class Main {
    public static void main(String[] args){
        Collection<ShopItem> hypermarket = new ArrayList<>();
        Collection<ElectronicItem> electronicsStore = new ArrayList<>();
        Collection<FoodItem> groceryStore = new ArrayList<>();
        Collection<Apple> appleShop = new ArrayList<>();

        ElectronicFabric.fillShopWithElectronicGoods(hypermarket);
        ElectronicFabric.fillShopWithElectronicGoods(electronicsStore);

        AppleGarden.fillShopWithApples(hypermarket);
        AppleGarden.fillShopWithApples(appleShop);
        AppleGarden.fillShopWithApples(groceryStore);

        FoodFactory.fillShopWithFood(hypermarket);
        FoodFactory.fillShopWithFood(groceryStore);

        ImJustLookingVisitor visitor = new ImJustLookingVisitor();
        ElectronicAddictedVisitor electronicVisitor = new ElectronicAddictedVisitor();
        RichVisitor richVisitor = new RichVisitor();
        System.out.println();

        System.out.println("Visit the hypermarket");
        visitor.visitShop(hypermarket);
        richVisitor.visitShop(hypermarket);
        electronicVisitor.visitShop(hypermarket);
        visitor.visitShop(hypermarket);
        System.out.println();

        System.out.println("Visit the electronicsStore");
        visitor.visitShop(electronicsStore);
        richVisitor.visitShop(electronicsStore);
        electronicVisitor.visitShop(electronicsStore);
        visitor.visitShop(electronicsStore);
        System.out.println();

        System.out.println("Visit the groceryStore");
        visitor.visitShop(groceryStore);
        richVisitor.visitShop(groceryStore);
        electronicVisitor.visitShop(groceryStore);
        visitor.visitShop(groceryStore);
        System.out.println();

        System.out.println("Visit the appleShop");
        visitor.visitShop(appleShop);
        richVisitor.visitShop(appleShop);
        electronicVisitor.visitShop(appleShop);
        visitor.visitShop(appleShop);
    }
}
