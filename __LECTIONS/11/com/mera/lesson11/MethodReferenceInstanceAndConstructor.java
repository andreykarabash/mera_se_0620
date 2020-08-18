package com.mera.lesson11;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

public class MethodReferenceInstanceAndConstructor {


    static class Person {
        String firstName;
        int age;

        public Person() {}

        public Person(String firstName, int age) {
            this.firstName = firstName;
            this.age = age;
        }


        @Override
        public String toString() {
            return String.format("%s, возраст %s", firstName, age);
        }
    }


    static class ComparatorFactory {
        int compareByAge(Person a,Person b) {
            return a.age - b.age;
        }
        int compareByName(Person a,Person b) {
            return a.firstName.compareTo(b.firstName);
        }
    }



    public static void main(String[] args) throws Exception {
        // instance method
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("Вася", 21));
        persons.add(new Person("Яна", 18));
        persons.add(new Person("Анна", 24));

        ComparatorFactory factory = new ComparatorFactory();
        persons.sort(factory::compareByAge);

       /* persons.sort(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return factory.compareByAge(o1, o2);
            }
        });

        persons.sort((Person o1,Person o2) -> factory.compareByAge(o1, o2));
        */
        System.out.println(persons);


        persons.sort(factory::compareByName);
        System.out.println(persons);


        // contructor reference

        Callable<Person> r = Person::new;
        Person p = r.call();
        System.out.println(p);

    }

}
