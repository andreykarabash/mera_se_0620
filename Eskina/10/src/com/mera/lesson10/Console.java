package com.mera.lesson10;

import java.util.Scanner;

import static java.lang.Integer.parseInt;

/**
 * This class is a console wrapper for user interaction.
 * Reads an input and prints the output to the console.
 * Stores a maze internally.
 *
 * @author Philipp Malkovsky
 * @see Maze
 */
public class Console {

    /**
     * Scanner is used for reading user input.
     */
    private Scanner scanner;

    /**
     * The current maze. It is null when the game starts.
     * {@code isMazeAvailable} indicates whether the
     * maze exists.
     */
    private Maze maze;

    public Maze getMaze() {
        return maze;
    }

    /**
     * Asks a user to enter the dimensions of the new maze
     * and then generates and prints the new one.
     */
    public void generate() {
        scanner = new Scanner(System.in);
        System.out.println("Enter the size of the new maze (in the [size] or [height width] format)");
        var line = scanner.nextLine();
        var split = line.split(" ");
        if (split.length == 1) {
            var size = parseInt(split[0]);
            maze = new Maze(size);
        } else if (split.length == 2) {
            var height = parseInt(split[0]);
            var width = parseInt(split[1]);
            maze = new Maze(height, width);
        } else {
            System.out.println("Cannot generate a maze. Invalid size");
        }
        display();
    }

    /**
     * Prints the current maze.
     */
    private void display() {
        System.out.println(maze);
    }

}
