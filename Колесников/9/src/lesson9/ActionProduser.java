package src.lesson9;

import java.util.ArrayList;

public interface ActionProduser {
    default void gameLogic(ArrayList<Object> steps){
        Scene scene = new Scene();

        scene.setupLivingCharacters();

        Creature[] creatures = scene.getBattleField();
        while(scene.getLivingCharacters() > 1){
            for (Creature i : creatures){
                if (i != null){
                    Weapon weapon = i.selectWeapon();
                    steps.add(weapon);
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




