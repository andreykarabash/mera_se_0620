/*********************************************************
 * File: KnightComputerPlayer.java
 * Purpose: Implements action for a specific character
 * Notice: (c) 2020 Nikolay Kuzmichev. All rights reserved.
 ********************************************************/

package main.objects.players.computer;

import main.actions.weapons.Weapon;
import main.actions.weapons.properties.CloseProperty;

import main.objects.characters.Character;
import main.objects.Position;
import main.objects.characters.AbstractKnight;
import main.objects.characters.stuff.Stuff;
import main.objects.players.ComputerPlayer;

import main.transactions.ReactionTransaction;
import main.transactions.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class KnightComputerPlayer extends AbstractKnight implements ComputerPlayer {

    /**
     * Empty constructor for game save implementation
     */
    public KnightComputerPlayer() {
    }

    public KnightComputerPlayer(String name, Integer maxHitPoint, ArrayList<Weapon> weapons) {
        super(name, maxHitPoint, weapons);
    }

    public KnightComputerPlayer(String name, Integer maxHitPoint, Integer power, ArrayList<Weapon> weapons) {
        super(name, maxHitPoint, power, weapons);
    }

    public KnightComputerPlayer(String name, Integer maxHitPoint, Integer power, ArrayList<Weapon> weapons, Stuff stuff) {
        super(name, maxHitPoint, power, weapons, stuff);
    }

    @Override
    public ArrayList<Transaction> act(Map<Position, Character> battlefield) {
        ArrayList<Transaction> transactions = new ArrayList<>();

        if ((getWeapons() == null) | (getWeapons().size() == 0)) {
            String message = String.format("Knight \"%s\": I don't have weapons", getName());
            transactions.add(new ReactionTransaction(message, null, false));
            return transactions;
        }

        Weapon weapon = getWeapons().get(0);

        if (!(weapon instanceof CloseProperty)) {
            String message = String.format("Knight \"%s\": I cannot use this weapon \"%s\"",
                    getName(), weapon.getDescriber().getName());
            transactions.add(new ReactionTransaction(message, null, false));
            return transactions;
        }

        Position enemyPosition = null;

        while (enemyPosition == null) {
            Integer enemyPositionID = ThreadLocalRandom.current().nextInt(battlefield.keySet().size());

            for (Position position : battlefield.keySet()) {
                if (enemyPositionID.equals(0) && battlefield.get(position) != this) {
                    enemyPosition = position;
                }
                enemyPositionID--;
            }
        }

        Map<Position, Character> enemyAndMe = new HashMap<>();
        enemyAndMe.put(enemyPosition, battlefield.get(enemyPosition));

        for (Position position : battlefield.keySet()) {
            if (battlefield.get(position) == this) {
                enemyAndMe.put(position, this);
                break;
            }
        }

        transactions.addAll(weapon.attack(enemyAndMe, this));
        return transactions;
    }
}
