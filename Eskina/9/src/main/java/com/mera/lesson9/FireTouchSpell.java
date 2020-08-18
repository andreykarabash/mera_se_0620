package com.mera.lesson9;

import java.util.ArrayList;
import java.util.List;

public class FireTouchSpell extends Spell {
    final static int DAMAGE = 8;

    protected FireTouchSpell(String name) {
        super(name);
        power = DAMAGE;
    }

    public FireTouchSpell() {
    }

    public static int getDAMAGE() {
        return DAMAGE;
    }

    //This spell makes a damage to neighbours of magician it's casted by
    @Override
    public List<GameCharacter> cast(Magician magician, Scene scene) {
        GameCharacter[] characters = scene.getCharacters();
        GameCharacter characterToDamage;
        List<GameCharacter> targets = new ArrayList<>();

        int leftNeighbourIndex = magician.getPosition() - 1;
        int rightNeighbourIndex = magician.getPosition() + 1;

        //check if right and left neighbours exist
        int targetIndex = (rightNeighbourIndex < characters.length) ? rightNeighbourIndex : leftNeighbourIndex;
        while(targetIndex > 0) {
            characterToDamage = characters[targetIndex];
            if (magician.checkIsTargetAvailableForAttack(characterToDamage)) {
                targets.add(characterToDamage);
                makeDamage(scene, magician, characterToDamage);
                if (scene.getNumOfAliveCharacters() <= 1) {
                    break;
                }
            }
            targetIndex = (targetIndex == leftNeighbourIndex) ? (-1) : leftNeighbourIndex;
        }
        return targets;
    }

    @Override
    public void cast(Magician magician, GameCharacter target, Scene scene) {
        if (magician.checkIsTargetAvailableForAttack(target)) {
            makeDamage(scene, magician, target);
        }
    }
}
