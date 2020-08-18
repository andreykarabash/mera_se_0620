/************************************************************
 * File: ChangeHPTransaction.java
 * Purpose: Class of transactions which change characters hp
 * Notice: (c) 2020 Nikolay Kuzmichev. All rights reserved.
 ************************************************************/

package main.transactions;

import main.objects.characters.Character;

public class ChangeHPTransaction extends WeaponTransaction {
    private Integer hitPoints;

    public ChangeHPTransaction(Character transactionCreator, Character transactionGetter, Class weaponClass, Integer hitPoints) {
        super(transactionCreator, transactionGetter, weaponClass);
        this.hitPoints = hitPoints;
    }

    public Integer getHitPoints() {
        return hitPoints;
    }
}