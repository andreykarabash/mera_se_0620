package com.mera.lesson11;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.mera.lesson11.StreamExample.Animal;
import com.mera.lesson11.StreamExample.AnimalType;
import com.sun.xml.internal.ws.model.wsdl.WSDLOutputImpl;

public class LazyStreamsExample {





    static class PersonWithAnimals {
        String name;
        List<Animal> animals;

        public PersonWithAnimals(String name, List<Animal> animals) {
            this.name = name;
            this.animals = animals;
        }
    }


    public static void main(String[] args) {
     /*   ArrayList<Integer> list = new ArrayList<>();

        list.add(42);
        list.add(1);


        final IntStream intStream = list
                .stream()
                .mapToInt(l -> l);

        list.remove((Integer) 42);

        System.out.println(intStream.sum());
*/

      //  Stream.of("A", "b", "c").forEach(System.out::println); создание стрима из константных значений

        printAllANimals();

    }

    private static void printAllANimals() {
        List<PersonWithAnimals> persons = new ArrayList<>();
        persons.add(new PersonWithAnimals("Вася", Collections.singletonList(new Animal("Барсик", AnimalType.CAT))));
        persons.add(new PersonWithAnimals("Петя",
                Arrays.asList(new Animal("Шарик", AnimalType.DOG),
                        new Animal("Михаил", AnimalType.BEAR))));


        persons.stream()
                .flatMap(LazyStreamsExample::getFilteredAnimalsFromPerson)
                .forEach(a -> System.out.println(a.name));


    }

    private static Stream<Animal> getFilteredAnimalsFromPerson(PersonWithAnimals p) {
        return p.animals.stream().filter(a -> !a.name.contains("л"));
    }
}
