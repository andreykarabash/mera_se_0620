package personsorting;

import java.util.*;

public class PersonSort {
    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();

        people.add(new Person("Joe", 24));
        people.add(new Person("Kate", 28));
        people.add(new Person("Sam", 30));
        people.add(new Person("Julia", 21));
        people.add(new Person("Ron", 35));

        System.out.println("Sorting by name");
        Comparator<Person> compareByName =
                Comparator.comparing(Person::getName);
        Collections.sort(people, compareByName);
        printPeople(people);

        System.out.println("Sorting by age");
        Comparator<Person> compareByAge =
                Comparator.comparing(Person::getAge);

        Collections.sort(people, compareByAge);
        printPeople(people);
    }

    private static void printPeople(Collection<Person> people) {
        for (Person person: people) {
            System.out.println(person.getName());
        }
    }
}
