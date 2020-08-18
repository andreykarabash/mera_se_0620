package com.mera.lesson9;

import java.util.ArrayList;
import java.util.List;

public class WallOfFireSpell extends Spell {
    final static int DAMAGE = 10;

    protected WallOfFireSpell(String name) {
        super(name);
        power = DAMAGE;
    }

    public WallOfFireSpell() {
    }

    //This spell makes a damage to the characters on even positions
    @Override
    public List<GameCharacter> cast(Magician magician, Scene scene) {
        GameCharacter[] characters = scene.getCharacters();
        GameCharacter characterToDamage;
        List<GameCharacter> targets = new ArrayList<>();

        for (int i = 0; i < characters.length; i += 2) {
            characterToDamage = characters[i];
            if (magician.checkIsTargetAvailableForAttack(characterToDamage)) {
                targets.add(characterToDamage);
                makeDamage(scene, magician, characterToDamage);
                if (scene.getNumOfAliveCharacters() <= 1) {
                    break;
                }
            }
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