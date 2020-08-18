package com.mera.lesson11.task1;

import com.mera.lesson11.task3.VisitStatistics;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        Person person1 = new Person("Саша", 37);
        Person person2 = new Person("Миша", 26);
        Person person3 = new Person("Лёва", 82);
        Person person4 = new Person("Антоша", 44);
        Person person5 = new Person("Федя", 59);
        people = Arrays.asList(person1, person2, person3, person4, person5 );
        System.out.println("Список людей создан\n");
        people.forEach(System.out::println);

        Collections.sort(people, (p1, p2) ->{return (p1.getName().compareTo(p2.getName()) );} );
        System.out.println("\nСортирую по имени\n");
        people.forEach(System.out::println);
        
        Collections.sort(people, ( p1,  p2) -> {
            return (p1.getAge() - p2.getAge());
        });
        System.out.println("\nСортирую по возрасту\n");
        people.forEach(System.out::println);

    }
}
