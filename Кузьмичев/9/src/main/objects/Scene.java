/*********************************************************
 * File: Scene.java
 * Purpose: Provides character interaction
 * Notice: (c) 2020 Nikolay Kuzmichev. All rights reserved.
 ********************************************************/

package main.objects;

import main.objects.characters.Character;

import main.transactions.Transaction;
import main.transactions.ReactionTransaction;
import main.transactions.InfoTransaction;
import main.transactions.WeaponTransaction;
import main.transactions.ChangeTransaction;

import java.io.IOException;
import java.util.*;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class Scene {
    private static Character NO_CHARACTER = null;

    private String sceneName;
    private Boolean isEndGame = false;
    private Boolean isReplayGame = false;

    private Map<Position, Character> battlefield;
    private Integer currentPositionNumber = 0;
    private Integer numberOfMissedMove = 0;

    private SaveUtils writeSaveUtils = null;
    private Queue<Transaction> transactionsHistory = new ArrayDeque<>();

    public Scene(ZipFile saveFile)
            throws IllegalAccessException, InstantiationException, IOException {

        SaveUtils loadSaveUtils = new SaveUtils(saveFile);

        battlefield = loadSaveUtils.getRestoredMap();
        transactionsHistory.addAll(loadSaveUtils.getRestoredTransactions());

        isReplayGame = true;
    }

    public Scene(String sceneName) {
        this.sceneName = sceneName;
        battlefield = new HashMap<>();
    }

    public String getSceneName() {
        return sceneName;
    }

    public Integer addCharacter(Character newCharacter) {
        for (Position position : battlefield.keySet()) {
            if (battlefield.get(position) == NO_CHARACTER) {
                battlefield.replace(position, newCharacter);
                return position.getPosition();
            }
        }

        Position newPosition = new Position(battlefield.size() + 1);
        battlefield.put(newPosition, newCharacter);
        return newPosition.getPosition();
    }

    public Boolean addCharacter(Character character, Integer newPositionNumber) {
        if (newPositionNumber <= 0) {
            return false;
        }

        Position newPosition = null;

        for (Position position : battlefield.keySet()) {
            if (position.getPosition().equals(newPositionNumber)) {
                newPosition = position;
            }
        }

        if (newPosition == null) {
            return false;
        }

        if (battlefield.get(newPosition) != NO_CHARACTER) {
            return false;
        }

        battlefield.replace(newPosition, character);
        return true;
    }

    public Boolean checkUsedPosition(Integer positionNumber) {
        for (Position position : battlefield.keySet()) {
            if (position.getPosition().equals(positionNumber)) {
                return true;
            }
        }
        return false;
    }

    private Map<Position, Character> getAliveCharacters() {
        Map<Position, Character> aliveCharacters = new HashMap<>();

        for (Position position : battlefield.keySet()) {
            if (battlefield.get(position) != NO_CHARACTER) {
                aliveCharacters.put(position, battlefield.get(position));
            }
        }
        return aliveCharacters;
    }

    public Integer getNumAliveCharacters() {
        return getAliveCharacters().size();
    }

    public Boolean getEndGame() {
        return isEndGame;
    }

    public void writeGameContainer(ZipOutputStream gameContainer) throws IOException {
        writeSaveUtils.putTransactionHistory(transactionsHistory);
        writeSaveUtils.writeGameContainer(gameContainer);
    }

    public void playMove() {
        if (getEndGame()) {
            System.out.printf("Server \"%s\" | Game Over!\n", sceneName);
            return;
        }

        if (checkNoPlayers()) {
            return;
        }

        if (!isReplayGame && writeSaveUtils == null) {
            writeSaveUtils = new SaveUtils(battlefield);
        }

        if (!isReplayGame) {
            Character currentCharacter = getCurrentCharacter();

            ArrayList<Transaction> transactions = new ArrayList<>(currentCharacter.act(getAliveCharacters()));
            transactionsHistory.addAll(transactions);
            Queue<Transaction> actionsQueue = new ArrayDeque<>(transactions);

            if (!checkExistChangeTransaction(actionsQueue, currentCharacter)) {
                verifyStandoff();
                return;
            }

            while (!actionsQueue.isEmpty()) {
                numberOfMissedMove = 0;

                Transaction transaction = actionsQueue.poll();

                if (transaction instanceof WeaponTransaction) {
                    Character defender = ((WeaponTransaction) transaction).getActionGetter();

                    for (Position position : battlefield.keySet()) {
                        if (battlefield.get(position) == defender) {
                            if (position.getPositionType() != null) {
                                transaction = position.getPositionType().modifyTransactions((WeaponTransaction) transaction);
                            }
                            if (position.getEffect() != null) {
                                transaction = position.getEffect().modifyTransactions((WeaponTransaction) transaction);
                            }
                            break;
                        }
                    }

                    ArrayList<Transaction> reaction = defender.react((WeaponTransaction) transaction);
                    transactionsHistory.addAll(reaction);
                    actionsQueue.addAll(reaction);
                    continue;
                }

                if (transaction instanceof InfoTransaction) {
                    if (transaction instanceof ReactionTransaction) {
                        if (!((ReactionTransaction) transaction).getExecuted()) {
                            System.out.printf(
                                    "Server \"%s\" | Error: %s ( %s ).\n",
                                    sceneName,
                                    ((ReactionTransaction) transaction).getMessage(),
                                    ((ReactionTransaction) transaction).getErrorTransaction());
                            isEndGame = true;
                            return;
                        }
                        continue;
                    }

                    System.out.printf("Server \"%s\" | %s\n",
                            sceneName,
                            ((InfoTransaction) transaction).getMessage());
                }
            }
            verifyCharactersStatus();
            verifyEndGame();
            return;
        }

        while (!transactionsHistory.isEmpty()) {
            numberOfMissedMove = 0;

            Transaction transaction = transactionsHistory.poll();

            if (transaction instanceof WeaponTransaction) {
                Character defender = ((WeaponTransaction) transaction).getActionGetter();

                for (Position position : battlefield.keySet()) {
                    if (battlefield.get(position) == defender) {
                        if (position.getPositionType() != null) {
                            transaction = position.getPositionType().modifyTransactions((WeaponTransaction) transaction);
                        }
                        if (position.getEffect() != null) {
                            transaction = position.getEffect().modifyTransactions((WeaponTransaction) transaction);
                        }
                        break;
                    }
                }

                defender.react((WeaponTransaction) transaction);
                continue;
            }

            if (transaction instanceof InfoTransaction) {
                if (transaction instanceof ReactionTransaction) {
                    if (!((ReactionTransaction) transaction).getExecuted()) {
                        System.out.printf(
                                "Server \"%s\" | Error: %s ( %s ).\n",
                                sceneName,
                                ((ReactionTransaction) transaction).getMessage(),
                                ((ReactionTransaction) transaction).getErrorTransaction());
                        isEndGame = true;
                        return;
                    }
                    continue;
                }

                System.out.printf("Server \"%s\" | %s\n",
                        sceneName,
                        ((InfoTransaction) transaction).getMessage());
            }

            verifyCharactersStatus();
            if (verifyEndGame()) {
                return;
            }
        }
    }

    private Boolean checkNoPlayers() {
        if ((battlefield.size() == 0) || (getAliveCharacters().keySet().size() == 0)) {
            System.out.printf("Server \"%s\" | No players.\n", sceneName);
            return true;
        }
        return false;
    }

    private Character getCurrentCharacter() {
        while (true) {
            currentPositionNumber = (currentPositionNumber + 1) % battlefield.keySet().size();

            for (Position position : battlefield.keySet()) {
                if (position.getPosition().equals(currentPositionNumber) &&
                        (battlefield.get(position) != NO_CHARACTER)) {
                    return battlefield.get(position);
                }
            }
        }
    }

    private Boolean checkExistChangeTransaction(Collection<Transaction> transactions,
                                                Character currentCharacter) {
        for (Transaction transaction : transactions) {
            if (transaction instanceof ChangeTransaction) {
                return true;
            }
        }

        if (currentCharacter != null) {
            System.out.printf("Server \"%s\" | Player \"%s\" missed a move.\n",
                    sceneName,
                    currentCharacter.getName());
        } else {
            System.out.printf("Server \"%s\" | Player  missed a move.\n", sceneName);
        }

        numberOfMissedMove++;
        return false;
    }

    private void verifyStandoff() {
        Map<Position, Character> aliveCharacters = getAliveCharacters();

        if (numberOfMissedMove.equals(aliveCharacters.size()) && numberOfMissedMove > 1) {
            isEndGame = true;

            System.out.printf("Server \"%s\" | Standoff!\n", sceneName);

            for (Position position : aliveCharacters.keySet()) {
                System.out.printf("Server \"%s\" | Remaining player - \"%s\"\n",
                        sceneName,
                        battlefield.get(position).getName());
            }

            System.out.printf("Server \"%s\" | Game Over!\n", sceneName);
        }
    }

    private void verifyCharactersStatus() {
        for (Position position : battlefield.keySet()) {
            if ((battlefield.get(position) != NO_CHARACTER) &&
                    (battlefield.get(position).getHitPoints().equals(0))) {

                System.out.printf("Server \"%s\" | Player \"%s\" was killed.\n",
                        sceneName,
                        battlefield.get(position).getName());
                battlefield.replace(position, NO_CHARACTER);
            }
        }
    }

    private Boolean verifyEndGame() {
        Map<Position, Character> aliveCharacters = getAliveCharacters();

        if (aliveCharacters.keySet().size() == 1) {
            isEndGame = true;

            ArrayList<Character> winner = new ArrayList<>(aliveCharacters.values());

            System.out.printf("Server \"%s\" | Player \"%s\" win!\n",
                    sceneName,
                    winner.get(0).getName());
            System.out.printf("Server \"%s\" | Game Over!\n", sceneName);
            return true;
        }
        return false;
    }
}