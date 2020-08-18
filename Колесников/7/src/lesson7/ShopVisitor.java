package src.lesson7;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

interface ShopVisitor {

    void visitShop(Collection <? extends  ShopItem> items);
}


class ImJustLookingVisitor implements ShopVisitor{
    @Override
    public void visitShop(Collection<? extends  ShopItem> items) {
        System.out.println("Visitor has seen items in the shop: " + items);
    }
}

class ElectronicAddictedVisitor implements ShopVisitor, Comparator <ElectronicItem>{

    @Override
    public int compare(ElectronicItem o1, ElectronicItem o2) {
        return o2.getCapacity() - (o1.getCapacity());
    }

    @Override
    public void visitShop(Collection<? extends  ShopItem> items) {
        List<ElectronicItem> currentElectronicItems = new ArrayList<>();
        for(ShopItem i: items){
            if (i instanceof ElectronicItem){
                currentElectronicItems.add((ElectronicItem) i);
            }
        }
        if (currentElectronicItems.isEmpty()){
            System.out.println("ElectronicVisitor has seen nothing electronic items in the shop.");
        }
        else{
            System.out.println("ElectronicVisitor has seen electronic items in the shop: " + currentElectronicItems);
            currentElectronicItems.sort(this::compare);
            System.out.println("ElectronicVisitor has bought " + currentElectronicItems.get(0).toString());
            items.remove(currentElectronicItems.get(0));
        }
    }
}


class RichVisitor implements ShopVisitor{

    @Override
    public void visitShop(Collection<? extends  ShopItem> items) {
        List<ShopItem> currentItems = new ArrayList<>();
        currentItems.addAll(items);
        if (currentItems.size() <= 1){
            System.out.println("RichVisitor has seen nothing interested items in the shop.");
        }
        else{
            StringBuilder bld = new StringBuilder();
            for (int i=0; i < currentItems.size(); i++){
                if (i%2 != 0){
                    bld.append(currentItems.get(i).toString());
                    items.remove(currentItems.get(i));
                }
            }
            System.out.println("RichVisitor has bought " + bld.toString());
        }
    }
}

