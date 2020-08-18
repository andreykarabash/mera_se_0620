package com.mera.lesson9;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.List;

@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include=JsonTypeInfo.As.PROPERTY, property="@class")
public abstract class Spell {
    public static final int DEFAULT_POWER = 10;
    protected String spellName;
    protected int power;

    public Spell() {
    }

    public static int getDefaultPower() {
        return DEFAULT_POWER;
    }

    public String getSpellName() {
        return spellName;
    }

    public void setSpellName(String spellName) {
        this.spellName = spellName;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    protected Spell(String name) {
        spellName = name;
        power = DEFAULT_POWER;
    }

    public abstract List<GameCharacter> cast(Magician magician, Scene scene);

    public abstract void cast(Magician magician, GameCharacter target, Scene scene);

    public void printCast(String magicianName, String targetName) {
        System.out.printf("Magician %s is casting %s to %s with power %d\n",magicianName, spellName, targetName, power );
    }

    public void makeDamage(Scene scene, GameCharacter magician, GameCharacter target) {
        printCast(magician.getName(), target.getName());
        target.health -= power;
        scene.checkCharacterAfterAttack(target);
    }

    public boolean isTargetMonster(GameCharacter target) {
        return (target instanceof Monster);
    }

    public boolean isTargetMagician(GameCharacter target) {
        return (target instanceof Magician);
    }

    @Override
    public String toString() {
        return "Spell{" +
                "spellName='" + spellName + '\'' +
                ", power=" + power +
                '}';
    }
}
