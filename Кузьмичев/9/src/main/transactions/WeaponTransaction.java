/*********************************************************
 * File: WeaponTransaction.java
 * Purpose: General class of weapon transactions
 * Notice: (c) 2020 Nikolay Kuzmichev. All rights reserved.
 ********************************************************/

package main.transactions;

import main.objects.characters.Character;

public abstract class WeaponTransaction extends ChangeTransaction {
    private Character transactionCreator;
    private Character transactionGetter;

    private Class weaponClass;

    public WeaponTransaction(Character transactionCreator, Character transactionGetter, Class weaponClass) {
        this.transactionCreator = transactionCreator;
        this.transactionGetter = transactionGetter;
        this.weaponClass = weaponClass;
    }

    public Character getActionCreator() {
        return transactionCreator;
    }

    public Character getActionGetter() {
        return transactionGetter;
    }

    public Class getWeaponClass() {
        return weaponClass;
    }
}
