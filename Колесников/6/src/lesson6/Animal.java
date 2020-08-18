package src.lesson6;

import java.util.Objects;

public class Animal {
    private String animalName;
    private String animalType;

    public Animal(String animalName, String animalType) {
        this.animalName = animalName;
        this.animalType = animalType;
    }

    public String getAnimalName() {
        return animalName;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public String getAnimalType() {
        return animalType;
    }

    public void setAnimalType(String animalType) {
        this.animalType = animalType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return Objects.equals(animalName, animal.animalName) &&
                Objects.equals(animalType, animal.animalType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(animalName, animalType);
    }

    @Override
    public String toString() {
        return animalType + " "+ animalName;
    }
}
