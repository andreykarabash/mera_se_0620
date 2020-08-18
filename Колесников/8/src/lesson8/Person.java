package src.lesson8;

import java.util.Objects;

@JsonTypeName("Person")
public class Person {

    @JsonName("имя")
    private String firstName;

    @JsonName("возраст")
    private int age;

    private final boolean isMammal = true;

    @JsonIgnore
    private String password;

    public Person() {
    }

    public Person(String firstName, int age, String passwprd) {
        this.firstName = firstName;
        this.age = age;
        this.password = passwprd;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPasswprd() {
        return password;
    }

    public void setPasswprd(String passwprd) {
        this.password = passwprd;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", age=" + age +
                ", isMammal=" + isMammal +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age &&
                Objects.equals(firstName, person.firstName) &&
                Objects.equals(password, person.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, age, password);
    }
}
