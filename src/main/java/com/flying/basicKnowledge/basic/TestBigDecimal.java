package com.flying.basicKnowledge.basic;

import java.math.BigDecimal;

public class TestBigDecimal {
//    public static void main(String[] args) {
//        BigDecimal a = BigDecimal.valueOf(0.0333);
//        System.out.println(a);
//
//        System.out.println(a.intValue());
//        if (a.equals(BigDecimal.ZERO)){
//
//        }
//        BigDecimal b = null;
//        if (b.equals(BigDecimal.ZERO)){
//
//        }
//        int a =10;
//        long b = 10;
//        System.out.println(a==b);


    //    }
    public static void main(String[] args) {
        Integer a = new Integer(3);
        Integer b = 3;  // 将3自动装箱成Integer类型
        int c = 3;
        System.out.println(a == b); // false 两个引用没有引用同一对象
        System.out.println(a.equals(b)); // true
        System.out.println(a == c); // true a自动拆箱成int类型再和c比较
        System.out.println(b == c); // true

        Integer a1 = 128;
        Integer b1 = 128;
        System.out.println(a1 == b1); // false

        Integer a2 = 127;
        Integer b2 = 127;
        System.out.println(a2 == b2); // true
    }
}
