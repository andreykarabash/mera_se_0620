package shopApp;

public class ItemSorters {
    static int sortByCategory(ShopItem one, ShopItem another) {
        return one.getCategory().compareTo(another.getCategory());
    }

    static int sortByTitle(ShopItem one, ShopItem another) {
        return one.getTitle().compareTo(another.getTitle());
    }

    static int sortByPrice(ShopItem one, ShopItem another) {
        return Double.compare(one.getPrice(), another.getPrice());
    }

    static int sortByQuantity(ShopItem one, ShopItem another) {
        return one.getQuantity() - another.getQuantity();
    }
}
