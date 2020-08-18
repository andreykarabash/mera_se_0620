package src.lesson9;

import java.util.ArrayList;
import java.util.Random;

abstract class Creature {
    private String name;
    private int health;
    private ArrayList<Weapon> weapons;
    private int monsterForce;
    private Scene scene;

    Creature(String name, Scene scene) {
        this.name = name;
        this.scene = scene;
    }

    public void setName(String name){
        this.name = name;
    }
    String getName(){
        return this.name;
    }
    void setHealth(int health){
        this.health = health;
    }
    int getHealth(){
        return this.health;
    }

    void healthDamage(int damage){
        this.health -= damage;
    }

    void healthHealing(int damage){
        this.health += damage;
    }

    public void setWeapons(ArrayList<Weapon> weapons) {
        this.weapons = weapons;
    }

    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }

    Weapon selectWeapon() {
        return weapons.get(new Random().nextInt(getWeapons().size()));
    }

    public int getMonsterForce() {
        return monsterForce;
    }

    public void setMonsterForce(int monsterForce) {
        this.monsterForce = monsterForce;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    abstract void attack(Weapon weapon);
}


class Ktulhu extends Creature implements Monster {

    Ktulhu(String name, int monsterForce, Scene scene) {
        super(name, scene);
        setHealth(Parameters.HitPoints.KTULHU.getHealth());
        ArrayList<Weapon> weapons = new ArrayList<>();
        weapons.add(new MonsterMeleeAttack(scene));
        setWeapons(weapons);
        setMonsterForce(monsterForce);
    }

    @Override
    public void attack(Weapon weapon){
        weapon.attackTarget(this);
    }
}

abstract class Wizard extends Creature{
    public Wizard(String name, Scene scene) {
        super(name, scene);
    }

    @Override
    void attack(Weapon weapon) {
        System.out.printf("Wizard %s reads %s spell.%n", this.getName(), weapon.getWeaponClassName());
        weapon.attackTarget(this);
    }
}

class LightningWizard extends Wizard {

    LightningWizard(String name, Scene scene) {
        super(name, scene);
        setHealth(Parameters.HitPoints.LIGHTING_WIZARD.getHealth());
        ArrayList<Weapon> weapons = new ArrayList<>();
        weapons.add(new MagicLightning(scene));
        weapons.add(new ChainLightning(scene));
        weapons.add(new Healing(scene));
        setWeapons(weapons);
    }
}


class FireWizard extends Wizard {

    FireWizard(String name, Scene scene) {
        super(name, scene);
        setHealth(Parameters.HitPoints.FIRE_WIZARD.getHealth());
        ArrayList<Weapon> weapons = new ArrayList<>();
        weapons.add(new Firewall(scene));
        weapons.add(new BurningTouch(scene));
        weapons.add(new Healing(scene));
        setWeapons(weapons);
    }
}


class Druid extends Wizard {

    Druid(String name, Scene scene) {
        super(name, scene);
        setHealth(Parameters.HitPoints.DRUID.getHealth());
        ArrayList<Weapon> weapons = new ArrayList<>();
        weapons.add(new Migraine(scene));
        weapons.add(new Exorcism(scene));
        weapons.add(new Healing(scene));
        setWeapons(weapons);
    }
}

interface Monster {}


