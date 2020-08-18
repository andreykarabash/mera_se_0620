/*********************************************************
 * File: Character.java
 * Purpose: Implements character
 * Notice: (c) 2020 Nikolay Kuzmichev. All rights reserved.
 ********************************************************/

package main.objects.characters;

import main.actions.weapons.Weapon;

import main.objects.Position;
import main.objects.characters.stuff.Stuff;

import main.transactions.WeaponTransaction;
import main.transactions.Transaction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Character {
    private static Integer MIN_CHARACTER_ID = 1;
    private static Integer MAX_CHARACTER_ID = Integer.MAX_VALUE - 1;
    private static Integer MIN_MAX_HP = 1;
    private static Integer MIN_POWER = 1;
    private static Integer MIN_HP = 0;

    public static Stuff DEFAULT_STUFF = null;
    public static ArrayList<Weapon> DEFAULT_WEAPONS = null;

    private static HashSet<Integer> idPool = null;

    private Integer id;
    private String name;
    private Integer maxHitPoints;
    private Integer hitPoints;
    private Integer power;
    private ArrayList<Weapon> weapons;
    private Stuff stuff;

    /**
     * Empty constructor for game save implementation
     */
    public Character() {
    }

    protected Character(String name, Integer maxHitPoint, Integer power) {
        this(name, maxHitPoint, power, DEFAULT_WEAPONS, DEFAULT_STUFF);
    }

    protected Character(String name, Integer maxHitPoint, Integer power, ArrayList<Weapon> weapons, Stuff stuff) {
        if (name == null) {
            throw new IllegalStateException("Character name is null pointer");
        }

        if (name.isEmpty()) {
            throw new IllegalStateException("Character name is empty");
        }

        if (maxHitPoint <= MIN_MAX_HP) {
            throw new IllegalStateException("Character max HP less or equals zero");
        }

        if (power < MIN_POWER) {
            throw new IllegalStateException("Character power less zero");
        }

        if (idPool == null) {
            idPool = new HashSet<>();
        }

        this.id = ThreadLocalRandom.current().nextInt(MIN_CHARACTER_ID, MAX_CHARACTER_ID);

        while (idPool.contains(this.id)) {
            this.id = ThreadLocalRandom.current().nextInt(MIN_CHARACTER_ID, MAX_CHARACTER_ID);
        }

        idPool.add(this.id);

        this.name = name;
        this.hitPoints = maxHitPoint;
        this.maxHitPoints = maxHitPoint;
        this.power = power;
        this.weapons = weapons;
        this.stuff = stuff;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getHitPoints() {
        return hitPoints;
    }

    protected void setHitPoints(Integer hitPoints) {
        if (hitPoints > maxHitPoints) {
            this.hitPoints = maxHitPoints;
            return;
        }

        if (hitPoints < MIN_HP) {
            this.hitPoints = MIN_HP;
            return;
        }

        this.hitPoints = hitPoints;
    }

    public Integer getMaxHitPoints() {
        return maxHitPoints;
    }

    public Integer getPower() {
        return power;
    }

    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }

    public Stuff getStuff() {
        return stuff;
    }

    abstract public ArrayList<Transaction> react(WeaponTransaction transaction);
    abstract public ArrayList<Transaction> act(Map<Position, Character> battlefield);

    @Override
    public String toString() {
        return "Character{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hitPoints=" + hitPoints +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Character character = (Character) o;
        return Objects.equals(id, character.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}