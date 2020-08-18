package shopApp;

import org.apache.commons.lang3.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Map;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toMap;

public class ShopItem {
    public static final int ID_LENGTH = 25;
    public static final Map<String, String> MAP_TITLE_CATEGORY;
    public static final Map<String, Double> MAP_TITLE_PRICE;
    static {
        String[][] array = {{"молоко", "хлеб", "стиральный порошок", "батарейки", "макароны",
                "тетрадь", "памперсы", "лейкопластырь", "зубная щетка", "пылесос"},
                {"бакалея", "бакалея", "бытовая химия", "электроника", "бакалея", "канцтовары", "средства гигиены", "аптека", "средства гигиены", "электроника"}};
        Double[] price = {55.0, 30.0, 330.99, 159.99, 59.0, 20.50, 999.0, 60.0, 69.0, 15599.0};
        MAP_TITLE_CATEGORY  = IntStream.range(0, array[0].length)
                .boxed()
                .collect(toMap(i -> array[0][i], i -> array[1][i]));
        MAP_TITLE_PRICE = IntStream.range(0, price.length)
                .boxed()
                .collect(toMap(i -> array[0][i], i -> price[i]));
    }

    private String category;
    private  double price;
    private String title;
    private int quantity;
    private final String id;

    public ShopItem(String category, double price, String title, int quantity, String id) {
        this.category = category;
        this.price = price;
        this.title = title;
        this.quantity = quantity;
        this.id = id;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("| %10s| %20s|%20s|%10.2f|%10d", StringUtils.abbreviate(id, 20),
                StringUtils.abbreviate(category, 20),
                StringUtils.abbreviate(title, 20),
                price,
                quantity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof ShopItem)) return false;

        ShopItem shopItem = (ShopItem) o;

        return new EqualsBuilder()
                .append(price, shopItem.price)
                .append(quantity, shopItem.quantity)
                .append(category, shopItem.category)
                .append(title, shopItem.title)
                .append(id, shopItem.id)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(category)
                .append(price)
                .append(title)
                .append(quantity)
                .append(id)
                .toHashCode();
    }
}
