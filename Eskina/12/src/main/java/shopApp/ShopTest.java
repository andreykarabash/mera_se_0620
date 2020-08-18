package shopApp;

import java.util.Comparator;
import java.util.concurrent.ThreadLocalRandom;

public class ShopTest {
    public static void main(String[] args) {
        Shop shop = new Shop();
        //choose comparator randomly
        Comparator<ShopItem> comparator = shop.getSorters().get(ThreadLocalRandom.current().nextInt(shop.getSorters().size()));
        //print items using random comparator
        shop.getGoods()
                .stream()
                .sorted((comparator))
        .forEach(System.out::println);
    }
}
