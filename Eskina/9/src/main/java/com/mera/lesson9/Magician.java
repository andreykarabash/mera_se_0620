package com.mera.lesson9;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.Random;

public class Magician extends GameCharacter {
    public static int MAX_NUMBER_OF_SPELLS = 3;
    public static int MIN_NUMBER_OF_SPELLS = 1;
    private static final String[] MAG_NAMES = {"Dumbledore", "WindsorOfOz", "Merlin", "Gandalf", "DoctorStrange", "HarryPotter",
            "Hottabych", "Maleficent", "Jadis", "LordVoldemort "};
    private static final Random RAND = new Random();

    private Spell[] spellBook;

    public Magician() {

    }

    @JsonCreator
    public Magician(int position) {
        super(position);
        name = MAG_NAMES[position];
        spellBook = createSpellBook();
        System.out.println("Character is created:\n" + this);
    }

    public Spell[] getSpellBook() {
        return spellBook;
    }

    public void setSpellBook(Spell[] spellBook) {
        this.spellBook = spellBook;
    }

//    @Override
    public GameCharacter copy() {
        Magician copy = new Magician();
        copy.setSpellBook(spellBook);
        copy.setHealth(health);
        copy.setPosition(position);
        copy.setName(name);
        return copy;
    }

    private Spell[] createSpellBook() {
        Spell[] availableSpells = new Spell[RAND.nextInt(MAX_NUMBER_OF_SPELLS - MIN_NUMBER_OF_SPELLS + 1) + MIN_NUMBER_OF_SPELLS];
        for (int i = 0; i < availableSpells.length; i++) {
            //repeat iteration is spell wasn't added for some reason
            if ((availableSpells[i] = createRandomSpell()) == null) {
                i--;
            }
        }
        return availableSpells;
    }

    public Spell takeRandomSpellFromBook() {
        return spellBook[RAND.nextInt(spellBook.length)];
    }

    private Spell createRandomSpell() {
        SpellsEnum spellType = SpellsEnum.generateRandomSpell();
        String spellName = spellType.getName();
        switch (spellType) {
            case MIGRAINE:
                return new MigraineSpell(spellName);
            case LIGHTNING:
                return new LightningSpell(spellName);
            case CHAIN_LIGHTNING:
                return new ChainLightningSpell(spellName);
            case FIRE_TOUCH:
                return new FireTouchSpell(spellName);
            case WALL_OF_FIRE:
                return new WallOfFireSpell(spellName);
            case HEALING:
                return new HealingSpell(spellName);
            case EXILE_OF_MONSTERS:
                return new ExileOfMonstersSpell(spellName);
        }
        return null;
    }

    @Override
    public String toString() {
        return "Magician { " +
                "name = '" + name + '\'' +
                " ,health = " + health +
                " ,position = " + position +
                " spellBook = " + Arrays.toString(spellBook) +
                " }";
    }

}
