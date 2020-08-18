package src.lesson7;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ElectronicFabric {

    public static void fillShopWithElectronicGoods(Collection<? super ElectronicItem> shop){
        List<ElectronicItem>currentItems = new ElectronicFabricStorage().getCurrentItems();
        System.out.println("Electronic items added to the shop: " + currentItems);
        shop.addAll(currentItems);
    }
}

class ElectronicFabricStorage{
    private List<ElectronicItem>currentItems = new ArrayList<>();

    ElectronicFabricStorage() {
        currentItems.add(new TV("Samsung", 500, 10,50));
        currentItems.add(new TV("Sony", 600, 20,70));
        currentItems.add(new Refrigerator("LG", 800, 50,40));
        currentItems.add(new Refrigerator("Atlant", 900, 55,50));
    }

    List<ElectronicItem> getCurrentItems() {
        return currentItems;
    }

    void setCurrentItems(List<ElectronicItem> currentItems) {
        this.currentItems = currentItems;
    }
}

