package src.lesson9;

import java.util.ArrayList;

public class ReplayActionProducer implements ActionProduser{
    @Override
    public void gameLogic(ArrayList<Object> steps) {
        Scene scene = new Scene();

        scene.setupLivingCharacters();

        int stepIndex = 0;
        for(Object step: steps){
            if(step instanceof Weapon){
                ((Weapon) step).setScene(scene);
            }
        }

        Creature[] creatures = scene.getBattleField();
        while(scene.getLivingCharacters() > 1){
            for (Creature i : creatures){
                if (i != null){
                    Weapon weapon = null;
                    if(steps.get(stepIndex) instanceof Weapon){
                        weapon = (Weapon) steps.get(stepIndex);
                        stepIndex++;
                    }
                    i.attack(weapon);
                }
            }
        }
        for (Creature i : creatures){
            if (i != null){
                System.out.printf("%s win!%n", i.getName());
            }
        }
    }
}
