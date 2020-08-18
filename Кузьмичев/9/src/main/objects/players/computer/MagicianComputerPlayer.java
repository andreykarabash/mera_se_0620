/*********************************************************
 * File: MagicianComputerPlayer.java
 * Purpose: Implements action for a specific character
 * Notice: (c) 2020 Nikolay Kuzmichev. All rights reserved.
 ********************************************************/

package main.objects.players.computer;

import main.actions.ActionDescriber;
import main.actions.weapons.Weapon;
import main.actions.weapons.properties.SpellProperty;
import main.actions.weapons.spells.Healing;

import main.objects.characters.Character;
import main.objects.Position;
import main.objects.characters.AbstractMagician;
import main.objects.characters.stuff.Stuff;
import main.objects.players.ComputerPlayer;

import main.transactions.InfoTransaction;
import main.transactions.ReactionTransaction;
import main.transactions.Transaction;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class MagicianComputerPlayer extends AbstractMagician implements ComputerPlayer {

    /**
     * Empty constructor for game save implementation
     */
    public MagicianComputerPlayer() {
    }

    public MagicianComputerPlayer(String name, Integer maxHitPoint, ArrayList<Weapon> weapons) {
        super(name, maxHitPoint, weapons);
    }

    public MagicianComputerPlayer(String name, Integer maxHitPoint, Integer power, ArrayList<Weapon> weapons) {
        super(name, maxHitPoint, power, weapons);
    }

    public MagicianComputerPlayer(String name, Integer maxHitPoint, Integer power, ArrayList<Weapon> weapons, Stuff stuff) {
        super(name, maxHitPoint, power, weapons, stuff);
    }

    @Override
    public ArrayList<Transaction> act(Map<Position, Character> battlefield) {
        ArrayList<Transaction> transactions = new ArrayList<>();

        if ((getWeapons() == null) | (getWeapons().size() == 0)) {
            String message = String.format("Magician \"%s\": I don't have weapons", getName());
            transactions.add(new ReactionTransaction(message, null, false));
            return transactions;
        }

        Integer weaponNumber = ThreadLocalRandom.current().nextInt(getWeapons().size());
        Weapon weapon = getWeapons().get(weaponNumber);

        if (!(weapon instanceof SpellProperty)) {
            String message = String.format("Magician \"%s\": I cannot use this weapon \"%s\"",
                    getName(), weapon.getDescriber().getName());
            transactions.add(new ReactionTransaction(message, null, false));
            return transactions;
        }

        ActionDescriber describer = weapon.getDescriber();
        String message = String.format("Magician \"%s\" is trying use weapon \"%s\".", getName(), describer.getName());
        transactions.add(new InfoTransaction(message));

        if (ThreadLocalRandom.current().nextBoolean()) {
            message = String.format("Magician \"%s\": \"%s\" used bad.", getName(), describer.getName());
            transactions.add(new InfoTransaction(message));
            return transactions;
        }

        if (weapon.getBlocked()) {
            transactions.addAll(weapon.attack(battlefield, this));
            return transactions;
        }

        message = String.format("Magician \"%s\" is casting spell \"%s\".", getName(), describer.getName());
        transactions.add(new InfoTransaction(message));

        if ((weapon instanceof Healing) && (getHitPoints() < LOW_HEALTH)) {
            transactions.addAll(weapon.attack(battlefield, this));
            return transactions;
        }

        transactions.addAll(weapon.attack(battlefield, this));
        return transactions;
    }
}
