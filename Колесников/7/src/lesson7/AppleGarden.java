package src.lesson7;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class AppleGarden {
    static void fillShopWithApples(Collection<? super Apple> shop){
        List<Apple> currentItems = new AppleGardenStorage().getCurrentItems();
        StringBuilder bld = new StringBuilder();
        for (Apple i: currentItems){
            bld.append(i.getColor() + ", ");
        }
        System.out.println("Apples added to the shop: " + bld.toString());
        shop.addAll(currentItems);
    }
}

class AppleGardenStorage{
    private List<Apple> currentItems = new ArrayList<>();

    AppleGardenStorage() {
        currentItems.add(new Apple("Kitty", 6, 6,30, "green"));
    }

    List<Apple> getCurrentItems() {
        return currentItems;
    }

    void setCurrentItems(List<Apple> currentItems) {
        this.currentItems = currentItems;
    }
}
