package com.mera.task8.objects;

import com.mera.task8.annotations.XmlIgnore;
import com.mera.task8.annotations.XmlName;
import com.mera.task8.annotations.XmlTypeName;

import java.util.Arrays;

@XmlTypeName(name = "Человек")
public class Person {

    @XmlIgnore
    private static final String DEFAULT_NAME = "Anonymous";

    @XmlName(name = "Имя")
    private String name;
    @XmlName(name = "Фамилия")
    private String surname;
    @XmlName(name = "Возраст")
    private int age;

    private Animal[] pets;
    private Book favoriteBook;

    public Person() {
        name = DEFAULT_NAME;
        surname = "";
    }

    public Person(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Animal[] getPets() {
        return pets;
    }

    public void setPets(Animal... pets) {
        this.pets = pets;
    }

    public Book getFavoriteBook() {
        return favoriteBook;
    }

    public void setFavoriteBook(Book favoriteBook) {
        this.favoriteBook = favoriteBook;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", pets=" + Arrays.toString(pets) +
                ", favoriteBook=" + favoriteBook +
                '}';
    }
}
