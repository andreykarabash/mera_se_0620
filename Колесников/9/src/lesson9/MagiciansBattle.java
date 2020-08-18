package src.lesson9;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class MagiciansBattle {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException{
        ArrayList<Object> steps = new ArrayList<>();
        while(true){
            Scanner in = new Scanner(System.in);
            System.out.print("Are you going to start new game or replay?: yes/no\n");
            String startOrReplay = in.nextLine();
            if(startOrReplay.equals("yes")){
                new RandomGameActionProducer().gameLogic(steps);
                System.out.print("Do you want to save the current game result?: yes/no\n");
                String saveGame = in.nextLine();
                if(saveGame.equals("yes")){
                    String json = Serializer.serialize(steps);
                    System.out.print("Specify the path to the save file\n");
                    String filePath = in.nextLine();
                    try (FileWriter writer = new FileWriter(filePath, false)){
                        writer.write(json);
                    }
                    catch (IOException ex){
                        System.out.println(ex.getMessage());
                    }
                }

                break;
            }
            if (startOrReplay.equals("no")){
                System.out.print("Specify the path to the save file\n");
                String filePath = in.nextLine();
                try (FileReader reader = new FileReader(filePath)){
                    Scanner scan = new Scanner(reader);
                    String json = "";
                    while (scan.hasNextLine()) {
                        json = json + scan.nextLine();
                        ArrayList<Object> deserialisationResalt = Deserializer.deserialize(json);
                        new ReplayActionProducer().gameLogic(deserialisationResalt);
                    }
                }
                catch (IOException ex){
                    System.out.println(ex.getMessage());
                }
                break;
            }
            else{
                System.out.println("sfsdf");
            }
        }
    }
}