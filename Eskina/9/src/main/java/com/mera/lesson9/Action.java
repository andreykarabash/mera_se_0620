package com.mera.lesson9;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.Objects;
@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include=JsonTypeInfo.As.PROPERTY, property="Action")
public abstract class Action {
    protected GameCharacter actor;

    Action() {

    }

    Action (GameCharacter actor) {
        this.actor = actor;
    }

    public GameCharacter getActor() {
        return actor;
    }

    public void setActor(GameCharacter actor) {
        this.actor = actor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Action)) return false;
        Action action = (Action) o;
        return Objects.equals(actor, action.actor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actor);
    }

    @Override
    public String toString() {
        return "Action{" +
                "actor=" + actor +
                '}';
    }

    public abstract void performAction(Scene scene);

    public abstract void performReplayAction(Scene scene);
}
