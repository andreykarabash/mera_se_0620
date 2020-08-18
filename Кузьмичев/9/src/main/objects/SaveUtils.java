/*********************************************************
 * File: SaveUtils.java
 * Purpose: Contains game save utils
 * Notice: (c) 2020 Nikolay Kuzmichev. All rights reserved.
 ********************************************************/

package main.objects;

import main.actions.weapons.Weapon;
import main.actions.weapons.material.BowShot;
import main.actions.weapons.material.MonsterStrike;
import main.actions.weapons.material.SwordStrike;
import main.actions.weapons.spells.Migraine;
import main.actions.weapons.spells.Lightning;
import main.actions.weapons.spells.Healing;
import main.actions.weapons.spells.Firewall;
import main.actions.weapons.spells.Firetouch;
import main.actions.weapons.spells.ExileMonsters;
import main.actions.weapons.spells.Chainlightning;

import main.objects.characters.Character;
import main.objects.characters.stuff.MantleStuff;
import main.objects.characters.stuff.ShieldStuff;
import main.objects.characters.stuff.Stuff;

import main.objects.players.computer.ArcherComputerPlayer;
import main.objects.players.computer.KnightComputerPlayer;
import main.objects.players.computer.MagicianComputerPlayer;
import main.objects.players.computer.MonsterComputerPlayer;

import main.objects.players.human.ArcherHumanPlayer;
import main.objects.players.human.KnightHumanPlayer;
import main.objects.players.human.MagicianHumanPlayer;

import main.objects.position.effects.Effect;
import main.objects.position.effects.HeatEffect;
import main.objects.position.effects.NightEffect;
import main.objects.position.effects.RainEffect;

import main.objects.position.types.FieldType;
import main.objects.position.types.ForestType;
import main.objects.position.types.MountainType;
import main.objects.position.types.PositionType;

import main.transactions.ChangeHPTransaction;
import main.transactions.InfoTransaction;
import main.transactions.ReactionTransaction;
import main.transactions.Transaction;

