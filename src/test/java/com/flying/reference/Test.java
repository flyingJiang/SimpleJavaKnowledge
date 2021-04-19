package com.flying.reference;

/**
 * @program: SimpleJavaKnowledge
 * @description:
 * @author: jiangjianfei
 * @create: 2021-03-31 10:28
 **/
public class Test {
    @org.junit.Test
    public void case1(){
        int a = 8;
        change(a);
        System.out.println(a); // 8

        int x=5;
        change1(x);
        System.out.println(x); // 5

        Integer b = new Integer(1);
        change2(b);
        System.out.println(b); // 1

    }

    private void change2(Integer b) {
        b++;
    }

    private void change1(int x) {
        x=9;
    }

    private void change(int a) {
        a++;
    }
}
