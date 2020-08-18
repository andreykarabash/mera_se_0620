package com.mera.lesson9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RandomSceneState implements State {
    public static final Random RAND = new Random();

    protected Scene scene;

    public RandomSceneState(Scene scene) {
        this.scene = scene;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    @Override
    public String toString() {
        return "RandomSceneState{" +
                "scene=" + scene +
                '}';
    }

    @Override
    public void createCharacters() {
        //create random number of characters needed for the game to start(from 2 to 10)
        GameCharacter[] characters = scene.getCharacters();
        scene.setNumOfAliveCharacters(RAND.nextInt(characters.length - Scene.MIN_NUMBER_OF_CHARACTERS + 1) + Scene.MIN_NUMBER_OF_CHARACTERS);
        int numOfAliveCharacters = scene.getNumOfAliveCharacters();
        System.out.println("Number of alive characters: " + numOfAliveCharacters);
        int randPosition;

        for (int i = 0; i < numOfAliveCharacters; i++) {
            do {
                randPosition = RAND.nextInt(characters.length);
            } while (characters[randPosition] != null);

            //if character wasn't added for some reason, repeat the iteration
            if ((characters[randPosition] = createRandomCharacter(randPosition)) == null) {
                i--;
            }
        }
        //record the initial state of characters to be available for the serializing and future replay
        recordScene();
     }

     //copy the characters at the beginning of the game
    //to save them for the future replay to be serialized
    public void recordScene() {
        GameReplay record = scene.getRecord();
        System.out.println("RecordScene \n");
        record.copyScene(scene);
    }

    //record the list of actions at the end of the game
    //to save them for the future replay to be serialized
    public void recordAction(Action action) {
        scene.getRecord().addAction(action);
    }

    //describes the behaviour of characters in one game round.
    //Every character consistently performs an action, the target is chosen
    //randomly.
    public void startRound() {
        for (int i = 0; i < scene.getCharacters().length; i++) {
            if (scene. getNumOfAliveCharacters() <= 1) {
                return;
            }
            GameCharacter character = scene.getCharacters()[i];
            //if position on scene is empty got to the next character
            if (character == null) {
                continue;
            } else {
                //check if the character is alive(health > 0)
                if (character.checkIsAlive()) {
                    Action action = createAction(character);
                    if (action != null) {
                        action.performAction(scene);
                        recordAction(action);
                    }
                }
            }
        }
    }

    public Action createAction(GameCharacter actor) {
        Action actionToCreate = null;
        if (actor instanceof Magician) {
            actionToCreate = createMagicianAction(actor);
        } else if (actor instanceof Monster) {
            actionToCreate = createMonsterAction(actor);
        }
        return actionToCreate;
    }

    private Action createMonsterAction(GameCharacter actor) {
        GameCharacter target = scene.findRandomTarget(actor);
        Action action = new MonsterAction(actor, target);
        return action;
    }

    private Action createMagicianAction(GameCharacter actor) {
        Spell spell = ((Magician)actor).takeRandomSpellFromBook();
        List<GameCharacter> targets = new ArrayList<>();
        Action action = new MagicianAction(actor, spell);
        return action;
    }

    public GameCharacter createRandomCharacter(int position) {
        Scene.CharactersEnum characterType = Scene.CharactersEnum.generateRandomCharacter();
        switch (characterType) {
            case MAGICIAN:
                return new Magician(position);
            case MONSTER:
                return new Monster(position);
        }
        return null;
    }

    @Override
    public void startBattle() {
        GameCharacter[] characters = scene.getCharacters();

        do {
            for (int i = 0; i < characters.length; i++) {
                if (characters[i] != null) {
                    startRound();
                }
            }
        } while (scene.getNumOfAliveCharacters() > 1);
        System.out.println("Game is over! Number of alive characters is " + scene.getNumOfAliveCharacters());
        scene.findWinner();
    }
}
