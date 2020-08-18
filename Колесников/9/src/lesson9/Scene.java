package src.lesson9;

final class Scene {
    private Creature[] battleField = new Creature[9];
    private int livingCharacters = 0;

    public Scene() {
        setCreatureOnPosition(new Ktulhu("Ktulhu",3, this), 3);
        setCreatureOnPosition(new LightningWizard("Lightning", this), 1);
        setCreatureOnPosition(new FireWizard("Fire", this), 8);
        setCreatureOnPosition(new Druid("Druid", this), 5);
    }

    Creature[] getBattleField() {
        return battleField;
    }

    void setCreatureOnPosition(Creature creature, int battleFieldIndex) {
        battleField[battleFieldIndex] = creature;
    }

    int getLivingCharacters() {
        return livingCharacters;
    }

    void setupLivingCharacters() {

        for (Creature i : battleField){
            if (i != null){
                livingCharacters++;
            }
        }
    }

    void checkCharacterDeath(int targetCharacterNumber){
        if (getBattleField()[targetCharacterNumber].getHealth()<1){
            System.out.printf("%s died.%n", getBattleField()[targetCharacterNumber].getName());
            getBattleField()[targetCharacterNumber] = null;
            reduceLivingCharacters();
        }
    }

    void reduceLivingCharacters() {
        livingCharacters--;
    }
}



