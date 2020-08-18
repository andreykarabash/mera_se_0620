/*********************************************************
 * File: Effect.java
 * Purpose: General class of effects
 * Notice: (c) 2020 Nikolay Kuzmichev. All rights reserved.
 ********************************************************/

package main.objects.position.effects;

import main.transactions.WeaponTransaction;

public abstract class Effect {
    public abstract WeaponTransaction modifyTransactions(WeaponTransaction transaction);
}