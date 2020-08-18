/*********************************************************
 * File: BattleOfMagicians.java
 * Purpose: Main game class and scenes manager
 * Notice: (c) 2020 Nikolay Kuzmichev. All rights reserved.
 ********************************************************/

package main;

import main.actions.weapons.material.BowShot;
import main.actions.weapons.material.SwordStrike;
import main.objects.Scene;

import main.actions.weapons.Weapon;
import main.actions.weapons.spells.Healing;
import main.actions.weapons.spells.Chainlightning;
import main.actions.weapons.spells.ExileMonsters;
import main.actions.weapons.spells.Firetouch;
import main.actions.weapons.spells.Firewall;
import main.actions.weapons.spells.Lightning;
import main.actions.weapons.spells.Migraine;
import main.actions.weapons.spells.SpellsList;

import main.objects.characters.AbstractArcher;
import main.objects.characters.AbstractKnight;
import main.objects.characters.AbstractMagician;
import main.objects.characters.AbstractMonster;

import main.objects.players.computer.ArcherComputerPlayer;
import main.objects.players.computer.KnightComputerPlayer;
import main.objects.players.computer.MagicianComputerPlayer;
import main.objects.players.computer.MonsterComputerPlayer;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class BattleOfMagicians {

    /**
     * These are important internal parameters for
     * the game. Available for editing before creating
     * and running a scene.
     */

    private static Integer MIN_CHARACTERS = 20;
    private static Integer MAX_CHARACTERS = 25;

    private static Integer MAX_MAGICIAN_HEALTH = 50;
    private static Integer MAX_MONSTER_HEALTH = 80;
    private static Integer MAX_ARCHERS_HEALTH = 40;
    private static Integer MAX_KNIGHT_HEALTH = 60;

    private static Integer MIN_SPELLS = 1;
    private static Integer MAX_SPELLS = 4;

    private static Integer MAX_GAME_STEPS = 200;

    public static void main(String[] args) throws IOException, InstantiationException, IllegalAccessException {
        System.out.println("Battle Of Magicians.");

        System.out.println("Do you want load the saved game?");
        System.out.println("Enter path to the game save or just press \"Enter\" to play game!");

        String answer = new Scanner(System.in).nextLine();

        if (!answer.equals("")) {
            File file = new File(answer);

            if (!file.exists() || !file.isFile() || !file.canRead()) {
                throw new FileNotFoundException("This game save file is not avaliable!");
            }

            ZipFile gameContainer = new ZipFile(answer);

            Scene replayScene = new Scene(gameContainer);
            replayScene.playMove();
            return;
        }

        Scene scene = new Scene("Avengers");

        Integer numOfPositions = ThreadLocalRandom.current().nextInt(MIN_CHARACTERS, MAX_CHARACTERS);
        createDemoScene(scene, numOfPositions);

        System.out.println("Starting the game.");

        Integer currentStep = 1;
        while (currentStep <= MAX_GAME_STEPS && !scene.getEndGame()) {
            if (!scene.getEndGame()) {
                scene.playMove();
            }
            currentStep++;
        }

        System.out.println("Do you want save the game?");
        System.out.println("Enter path to the new game save or just press \"Enter\" to exit");

        answer = new Scanner(System.in).nextLine();

        if (!answer.equals("")) {
            ZipOutputStream gameContainer = new ZipOutputStream(new FileOutputStream(answer));
            scene.writeGameContainer(gameContainer);
            gameContainer.close();
        }
    }

    private static void createDemoScene(Scene scene, Integer numOfPositions) {
        if (scene == null) {
            return;
        }
        System.out.printf("Creating the server \"%s\" ...\n", scene.getSceneName());

        for (Integer i = 0; i < numOfPositions; i++) {
            if (ThreadLocalRandom.current().nextBoolean()) {
                AbstractMonster monster = new MonsterComputerPlayer("monster" + i, MAX_MONSTER_HEALTH);
                scene.addCharacter(monster);
                continue;
            }

            if (ThreadLocalRandom.current().nextBoolean()) {
                ArrayList<Weapon> weapons = new ArrayList<>();
                weapons.add(new BowShot());
                AbstractArcher archer = new ArcherComputerPlayer("archer" + i, MAX_ARCHERS_HEALTH, weapons);
                scene.addCharacter(archer);
                continue;
            }

            if (ThreadLocalRandom.current().nextBoolean()) {
                ArrayList<Weapon> weapons = new ArrayList<>();
                weapons.add(new SwordStrike());
                AbstractKnight knight = new KnightComputerPlayer("knight" + i, MAX_KNIGHT_HEALTH, weapons);
                scene.addCharacter(knight);
                continue;
            }

            Integer numOfSpells = ThreadLocalRandom.current().nextInt(MIN_SPELLS, MAX_SPELLS);
            ArrayList<Weapon> spells = new ArrayList<>();

            for (Integer j = 0; j < numOfSpells; j++) {
                SpellsList randomSpell = SpellsList.values()[ThreadLocalRandom.current().nextInt(SpellsList.values().length)];

                if (randomSpell == SpellsList.CHAINLIGHTNING) {
                    spells.add(new Chainlightning());
                } else if (randomSpell == SpellsList.EXILEMONSTER) {
                    spells.add(new ExileMonsters());
                } else if (randomSpell == SpellsList.FIRETOUCH) {
                    spells.add(new Firetouch());
                } else if (randomSpell == SpellsList.FIREWALL) {
                    spells.add(new Firewall());
                } else if (randomSpell == SpellsList.HEALING) {
                    spells.add(new Healing());
                } else if (randomSpell == SpellsList.LIGHTNING) {
                    spells.add(new Lightning());
                } else if (randomSpell == SpellsList.MIGRAINE) {
                    spells.add(new Migraine());
                }
            }

            AbstractMagician magician = new MagicianComputerPlayer("magician" + i, MAX_MAGICIAN_HEALTH, spells);
            scene.addCharacter(magician);
        }

        System.out.println("Creating completed.");
    }
}