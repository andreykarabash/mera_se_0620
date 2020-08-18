package com.mera.lesson9;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LightningSpell extends Spell {
    final static int DAMAGE = 10;

    protected LightningSpell(String name) {
        super(name);
        power = DAMAGE;
    }

    public LightningSpell() {
    }

    public static int getDAMAGE() {
        return DAMAGE;
    }

    //This spell makes a damage to one random character
    @Override
    public List<GameCharacter> cast(Magician magician, Scene scene) {
        Random rand = new Random();
        List<GameCharacter> targets = new ArrayList<>();

        GameCharacter[] characters = scene.getCharacters();
        GameCharacter characterToDamage = scene.findRandomTarget(magician);
        targets.add(characterToDamage);
        makeDamage(scene, magician, characterToDamage);
        return targets;
    }

    @Override
    public void cast(Magician magician, GameCharacter target, Scene scene) {
        if (magician.checkIsTargetAvailableForAttack(target)) {
            makeDamage(scene, magician, target);
        }
    }
}
