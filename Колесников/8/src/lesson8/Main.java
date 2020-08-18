package src.lesson8;

import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        List<Class> classes = new ArrayList<>();
        classes.add(Person.class);
        classes.add(Cat.class);

        List<Object> items = new ArrayList<>();
        items.add(new Person("Vika", 30, "111"));
        items.add(new Person("Mikhail", 24, "112"));
        items.add(new Cat("Vasiliy", 3, "black"));
        items.add(new NoSerializable("NoSerializableObject"));

        String json = Serializer.serialize(items);

        System.out.println("The result of serialisation: json " + json);
        System.out.println("The result of deserialization: objects " + Deserializer.deserialize(json, classes));
    }
}