package src.lesson10;

import java.util.ArrayList;

public class CarNavigator {
    private Maze maze;
    private NavigatorCoordinate navigator[][];
    private ArrayList <Coordinate> route = new ArrayList<>();

    public ArrayList<Coordinate> getRoute() {
        return route;
    }

    public void setRoute(ArrayList<Coordinate> route) {
        this.route = route;
    }

    Coordinate currentCoordinate;


    public CarNavigator(Maze maze) {
        this.maze = maze;
        navigator = new NavigatorCoordinate[Parameters.parameters.MAZE_SIZE_X.getValue()][Parameters.parameters.MAZE_SIZE_Y.getValue()];

        for (int i = 0; i < maze.getMaze().length; i++){
            for (int k = 0; k < maze.getMaze()[i].length; k++){
                if (!maze.getMaze()[i][k]){
                    navigator[i][k]=new NavigatorCoordinate(i,k,false,0);
                }
                else{
                    navigator[i][k]=new NavigatorCoordinate(i,k,false,9);
                }
            }

        }
        for (int i = 1; i < maze.getMaze().length-1; i++){
            for (int k = 1; k < maze.getMaze()[i].length-1; k++){
                if (ckeckNode(navigator[i][k])){
                    navigator[i][k].setNode(true);
                }
            }
        }
        currentCoordinate = new Coordinate(maze.getStartPosition().getX(), maze.getStartPosition().getY());
        navigator[maze.getFinishPosition().getX()][maze.getFinishPosition().getY()].setFinish(true);
        route.add(new Coordinate(currentCoordinate.getX(), currentCoordinate.getY()));


    }

    public boolean ckeckNode(NavigatorCoordinate currentCoordinate){
        int noWallCoordinate = 0;
        if (navigator[currentCoordinate.getX()+1][currentCoordinate.getY()].getValue()==0){
            noWallCoordinate++;
        }
        if (navigator[currentCoordinate.getX()-1][currentCoordinate.getY()].getValue()==0){
            noWallCoordinate++;
        }
        if (navigator[currentCoordinate.getX()][currentCoordinate.getY()+1].getValue()==0){
            noWallCoordinate++;
        }
        if (navigator[currentCoordinate.getX()][currentCoordinate.getY()-1].getValue()==0){
            noWallCoordinate++;
        }
        if (noWallCoordinate >2){
            return true;
        }
        else return false;
    }

    public ArrayList <Coordinate> startNavigator(){
        boolean firstSteps = true;
        while(!navigator[currentCoordinate.getX()][currentCoordinate.getY()].isFinish()){
            if (navigator[currentCoordinate.getX()][currentCoordinate.getY()].isNode()){
                navigator[currentCoordinate.getX()][currentCoordinate.getY()].setValue(navigator[currentCoordinate.getX()][currentCoordinate.getY()].getValue()+3);
                firstSteps = false;
            }
            else{
                if(firstSteps){
                    navigator[currentCoordinate.getX()][currentCoordinate.getY()].setValue(navigator[currentCoordinate.getX()][currentCoordinate.getY()].getValue()+4);
                }
                else{
                    navigator[currentCoordinate.getX()][currentCoordinate.getY()].setValue(navigator[currentCoordinate.getX()][currentCoordinate.getY()].getValue()+2);
                }
            }
            takeMovement(navigatorSelectMovement());
            route.add(new Coordinate(currentCoordinate.getX(), currentCoordinate.getY()));
        }
        return route;
    }


    public void takeMovement(String selectedMovement) {

       switch (selectedMovement) {
           case  ("UP"):
               currentCoordinate.setX(currentCoordinate.getX()-1);
                break;
            case ("DOWN"):
                currentCoordinate.setX(currentCoordinate.getX()+1);
                break;
            case ("LEFT"):
                currentCoordinate.setY(currentCoordinate.getY()-1);
                break;
            case ("RIGHT"):
                currentCoordinate.setY(currentCoordinate.getY()+1);
                break;
        }
    }



    public String navigatorSelectMovement() {

        if (currentCoordinate.getX() == 0){
            navigator[currentCoordinate.getX()][currentCoordinate.getY()].setValue(navigator[currentCoordinate.getX()][currentCoordinate.getY()].getValue()+2);
            return "DOWN";
        }
        if (currentCoordinate.getX() == Parameters.parameters.MAZE_SIZE_X.getValue() - 1){
            navigator[currentCoordinate.getX()][currentCoordinate.getY()].setValue(navigator[currentCoordinate.getX()][currentCoordinate.getY()].getValue()+2);
            return "UP";
        }
        if (currentCoordinate.getY() == 0){
            navigator[currentCoordinate.getX()][currentCoordinate.getY()].setValue(navigator[currentCoordinate.getX()][currentCoordinate.getY()].getValue()+2);
            return "RIGHT";
        }
        if (currentCoordinate.getY() == Parameters.parameters.MAZE_SIZE_Y.getValue() - 1){
            navigator[currentCoordinate.getX()][currentCoordinate.getY()].setValue(navigator[currentCoordinate.getX()][currentCoordinate.getY()].getValue()+2);
            return "LEFT";
        }

        return chooseCoordinateIndexMin();
    }


    public String  chooseCoordinateIndexMin(){
        int CoordinateValueMin = 100;
        String CoordinateIndexMin = "";

        if (navigator[currentCoordinate.getX()][currentCoordinate.getY()+1].getValue()<CoordinateValueMin){
            CoordinateValueMin = navigator[currentCoordinate.getX()][currentCoordinate.getY()+1].getValue();
            CoordinateIndexMin = "RIGHT";
        }
        if (navigator[currentCoordinate.getX()+1][currentCoordinate.getY()].getValue()<CoordinateValueMin){
            CoordinateValueMin = navigator[currentCoordinate.getX()+1][currentCoordinate.getY()].getValue();
            CoordinateIndexMin = "DOWN";
        }
        if (navigator[currentCoordinate.getX()][currentCoordinate.getY()-1].getValue()<CoordinateValueMin){
            CoordinateValueMin = navigator[currentCoordinate.getX()][currentCoordinate.getY()-1].getValue();
            CoordinateIndexMin = "LEFT";
        }
        if (navigator[currentCoordinate.getX()-1][currentCoordinate.getY()].getValue()<CoordinateValueMin){
            CoordinateValueMin = navigator[currentCoordinate.getX()-1][currentCoordinate.getY()].getValue();
            CoordinateIndexMin = "UP";
        }
        return CoordinateIndexMin;
    }

    public void printCarNavigator(){
        {
            for (int i = 0; i < navigator.length; i++){
                for (int k = 0; k < navigator[i].length; k++){
                    System.out.print(navigator[i][k].getValue());
                }
                System.out.println("");
            }
        }
    }
}




