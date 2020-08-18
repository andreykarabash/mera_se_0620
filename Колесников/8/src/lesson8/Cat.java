package src.lesson8;

import java.util.Objects;

@JsonTypeName("Cat")
public class Cat {
    private String Name;
    private int age;

    @JsonName("цвет")
    private String color;

    private final boolean isMammal = true;

    public Cat() {
    }

    public Cat(String firstName, int age, String color) {
        this.Name = firstName;
        this.age = age;
        this.color = color;
    }

    public String getFirstName() {
        return Name;
    }

    public void setFirstName(String firstName) {
        this.Name = firstName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "Name='" + Name + '\'' +
                ", age=" + age +
                ", color='" + color + '\'' +
                ", isMammal=" + isMammal +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cat cat = (Cat) o;
        return age == cat.age &&
                Objects.equals(Name, cat.Name) &&
                Objects.equals(color, cat.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Name, age, color);
    }
}
