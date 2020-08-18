package com.mera.task8.objects;

import com.mera.task8.annotations.XmlName;
import com.mera.task8.annotations.XmlTypeName;

@XmlTypeName(name = "Питомец")
public class Animal {

    @XmlName(name = "Имя")
    private String name;
    @XmlName(name = "Тип")
    private String type;
    @XmlName(name = "Есть хозяин")
    private boolean hasOwner;

    public Animal() {
        name = "";
        type = "undefined";
        hasOwner = false;
    }

    public Animal(String name, String type, boolean hasOwner) {
        this.name = name;
        this.type = type;
        this.hasOwner = hasOwner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isHasOwner() {
        return hasOwner;
    }

    public void setHasOwner(boolean hasOwner) {
        this.hasOwner = hasOwner;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", hasOwner=" + hasOwner +
                '}';
    }
}
