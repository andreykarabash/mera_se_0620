package src.lesson7;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FoodFactory {
    public static void fillShopWithFood(Collection<? super FoodItem> shop){
        List<FoodItem> currentItems = new FoodFactoryStorage().getCurrentItems();

        int breadsWeightSum = 0;
        StringBuilder bld = new StringBuilder();

        for (FoodItem i: currentItems){
            if(i instanceof Apple){
                bld.append(((Apple) i).getColor());
            }
            if (i instanceof Bread){
                breadsWeightSum += ((Bread) i).getWeight();
            }
        }

        System.out.println("Apples " + bld.toString() + " and bread weighing " + breadsWeightSum + " grams added to the shop.");
        shop.addAll(currentItems);
    }
}

class FoodFactoryStorage{
    private List<FoodItem> currentItems = new ArrayList<>();

    FoodFactoryStorage() {
        currentItems.add(new Apple("Russia", 4, 7,30, "red"));
        currentItems.add(new Bread("Borodinsky", 2, 8,30, 15));
        currentItems.add(new Bread("Loaf", 1, 7,30, 20));
    }

    List<FoodItem> getCurrentItems() {
        return currentItems;
    }

    void setCurrentItems(List<FoodItem> currentItems) {
        this.currentItems = currentItems;
    }
}
