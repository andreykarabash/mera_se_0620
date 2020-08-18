/****************************************************************
 * File: MaterialWeaponsList.java
 * Purpose: Enum for name and description of material weapons
 * Notice: (c) 2020 Nikolay Kuzmichev. All rights reserved.
 ****************************************************************/

package main.actions.weapons.material;

import main.actions.ActionDescriber;

public enum MaterialWeaponsList implements ActionDescriber {
    BOWSHOT("BowShot",""),
    MONSTERSTRIKE("MonsterStrike", ""),
    KNIGHTSTRIKE("SwordStrike", "");

    private String name;
    private String description;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    MaterialWeaponsList(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
