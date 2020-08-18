/*********************************************************
 * File: AbstractArcher.java
 * Purpose: Implements character
 * Notice: (c) 2020 Nikolay Kuzmichev. All rights reserved.
 ********************************************************/

package main.objects.characters;

import main.actions.weapons.Weapon;

import main.objects.characters.stuff.Stuff;

import main.transactions.Transaction;
import main.transactions.ChangeHPTransaction;
import main.transactions.WeaponTransaction;
import main.transactions.InfoTransaction;
import main.transactions.ReactionTransaction;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public abstract class AbstractArcher extends Character {
    protected static Integer MIN_POWER = 15;
    protected static Integer MAX_POWER = 25;

    /**
     * Empty constructor for game save implementation
     */
    public AbstractArcher() {
    }

    protected AbstractArcher(String name, Integer maxHitPoint, ArrayList<Weapon> weapons) {
        this(name, maxHitPoint, ThreadLocalRandom.current().nextInt(MIN_POWER, MAX_POWER), weapons, null);
    }

    protected AbstractArcher(String name, Integer maxHitPoint, Integer power, ArrayList<Weapon> weapons) {
        this(name, maxHitPoint, power, weapons, null);
    }

    protected AbstractArcher(String name, Integer maxHitPoint, Integer power, ArrayList<Weapon> weapons, Stuff stuff) {
        super(name, maxHitPoint, power, weapons, stuff);
    }

    @Override
    public ArrayList<Transaction> react(WeaponTransaction transaction) {
        ArrayList<Transaction> reaction = new ArrayList<>();

        if (transaction instanceof ChangeHPTransaction) {
            Character attacker = transaction.getActionCreator();
            Integer hitPoints = ((ChangeHPTransaction) transaction).getHitPoints();

            if (attacker == null) {
                reaction.add(new ReactionTransaction("Attacker Null Pointer Exception.", transaction, false));
                return reaction;
            }

            reaction.add(new ReactionTransaction("SUCCESS", transaction, true));
            setHitPoints(getHitPoints() + hitPoints);
            reaction.add(new InfoTransaction(String.format("Archer \"%s\" got damage on %d hp.", getName(), hitPoints)));
            return reaction;
        }

        reaction.add(new ReactionTransaction("Bad Transaction.", transaction, false));
        return reaction;
    }
}