/******************************************************************
 * File: MountainType.java
 * Purpose: Changing transactions depending on the type of position
 * Notice: (c) 2020 Nikolay Kuzmichev. All rights reserved.
 ******************************************************************/

package main.objects.position.types;

import main.transactions.WeaponTransaction;

public class MountainType extends PositionType {
    @Override
    public WeaponTransaction modifyTransactions(WeaponTransaction transaction) {
        return transaction;
    }
}
