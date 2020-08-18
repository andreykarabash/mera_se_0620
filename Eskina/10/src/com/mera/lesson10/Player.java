package com.mera.lesson10;

import java.util.Objects;

public class Player implements Runnable {
    String name;
    Maze maze;
    int stepNum;
    long time;

    public Player(String name, Maze maze) {
        this.name = name;
        this.maze = maze;
    }

    public String getName() {
        return name;
    }

    public long getTime() {
        return time;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Maze getMaze() {
        return maze;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    public int getStepNum() {
        return stepNum;
    }

    public void setStepNum(int stepNum) {
        this.stepNum = stepNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        Player player = (Player) o;
        return stepNum == player.stepNum &&
                Objects.equals(name, player.name) &&
                Objects.equals(maze, player.maze);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, maze, stepNum);
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", stepNum=" + stepNum +
                ", time=" + time +
                '}';
    }

    @Override
    public void run() {
        final long startTime = System.currentTimeMillis();
        System.out.println("Выход из лабиринта  ▓▓\n");
        System.out.println(maze.findEscape(this));
        time = System.currentTimeMillis() - startTime;
        System.out.println(this);
    }

}
