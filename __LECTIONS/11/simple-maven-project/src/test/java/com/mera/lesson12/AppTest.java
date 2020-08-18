package com.mera.lesson12;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void testArithmetic()
    {
        assertTrue("Арифметика не работает",App.sum(2,2).intValue() == 4);
    }

    @Test
    public void testArithmeticOverflow()
    {
        assertTrue("Арифметика не работает",App.sum(Integer.MAX_VALUE,42).signum() > 0);
    }
}
