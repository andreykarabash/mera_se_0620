package com.mera.lesson6;

import java.util.List;
import java.util.Random;

public class FeedAnimals {
    public static void feedAnimal(List<Pair<Animal, String >> animals) {
        int favoriteFoodId = new Random().nextInt(animals.size());

        for (Pair<Animal, String> animal : animals) {
            System.out.printf("%s %s с радостью съедает %s\n",
                    animal.getFirstElement().getAnimalType(), animal.getFirstElement().getAnimalName(), animal.getSecondElement());
        }
        System.out.printf("Счастливый %s %s получает двойную порцию: %s\n",
                animals.get(favoriteFoodId).getFirstElement().getAnimalType(), animals.get(favoriteFoodId).getFirstElement().getAnimalName(),
                animals.get(favoriteFoodId).getSecondElement());
    }
}
