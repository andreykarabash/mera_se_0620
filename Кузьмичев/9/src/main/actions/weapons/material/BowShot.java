/*****************************************************************************
 * File: BowShot.java
 * Purpose: For creation scene transactions depending on weapon specification
 * Notice: (c) 2020 Nikolay Kuzmichev. All rights reserved.
 *****************************************************************************/

package main.actions.weapons.material;

import main.actions.ActionDescriber;

import main.actions.weapons.Weapon;
import main.actions.weapons.properties.ColdProperty;
import main.actions.weapons.properties.LongRangeProperty;

import main.actions.weapons.properties.MaterialProperty;
import main.objects.Position;
import main.objects.characters.Character;

import main.transactions.ChangeHPTransaction;
import main.transactions.InfoTransaction;
import main.transactions.Transaction;

import java.util.ArrayList;
import java.util.Map;

/**
 * Weapon specification
 *
 * Name: BowShot
 * Target: All characters excluding attacker
 * Damage : Depends on character
 * Properties: Material, Long Range, Cold
 */

public class BowShot extends Weapon implements MaterialProperty, LongRangeProperty, ColdProperty {
    public ActionDescriber getDescriber() {
        return MaterialWeaponsList.BOWSHOT;
    }

    @Override
    public ArrayList<Transaction> attack(Map<Position, Character> battlefield, Character attacker) {
        ArrayList<Transaction> transactions = new ArrayList<>();

        Position archerPosition = null;

        for (Position position : battlefield.keySet()) {

            if (battlefield.get(position) == attacker) {
                archerPosition = position;
                break;
            }
        }

        if (archerPosition == null) {
            return transactions;
        }

        for (Position position : battlefield.keySet()) {
            if (Math.abs(position.getPosition() - archerPosition.getPosition()) <= 10 &&
                    Math.abs(position.getPosition() - archerPosition.getPosition()) > 0) {
                Character target = battlefield.get(position);

                transactions.add(new ChangeHPTransaction(attacker, target, this.getClass(), -attacker.getPower()));
                String message = String.format("Archer \"%s\" is attacking \"%s\" on %d hp.",
                        attacker.getName(), target.getName(), attacker.getPower());
                transactions.add(new InfoTransaction(message));
                break;
            }
        }

        return transactions;
    }
}