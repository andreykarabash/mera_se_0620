package com.mera.lesson9;

import java.util.ArrayList;
import java.util.List;

public class ChainLightningSpell extends Spell {
    public final static int DAMAGE = 7;
    protected ChainLightningSpell(String name) {
        super(name);
        power = DAMAGE;
    }

    public ChainLightningSpell() {
    }

    public static int getDAMAGE() {
        return DAMAGE;
    }

    //This spell makes a damage to all characters except the character it's been casted by.
    @Override
    public List<GameCharacter> cast(Magician magician, Scene scene) {
        GameCharacter[] characters = scene.getCharacters();
        List<GameCharacter> targets = new ArrayList<>();

        for (int i = 0; i < characters.length; i++) {
            GameCharacter characterToDamage = characters[i];
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
