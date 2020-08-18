package com.mera.lesson9;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.Serializable;
import java.util.Random;

@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include=JsonTypeInfo.As.PROPERTY, property="GameCharacter")
@JsonSubTypes({
        @JsonSubTypes.Type(name = "Magician", value = Magician.class),
        @JsonSubTypes.Type(name = "Monster", value = Monster.class)})
public abstract class GameCharacter {
    public static final Random RAND = new Random();
    @JsonProperty
    protected String name;
    @JsonProperty
    protected int health;
    @JsonProperty
    protected int position;

    @JsonCreator
    protected GameCharacter(int position) {
        this.position = position;
        health = 100;
    }

    GameCharacter() {

    }

    public String getName () {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getPosition() {
        return position;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean checkIsTargetAvailableForAttack(GameCharacter target) {
        return ((target != null) && (target != this) && (target.checkIsAlive()));
    }

    public boolean checkIsAlive() {
        return (health > 0);
    }

    public abstract GameCharacter copy();
}
