package src.lesson9;

abstract class Weapon {

    private String weaponClassName;
    private int damage;
    private Scene scene;

    public Weapon(Scene scene) {
        this.scene = scene;
    }

    public Weapon() {
    }

    String getWeaponClassName() {
        return weaponClassName;
    }
    void setWeaponClassName(String weaponClassName) {
        this.weaponClassName = weaponClassName;
    }
    int getDamage() {
        return damage;
    }
    void setDamage(int damage) {
        this.damage = damage;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    abstract void attackTarget(Creature master);

}

@JsonTypeName("MonsterMeleeAttack")
class MonsterMeleeAttack extends Weapon {

    public MonsterMeleeAttack(Scene scene) {
        super(scene);
    }

    public MonsterMeleeAttack() {
        setWeaponClassName("MeleeAttack");
    }

    public void attackTarget(Creature master){
        setDamage(master.getMonsterForce());
        Creature[] creatures = getScene().getBattleField();
        for (int i = 0; i < creatures.length; i++){
            if (creatures[i] != master && creatures[i] != null){
                creatures[i].healthDamage(getDamage());
                System.out.printf("Monster %s attacks %s to %d HP.%n", master.getName(), creatures[i].getName(), getDamage());
                getScene().checkCharacterDeath(i);
                break;
            }
        }
    }


}

@JsonTypeName("MagicLightning")
class MagicLightning extends Weapon {
    MagicLightning(Scene scene){
        super(scene);
        setDamage(Parameters.WeaponDamage.MAGIC_LIGHTNING.getDamage());
        setWeaponClassName("MagicLightning");
    }

    public MagicLightning() {
        setDamage(Parameters.WeaponDamage.MAGIC_LIGHTNING.getDamage());
        setWeaponClassName("MagicLightning");
    }

    public void attackTarget(Creature master){
        Creature[] creatures = getScene().getBattleField();
        for (int i = 0; i < creatures.length; i++){
            if (creatures[i] != master && creatures[i] != null){
                creatures[i].healthDamage(getDamage());
                System.out.printf("MagicLightning hits %s to %d HP.%n", creatures[i].getName(), getDamage());
                getScene().checkCharacterDeath(i);
                break;
            }
        }
    }
}

@JsonTypeName("ChainLightning")
class ChainLightning extends Weapon {
    ChainLightning(Scene scene){
        super(scene);
        setDamage(Parameters.WeaponDamage.CHAIN_LIGHTING.getDamage());
        setWeaponClassName("ChainLightning");
    }

    public ChainLightning() {
        setDamage(Parameters.WeaponDamage.CHAIN_LIGHTING.getDamage());
        setWeaponClassName("ChainLightning");
    }

    public void attackTarget(Creature master){
        Creature[] creatures = getScene().getBattleField();
        for (int i = 0; i < creatures.length; i++){
            if (creatures[i] != master && creatures[i] != null){
                creatures[i].healthDamage(getDamage());
                System.out.printf("ChainLightning hits %s to %d HP.%n", creatures[i].getName(), getDamage());
                getScene().checkCharacterDeath(i);
            }
        }
    }
}

@JsonTypeName("Healing")
class Healing extends Weapon {
    Healing(Scene scene){
        super(scene);
        setDamage(Parameters.WeaponDamage.HEALING.getDamage());
        setWeaponClassName("Healing");
    }

    public Healing() {
        setDamage(Parameters.WeaponDamage.HEALING.getDamage());
        setWeaponClassName("Healing");
    }

    public void attackTarget(Creature master){
        Creature[] creatures = getScene().getBattleField();
        master.healthHealing(getDamage());
        if (master.getHealth() > Parameters.HitPoints.FIRE_WIZARD.getHealth()){
            master.setHealth(Parameters.HitPoints.FIRE_WIZARD.getHealth());
        }
        System.out.printf("%s heals himself %d HP. His current health is %s.%n", master.getName(), getDamage(), master.getHealth());
    }
}


@JsonTypeName("Firewall")
class Firewall extends Weapon {
    Firewall(Scene scene){
        super(scene);
        setDamage(Parameters.WeaponDamage.FIREWALL.getDamage());
        setWeaponClassName("Firewall");
    }

