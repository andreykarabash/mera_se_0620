package shopApp;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class Shop {
    private List<ShopItem> goods;
    private List<Comparator<ShopItem>> sorters;


    public Shop(List<ShopItem> goods) {
        this.goods = goods;
    }

    public Shop() {
        goods = new ArrayList<>();
        for (Map.Entry<String, String> entry : ShopItem.MAP_TITLE_CATEGORY.entrySet()) {
            String title = entry.getKey();
            String category = entry.getValue();
            String id = RandomStringUtils.randomAlphanumeric(ShopItem.ID_LENGTH);
            double price = ShopItem.MAP_TITLE_PRICE.get(title);
            int quantity = ThreadLocalRandom.current().nextInt(100);
            goods.add(new ShopItem(category, price, title, quantity,id));
        }

        sorters = new ArrayList<>();
        sorters.add(ItemSorters::sortByPrice);
        sorters.add(ItemSorters::sortByTitle);
        sorters.add(ItemSorters::sortByCategory);
        sorters.add(ItemSorters::sortByQuantity);

    }

    public List<ShopItem> getGoods() {
        return goods;
    }

    public List<Comparator<ShopItem>> getSorters() {
        return sorters;
    }

    @Override
    public String toString() {
        return goods.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Shop)) return false;

        Shop shop = (Shop) o;

        return new EqualsBuilder()
                .append(goods, shop.goods)
                .append(sorters, shop.sorters)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(goods)
                .append(sorters)
                .toHashCode();
    }
}
