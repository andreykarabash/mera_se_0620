package com.mera.lesson10;

// The program is based on the maze generation and solving solution described there:
// "https://github.com/malkfilipp/maze-runner"


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Console console = new Console();
        //generate the maze
        console.generate();

        //create 3 players and run them looking for the escape from the maze
        List<Player> players = new ArrayList<>();
        players.add(new Player ("Тесей", console.getMaze()));
        players.add(new Player ("Ариадна", console.getMaze()));
        players.add(new Player ("Минотавр", console.getMaze()));

        players.forEach(player -> {
            Thread thread = new Thread(player);
            thread.start();
        });

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Игроки финишировали");players.forEach(System.out::println);
        //find the winner
        System.out.println("Победитель : \n" + Collections.max(players, Comparator.comparing(Player::getTime)));
    }
}