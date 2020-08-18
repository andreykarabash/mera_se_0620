package com.mera.task6;

import com.mera.task6.animal.Animal;
import com.mera.task6.animal.AnimalFeeding;
import com.mera.task6.date.Date;
import com.mera.task6.person.Person;
import com.mera.task6.tuple.Pair;
import com.mera.task6.tuple.Triple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    private static final int NUM_OF_VISITS = 300;

    public static void main(String[] args) {
        System.out.println("Задание А:");

        Animal cat = new Animal("Барсик", "кот");
        Animal dog = new Animal("Шарик", "собака");
        Animal fox = new Animal("Патрикеевна", "лиса");
        Animal goat = new Animal("Ночка", "коза");
        Animal wolf = new Animal("Белый клык", "волк");
        Animal crocodile = new Animal("Гена", "крокодил");

        Pair<Animal, String>[] menu = new Pair[] {
                new Pair<>(cat, "Форель"), new Pair<>(dog, "Борщ"),
                new Pair<>(fox, "Сыр"), new Pair<>(goat, "Капуста"),
                new Pair<>(wolf, "Пирожок"), new Pair<>(crocodile, "Апельсин")
        };

        AnimalFeeding.feedAnimals(Arrays.asList(menu));

        System.out.println("_____________");
        System.out.println("Задание Б: ");

        Person[] persons = new Person[] {
                new Person("Владимир", "Ленин"),
                new Person("Иосиф", "Сталин"),
                new Person("Никита", "Хрущев"),
                new Person("Леонид", "Брежнев"),
                new Person("Юрий", "Андропов")
        };

        String[] places = {
                "Центральный комитет", "ГУМ", "Баня"
        };

        List< Triple<Person, Date, String> > visitList = new ArrayList<>();
        HashSet< Triple<Person, Date, String> > visitSet = new HashSet<>();

        for(Person person : persons) {
            //Пусть каждый человек посетит NUM_OF_VISITS случайных мест в случайные даты
            for (int i = 0; i < NUM_OF_VISITS; i++) {
                String randomPlace = places[ThreadLocalRandom.current().nextInt(places.length)];
                Date randomDate = Date.randomDateAtYear(2020);

                visitList.add(new Triple<>(person, randomDate, randomPlace));
                visitSet.add(new Triple<>(person, randomDate, randomPlace));
            }
        }

        System.out.println("Всего посещений: " + visitList.size() + ".");
        System.out.println("Уникальных посещений: " + visitSet.size() + ".");

        if (visitList.size() == visitSet.size()) {
            System.out.println("Ни один человек не посещал одно и то же место два раза в один день.");
        }
    }
}
