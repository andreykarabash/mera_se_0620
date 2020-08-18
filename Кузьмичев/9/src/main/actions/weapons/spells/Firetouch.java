/*****************************************************************************
 * File: Firetouch.java
 * Purpose: For creation scene transactions depending on weapon specification
 * Notice: (c) 2020 Nikolay Kuzmichev. All rights reserved.
 *****************************************************************************/

package main.actions.weapons.spells;

import main.actions.ActionDescriber;

import main.actions.weapons.Weapon;
import main.actions.weapons.properties.FireProperty;
import main.actions.weapons.properties.SpellProperty;

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
 * Name: Firetouch
 * Target: Characters who are next to the attacker (position difference = 1)
 * Damage : Depends on character
 * Properties: Spell, Fire
 */

public class Firetouch extends Weapon implements SpellProperty, FireProperty {
    public ActionDescriber getDescriber() {
        return SpellsList.FIRETOUCH;
    }

    @Override
    public ArrayList<Transaction> attack(Map<Position, Character> battlefield, Character attacker) {
        ArrayList<Transaction> transactions = new ArrayList<>();

        if (getBlocked()) {
            String message = String.format("Spell \"%s\" has been casted by the character already.", getDescriber().getName());
            transactions.add(new InfoTransaction(message));
            return transactions;
        }

        Position magicianPosition = null;

        for (Position position : battlefield.keySet()) {
            if (battlefield.get(position) == attacker) {
                magicianPosition = position;
                break;
            }
        }

        if (magicianPosition == null) {
            return transactions;
        }

        for (Position position : battlefield.keySet()) {
            if (Math.abs(position.getPosition() - magicianPosition.getPosition()) == 1) {
                Character target = battlefield.get(position);

                transactions.add(new ChangeHPTransaction(attacker, target, this.getClass(), -attacker.getPower()));
                String message = String.format("Magician \"%s\" is attacking \"%s\" on %d hp.",
                        attacker.getName(), target.getName(), attacker.getPower());
                transactions.add(new InfoTransaction(message));
            }
        }

        setBlocked(true);
        return transactions;
    }
}