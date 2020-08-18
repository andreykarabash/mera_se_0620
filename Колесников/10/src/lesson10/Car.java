package src.lesson10;

import java.util.ArrayList;

public class Car implements Runnable {
    private String name;
    private CarNavigator carNavigator;
    private ArrayList<Coordinate> steps = new ArrayList<>();
    private Maze maze;

    public Car(Maze maze, String name) {
        this.name = name;
        this.maze = maze;
        this.carNavigator = new CarNavigator(maze);
    }

    @Override
    public void run() {
        steps.addAll(carNavigator.startNavigator());
        long startTime = System.currentTimeMillis();
        for(Coordinate i: steps){
            if(step(i)){
                long finishTime = System.currentTimeMillis() - startTime;
                System.out.println(name + ": steps number - " + steps.size()+ " : time - " + finishTime);
            }
        }
    }

    public boolean step(Coordinate step){
        return step.equals(maze.getFinishPosition());
    }


}
