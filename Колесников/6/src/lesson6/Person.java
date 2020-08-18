package src.lesson6;

import java.util.Objects;

public class Person {
    private String personFirstName;
    private String personSecondName;

    public Person(String personFirstName, String personSecondName) {
        this.personFirstName = personFirstName;
        this.personSecondName = personSecondName;
    }

    public String getPersonFirstName() {
        return personFirstName;
    }

    public void setPersonFirstName(String personFirstName) {
        this.personFirstName = personFirstName;
    }

    public String getPersonSecondName() {
        return personSecondName;
    }

    public void setPersonSecondName(String personSecondName) {
        this.personSecondName = personSecondName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(personFirstName, person.personFirstName) &&
                Objects.equals(personSecondName, person.personSecondName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personFirstName, personSecondName);
    }

    @Override
    public String toString() {
        return "Person{" +
                "personFirstName='" + personFirstName + '\'' +
                ", personSecondName='" + personSecondName + '\'' +
                '}';
    }
}
