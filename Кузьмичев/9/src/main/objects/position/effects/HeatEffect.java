/*********************************************************
 * File: HeatEffect.java
 * Purpose: Changing transactions depending on the effect
 * Notice: (c) 2020 Nikolay Kuzmichev. All rights reserved.
 ********************************************************/

package main.objects.position.effects;

import main.transactions.WeaponTransaction;

public class HeatEffect extends Effect {
    @Override
    public WeaponTransaction modifyTransactions(WeaponTransaction transaction) {
        return transaction;
    }
}