package com.mera.lesson9;

import java.util.Objects;

public class MonsterAction extends Action {
    protected GameCharacter target;

    public MonsterAction() {
    }

    public MonsterAction(GameCharacter target) {
        this.target = target;
    }

    public MonsterAction(GameCharacter actor, GameCharacter target) {
        super(actor);
        this.target = target;
    }

    public GameCharacter getTarget() {
        return target;
    }

    public void setTarget(GameCharacter target) {
        this.target = target;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MonsterAction)) return false;
        if (!super.equals(o)) return false;
        MonsterAction that = (MonsterAction) o;
        return Objects.equals(target, that.target);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), target);
    }

    @Override
    public String toString() {
        return "MonsterAction{" +
                "target=" + target +
                ", actor=" + actor +
                '}';
    }

    public void performAction(Scene scene) {
        ((Monster)actor).makeDamage(target);
        scene.checkCharacterAfterAttack(target);
    }

    public void performReplayAction(Scene scene) {
        //find the actor in the associated array of GameCharacters by the position
        Monster replayActor = (Monster) scene.getCharacters()[actor.position];
        GameCharacter replayTarget = scene.getCharacters()[target.position];
        replayActor.makeDamage(replayTarget);
        scene.checkCharacterAfterAttack(replayTarget);
    }
 }
