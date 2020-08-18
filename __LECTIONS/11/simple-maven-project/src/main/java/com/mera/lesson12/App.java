package com.mera.lesson12;

import java.math.BigInteger;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }


    public static BigInteger sum(int one, int another) {
        return BigInteger.valueOf(one).add(BigInteger.valueOf(another));
    }
}
