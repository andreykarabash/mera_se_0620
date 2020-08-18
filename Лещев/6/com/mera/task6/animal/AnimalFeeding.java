package com.mera.task6.animal;

import com.mera.task6.tuple.Pair;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class AnimalFeeding {

    public static void feedAnimals(List<Pair<Animal, String>> menu) {
        if(menu.isEmpty()) {
            System.out.println("Пустое меню!");
            return;
        }

        for(Pair<Animal, String> pair : menu) {
            printAnimalEating(pair.getFirst(), pair.getSecond());
        }
        int luckyNumber = ThreadLocalRandom.current().nextInt(menu.size());
        Pair<Animal, String> happyPair = menu.get(luckyNumber);
        printAnimalHappyEating(happyPair.getFirst(), happyPair.getSecond());
    }

    private static void printAnimalEating(Animal animal, String food) {
        System.out.printf("Животное %s %s с радостью съедает блюдо '%s'.%n", animal.getType(), animal.getName(), food);
    }

    private static void printAnimalHappyEating(Animal animal, String food) {
        System.out.printf("Счастливое животное %s %s получает двойную порцию блюда '%s'.%n"
                          , animal.getType(), animal.getName(), food);
    }
}
