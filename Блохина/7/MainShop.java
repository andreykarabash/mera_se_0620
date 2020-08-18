import com.mera.lesson7.*;

import java.util.ArrayList;
import java.util.Collection;

public class MainShop {
    public static void main(String[] args) {

        Collection<ShopItem> hypermarketShop = new ArrayList<>();
        Collection<ElectronicItem> electronicShop = new ArrayList<>();
        Collection<FoodItem> groceryShop = new ArrayList<>();
        Collection<Apple> fruitShop = new ArrayList<>();

        System.out.println("\nГИПЕРМАРКЕТ");
        ElectronicFabric.fillShopWithElectronicGoods(hypermarketShop);
        AppleGarden.fillShopWithApples(hypermarketShop);
        FoodFactory.fillShopWithFood(hypermarketShop);

        System.out.println("\nЭЛЕКТРОНИКА");
        ElectronicFabric.fillShopWithElectronicGoods(electronicShop);

        System.out.println("\nПРОДУКТЫ");
        AppleGarden.fillShopWithApples(groceryShop);
        FoodFactory.fillShopWithFood(groceryShop);

        System.out.println("\nЛАРЕК");
        AppleGarden.fillShopWithApples(fruitShop);

        System.out.println("\nПосетитель, который только смотрит");
        ImJustLookingVisitor justLookingVisitor = new ImJustLookingVisitor();
        justLookingVisitor.visitShop(hypermarketShop);
        justLookingVisitor.visitShop(electronicShop);
        justLookingVisitor.visitShop(groceryShop);
        justLookingVisitor.visitShop(fruitShop);

        System.out.println("\nПосетитель, который выводит на экран всю электронику и покупает электронный товар с самой большой потребляемой мощностью");
        ElectronicAddictedVisitor electronicAddictedVisitor = new ElectronicAddictedVisitor();
        electronicAddictedVisitor.visitShop(hypermarketShop);
        electronicAddictedVisitor.visitShop(electronicShop);
        electronicAddictedVisitor.visitShop(groceryShop);
        electronicAddictedVisitor.visitShop(fruitShop);

        System.out.println("\nПосетитель, который покупает каждый второй товар");
        RichVisitor richVisitor = new RichVisitor();
        richVisitor.visitShop(hypermarketShop);
        richVisitor.visitShop(electronicShop);
        richVisitor.visitShop(groceryShop);
        richVisitor.visitShop(fruitShop);
    }
}
