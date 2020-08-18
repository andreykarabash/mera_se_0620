package src.lesson10;

public class Main {
    public static void main(String[] args){
        Maze maze = new Maze();
        new Digger(new Coordinate(1,0), maze).startDigging();
        maze.printMaze();
        maze.setupStartFinishPosition();

        Car car1 = new Car(maze, "car1");
        Car car2 = new Car(maze, "car2");
        Car car3 = new Car(maze, "car3");
        Car car4 = new Car(maze, "car4");
        Car car5 = new Car(maze, "car5");

        Thread thread1 = new Thread(car1);
        Thread thread2 = new Thread(car2);
        Thread thread3 = new Thread(car3);
        Thread thread4 = new Thread(car4);
        Thread thread5 = new Thread(car5);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();

        try{
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
            thread5.join();
        }
        catch (InterruptedException ex){
            System.out.println(ex.getMessage());
        }

        System.out.println("Все автомобили прошли одинаковое количество шагов");
    }
}

