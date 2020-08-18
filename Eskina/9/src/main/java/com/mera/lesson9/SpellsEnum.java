package com.mera.lesson9;

import java.util.Random;

public enum SpellsEnum {
    MIGRAINE("Migraine"),
    LIGHTNING("Lightning"),
    CHAIN_LIGHTNING("Chain Lightning"),
    FIRE_TOUCH("Fire Touch"),
    WALL_OF_FIRE("Wall of fire"),
    HEALING("Healing"),
    EXILE_OF_MONSTERS("Exile of monsters");

    private String name = new String();

    private SpellsEnum(String name) {
        this.name = name;
    }
    private static final SpellsEnum[] VALUES = values();
    private static final int SIZE = VALUES.length;

    public String getName() {
        return name;
    }
    public static SpellsEnum generateRandomSpell() {
        Random rand = new Random();
        return VALUES[rand.nextInt(SIZE)];
    }
}
