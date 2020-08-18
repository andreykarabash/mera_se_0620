package com.mera.lesson11;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class DefaultMethods {




    interface MyList extends List<Integer> {

        default void removeEven() {
            final Iterator<Integer> iterator = iterator();
            while (iterator.hasNext()) {
                if (iterator.next() %2 == 0) {
                    iterator.remove();
                }
            }
        }
    }



    static class MyLinkedList extends LinkedList<Integer> implements MyList {

    }
    static class MyArrayList extends ArrayList<Integer> implements MyList {

    }


    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        System.out.println(list);
        list.removeEven();

        System.out.println(list);
    }





}
