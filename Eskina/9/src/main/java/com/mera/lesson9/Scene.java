package com.mera.lesson9;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Scene {
    public static final int MAX_NUMBER_OF_CHARACTERS = 10;
    public static final int MIN_NUMBER_OF_CHARACTERS = 2;

    private GameCharacter[] characters;
    private int numOfAliveCharacters;
    @JsonIgnore
    State state;
    @JsonIgnore
    GameReplay record;

    //Use pattern "State" for managing scene depending on the existence of game replay
    public Scene(GameReplay replay) {
        characters  = new GameCharacter[MAX_NUMBER_OF_CHARACTERS];
        if (replay == null) {
            state = new RandomSceneState(this);
            record = new GameReplay();
        } else {
            record = replay;
            state = new ReplaySceneState(this);
        }
    }

    public Scene() {
        characters  = new GameCharacter[MAX_NUMBER_OF_CHARACTERS];
    }

    public GameCharacter[] getCharacters() {
        return characters;
    }

    public void setCharacters(GameCharacter[] characters) {
        this.characters = characters;
    }

    public Scene getCopy() {
        //get copy of scene characters
        Scene sceneCopy = new Scene();
        GameCharacter[] copyCharacters = new GameCharacter[MAX_NUMBER_OF_CHARACTERS];
        for (int i = 0; i < characters.length; i++) {
            if (characters[i] != null) {
                copyCharacters[i] = characters[i].copy();
            }
        }
        sceneCopy.setCharacters(copyCharacters);
        sceneCopy.numOfAliveCharacters = numOfAliveCharacters;
        return sceneCopy;
    }

    public int getNumOfAliveCharacters() {
        return numOfAliveCharacters;
    }

    public void setNumOfAliveCharacters(int numOfAliveCharacters) {
        this.numOfAliveCharacters = numOfAliveCharacters;
    }

    public State getState() {
        return state;
    }

    public GameReplay getRecord() {
        return record;
    }

    public void setRecord(GameReplay record) {
        this.record = record;
    }

    public void createCharacters() {
        state.createCharacters();
    }

    public void start() {
        createCharacters();
        state.startBattle();
    }

    //calls after action is performed. If target isn't alive, decrement counter of alive characters
    //and print the state of target.
    public void checkCharacterAfterAttack(GameCharacter character) {
        if (character.checkIsAlive()) {
            System.out.println("Character " + character.getName() + "is alive. Health = " + character.getHealth());
        } else {
            --numOfAliveCharacters;
            System.out.println("Character " + character.getName() + "is killed!!! Health = " + character.getHealth());
            System.out.println("Alive: " + numOfAliveCharacters);
        }
    }

    //choose random target for the character to attack
    public GameCharacter findRandomTarget(GameCharacter character) {
        GameCharacter target;
        do {
            target = characters[ThreadLocalRandom.current().nextInt(characters.length)];
        } while (!character.checkIsTargetAvailableForAttack(target));
        return target;
    }


    public void findWinner() {
        System.out.println("\nPrint all characters\n");
        for (int i = 0; i < characters.length; i++) {
            if(characters[i] != null) {
                if (characters[i].getHealth() > 0)
                {
                    System.out.println("\nThe Winner is: ");
                }
                System.out.println(characters[i]);
            }
        }
    }

    public enum CharactersEnum {
        MONSTER,
        MAGICIAN;

        public static CharactersEnum generateRandomCharacter() {
            return values()[ThreadLocalRandom.current().nextInt(values().length)];
        }
    }
}
