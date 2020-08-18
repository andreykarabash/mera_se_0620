package com.mera.task8;

import com.mera.task8.objects.Animal;
import com.mera.task8.objects.Book;
import com.mera.task8.objects.Person;
import com.mera.task8.serializer.XmlSerializer;

import java.util.Arrays;

public class SerializerMain {

    public static void main(String[] args) {

        Person person = new Person("Иван", "Иванов", 22);
        Animal pet = new Animal("Барсик", "кот", true);
        person.setPets(pet);
        Book warAndPeace = new Book("Война и мир", "Л.Н. Толстой", 1225);
        person.setFavoriteBook(warAndPeace);

        System.out.println("Original objects:");
        System.out.println(person);
        System.out.println(pet);
        System.out.println(warAndPeace);
        System.out.println();

        System.out.println("Serialized data:");
        String serializedXmlData = "";
        try {
            serializedXmlData = XmlSerializer.serialize(Arrays.asList(person, pet, warAndPeace));
            System.out.println(serializedXmlData);
            System.out.println();
        }
        catch (IllegalAccessException exception) {
            System.out.println("Exception caught while serializing objects: " + exception.getMessage());
        }

        System.out.println("Deserialized objects: ");
        try {
            Person deserializedPerson = XmlSerializer.deserialize(serializedXmlData, Person.class);
            System.out.println(deserializedPerson);
            Animal deserializedPet = XmlSerializer.deserialize(serializedXmlData, Animal.class);
            System.out.println(deserializedPet);
            Book deserializedBook = XmlSerializer.deserialize(serializedXmlData, Book.class);
            System.out.println(deserializedBook);
            //String someString = XmlSerializer.deserialize(serializedXmlData, String.class);
            //System.out.println(someString);
        }
        catch (Exception exception) {
            System.out.println("Exception caught while deserializing objects: " + exception.getMessage());
        }
    }
}
