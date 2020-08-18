/*********************************************************
 * File: Protection.java
 * Purpose: Implements character stuff
 * Notice: (c) 2020 Nikolay Kuzmichev. All rights reserved.
 ********************************************************/

package main.objects.characters.stuff;

public abstract class Protection extends Stuff {
    private String name;
    private Integer armor;

    public Protection(String name, Integer armor) {
        this.name = name;
        this.armor = armor;
    }

    public String getName() {
        return name;
    }

    public Integer getArmor() {
        return armor;
    }

    public void setArmor(Integer armor) {
        if (armor < 0) {
            this.armor = 0;
            return;
        }

        this.armor = armor;
    }

    public Integer protect(Integer hitPoints) {
        if ((armor - Math.abs(hitPoints)) < 0) {
            setArmor(0);
            return armor - Math.abs(hitPoints);
        }

        setArmor(armor - Math.abs(hitPoints));
        return 0;
    }
}