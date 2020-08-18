package com.mera.lesson9;


import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.List;
import java.util.Objects;

public class MagicianAction extends Action {
    protected Spell spell;
    protected List<GameCharacter> targets;

    MagicianAction() {

    }

    public MagicianAction(GameCharacter actor, Spell spell, List<GameCharacter> targets) {
        super(actor);
        this.spell = spell;
        this.targets = targets;
    }

    public MagicianAction(GameCharacter actor, Spell spell) {
        super(actor);
        this.spell = spell;
    }

    public Spell getSpell() {
        return spell;
    }

    public void setSpell(Spell spell) {
        this.spell = spell;
    }

    public List<GameCharacter> getTargets() {
        return targets;
    }

    public void setTargets(List<GameCharacter> targets) {
        this.targets = targets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MagicianAction)) return false;
        if (!super.equals(o)) return false;
        MagicianAction that = (MagicianAction) o;
        return Objects.equals(spell, that.spell) &&
                Objects.equals(targets, that.targets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), spell, targets);
    }

    @Override
    public String toString() {
        return "MagicianAction{" +
                "spell=" + spell +
                ", targets=" + targets +
                ", actor=" + actor +
                '}';
    }

    public void performAction(Scene scene) {
        if (spell != null) {
            targets = spell.cast((Magician) actor, scene);
        }
    }

    public void performReplayAction(Scene scene) {
        //find the actor in the associated array of GameCharacters by the position
        Magician replayActor = (Magician) scene.getCharacters()[actor.position];
        GameCharacter target;
        //go through the list of targets defined in the replay action
        if (targets != null) {
            for (GameCharacter character : targets) {
                //find the target in the associated array of GameCharacters by the position
                target = scene.getCharacters()[character.position];

                if ((spell != null) && (replayActor != null)) {
                    spell.cast(replayActor,target,scene);
                }
            }
        }
    }
}
