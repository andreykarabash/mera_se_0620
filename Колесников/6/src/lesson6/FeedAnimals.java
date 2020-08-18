package src.lesson6;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FeedAnimals {

    public static void main(String[] args) {
        List<Pair<Animal, String>> animals = new ArrayList<>();
        animals.add(new Pair<>(new Animal("Vasiliy", "Cat"), "fish"));
        animals.add(new Pair<>(new Animal("Barbos", "Dog"), "meet"));
        animals.add(new Pair<>(new Animal("Grivka", "Horse"), "hay"));
        animals.add(new Pair<>(new Animal("Flint", "Monkey"), "fruits"));

        feedAnimals(animals);


    }

    private static void feedAnimals(List<Pair<Animal,String>> animals){
        int luckyAnimalIndex = new Random().nextInt(animals.size());
        for (int i = 0; i < animals.size(); i++){
            if (i == luckyAnimalIndex){
                System.out.println("Lucky " + animals.get(i).getFirst() + " is eating the double "+ animals.get(i).getSecond());
            }
            else System.out.println(animals.get(i).getFirst() + " is eating the "+ animals.get(i).getSecond());
        }
    }
}
