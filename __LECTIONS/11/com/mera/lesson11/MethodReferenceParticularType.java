package com.mera.lesson11;

import java.util.Arrays;

public class MethodReferenceParticularType {


    public static void main(String[] args) {
        String[] stringArray = { "Barbara", "James", "Mary", "John",
                "Patricia", "Robert", "Michael", "Linda" };

        // int compare(String s1, String s2);
        Arrays.sort(stringArray, String::compareToIgnoreCase);
        Arrays.sort(stringArray, (s, str) -> s.compareToIgnoreCase(str));

        System.out.println(Arrays.toString(stringArray));
    }



}
