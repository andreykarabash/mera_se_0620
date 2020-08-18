package src.lesson7;

public abstract class ShopItem {
    private String name;
    private int price;

    public ShopItem(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}


abstract class ElectronicItem extends  ShopItem{
    private int capacity;

    public ElectronicItem(String name, int price, int capacity) {
        super(name, price);
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}


abstract class FoodItem extends ShopItem{
    private int calorificValue;
    private int shelfLife;

    public FoodItem(String name, int price, int calorificValue, int shelfLife) {
        super(name, price);
        this.calorificValue = calorificValue;
        this.shelfLife = shelfLife;
    }

    public int getCalorificValue() {
        return calorificValue;
    }

    public void setCalorificValue(int calorificValue) {
        this.calorificValue = calorificValue;
    }

    public int getShelfLife() {
        return shelfLife;
    }

    public void setShelfLife(int shelfLife) {
        this.shelfLife = shelfLife;
    }
}


class TV extends ElectronicItem{
    private int volume;

    public TV(String name, int price, int capacity, int volume) {
        super(name, price, capacity);
        this.volume = volume;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "TV{" +
                "name='" + getName() + '\'' +
                ", price=" + getPrice() +
                '}';
    }
}


class Refrigerator extends ElectronicItem{
    private int freezerVolume;

    public Refrigerator(String name, int price, int capacity, int freezerVolume) {
        super(name, price, capacity);
        this.freezerVolume = freezerVolume;
    }

    public int getFreezerVolume() {
        return freezerVolume;
    }

    public void setFreezerVolume(int freezerVolume) {
        this.freezerVolume = freezerVolume;
    }

    @Override
    public String toString() {
        return "Refrigerator{" +
                "name='" + getName() + '\'' +
                ", price=" + getPrice() +
                '}';
    }
}


class Apple extends FoodItem{
    private String color;

    public Apple(String name, int price, int calorificValue, int shelfLife, String color) {
        super(name, price, calorificValue, shelfLife);
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "name='" + getName() + '\'' +
                ", price=" + getPrice() +
                '}';
    }
}


class Bread extends FoodItem{
    private int weight;

    public Bread(String name, int price, int calorificValue, int shelfLife, int weight) {
        super(name, price, calorificValue, shelfLife);
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Bread{" +
                "name='" + getName() + '\'' +
                ", price=" + getPrice() +
                '}';
    }
}