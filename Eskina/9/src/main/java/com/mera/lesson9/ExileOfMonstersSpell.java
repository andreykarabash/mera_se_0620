package com.mera.lesson9;

import java.util.ArrayList;
import java.util.List;

public class ExileOfMonstersSpell extends Spell {
    public final static int DAMAGE = 6;

    public ExileOfMonstersSpell() {
    }

    protected ExileOfMonstersSpell(String name) {
        super(name);
        power = DAMAGE;
    }

    public static int getDAMAGE() {
        return DAMAGE;
    }

    //This spell makes a damage to all the monsters
    @Override
    public List<GameCharacter> cast(Magician magician, Scene scene) {
        GameCharacter[] characters = scene.getCharacters();
        List<GameCharacter> targets = new ArrayList<>();
        GameCharacter characterToDamage;

        for (int i = 0; i < characters.length; i++) {
            characterToDamage = characters[i];
            if (magician.checkIsTargetAvailableForAttack(characterToDamage) && isTargetMonster(characterToDamage)) {
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
        if (magician.checkIsTargetAvailableForAttack(target) && isTargetMonster(target)) {
            makeDamage(scene, magician, target);
        }
    }
}