    public Firewall() {
        setDamage(Parameters.WeaponDamage.FIREWALL.getDamage());
        setWeaponClassName("Firewall");
    }

    public void attackTarget(Creature master){
        Creature[] creatures = getScene().getBattleField();
        for (int i = 0; i < creatures.length; i++){
            if (creatures[i] != master && creatures[i] != null && i%2 == 0){
                creatures[i].healthDamage(getDamage());
                System.out.printf("Firewall hits %s to %d HP.%n", creatures[i].getName(), getDamage());
                getScene().checkCharacterDeath(i);
            }
        }
    }
}

@JsonTypeName("BurningTouch")
class BurningTouch extends Weapon {
    BurningTouch(Scene scene){
        super(scene);
        setDamage(Parameters.WeaponDamage.BURNING_TOUCH.getDamage());
        setWeaponClassName("Burning Touch");
    }

    public BurningTouch() {
        setDamage(Parameters.WeaponDamage.BURNING_TOUCH.getDamage());
        setWeaponClassName("Burning Touch");
    }

    public void attackTarget(Creature master){
        Creature[] creatures = getScene().getBattleField();
        for (int i = 0; i < creatures.length; i++){
            if (creatures[i] == master){
                if (i!=0){
                    if (creatures[i-1]!= null){
                        creatures[i-1].healthDamage(getDamage());
                        System.out.printf("Burning Touch hits %s to %d HP.%n", creatures[i-1].getName(), getDamage());
                        getScene().checkCharacterDeath(i-1);
                    }
                }
                if (i!=creatures.length-1){
                    if(creatures[i+1]!= null){
                        creatures[i+1].healthDamage(getDamage());
                        System.out.printf("Burning Touch hits %s to %d HP.%n", creatures[i+1].getName(), getDamage());
                        getScene().checkCharacterDeath(i+1);
                    }
                }
                break;
            }
        }
    }
}

@JsonTypeName("Migraine")
class Migraine extends Weapon {
    Migraine(Scene scene){
        super(scene);
        setDamage(Parameters.WeaponDamage.MIGRAINE.getDamage());
        setWeaponClassName("Migraine");
    }

    public Migraine() {
        setDamage(Parameters.WeaponDamage.MIGRAINE.getDamage());
        setWeaponClassName("Migraine");
    }

    public void attackTarget(Creature master){
        Creature[] creatures = getScene().getBattleField();
        for (int i = 0; i < creatures.length; i++){
            if (creatures[i] != master && creatures[i] instanceof Wizard){
                creatures[i].healthDamage(getDamage());
                System.out.printf("Migraine hits %s to %d HP.%n", creatures[i].getName(), getDamage());
                getScene().checkCharacterDeath(i);
            }
        }
    }
}

@JsonTypeName("Exorcism")
class Exorcism extends Weapon {
    Exorcism(Scene scene){
        super(scene);
        setDamage(Parameters.WeaponDamage.EXORCISM.getDamage());
        setWeaponClassName("Exorcism");
    }

    public Exorcism() {
        setDamage(Parameters.WeaponDamage.EXORCISM.getDamage());
        setWeaponClassName("Exorcism");
    }

    public void attackTarget(Creature master){
        Creature[] creatures = getScene().getBattleField();
        for (int i = 0; i < creatures.length; i++){
            if (creatures[i] != master && creatures[i] instanceof Monster){
                creatures[i].healthDamage(getDamage());
                System.out.printf("Exorcism hits %s to %d HP.%n", creatures[i].getName(), getDamage());
                getScene().checkCharacterDeath(i);
            }
        }
    }
}