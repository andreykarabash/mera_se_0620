package com.mera.lesson9;

import java.util.ArrayList;
import java.util.List;

public class MigraineSpell extends Spell {
    public final static int DAMAGE = 8;

    protected MigraineSpell(String name) {
        super(name);
        power = 8;
    }

    public MigraineSpell() {
    }

    //This spell makes a damage to all the magicians
    @Override
    public List<GameCharacter> cast(Magician magician, Scene scene) {
        GameCharacter[] characters = scene.getCharacters();
        GameCharacter characterToDamage;
        List<GameCharacter> targets = new ArrayList<>();

        for (int i = 0; i < characters.length; i++) {
            characterToDamage = characters[i];
            if (magician.checkIsTargetAvailableForAttack(characterToDamage) && (isTargetMagician(characterToDamage))) {
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
        if (magician.checkIsTargetAvailableForAttack(target) && (isTargetMagician(target))) {
            makeDamage(scene, magician, target);
        }
    }
}
