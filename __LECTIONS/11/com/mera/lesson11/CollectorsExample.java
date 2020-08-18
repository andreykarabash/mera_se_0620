package com.mera.lesson11;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.mera.lesson11.MethodReferenceInstanceAndConstructor.Person;

public class CollectorsExample {


    public static void main(String[] args) {


        List<Person> list = new ArrayList<>();
        list.add(new Person("",1));
        list.add(new Person("",3));
        list.add(new Person("",5));
        list.add(new Person("",7));
        list.add(new Person("", 10));


        final Double averageAge = list.stream().collect(Collectors.averagingInt(p -> p.age));
        final Double averageAgeFromStream = list.stream().mapToInt(p -> p.age).average().orElse(0);
        System.out.println(averageAge);
        System.out.println(averageAgeFromStream);


    }
}
