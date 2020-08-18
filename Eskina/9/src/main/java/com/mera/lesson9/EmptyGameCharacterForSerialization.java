package com.mera.lesson9;

public class EmptyGameCharacterForSerialization extends GameCharacter {
    public static String EMPTY_NAME = "";
    public static int EMPTY_HEALTH = -1;

    public EmptyGameCharacterForSerialization(){}

    public EmptyGameCharacterForSerialization(int position) {
        super(position);
        name = EMPTY_NAME;
        health = EMPTY_HEALTH;
    }

    @Override
    public GameCharacter copy() {
        return null;
    }
}
