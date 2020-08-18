/*********************************************************
 * File: RainEffect.java
 * Purpose: Changing transactions depending on the effect
 * Notice: (c) 2020 Nikolay Kuzmichev. All rights reserved.
 ********************************************************/

package main.objects.position.effects;

import main.actions.weapons.properties.FireProperty;

import main.transactions.ChangeHPTransaction;
import main.transactions.WeaponTransaction;

public class RainEffect extends Effect{
    @Override
    public WeaponTransaction modifyTransactions(WeaponTransaction transaction) {
        WeaponTransaction changedTransaction = transaction;
        if (transaction.getWeaponClass() == FireProperty.class) {
            if (transaction.getWeaponClass() == ChangeHPTransaction.class) {
                Integer effectedHP = ((ChangeHPTransaction) transaction).getHitPoints();
                if (effectedHP < 0) {
                    effectedHP = effectedHP / 2;
                }

                changedTransaction = new ChangeHPTransaction(transaction.getActionCreator(),
                        transaction.getActionGetter(), transaction.getWeaponClass(),
                        effectedHP);
            }
        }
        return changedTransaction;
    }
}