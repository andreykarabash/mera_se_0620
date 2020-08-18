package com.mera.lesson9;


import java.util.Random;

public class Monster extends GameCharacter {
    private static final int MAX_DAMAGE = 15;
    private static final String[] MONSTER_NAMES = {"Vampire", "BigFoot", "Snowman", "Boogieman", "WerWolf", "Hydra",
                                        "Viy", "Nessy", "FrankensteinsMonster", "Dementor"};
    private int damage;

    public Monster(int position) {
        super(position);
        //initialize amount of damage (1-15) which will be constant throughout the game
        damage = RAND.nextInt(MAX_DAMAGE) + 1;
        name = MONSTER_NAMES[position];
        System.out.println("Character is created:\n" + this);
    }

    public Monster() {

    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    public GameCharacter copy() {
        Monster copy = new Monster();
        copy.setDamage(damage);
        copy.setHealth(health);
        copy.setPosition(position);
        copy.setName(name);
        return copy;
    }

    public int getDamage() {
        return damage;
    }

    public void printDamage(String targetName) {
        System.out.printf("Monster %s attacks %s with damage %d\n", name, targetName, damage);
    }

    public void makeDamage(GameCharacter target) {
        target.health -= damage;
        printDamage(target.getName());
    }

    @Override
    public String toString() {
        return "Monster { " +
                " name = " + name +
                " health = " + health +
                " damage = " + damage +
                " position = " + position +
                " }";
    }
}
