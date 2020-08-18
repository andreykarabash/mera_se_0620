package com.mera.lesson9;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include=JsonTypeInfo.As.PROPERTY, property="GameReplayN")
public class GameReplay {
    @JsonProperty
    protected List<Action> actions;
    @JsonProperty
    protected int numOfAliveCharacters;
    @JsonProperty
    protected GameCharacter[] characters;

    public GameReplay() {
    }

    public GameReplay(List<Action> actions, Scene scene) {
        this.actions = actions;
        this.characters = scene.getCharacters();
        this.numOfAliveCharacters = scene.getNumOfAliveCharacters();
    }

    @JsonCreator
    public GameReplay(@JsonProperty(value = "actions") List<Action> actions,
                      @JsonProperty(value = "characters") GameCharacter[] characters,
                      @JsonProperty(value = "numOfAliveCharacters") int numOfAliveCharacters) {
        this.actions = actions;
        this.characters = characters;
        this.numOfAliveCharacters = numOfAliveCharacters;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    public void addAction(Action action) {
        System.out.println("Print record action" + action);
        if (actions == null) {
            actions = new ArrayList<>();
        }
        if (action != null) {
            actions.add(action);
        }
    }

    public int getNumOfAliveCharacters() {
        return numOfAliveCharacters;
    }

    public GameCharacter[] getCharacters() {
        return characters;
    }

    public void setCharacters(GameCharacter[] characters) {
        this.characters = characters;
    }

    public void setNumOfAliveCharacters(int numOfAliveCharacters) {
        this.numOfAliveCharacters = numOfAliveCharacters;
    }

    public void copyScene(Scene scene) {
        Scene sceneToCopy = scene.getCopy();
        characters = sceneToCopy.getCharacters();
        numOfAliveCharacters = sceneToCopy.getNumOfAliveCharacters();
    }

    @Override
    public String toString() {
        return "GameReplay{" +
                "actions=" + actions +
                ", numOfAliveCharacters=" + numOfAliveCharacters +
                ", characters=" + Arrays.toString(characters) +
                '}';
    }
}
