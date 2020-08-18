/*********************************************************
 * File: PositionType.java
 * Purpose: General class of position types
 * Notice: (c) 2020 Nikolay Kuzmichev. All rights reserved.
 ********************************************************/

package main.objects.position.types;

import main.transactions.WeaponTransaction;

public abstract class PositionType {
    public abstract WeaponTransaction modifyTransactions(WeaponTransaction transaction);
}
