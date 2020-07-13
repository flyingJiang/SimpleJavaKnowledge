package com.flying.unit;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class UnitTest {

    private static String name;

    @BeforeClass
    public static void beforeClass(){
        name = "BeforeClass";
        System.out.println("BeforeClass:" + name);
    }

    @Before
    public void before(){
        name = "Before";
        System.out.println("Before:" + name);
    }

    @After
    public void after(){
        name = "After";
        System.out.println("After:" +name);
    }

    @Test
    public void test1() {
        System.out.println("Test1:" +name);
        name = "test1";
        System.out.println("Test1:" +name);
    }

    @Test
    public void test2() {
        System.out.println("Test2:" +name);
        name = "test2";
        System.out.println("Test2:" +name);
    }


}
