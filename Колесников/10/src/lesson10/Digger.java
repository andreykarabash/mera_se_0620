package src.lesson10;


import java.util.Random;

public class Digger {
    private Maze maze;
    private Coordinate currentCoordinate;
    private DiggerMovement diggerMovement;

    public Digger(Coordinate startCoordinate, Maze maze) {
        this.maze = maze;
        this.currentCoordinate = startCoordinate;
        maze.deleteWall(currentCoordinate);
        maze.addExitPositions(new Coordinate(currentCoordinate.getX(),currentCoordinate.getY()));

        maze.printExitPositions();
        diggerMovement = new DiggerMovement(maze, currentCoordinate);
    }

    public void startDigging(){

        while(maze.getExitPositions().size() != Parameters.parameters.EXITS_NUMBER.getValue()){
            diggerMovement.movement();
        }
    }

}


class DiggerMovement implements DiggerMovementType,DiggerSelectMovement{
    Maze maze;
    Coordinate currentCoordinate;

    public DiggerMovement(Maze maze, Coordinate currentCoordinatecoordinate) {
        this.maze = maze;
        this.currentCoordinate = currentCoordinatecoordinate;
    }

    public void movement(){
        int stepLenght = (new Random().nextInt(Parameters.parameters.MAX_DIGGER_STEP.getValue())+1)*2;
        String selectedMovement = selectMovement(currentCoordinate, stepLenght);
        takeMovement(maze,currentCoordinate,selectedMovement, stepLenght);

    }
}


interface DiggerSelectMovement {
    default String selectMovement(Coordinate currentCoordinate, int stepLenght) {

        String selectedMovement = "";
        while (true) {
            int course = new Random().nextInt(4);
            if (currentCoordinate.getX() == 0){
                selectedMovement = "DOWN";
                break;
            }
            if (currentCoordinate.getX() == Parameters.parameters.MAZE_SIZE_X.getValue() - 1){
                selectedMovement = "UP";
                break;
            }
            if (currentCoordinate.getY() == 0){
                selectedMovement = "RIGHT";
                break;
            }
            if (currentCoordinate.getY() == Parameters.parameters.MAZE_SIZE_Y.getValue() - 1){
                selectedMovement = "LEFT";
                break;
            }
            if ((course == 0 && currentCoordinate.getX()-stepLenght < 0) || (course == 1 && currentCoordinate.getX()+stepLenght > Parameters.parameters.MAZE_SIZE_X.getValue() - 1)
                    || (course == 2 && currentCoordinate.getY()-stepLenght < 0) || (course == 3 && currentCoordinate.getY()+stepLenght > Parameters.parameters.MAZE_SIZE_Y.getValue() - 1)) {
                continue;
            }
            if (course == 0) {
                selectedMovement = "UP";
                break;
            }
            if (course == 1) {
                selectedMovement = "DOWN";
                break;
            }
            if (course == 2) {
                selectedMovement = "LEFT";
                break;
            }
            if (course == 3) {
                selectedMovement = "RIGHT";
                break;
            }
        }
        return selectedMovement;
    }
}

interface DiggerMovementType {
    default void takeMovement(Maze maze, Coordinate currentCoordinate, String selectedMovement, int stepLeight) {

        for (int i = 0; i < stepLeight; i++){
            switch (selectedMovement) {
                case  ("UP"):
                    currentCoordinate.setX(currentCoordinate.getX()-1);
                    checkExit(maze, currentCoordinate);
                    maze.deleteWall(currentCoordinate);
                    break;
                case ("DOWN"):
                    currentCoordinate.setX(currentCoordinate.getX()+1);
                    checkExit(maze, currentCoordinate);
                    maze.deleteWall(currentCoordinate);
                    break;
                case ("LEFT"):
                    currentCoordinate.setY(currentCoordinate.getY()-1);
                    checkExit(maze, currentCoordinate);
                    maze.deleteWall(currentCoordinate);
                    break;
                case ("RIGHT"):
                    currentCoordinate.setY(currentCoordinate.getY()+1);
                    checkExit(maze, currentCoordinate);
                    maze.deleteWall(currentCoordinate);
                    break;
            }
        }
    }

    default void checkExit(Maze maze, Coordinate coordinate){
        if (((coordinate.getX() == 0)||(coordinate.getX() == Parameters.parameters.MAZE_SIZE_X.getValue() - 1)||(coordinate.getY() == 0)||(coordinate.getY() == Parameters.parameters.MAZE_SIZE_Y.getValue() - 1))&&!maze.checkExitPosition(coordinate)){
            maze.addExitPositions(new Coordinate(coordinate.getX(),coordinate.getY()));
        }
    }
}
