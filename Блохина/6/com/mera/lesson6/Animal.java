package com.mera.lesson6;

public class Animal {
    String animalName;
    String animalType;

    public Animal(String animalName, String animalType) {
        this.animalName = animalName;
        this.animalType = animalType;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public void setAnimalType(String animalType) {
        this.animalType = animalType;
    }

    public String getAnimalName() {
        return animalName;
    }

    public String getAnimalType() {
        return animalType;
    }
}
