package com.mera.lesson9;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class Main {
    public static String YES = "да";
    public static String NO = "нет";
    public static Scanner SC = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
      startGame();
    }

    public static void startGame() throws IOException {
        String answer = "";
        String fileName = "";
        GameReplay replayToExecute = null;
        do {
            //find out if the user wants to start a new game
            answer = scanQuestionYesNo("Hачать новую игру?(да/нет)");
            replayToExecute = null;
            if (answer.toLowerCase().equals(NO)) {
                System.out.println("Укажите путь к реплею:");
                fileName = SC.next();

                if (Files.exists(Paths.get(fileName))) {
                    ObjectMapper mapper = new XmlMapper();
                    try (InputStream in = Files.newInputStream(Paths.get(fileName),StandardOpenOption.READ)) {
                        replayToExecute =  mapper.readValue(in, GameReplay.class);
                    }
                    processGameReplayAfterDeserialization(replayToExecute);
                    System.out.println("Игра загружена");
                } else {
                    System.out.println("Файл не существует, начинаю новую игру");
                }
            } else {
                System.out.println("Начинаю новую игру");
            }
            Scene scene = new Scene(replayToExecute);
            scene.start();
            if (replayToExecute == null) {
                answer = scanQuestionYesNo("Хотите сохранить реплэй?");

                if (answer.equals(YES))
                {
                    System.out.print("Введите имя файла:");
                    fileName = SC.next();

                    ObjectMapper mapper = new XmlMapper();
                    mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.JAVA_LANG_OBJECT);
                    prepareGameReplayForSerialization(scene.getRecord());
                    try (OutputStream out = Files.newOutputStream(Paths.get(fileName),StandardOpenOption.CREATE,
                            StandardOpenOption.TRUNCATE_EXISTING )) {
                        mapper.writeValue(out, scene.getRecord());
                    }
                    System.out.println("Реплэй сохранен в файл " + fileName);
                }
            }
            answer = scanQuestionYesNo("Хотите продолжить?(да/нет) ");
        } while (!answer.toLowerCase().equals(NO));
        System.out.println("Выходим из игры");
    }

    public static String scanQuestionYesNo(String question) {
        String answer;
        do {
            System.out.print(question);
            answer = SC.next();
        } while (!(answer.toLowerCase().equals(YES) || answer.toLowerCase().equals(NO)));
        return answer;
    }

    //This function fills all null characters in gameReplay to EmptyGameCharacterForSerialization
    //to avoid problems with serializing
    public static void prepareGameReplayForSerialization(GameReplay replay) {
        GameCharacter[] characters = replay.getCharacters();
        for (int i = 0; i < characters.length; i++) {
            if (characters[i] == null) {
                characters[i] = new EmptyGameCharacterForSerialization(i);
            }
        }
        System.out.println("Print characters before serialization\n" + Arrays.toString(replay.getCharacters()));
    }

    //Replace EmptyGameCharacterForSerialization with null after deserialization game replay
    public static void processGameReplayAfterDeserialization(GameReplay replay) {
        GameCharacter[] characters = replay.getCharacters();
        for (int i = 0; i < characters.length; i++) {
            if (characters[i] instanceof EmptyGameCharacterForSerialization) {
                characters[i] = null;
            }
        }
        System.out.println("Print characters after deserialization\n" + Arrays.toString(replay.getCharacters()));
    }
}

