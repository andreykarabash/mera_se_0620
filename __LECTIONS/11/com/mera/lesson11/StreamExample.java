package com.mera.lesson11;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class StreamExample {



    enum AnimalType {
        CAT, FOX, DOG, WOLF, BEAR
    }

    static class Animal {
        String name;
        AnimalType type;
        boolean isHungry = true;

        public Animal(String name, AnimalType type) {
            this.name = name;
            this.type = type;
        }


        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return name;
        }
    }


    public static void main(String[] args) {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Мурка", AnimalType.CAT));
        animals.add(new Animal("Мухтар", AnimalType.CAT));
        animals.add(new Animal("Васька", AnimalType.CAT));
        animals.add(new Animal("Барсик", AnimalType.CAT));


        animals.add(new Animal("Алиса", AnimalType.FOX));
        animals.add(new Animal("Алёна", AnimalType.FOX));
        animals.add(new Animal("Юлька", AnimalType.FOX));

        animals.add(new Animal("Шарик", AnimalType.DOG));

        animals.add(new Animal("Серый", AnimalType.WOLF));


        sortAndFeedCats(animals);
        //printAnimalTypes(animals);

        //checkHungryAnimals(animals);

       // printAnimalNames(animals);
       // printAnimalNames(new ArrayList<>());

        //groupAnimalsByType(animals);

        printHungryAnimals(animals);



    }

    private static void printHungryAnimals(List<Animal> animals) {
        final Map<Boolean, List<Animal>> collect = animals
                .stream()
                .collect(Collectors.partitioningBy(a -> a.isHungry));
        System.out.println(collect);
    }

    private static void groupAnimalsByType(List<Animal> animals) {
        final Map<AnimalType, List<Animal>> collect = animals
                .stream()
                .collect(Collectors.groupingBy(an -> an.type));

        System.out.println(collect);
    }

    private static void printAnimalNames(List<Animal> animals) {
        final Optional<String> reduce = animals
                .stream()
                .map(Animal::getName)
                .reduce((accumulated, elementFromStream) -> accumulated + "," + elementFromStream);


        System.out.println(reduce.orElse("Животных нет"));
    }

    private static void checkHungryAnimals(List<Animal> animals) {
        final boolean isAnyHungry = animals.stream().anyMatch(a -> a.isHungry);
        System.out.println("isAnyHungry=" + isAnyHungry);

        final boolean isAllHungry = animals.stream().allMatch(a -> a.isHungry);
        System.out.println("isAllHungry=" + isAllHungry);

        final boolean isNoneHungry = animals.stream().noneMatch(a -> a.isHungry);
        System.out.println("isNoneHungry=" + isNoneHungry);
    }

    private static void printAnimalTypes(List<Animal> animals) {
      /*  animals
                .stream()
                .map((Animal animal) -> animal.type)
                .distinct()
                .forEach((AnimalType type) -> System.out.println(type));
*/

        final Set<AnimalType> collect = animals
                .stream()
                .map((Animal animal) -> animal.type)
                .collect(Collectors.toCollection((Supplier<Set<AnimalType>>) TreeSet::new));


        System.out.printf("всего %s типов животных:%s\n", collect.size(), collect.toString());

    }

    private static void sortAndFeedCats(List<Animal> animals) {
        animals
                .stream()
                .filter(animal -> animal.type == AnimalType.CAT)
                .sorted(Comparator.comparing(animal -> animal.name))
                .forEach(cat -> {
                    System.out.printf("Кошка %s получает молоко на завтрак\n", cat.name);
                    cat.isHungry = false;
                });
    }
}