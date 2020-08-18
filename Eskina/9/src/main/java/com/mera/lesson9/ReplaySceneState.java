package com.mera.lesson9;

import java.util.ArrayList;
import java.util.List;

public class ReplaySceneState implements State {
    Scene scene;
    List <Action> actions;

    public ReplaySceneState(Scene scene) {
        this.scene = scene;
    }

    //get copy of characters from the replay stored at the scene record field
    //and get copy of actions provided with the replay
    @Override
    public void createCharacters() {
        GameReplay replay = scene.getRecord();
        scene.setCharacters(replay.getCharacters());
        scene.setNumOfAliveCharacters(replay.getNumOfAliveCharacters());
        createScenario();
    }

    //get copy of actions from the replay stored at scene record field
    public void createScenario() {
        actions = new ArrayList<>(getReplayActions());
    }

    public List< Action> getReplayActions() {
        return scene.getRecord().getActions();
    }

    @Override
    public void startBattle() {
        for (Action action : actions) {
            if (action != null) {
                action.performReplayAction(scene);
            }
        }
        scene.findWinner();
    }



}
