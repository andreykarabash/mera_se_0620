package com.mera.lesson9;

import java.util.ArrayList;
import java.util.List;

public class HealingSpell extends Spell {
    public final static int POWER = 5;

    protected HealingSpell(String name) {
        super(name);
        power = POWER;
    }

    public HealingSpell() {
    }

    public static int getPOWER() {
        return POWER;
    }

    //This spell adds health to a magician it's been casted by.
    @Override
    public List<GameCharacter> cast(Magician magician, Scene scene) {
        List<GameCharacter> targets = new ArrayList<>();
        targets.add(magician);
        magician.health += POWER;
        printCast(magician.getName(), magician.getName());
        magician.checkIsAlive();
        return targets;
    }

    @Override
    public void cast(Magician magician, GameCharacter target, Scene scene) {
        target.health += POWER;
        printCast(magician.getName(), target.getName());
    }
}
