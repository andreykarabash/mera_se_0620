/*****************************************************************************
 * File: SwordStrike.java
 * Purpose: For creation scene transactions depending on weapon specification
 * Notice: (c) 2020 Nikolay Kuzmichev. All rights reserved.
 *****************************************************************************/

package main.actions.weapons.material;

import main.actions.ActionDescriber;
import main.actions.weapons.Weapon;

import main.actions.weapons.properties.CloseProperty;
import main.actions.weapons.properties.ColdProperty;
import main.actions.weapons.properties.MaterialProperty;
import main.objects.characters.Character;
import main.objects.Position;

import main.transactions.ChangeHPTransaction;
import main.transactions.InfoTransaction;
import main.transactions.Transaction;

import java.util.ArrayList;
import java.util.Map;

/**
 * Weapon specification
 *
 * Name: SwordStrike
 * Target: All characters excluding attacker
 * Damage : Depends on character
 * Properties: Material, Close, Cold
 */

public class SwordStrike extends Weapon implements MaterialProperty, CloseProperty, ColdProperty {
    @Override
    public ActionDescriber getDescriber() {
        return MaterialWeaponsList.KNIGHTSTRIKE;
    }

    @Override
    public ArrayList<Transaction> attack(Map<Position, Character> battlefield, Character attacker) {
        ArrayList<Transaction> transactions = new ArrayList<>();

        for (Position position : battlefield.keySet()) {
            Character target = battlefield.get(position);

            if (target == attacker) {
                continue;
            }

            transactions.add(new ChangeHPTransaction(attacker, target, this.getClass(), -attacker.getPower()));
            String message = String.format("Knight \"%s\" is attacking \"%s\" on %d hp.",
                    attacker.getName(), target.getName(), attacker.getPower());
            transactions.add(new InfoTransaction(message));

        }

        return transactions;
    }
}
