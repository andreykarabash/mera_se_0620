/*********************************************************
 * File: MonsterComputerPlayer.java
 * Purpose: Implements action for a specific character
 * Notice: (c) 2020 Nikolay Kuzmichev. All rights reserved.
 ********************************************************/

package main.objects.players.computer;

import main.actions.weapons.material.MonsterStrike;

import main.objects.characters.Character;
import main.objects.Position;
import main.objects.characters.AbstractMonster;
import main.objects.characters.stuff.Stuff;
import main.objects.players.ComputerPlayer;

import main.transactions.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class MonsterComputerPlayer extends AbstractMonster implements ComputerPlayer {

    /**
     * Empty constructor for game save implementation
     */
    public MonsterComputerPlayer() {
    }

    public MonsterComputerPlayer(String name, Integer maxHitPoint) {
        super(name, maxHitPoint);
    }

    public MonsterComputerPlayer(String name, Integer maxHitPoint, Integer power) {
        super(name, maxHitPoint, power);
    }

    public MonsterComputerPlayer(String name, Integer maxHitPoint, Integer power, Stuff stuff) {
        super(name, maxHitPoint, power, stuff);
    }

    @Override
    public ArrayList<Transaction> act(Map<Position, Character> battlefield) {
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

        return (new MonsterStrike().attack(enemyAndMe, this));
    }
}
