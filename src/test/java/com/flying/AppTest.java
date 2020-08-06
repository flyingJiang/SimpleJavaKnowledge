package com.flying;

import java.time.LocalTime;

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
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void test() {
        System.out.println(LocalTime.MIN);
        System.out.println(LocalTime.MIDNIGHT);
    }

    @Test
    public void test1() {
        Long a = new Long("10");
        Integer b = new Integer("10");
        System.out.println(a.equals(b));
        System.out.println(b.equals(a));
        System.out.println(a.intValue()==b.intValue());
    }
}
