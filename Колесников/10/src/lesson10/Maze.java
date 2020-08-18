package src.lesson10;

import java.util.ArrayList;
import java.util.Random;

public class Maze {
    private Boolean[][] maze;
    private final ArrayList<Coordinate> exitPositions = new ArrayList<>();
    private Coordinate startPosition;
    private Coordinate finishPosition;

    public Maze() {
        this.maze = new Boolean[Parameters.parameters.MAZE_SIZE_X.getValue()][Parameters.parameters.MAZE_SIZE_Y.getValue()];

        for (int i = 0; i < Parameters.parameters.MAZE_SIZE_X.getValue(); i++){
            for (int k = 0; k < Parameters.parameters.MAZE_SIZE_Y.getValue(); k++){
                maze[i][k] = true;
            }
        }
    }



    public void deleteWall(Coordinate coordinate){
        maze[coordinate.getX()][coordinate.getY()] = false;
    }

    public void printMaze(){
        for (int i = 0; i < maze.length; i++){
            for (int k = 0; k < maze[i].length; k++){
                if (maze[i][k])
                System.out.print("* ");
                else{
                    System.out.print("  ");
                }
            }
            System.out.println("");
        }
    }

    public void printExitPositions(){
            System.out.println(exitPositions);
    }

    public void addExitPositions(Coordinate coordinate){
        exitPositions.add(coordinate);
    }

    public boolean checkExitPosition(Coordinate coordinate){
        return exitPositions.contains(coordinate);
    }

    public ArrayList<Coordinate> getExitPositions() {
        return exitPositions;
    }

    public boolean checkWall(Coordinate coordinate){
        return maze[coordinate.getX()][coordinate.getY()];
    }

    public void setupStartFinishPosition(){
        Random random = new Random();
        int startPositionIndex = random.nextInt(getExitPositions().size());
        startPosition = exitPositions.remove(startPositionIndex);
        int finishPositionIndex = random.nextInt(getExitPositions().size());
        finishPosition = exitPositions.remove(finishPositionIndex);
    }

    public Coordinate getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(Coordinate startPosition) {
        this.startPosition = startPosition;
    }

    public Coordinate getFinishPosition() {
        return finishPosition;
    }

    public void setFinishPosition(Coordinate finishPosition) {
        this.finishPosition = finishPosition;
    }

    public Boolean[][] getMaze() {
        return maze;
    }
}
