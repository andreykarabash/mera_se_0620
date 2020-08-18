import com.mera.lesson6.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Задание 1");
        List<Pair<Animal, String>> animals = new ArrayList<>();
        animals.add(new Pair<>(new Animal("Барсик", "Кот"), "рыбку"));
        animals.add(new Pair<>(new Animal("Шарик", "Пес"), "косточку"));
        animals.add(new Pair<>(new Animal("Миша", "Медведь"), "мед"));
        animals.add(new Pair<>(new Animal("Джамбо", "Слон"), "сено"));

        FeedAnimals.feedAnimal(animals);

        System.out.println("\nЗадание 2");
        List<Person> people = new ArrayList<>();
        people.add(new Person("Евгений", "Леонов"));
        people.add(new Person("Олег", "Янковский"));
        people.add(new Person("Николай", "Караченцов"));
        people.add(new Person("Андрей", "Миронов"));
        people.add(new Person("Александр", "Абдулов"));

        List<String> locations = new ArrayList<>();
        locations.add("дом");
        locations.add("театр");
        locations.add("съемочная площадка");

        System.out.println("Список посещений:");
        ListOfVisits listOfVisits = new ListOfVisits();
        listOfVisits.getRandomListOfVisits(people,locations);

        System.out.println("Количество посещений в год " + listOfVisits.getVisitList().size());
        System.out.println("Количество разных записей " + listOfVisits.getVisitSet().size());

        if (listOfVisits.isListAndSetSameLength()){
            System.out.println("Ни один человек не посещал одно и то же место два раза в один день");
            listOfVisits.printVisitSet();
        }
    }
}