import java.io.*;
import java.lang.reflect.Field;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class SaveUtils {
    private String stringMap = null;
    private String stringPositions = null;
    private String stringCharacters = null;
    private String stringTransactions = null;

    private ArrayList<Position> restoredPositions = null;
    private ArrayList<Character> restoredCharacters = null;
    SaveUtils() {
    }

    SaveUtils(Map<Position, Character> battlefiled) {
        stringMap = getSavedMap(battlefiled);
        stringCharacters = getSavedCharacters(battlefiled);
        stringPositions = getSavedPositions(battlefiled);
    }

    SaveUtils(ZipFile gameContainer) throws IOException {
        ZipEntry positionsSaveEntry = gameContainer.getEntry("positions");
        ZipEntry charactersSaveEntry = gameContainer.getEntry("characters");
        ZipEntry mapSaveEntry = gameContainer.getEntry("map");
        ZipEntry transactionsSaveEntry = gameContainer.getEntry("transactions");

        final Integer NO_OFFSET = 0;
        byte[] buffer = new byte[1024];
        Integer bufferLength;
        ByteArrayOutputStream stringByte = new ByteArrayOutputStream();

        InputStream inputStream = gameContainer.getInputStream(positionsSaveEntry);
        while ((bufferLength = inputStream.read(buffer)) > 0) {
            stringByte.write(buffer, NO_OFFSET, bufferLength);
        }
        stringByte.close();
        stringPositions = stringByte.toString();
        stringByte.reset();

        inputStream = gameContainer.getInputStream(charactersSaveEntry);
        while ((bufferLength = inputStream.read(buffer)) > 0) {
            stringByte.write(buffer, NO_OFFSET, bufferLength);
        }
        stringByte.close();
        stringCharacters = stringByte.toString();
        stringByte.reset();

        inputStream = gameContainer.getInputStream(mapSaveEntry);
        while ((bufferLength = inputStream.read(buffer)) > 0) {
            stringByte.write(buffer, NO_OFFSET, bufferLength);
        }
        stringByte.close();
        stringMap = stringByte.toString();
        stringByte.reset();

        inputStream = gameContainer.getInputStream(transactionsSaveEntry);
        while ((bufferLength = inputStream.read(buffer)) > 0) {
            stringByte.write(buffer, NO_OFFSET, bufferLength);
        }
        stringByte.close();
        stringTransactions = stringByte.toString();
        stringByte.reset();
    }

    void writeGameContainer(ZipOutputStream gameContainer) throws IOException {
        ZipEntry positionsSaveEntry = new ZipEntry("positions");
        ZipEntry charactersSaveEntry = new ZipEntry("characters");
        ZipEntry mapSaveEntry = new ZipEntry("map");
        ZipEntry transactionsSaveEntry = new ZipEntry("transactions");

        if (stringPositions == null ||
                stringCharacters == null ||
                stringMap == null ||
                stringTransactions == null) {
            throw new IllegalStateException("No scene data in scene manager instance.");
        }

        gameContainer.putNextEntry(positionsSaveEntry);
        gameContainer.write(stringPositions.getBytes());

        gameContainer.putNextEntry(charactersSaveEntry);
        gameContainer.write(stringCharacters.getBytes());

        gameContainer.putNextEntry(mapSaveEntry);
        gameContainer.write(stringMap.getBytes());

        gameContainer.putNextEntry(transactionsSaveEntry);
        gameContainer.write(stringTransactions.getBytes());
        gameContainer.closeEntry();
    }

    Map<Position, Character> getRestoredMap()
            throws InstantiationException, IllegalAccessException {
        if (stringPositions == null ||
                stringCharacters == null ||
                stringMap == null ||
                stringTransactions == null) {
            throw new IllegalStateException("No scene data in scene manager instance.");
        }

        if (restoredCharacters == null ) {
            restoredCharacters = restoreCharacters(stringCharacters);
        }
        if (restoredPositions == null) {
            restoredPositions = restorePositions(stringPositions);
        }
        return restoreMap(stringMap, restoredPositions, restoredCharacters);
    }

    ArrayList<Transaction> getRestoredTransactions()
            throws InstantiationException, IllegalAccessException {
        if (stringPositions == null ||
                stringCharacters == null ||
                stringMap == null ||
                stringTransactions == null) {
            throw new IllegalStateException("No scene data in scene manager instance.");
        }

        if (restoredCharacters == null) {
            restoredCharacters = restoreCharacters(stringCharacters);
        }
        return restoreTransactions(stringTransactions, restoredCharacters);
    }

    void putBattlefield(Map<Position, Character> battlefield) {
        stringMap = getSavedMap(battlefield);
        stringCharacters = getSavedCharacters(battlefield);
        stringPositions = getSavedPositions(battlefield);
    }

    void putTransactionHistory(Queue<Transaction> transactions) {
        stringTransactions = getSavedTransactionsHistory(transactions);
    }

    /**
     * Battlefield Save Example
     *
     * 101:1
     * 232:2
     * 228:3
     * 1488:4
     * 1234:5
     *
     */
    private String getSavedMap(Map<Position, Character> battlefield) {

        StringBuilder savedMap = new StringBuilder();

        for (Position position : battlefield.keySet()) {

            savedMap.append(String.format("%d:%d\n",
                    position.getId(),
                    battlefield.get(position).getId()));
        }
        return savedMap.toString();
    }

    /**
     * Position Save Example
     *
     * #
     * id:1
     * position:1
     * positionType:Field
     * effect:null
     * #
     * id:2
     * position:2
     * positionType:Forest
     * effect:Rain
     * #
     *
     */
    private String getSavedPositions(Map<Position, Character> battlefield) {

        StringBuilder savedPositions = new StringBuilder();

        for (Position position : battlefield.keySet()) {

            savedPositions.append("#\n");
            savedPositions.append(String.format("id:%d\n",
                    position.getId()));
            savedPositions.append(String.format("position:%d\n",
                    position.getPosition()));
            if (position.getPositionType() != null) {
                savedPositions.append(String.format("positionType:%s\n",
                        position.getPositionType().getClass().getSimpleName()));
            }
            if (position.getEffect() != null) {
                savedPositions.append(String.format("effect:%s\n",
                        position.getEffect().getClass().getSimpleName()));
            }
        }
        return savedPositions.toString();
    }

    /**
     * Character Save Example
     *
     * #
     * type:MonsterComputerPlayer
     * id:1
     * name:monster0
     * maxHitPoints:75
     * power:42
     * weapons:MonsterStrike;
     * stuff:null
     * #
     * id:2
     * name:magician1
     * maxHitPoints:42
     * power:24
     * weapons:Chainlightning;Healing;ExilMonsters;
     * stuff:null
     * #
     *
     */
    private String getSavedCharacters(Map<Position, Character> battlefield) {

        StringBuilder savedCharacters = new StringBuilder();

        for (Character character : battlefield.values()) {
            if (character == null) {
                continue;
            }

            savedCharacters.append("#\n");
            savedCharacters.append(String.format("type:%s\n",
                    character.getClass().getSimpleName()));
            savedCharacters.append(String.format("id:%d\n",
                    character.getId()));
            savedCharacters.append(String.format("name:%s\n",
                    character.getName()));
            savedCharacters.append(String.format("maxHitPoints:%d\n",
                    character.getMaxHitPoints()));
            savedCharacters.append(String.format("hitPoints:%d\n",
                    character.getHitPoints()));
            savedCharacters.append(String.format("power:%d\n",
                    character.getPower()));
            if (character.getWeapons() != null) {
                savedCharacters.append("weapons:");

                for (Weapon weapon : character.getWeapons()) {
                    savedCharacters.append(String.format("%s;",
                            weapon.getClass().getSimpleName()));
                }

                savedCharacters.append("\n");
            }

            if (character.getStuff() != null) {
                savedCharacters.append(String.format("stuff:%s\n",
                        character.getStuff().getClass().getSimpleName()));
            }
        }
        return savedCharacters.toString();
    }

    /**
     * Transaction Save Example
     *
     * #
     * type:ChangeHPTransaction
     * transactionCreator:1
     * transactionGetter:2
     * hitPoints:42
     * weaponClass:MonsterStrike
     * #
     * type:InfoTransaction
     * message:Monster "monster0" is attacking player "magician1" on 42 hp
     * #
     *
     */
    private String getSavedTransactionsHistory(Queue<Transaction> transactionsHistory) {

        StringBuilder savedTransactionsHistory = new StringBuilder();

        for (Transaction transaction: transactionsHistory) {
            if (transaction == null) {
                continue;
            }

            savedTransactionsHistory.append("#\n");
            savedTransactionsHistory.append(String.format("type:%s\n",
                    transaction.getClass().getSimpleName()));

            if (transaction instanceof ChangeHPTransaction) {
                savedTransactionsHistory.append(String.format("transactionCreator:%s\n",
                        ((ChangeHPTransaction) transaction).getActionCreator().getId()));
                savedTransactionsHistory.append(String.format("transactionGetter:%s\n",
                        ((ChangeHPTransaction) transaction).getActionGetter().getId()));
                savedTransactionsHistory.append(String.format("weaponClass:%s\n",
                        ((ChangeHPTransaction) transaction).getWeaponClass().getSimpleName()));
                savedTransactionsHistory.append(String.format("hitPoints:%s\n",
                        ((ChangeHPTransaction) transaction).getHitPoints()));
            } else if (transaction instanceof InfoTransaction) {
                savedTransactionsHistory.append(String.format("message:%s\n",
                        ((InfoTransaction) transaction).getMessage()));

                if (transaction instanceof ReactionTransaction) {
                    savedTransactionsHistory.append(String.format("transaction:%s\n",
                            ((ReactionTransaction) transaction).getErrorTransaction()));
                    savedTransactionsHistory.append(String.format("isExecuted:%s\n",
                            ((ReactionTransaction) transaction).getExecuted()));
                }
            }
        }
        return savedTransactionsHistory.toString();
    }



    /**
     * Battlefield Save Example
     *
     * 101:1
     * 232:2
     * 228:3
     * 1488:4
     * 1234:5
     *
     */
    private Map<Position, Character> restoreMap(String saveMap, ArrayList<Position> positions,
                                                ArrayList<Character> characters) {

        Map<Position, Character> restoredBattlefield = new HashMap<>();

        final String MAIN_SEPARATOR = "\n";
        String[] records = saveMap.split(MAIN_SEPARATOR);
        for (String record : records) {
            final Integer STRING_START = 0;
            final String RECORD_SEPARATOR = ":";

            String fieldName = record.substring(STRING_START, record.indexOf(RECORD_SEPARATOR));
            String fieldValue = record.substring(
                    record.indexOf(RECORD_SEPARATOR) + RECORD_SEPARATOR.length(), record.length());

            Integer positionID = Integer.valueOf(fieldName);
            Integer characterID = Integer.valueOf(fieldValue);

            for (Position position: positions) {
                if (position == null) {
                    continue;
                }
                if (!position.getId().equals(positionID)) {
                    continue;
                }

                for (Character character: characters) {
                    if(character == null) {
                        continue;
                    }
                    if (!character.getId().equals(characterID)) {
                        continue;
                    }

                    restoredBattlefield.put(position, character);
                }
            }
        }
        return restoredBattlefield;
    }

    /**
     * Position Save Example
     *
     * #
     * id:1
     * position:1
     * positionType:Field
     * effect:null
     * #
     * id:2
     * position:2
     * positionType:Forest
     * effect:Rain
     * #
     *
     */
    private ArrayList<Position> restorePositions(String savePositions)
            throws IllegalAccessException, InstantiationException {

        final String ID_FIELD_NAME = "id";
        final String POSITION_FIELD_NAME = "position";
        final String POSITION_TYPE_FIELD_NAME = "positionType";
        final String EFFECT_FIELD_NAME = "effect";

        ArrayList<Position> restoredPositions = new ArrayList<>();

        final String MAIN_SEPARATOR = "#";
        String[] positions = savePositions.split(MAIN_SEPARATOR);

        for (String position: positions) {

            Integer idField = null;
            Integer positionField = null;
            PositionType positionTypeField = null;
            Effect effectField = null;

            final String FIELD_SEPARATOR = "\n";
            String[] records = position.split(FIELD_SEPARATOR);

            for (String record: records) {
                final Integer STRING_START = 0;
                final String NAME_VALUE_SEPARATOR = ":";

                if (!record.contains(NAME_VALUE_SEPARATOR)) {
                    continue;
                }

                String fieldName = record.substring(STRING_START, record.indexOf(NAME_VALUE_SEPARATOR));
                String fieldValue = record.substring(
                        record.indexOf(NAME_VALUE_SEPARATOR) + NAME_VALUE_SEPARATOR.length(), record.length());

                switch (fieldName) {
                    case ID_FIELD_NAME:
                        idField = Integer.valueOf(fieldValue);
                        break;
                    case POSITION_FIELD_NAME:
                        positionField = Integer.valueOf(fieldValue);
                        break;
                    case POSITION_TYPE_FIELD_NAME:
                        final String FIELD_POSITION_TYPE_NAME = FieldType.class.getSimpleName();
                        final String FOREST_POSITION_TYPE_NAME = ForestType.class.getSimpleName();
                        final String MOUNTAIN_POSITION_TYPE_NAME = MountainType.class.getSimpleName();

                        if (fieldValue.equals(FIELD_POSITION_TYPE_NAME)) {
                            positionTypeField = new FieldType();
                        } else if (fieldValue.equals(FOREST_POSITION_TYPE_NAME)) {
                            positionTypeField = new ForestType();
                        } else if (fieldValue.equals(MOUNTAIN_POSITION_TYPE_NAME)) {
                            positionTypeField = new MountainType();
                        }
                        break;
                    case EFFECT_FIELD_NAME:
                        final String HEAT_EFFECT_NAME = HeatEffect.class.getSimpleName();
                        final String NIGHT_EFFECT_NAME = NightEffect.class.getSimpleName();
                        final String RAIN_EFFECT_NAME = RainEffect.class.getSimpleName();

                        if (fieldValue.equals(HEAT_EFFECT_NAME)) {
                            effectField = new HeatEffect();
                        } else if (fieldValue.equals(NIGHT_EFFECT_NAME)) {
                            effectField = new NightEffect();
                        } else if (fieldValue.equals(RAIN_EFFECT_NAME)) {
                            effectField = new RainEffect();
                        }
                }
            }

            if (idField == null) {
                continue;
            }

            Position newPosition = Position.class.newInstance();

            for (Field field: Position.class.getDeclaredFields()) {
                field.setAccessible(true);

                if (field.getName().equals(ID_FIELD_NAME)) {
                    field.set(newPosition, idField);
                } else if (field.getName().equals(POSITION_FIELD_NAME) && positionField != null) {
                    field.set(newPosition, positionField);
                } else if (field.getName().equals(POSITION_TYPE_FIELD_NAME) && positionTypeField != null) {
                    field.set(newPosition, positionTypeField);
                } else if (field.getName().equals(EFFECT_FIELD_NAME) && effectField != null) {
                    field.set(newPosition, effectField);
                }
            }
            restoredPositions.add(newPosition);
        }
        return restoredPositions;
    }

    /**
     * Character Save Example
     *
     * #
     * type:MonsterComputerPlayer
     * id:1
     * name:monster0
     * maxHitPoints:75
     * power:42
     * weapons:MonsterStrike;
     * stuff:null
     * #
     * id:2
     * name:magician1
     * maxHitPoints:42
     * power:24
     * weapons:Chainlightning;Healing;ExilMonsters;
     * stuff:null
     * #
     *
     */
    private ArrayList<Character> restoreCharacters(String saveCharacters)
            throws IllegalAccessException, InstantiationException {

        final String CHARACTER_TYPE_FIELD_NAME = "type";
        final String ID_FIELD_NAME = "id";
        final String NAME_FIELD_NAME = "name";
        final String MAX_HP_FIELD_NAME = "maxHitPoints";
        final String HP_FIELD_NAME = "hitPoints";
        final String POWER_FIELD_NAME = "power";
        final String WEAPON_FIELD_NAME = "weapons";
        final String STUFF_FIELD_NAME = "stuff";

        ArrayList<Character> restoredCharacters = new ArrayList<>();

        final String MAIN_SEPARATOR = "#";
        String[] positions = saveCharacters.split(MAIN_SEPARATOR);

        for (String character: positions) {

            Character newCharacter = null;

            Integer idField = null;
            String nameField = null;
            Integer maxHitPointsField = null;
            Integer hitPointsField = null;
            Integer powerField = null;
            ArrayList<Weapon> weaponsField = new ArrayList<>();
            Stuff stuffField = null;

            final String FIELD_SEPARATOR = "\n";
            String[] records = character.split(FIELD_SEPARATOR);

            for (String record: records) {
                final Integer STRING_START = 0;
                final String NAME_VALUE_SEPARATOR = ":";

                if (!record.contains(NAME_VALUE_SEPARATOR)) {
                    continue;
                }

                String fieldName = record.substring(STRING_START, record.indexOf(NAME_VALUE_SEPARATOR));
                String fieldValue = record.substring(
                        record.indexOf(NAME_VALUE_SEPARATOR) + NAME_VALUE_SEPARATOR.length(), record.length());

                switch (fieldName) {
                    case CHARACTER_TYPE_FIELD_NAME:
                        final String ARCHER_COMPUTER_PLAYER_NAME = ArcherComputerPlayer.class.getSimpleName();
                        final String KNIGHT_COMPUTER_PLAYER_NAME = KnightComputerPlayer.class.getSimpleName();
                        final String MAGICIAN_COMPUTER_PLAYER_NAME = MagicianComputerPlayer.class.getSimpleName();
                        final String MONSTER_COMPUTER_PLAYER_NAME = MonsterComputerPlayer.class.getSimpleName();

                        final String ARCHER_HUMAN_PLAYER_NAME = ArcherHumanPlayer.class.getSimpleName();
                        final String KNIGHT_HUMAN_PLAYER_NAME = KnightHumanPlayer.class.getSimpleName();
                        final String MAGICIAN_HUMAN_PLAYER_NAME = MagicianHumanPlayer.class.getSimpleName();

                        if (fieldValue.equals(ARCHER_COMPUTER_PLAYER_NAME)) {
                            newCharacter = ArcherComputerPlayer.class.newInstance();
                        } else if (fieldValue.equals(KNIGHT_COMPUTER_PLAYER_NAME)) {
                            newCharacter = KnightComputerPlayer.class.newInstance();
                        } else if (fieldValue.equals(MAGICIAN_COMPUTER_PLAYER_NAME)) {
                            newCharacter = MagicianComputerPlayer.class.newInstance();
                        } else if (fieldValue.equals(MONSTER_COMPUTER_PLAYER_NAME)) {
                            newCharacter = MonsterComputerPlayer.class.newInstance();
                        } else if (fieldValue.equals(ARCHER_HUMAN_PLAYER_NAME)) {
                            newCharacter = ArcherHumanPlayer.class.newInstance();
                        } else if (fieldValue.equals(KNIGHT_HUMAN_PLAYER_NAME)) {
                            newCharacter = KnightHumanPlayer.class.newInstance();
                        } else if (fieldValue.equals(MAGICIAN_HUMAN_PLAYER_NAME)) {
                            newCharacter = MagicianHumanPlayer.class.newInstance();
                        }
                        break;
                    case ID_FIELD_NAME:
                        idField = Integer.valueOf(fieldValue);
                        break;
                    case NAME_FIELD_NAME:
                        nameField = fieldValue;
                        break;
                    case MAX_HP_FIELD_NAME:
                        maxHitPointsField = Integer.valueOf(fieldValue);
                        break;
                    case HP_FIELD_NAME:
                        hitPointsField = Integer.valueOf(fieldValue);
                        break;
                    case POWER_FIELD_NAME:
                        powerField = Integer.valueOf(fieldValue);
                        break;
                    case WEAPON_FIELD_NAME:
                        final String BOW_SHOT_NAME = BowShot.class.getSimpleName();
                        final String MONSTER_STRIKE_NAME = MonsterStrike.class.getSimpleName();
                        final String SWORD_STRIKE_NAME = SwordStrike.class.getSimpleName();

                        final String CHAINLIGHTNING_NAME = Chainlightning.class.getSimpleName();
                        final String EXILE_MONSTERS_NAME = ExileMonsters.class.getSimpleName();
                        final String FIRETOUCH_NAME = Firetouch.class.getSimpleName();
                        final String FIREWALL_NAME = Firewall.class.getSimpleName();
                        final String HEALING_NAME = Healing.class.getSimpleName();
                        final String LIGHTNING_NAME = Lightning.class.getSimpleName();
                        final String MIGRAINE_NAME = Migraine.class.getSimpleName();

                        final String WEAPON_SEPARATOR = ";";
                        String[] stringWeapons = fieldValue.split(WEAPON_SEPARATOR);

                        for (String weapon: stringWeapons) {
                            if (weapon.equals(BOW_SHOT_NAME)) {
                                weaponsField.add(new BowShot());
                            } else if (weapon.equals(MONSTER_STRIKE_NAME)) {
                                weaponsField.add(new MonsterStrike());
                            } else if (weapon.equals(SWORD_STRIKE_NAME)) {
                                weaponsField.add(new SwordStrike());
                            } else if (weapon.equals(CHAINLIGHTNING_NAME)) {
                                weaponsField.add(new Chainlightning());
                            } else if (weapon.equals(EXILE_MONSTERS_NAME)) {
                                weaponsField.add(new ExileMonsters());
                            } else if (weapon.equals(FIRETOUCH_NAME)) {
                                weaponsField.add(new Firetouch());
                            } else if (weapon.equals(FIREWALL_NAME)) {
                                weaponsField.add(new Firewall());
                            } else if (weapon.equals(HEALING_NAME)) {
                                weaponsField.add(new Healing());
                            } else if (weapon.equals(LIGHTNING_NAME)) {
                                weaponsField.add(new Lightning());
                            } else if (weapon.equals(MIGRAINE_NAME)) {
                                weaponsField.add(new Migraine());
                            }
                        }
                        break;
                    case STUFF_FIELD_NAME:
                        //TODO: Full implementation of stuff class
                        final String MANTLE_STUFF_NAME = MantleStuff.class.getSimpleName();
                        final String SHIELD_EFFECT_NAME = ShieldStuff.class.getSimpleName();

                        if (fieldValue.equals(MANTLE_STUFF_NAME)) {
                            stuffField = new MantleStuff("NONAME", 50);
                        } else  if (fieldValue.equals(SHIELD_EFFECT_NAME)) {
                            stuffField = new ShieldStuff("NONAME", 50);
                        }
                }
            }

            for (Field field: Character.class.getDeclaredFields()) {
                field.setAccessible(true);

                if (field.getName().equals(ID_FIELD_NAME) && idField != null) {
                    field.set(newCharacter, idField);
                } else if (field.getName().equals(NAME_FIELD_NAME) && nameField != null) {
                    field.set(newCharacter, nameField);
                } else if (field.getName().equals(MAX_HP_FIELD_NAME) && maxHitPointsField != null) {
                    field.set(newCharacter, maxHitPointsField);
                } else if (field.getName().equals(HP_FIELD_NAME) && hitPointsField != null) {
                    field.set(newCharacter, hitPointsField);
                } else if (field.getName().equals(POWER_FIELD_NAME) && powerField != null) {
                    field.set(newCharacter, powerField);
                } else if (field.getName().equals(WEAPON_FIELD_NAME) && weaponsField.size() != 0) {
                    field.set(newCharacter, weaponsField);
                } else if (field.getName().equals(STUFF_FIELD_NAME) && stuffField != null) {
                    field.set(newCharacter, stuffField);
                }
            }
            restoredCharacters.add(newCharacter);
        }
        return restoredCharacters;
    }

    /**
     * Transaction Save Example
     *
     * #
     * type:ChangeHPTransaction
     * transactionCreator:1
     * transactionGetter:2
     * hitPoints:42
     * weaponClass:MonsterStrike
     * #
     * type:InfoTransaction
     * message:Monster "monster0" is attacking player "magician1" on 42 hp
     * #
     *
     */
    private ArrayList<Transaction> restoreTransactions(String saveTransactions, ArrayList<Character> characters)
            throws IllegalAccessException, InstantiationException {

        ArrayList<Transaction> restoredTransactions = new ArrayList<>();

        final String MAIN_SEPARATOR = "#";
        String[] positions = saveTransactions.split(MAIN_SEPARATOR);

        for (String transaction: positions) {

            String typeField = null;

            Character transactionCreatorField = null;
            Character transactionGetterField = null;
            Integer hitPointsField = null;
            Class weaponClassField = null;

            String messageField = null;
            //TODO: Full implementation of restoring transaction field
            Transaction transactionField = null;
            Boolean isExecutedField = null;

            final String FIELD_SEPARATOR = "\n";
            String[] records = transaction.split(FIELD_SEPARATOR);

            for (String record: records) {
                final Integer STRING_START = 0;
                final String NAME_VALUE_SEPARATOR = ":";

                if (!record.contains(NAME_VALUE_SEPARATOR)) {
                    continue;
                }

                String fieldName = record.substring(STRING_START, record.indexOf(NAME_VALUE_SEPARATOR));
                String fieldValue = record.substring(
                        record.indexOf(NAME_VALUE_SEPARATOR) + NAME_VALUE_SEPARATOR.length(), record.length());

                final String TRANSACTION_TYPE_FIELD_NAME = "type";
                final String TRANSACTION_CREATOR_FIELD_NAME = "transactionCreator";
                final String TRANSACTION_GETTER_HP_FIELD_NAME = "transactionGetter";
                final String HP_FIELD_NAME = "hitPoints";
                final String WEAPON_CLASS_FIELD_NAME = "weaponClass";

                final String MESSAGE_FIELD_NAME = "message";
                final String TRANSACTION_FIELD_NAME = "transaction";
                final String IS_EXECUTED_FIELD_NAME = "isExecuted";

                switch (fieldName) {
                    case TRANSACTION_TYPE_FIELD_NAME:
                        typeField = fieldValue;
                        break;
                    case TRANSACTION_CREATOR_FIELD_NAME:
                        Integer transactionCreatorID = Integer.valueOf(fieldValue);

                        for (Character character: characters) {
                            if (character == null) {
                                continue;
                            }

                            if (character.getId().equals(transactionCreatorID)) {
                                transactionCreatorField = character;
                                break;
                            }
                        }
                        break;
                    case TRANSACTION_GETTER_HP_FIELD_NAME:
                        Integer transactionGetterID = Integer.valueOf(fieldValue);

                        for (Character character: characters) {
                            if (character == null) {
                                continue;
                            }
                            if (character.getId().equals(transactionGetterID)) {
                                transactionGetterField = character;
                                break;
                            }
                        }
                        break;
                    case HP_FIELD_NAME:
                        hitPointsField = Integer.valueOf(fieldValue);
                        break;
                    case WEAPON_CLASS_FIELD_NAME:
                        final String BOW_SHOT_NAME = BowShot.class.getSimpleName();
                        final String MONSTER_STRIKE_NAME = MonsterStrike.class.getSimpleName();
                        final String SWORD_STRIKE_NAME = SwordStrike.class.getSimpleName();

                        final String CHAINLIGHTNING_NAME = Chainlightning.class.getSimpleName();
                        final String EXILE_MONSTERS_NAME = ExileMonsters.class.getSimpleName();
                        final String FIRETOUCH_NAME = Firetouch.class.getSimpleName();
                        final String FIREWALL_NAME = Firewall.class.getSimpleName();
                        final String HEALING_NAME = Healing.class.getSimpleName();
                        final String LIGHTNING_NAME = Lightning.class.getSimpleName();
                        final String MIGRAINE_NAME = Migraine.class.getSimpleName();

                        if (fieldValue.equals(BOW_SHOT_NAME)) {
                            weaponClassField = BowShot.class;
                        } else if (fieldValue.equals(MONSTER_STRIKE_NAME)) {
                            weaponClassField = MonsterStrike.class;
                        } else if (fieldValue.equals(SWORD_STRIKE_NAME)) {
                            weaponClassField = SwordStrike.class;
                        } else if (fieldValue.equals(CHAINLIGHTNING_NAME)) {
                            weaponClassField = Chainlightning.class;
                        } else if (fieldValue.equals(EXILE_MONSTERS_NAME)) {
                            weaponClassField = ExileMonsters.class;
                        } else if (fieldValue.equals(FIRETOUCH_NAME)) {
                            weaponClassField = Firetouch.class;
                        } else if (fieldValue.equals(FIREWALL_NAME)) {
                            weaponClassField = Firewall.class;
                        } else if (fieldValue.equals(HEALING_NAME)) {
                            weaponClassField = Healing.class;
                        } else if (fieldValue.equals(LIGHTNING_NAME)) {
                            weaponClassField = Lightning.class;
                        } else if (fieldValue.equals(MIGRAINE_NAME)) {
                            weaponClassField = Migraine.class;
                        }
                        break;
                    case MESSAGE_FIELD_NAME:
                        messageField = fieldValue;
                        break;
                    case TRANSACTION_FIELD_NAME:
                        transaction = fieldValue;
                        break;
                    case IS_EXECUTED_FIELD_NAME:
                        isExecutedField = Boolean.valueOf(fieldValue);
                }
            }

            if (typeField == null) {
                continue;
            }

            Transaction newTransaction = null;

            final String CHANGE_HP_TRANSACTION_NAME = ChangeHPTransaction.class.getSimpleName();
            final String INFO_TRANSACTION_NAME = InfoTransaction.class.getSimpleName();
            final String REACTION_TRANSACTION_PLAYER_NAME = ReactionTransaction.class.getSimpleName();

            if (typeField.equals(CHANGE_HP_TRANSACTION_NAME) &&
                    transactionCreatorField != null && transactionGetterField != null &&
                    hitPointsField != null && weaponClassField != null) {
                newTransaction = new ChangeHPTransaction(transactionCreatorField, transactionGetterField, weaponClassField, hitPointsField);
            } else if (typeField.equals(INFO_TRANSACTION_NAME) &&
                    messageField != null) {
                newTransaction = new InfoTransaction(messageField);
            } else if (typeField.equals(REACTION_TRANSACTION_PLAYER_NAME) &&
                    messageField != null && isExecutedField != null) {
                newTransaction = new ReactionTransaction(messageField, transactionField, isExecutedField);
            }
            restoredTransactions.add(newTransaction);
        }
        return restoredTransactions;
    }
}